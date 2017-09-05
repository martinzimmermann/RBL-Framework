package at.tugraz.ist.compiler;

import at.tugraz.ist.compiler.compiler.SourceWriter;
import at.tugraz.ist.compiler.interpreter.ClassCompiler;
import at.tugraz.ist.compiler.interpreter.Executor;
import at.tugraz.ist.compiler.interpreter.Model;
import at.tugraz.ist.compiler.parser.RuleLexer;
import at.tugraz.ist.compiler.parser.RuleParser;
import at.tugraz.ist.compiler.rule.ActionFailedException;
import at.tugraz.ist.compiler.ruleGenerator.RuleGenerator;

import javax.tools.ToolProvider;
import java.io.IOException;
import java.nio.file.Paths;

class RuleBasedCompiler {
    public static void main(String[] args) {
        Setting setting = generateSetting(args);
        if (setting == null) return;

        try {
            if (!setting.isCompiling())
                ClassCompiler.compileClasses(setting);

            RuleLexer ruleLexer = new RuleLexer(Paths.get(setting.getPathToRuleFile()));
            RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
            RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree(), setting);

            Model model = new Model(gen.getMemory(), gen.getRules());

            if (setting.isCompiling()) {
                SourceWriter writer = new SourceWriter(setting.getOutputPath(), setting.getPackageName());
                writer.writeSource(model);

            } else {
                Executor executor = new Executor();
                executor.executeNTimes(model, setting.getNumberOfRuns());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ActionFailedException e) {
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

        if (args.length == 0) {
            printHelp();
            return null;
        }

        if (args[0].equals("interpret")) {
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

        } else if (args[0].equals("compile")) {
            compile = true;
            if (args.length == 2) {
                ruleFile = args[1];
            } else if (args.length == 4 && args[1].equals("-o")) {
                outputPath = args[2];
                ruleFile = args[3];
            } else if (args.length == 4 && args[1].equals("-p")) {
                packageName = args[2];
                checkPackageName(packageName);
                ruleFile = args[3];
            } else if (args.length == 6 && args[1].equals("-o") && args[3].equals("-p")) {
                outputPath = args[2];
                packageName = args[4];
                checkPackageName(packageName);
                ruleFile = args[5];
            } else {
                printHelp();
                return null;
            }
        } else {
            printHelp();
            return null;
        }

        Setting setting = new Setting(javaFiles, ruleFile, compile, numberOfRuns, outputPath, packageName);
        return setting;
    }

    private static void checkPackageName(String packageName) {
        if(!packageName.equals(packageName.toLowerCase()))
            ErrorHandler.Instance().reportWarning(ErrorHandler.Type.Input, 0, 0, "Java package name should be in lower case letters.");
    }

    private static void printHelp() {
        System.out.println("Usage:\n" +
                "   rule interpret [-times n] PATHTOJAVAFILES PATHTORULEFILE           interprets the rules n times, where n must be >= 0\n" +
                "   rule compile [-o OUTPUTPATH] [-p PACKAGENAME] PATHTORULEFILE       compiles th rules to Java source\n" +
                "   rule (-h | --help)                                                 shows this help");
    }
}