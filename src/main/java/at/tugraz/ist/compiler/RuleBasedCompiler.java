package at.tugraz.ist.compiler;

import at.tugraz.ist.compiler.interpreter.ExecutionFailedException;
import at.tugraz.ist.compiler.interpreter.Executor;
import at.tugraz.ist.compiler.interpreter.Model;
import at.tugraz.ist.compiler.parser.RuleLexer;
import at.tugraz.ist.compiler.parser.RuleParser;
import at.tugraz.ist.compiler.ruleGenerator.RuleGenerator;

import java.io.IOException;
import java.nio.file.Paths;

class RuleBasedCompiler {
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }

        String javaFiles = args[0];
        String ruleFile = args[1];
        int numberOfRuns = 1;

        if(args.length == 3)
            numberOfRuns = Integer.parseInt(args[2]);


        Setting setting = new Setting(javaFiles, ruleFile, true);
        try {
            RuleLexer ruleLexer = new RuleLexer(Paths.get(setting.getPathToRuleFile()));
            RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
            RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree(), setting);

            Model model = new Model(gen.getMemory(), gen.getRules());

            Executor executor = new Executor();
            executor.executeNTimes(model, numberOfRuns);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionFailedException e) {
            e.printStackTrace();
        }
    }
}