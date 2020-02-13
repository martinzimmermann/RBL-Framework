package at.tugraz.ist.compiler;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import at.tugraz.ist.compiler.compiler.SourceWriter;
import at.tugraz.ist.compiler.interpreter.NoPlanFoundException;
import at.tugraz.ist.compiler.parser.RuleLexer;
import at.tugraz.ist.compiler.parser.RuleParser;
import at.tugraz.ist.compiler.ruleGenerator.RuleGenerator;
import fr.uga.pddl4j.parser.Message;
import fr.uga.pddl4j.parser.Parser;

class RuleBasedCompiler {
    public static void main(String[] args) throws NoPlanFoundException {
        Setting setting = generateSetting(args);
        if (setting == null) return;


        try {
            Parser parser = new Parser();
            parser.parse(setting.getPathToDomainFile(), setting.getPathToProblemFile());
            if(!parser.getErrorManager().isEmpty()) {
                for (Message msg : parser.getErrorManager().getMessages()) {
                    System.out.println(msg.toString());
                }
            }
            
            RuleGenerator gen = new RuleGenerator(parser.getDomain(), parser.getProblem());
            SourceWriter writer = new SourceWriter(setting.getOutputPath(), setting.getPackageName());
            writer.writeSource(gen, setting.isDeferred(),true);
        } catch (IOException ex) {
            throw new IllegalStateException("Could not read PDDL file for parsing", ex);
        }
    }

    private static RuleParser parsFile(String pathToFile) throws IOException {
        RuleLexer ruleLexer = new RuleLexer(Paths.get(pathToFile));
        if (ruleLexer.getErrorCount() != 0) {
            ErrorHandler.Instance().printErrorCount();
            System.exit(1);
        }

        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        if (ruleParser.getErrorCount() != 0) {
            ErrorHandler.Instance().printErrorCount();
            System.exit(1);
        }
        return ruleParser;
    }

    private static Setting generateSetting(String[] args) {
        String domainFile;
        String problemFile;
        String outputPath = ".\\";
        String packageName = null;
        boolean deferred = false;

        if (args.length == 0) {
            printHelp();
            return null;
        }

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

        if (args.length > (currentIndex + 1)) {
            domainFile = args[currentIndex];
            problemFile = args[currentIndex+1];
        } else {
            printHelp();
            return null;
        }

        return new Setting(domainFile, problemFile, outputPath, packageName, deferred);
    }

    private static void checkPackageName(String packageName) {
        if (!packageName.equals(packageName.toLowerCase()))
            ErrorHandler.Instance().reportWarning(ErrorHandler.Type.Input, "Java package name should be in lower case letters.");
    }

    private static void printHelp() {
        System.out.println("Usage:\n" +
                "   rule compile [-o OUTPUTPATH] [-p PACKAGENAME] [-d] PATHTODOMAINFILE PATHTOPROBLEMFILE compiles th rules to Java source\n" +
                "   rule (-h | --help)                                                        shows this help");
    }
}