package at.tugraz.ist.compiler;

import at.tugraz.ist.compiler.interpreter.ExecutionFailedException;
import at.tugraz.ist.compiler.interpreter.Executor;
import at.tugraz.ist.compiler.interpreter.Model;
import at.tugraz.ist.compiler.parser.RuleLexer;
import at.tugraz.ist.compiler.parser.RuleParser;
import at.tugraz.ist.compiler.ruleGenerator.RuleGenerator;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.nio.file.Paths;

class RuleBasedCompiler {
    public static void main(String[] args) {
        Setting setting = generateSetting(args);
        if (setting == null) return;

        try {
            RuleLexer ruleLexer = new RuleLexer(Paths.get(setting.getPathToRuleFile()));
            RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
            RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree(), setting);

            Model model = new Model(gen.getMemory(), gen.getRules());

            Executor executor = new Executor();
            executor.executeNTimes(model, setting.getNumberOfRuns());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionFailedException e) {
            e.printStackTrace();
        }
    }

    private static Setting generateSetting(String[] args) {
        String javaFiles;
        String ruleFile;
        boolean compile;
        int numberOfRuns = 1;

        if (args.length == 0) {
            printHelp();
            return null;
        }

        if (args[0].equals("interprete")) {
            JavaCompiler c = ToolProvider.getSystemJavaCompiler();
            if (c == null) {
                System.out.println("Error: if you want to interprete the rules, please make sure to run this program with the JDK rather than the JRE ");
                System.exit(1);
            }

            compile = false;
            if (args.length == 5 && args[1].equals("-n")) {
                try {
                    numberOfRuns = Integer.parseInt(args[2]);
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
            throw new NotImplementedException();
        } else {
            printHelp();
            return null;
        }

        Setting setting = new Setting(javaFiles, ruleFile, compile, numberOfRuns);
        return setting;
    }

    private static void printHelp() {
        System.out.println("Usage:\n" +
                "   rule interprete [-n n] PATHTOJAVAFILES PATHTORULEFILE           interprets the rules n times\n" +
                "   rule compile [-o outputpath] PATHTOJAVAFILES PATHTORULEFILE     compiles th rules to Java source\n" +
                "   rule (-h | --help)                                              shows this help");
    }
}