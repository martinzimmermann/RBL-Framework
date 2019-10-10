package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.parser.RuleLexer;
import at.tugraz.ist.compiler.parser.RuleParser;
import at.tugraz.ist.compiler.ruleGenerator.RuleGenerator;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;

public class ExecutorTest {

    @Test
    public void simple_test() throws IOException, NoPlanFoundException {
        RuleLexer ruleLexer = new RuleLexer("-> #goal Actions.action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        Model model = new Model(gen.getMemory(), gen.getRules());

        LibExecutor executor = new LibExecutor(model, new BottomUpPlanFinder());
        executor.executeOnce();
    }


    @Test
    public void with_1precondition_test() throws IOException, NoPlanFoundException {
        RuleLexer ruleLexer = new RuleLexer("pre1.\n" +
                "pre1 -> #goal Actions.action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
        Model model = new Model(gen.getMemory(), gen.getRules());

        LibExecutor executor = new LibExecutor(model, new BottomUpPlanFinder());
        executor.executeNTimes(100);
    }

    @Test
    public void with_2steps_test() throws IOException, NoPlanFoundException {
        RuleLexer ruleLexer = new RuleLexer("pre1.\n" +
                "pre1 -> +pre2 Actions.action.\n" +
                "pre2 -> #goal Actions.action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Model model = new Model(gen.getMemory(), gen.getRules());

        LibExecutor executor = new LibExecutor(model, new BottomUpPlanFinder());
        executor.executeOnce();
    }

    @Test
    public void with_2preconditions_test() throws IOException, NoPlanFoundException {
        RuleLexer ruleLexer = new RuleLexer("pre1.\n" +
                "pre1 -> +pre2 Actions.action.\n" +
                "pre1 -> +pre3 Actions.action.\n" +
                "pre2, pre3 -> #goal Actions.action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        Model model = new Model(gen.getMemory(), gen.getRules());

        LibExecutor executor = new LibExecutor(model, new BottomUpPlanFinder());
        executor.executeOnce();
    }

    @Test
    public void rules_out_of_order_test() throws IOException, NoPlanFoundException {
        RuleLexer ruleLexer = new RuleLexer("pre1.\n" +
                "pre2 -> +pre4 Actions.action.\n" +
                "pre1 -> +pre2 Actions.action.\n" +
                "pre1 -> +pre3 Actions.action.\n" +
                "pre3, pre4 -> #goal Actions.action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        Model model = new Model(gen.getMemory(), gen.getRules());

        LibExecutor executor = new LibExecutor(model, new BottomUpPlanFinder());
        executor.executeOnce();
    }

    @Test
    public void dead_end_test() throws IOException, NoPlanFoundException {
        RuleLexer ruleLexer = new RuleLexer("pre7.\n" +
                "pre2 -> +pre1 Actions.action.\n" +
                "pre4 -> +pre2 Actions.action.\n" +
                "pre7 -> +pre3 Actions.action.\n" +
                "pre6 -> +pre3 Actions.action.\n" +
                "pre3 -> +pre1 Actions.action.\n" +
                "pre1 -> #goal Actions.action.\n" +
                "pre5 -> +pre2 Actions.action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        Model model = new Model(gen.getMemory(), gen.getRules());

         LibExecutor executor = new LibExecutor(model, new BottomUpPlanFinder());
        executor.executeOnce();
    }

    @Test
    public void withDeletions_test() throws IOException, NoPlanFoundException {
        RuleLexer ruleLexer = new RuleLexer("pre1.\n" +
                "pre1 -> +pre2 Actions.action.\n" +
                "pre1 -> +pre2 -pre1 Actions.action.\n" +
                "pre1, pre2 -> #goal Actions.action.\n");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        Model model = new Model(gen.getMemory(), gen.getRules());

         LibExecutor executor = new LibExecutor(model, new BottomUpPlanFinder());
        executor.executeOnce();
    }

    @Test
    public void find_rule_with_greater_weight_test() throws IOException, NoPlanFoundException {
        RuleLexer ruleLexer = new RuleLexer(
                "-> +pre1 Actions.action.\n" +
                        "-> +pre1 Actions.action.\n" +
                        "pre1-> #goal Actions.action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        Model model = new Model(gen.getMemory(), gen.getRules());

         LibExecutor executor = new LibExecutor(model, new BottomUpPlanFinder());
        executor.executeOnce();
    }

    @Test
    public void find_rule_with_greater_weight2_test() throws IOException, NoPlanFoundException {
        RuleLexer ruleLexer = new RuleLexer(
                "-> +pre1 Actions.action.\n" +
                        "-> +pre1 Actions.action.\n" +
                        "pre1-> #goal Actions.action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        Model model = new Model(gen.getMemory(), gen.getRules());

         LibExecutor executor = new LibExecutor(model, new BottomUpPlanFinder());
        executor.executeOnce();
    }

    @Test
    public void lower_rule_should_be_used_after_n_executions_test() throws IOException, NoPlanFoundException {
        // This is also not possible in the original algorithm, because it is not possible to delete predicates that you
        // need later on, even thought you could add them again.
        RuleLexer ruleLexer = new RuleLexer(
                "-> +pre1 Actions.action.\n" +
                        "-> +pre1 Actions.action.\n" +
                        "pre1-> #goal -pre1 Actions.action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        Model model = new Model(gen.getMemory(), gen.getRules());

         LibExecutor executor = new LibExecutor(model, new BottomUpPlanFinder());
        executor.executeNTimes(2);
    }


    @Test
    public void example1_test() throws IOException, NoPlanFoundException {
        RuleLexer ruleLexer = new RuleLexer(Paths.get("src/test/resources/compiler/parser/input/pass/example1.rule"));
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        Model model = new Model(gen.getMemory(), gen.getRules());

         LibExecutor executor = new LibExecutor(model, new BottomUpPlanFinder());
        executor.executeOnce();
    }

    @Test
    public void example2_test() throws IOException, NoPlanFoundException {
        RuleLexer ruleLexer = new RuleLexer(Paths.get("src/test/resources/compiler/parser/input/pass/example2.rule"));
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        Model model = new Model(gen.getMemory(), gen.getRules());

         LibExecutor executor = new LibExecutor(model, new BottomUpPlanFinder());
        executor.executeOnce();
    }
}
