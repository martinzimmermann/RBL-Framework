package at.tugraz.ist.compiler;

import at.tugraz.ist.compiler.compiler.SourceWriter;
import at.tugraz.ist.compiler.interpreter.*;
import at.tugraz.ist.compiler.parser.RuleLexer;
import at.tugraz.ist.compiler.parser.RuleParser;
import at.tugraz.ist.compiler.ruleGenerator.RuleGenerator;

import javax.tools.ToolProvider;
import java.io.IOException;
import java.nio.file.Paths;

class RuleBasedCompiler {
    public static void main(String[] args) throws NoPlanFoundException {
        Setting setting = generateSetting(args);
        if (setting == null) return;

        try {
            if (!setting.isCompiling())
                ClassCompiler.compileClasses(setting.getPathToJavaFiles());

            RuleLexer ruleLexer = new RuleLexer(Paths.get(setting.getPathToRuleFile()));
            if (ruleLexer.getErrorCount() != 0) {
                ErrorHandler.Instance().printErrorCount();
                System.exit(1);
            }

            RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
            if (ruleParser.getErrorCount() != 0) {
                ErrorHandler.Instance().printErrorCount();
                System.exit(1);
            }
            RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
            if (ErrorHandler.Instance().hasErrors()) {
                ErrorHandler.Instance().printErrorCount();
                System.exit(1);
            }

            if (setting.isCompiling()) {
                SourceWriter writer = new SourceWriter(setting.getOutputPath(), setting.getPackageName());
                writer.writeSource(gen, setting.isDeferred(), setting.isLibraryUsed());

            } else {
                Model model = new Model(gen.getMemory(), gen.getRules());
                if (ErrorHandler.Instance().hasErrors()) {
                    ErrorHandler.Instance().printErrorCount();
                    System.exit(1);
                }
                Executor executor = new Executor(new BottomUpPlanFinder());
                executor.executeNTimes(model, setting.getNumberOfRuns());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Setting generateSetting(String[] args) {
        String javaFiles = null;
        String ruleFile;
        String outputPath = ".\\";
        String packageName = null;
        boolean compile;
        int numberOfRuns = 1;
        boolean deferred = false;
        boolean lib = false;

        if (args.length == 0) {
            printHelp();
            return null;
        }

        switch (args[0]) {
            case "interpret":
                javax.tools.JavaCompiler c = ToolProvider.getSystemJavaCompiler();
                if (c == null) {
                    System.out.println("Error: if you want to interpret the rules, please make sure to run this program with the JDK rather than the JRE ");
                    System.exit(1);
                }

                compile = false;
                if (args.length == 5 && args[1].equals("-times")) {
                    try {
                        numberOfRuns = Integer.parseInt(args[2]);
                        if (numberOfRuns < 0) {
                            printHelp();
                            return null;
                        }
                    } catch (NumberFormatException e) {
                        printHelp();
                        return null;
                    }
                    javaFiles = args[3];
                    ruleFile = args[4];
                } else if (args.length == 3) {
                    javaFiles = args[1];
                    ruleFile = args[2];
                } else {
                    printHelp();
                    return null;
                }

                break;
            case "compile":
                compile = true;
                int currentIndex = 1;

                if (args.length > (currentIndex + 1) && args[currentIndex].equals("-o")) {
                    currentIndex++;
                    outputPath = args[currentIndex++];
                }

                if (args.length > (currentIndex + 1) && args[currentIndex].equals("-p")) {
                    currentIndex++;
                    packageName = args[currentIndex++];
                    checkPackageName(packageName);
                }

                if (args.length > (currentIndex) && args[currentIndex].equals("-d")) {
                    currentIndex++;
                    deferred = true;
                }

                if (args.length > (currentIndex) && args[currentIndex].equals("-lib")) {
                    currentIndex++;
                    lib = true;
                }

                if (args.length > (currentIndex)) {
                    ruleFile = args[currentIndex];
                } else {
                    printHelp();
                    return null;
                }
                break;
            default:
                printHelp();
                return null;
        }

        return new Setting(javaFiles, ruleFile, compile, numberOfRuns, outputPath, packageName, deferred, lib);
    }

    private static void checkPackageName(String packageName) {
        if (!packageName.equals(packageName.toLowerCase()))
            ErrorHandler.Instance().reportWarning(ErrorHandler.Type.Input, "Java package name should be in lower case letters.");
    }

    private static void printHelp() {
        System.out.println("Usage:\n" +
                "   rule interpret [-times n] PATHTOJAVAFILES PATHTORULEFILE                  interprets the rules n times, where n must be >= 0\n" +
                "   rule compile [-o OUTPUTPATH] [-p PACKAGENAME] [-d] [-lib} PATHTORULEFILE  compiles th rules to Java source\n" +
                "   rule (-h | --help)                                                        shows this help");
    }
}