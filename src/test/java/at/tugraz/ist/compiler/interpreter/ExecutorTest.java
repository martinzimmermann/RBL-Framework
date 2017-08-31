package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.parser.RuleLexer;
import at.tugraz.ist.compiler.parser.RuleParser;
import at.tugraz.ist.compiler.rule.Rule;
import at.tugraz.ist.compiler.ruleGenerator.RuleGenerator;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class ExecutorTest {

    @Test
    public void simple_test() throws IOException, ExecutionFailedException {
        RuleLexer ruleLexer = new RuleLexer("-> #goal action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
        Model model = new Model(gen.getMemory(), gen.getRules());

        Executor executor = new Executor();
        executor.executeOnce(model);
    }


    @Test
    public void with_1precondition_test() throws IOException, ExecutionFailedException {
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "pre1 -> #goal action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
        Model model = new Model(gen.getMemory(), gen.getRules());

        Executor executor = new Executor();
        executor.executeOnce(model);
    }

    @Test
    public void with_2steps_test() throws IOException, ExecutionFailedException {
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "pre1 -> +pre2 action." +
                "pre2 -> #goal action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Model model = new Model(gen.getMemory(), gen.getRules());

        Executor executor = new Executor();
        executor.executeOnce(model);
    }

    @Test
    public void with_2preconditions_test() throws IOException, ExecutionFailedException {
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "pre1 -> +pre2 action." +
                "pre1 -> +pre3 action." +
                "pre2, pre3 -> #goal action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
        Model model = new Model(gen.getMemory(), gen.getRules());

        Executor executor = new Executor();
        executor.executeOnce(model);
    }

    @Test
    public void rules_out_of_order_test() throws IOException, ExecutionFailedException {
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "pre2 -> +pre4 action." +
                "pre1 -> +pre2 action." +
                "pre1 -> +pre3 action." +
                "pre3, pre4 -> #goal action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
        Model model = new Model(gen.getMemory(), gen.getRules());

        Executor executor = new Executor();
        executor.executeOnce(model);
    }

    @Test
    public void dead_end_test() throws IOException, ExecutionFailedException {
        RuleLexer ruleLexer = new RuleLexer("pre7." +
                "pre2 -> +pre1 action." +
                "pre4 -> +pre2 action." +
                "pre7 -> +pre3 action." +
                "pre6 -> +pre3 action." +
                "pre3 -> +pre1 action." +
                "pre1 -> #goal action." +
                "pre5 -> +pre2 action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
        Model model = new Model(gen.getMemory(), gen.getRules());

        Executor executor = new Executor();
        executor.executeOnce(model);
    }

    @Test
    public void withDeletions_test() throws IOException, ExecutionFailedException {
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "pre1 -> +pre2 action." +
                "pre1 -> +pre2 -pre1 action." +
                "pre1, pre2 -> #goal action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
        Model model = new Model(gen.getMemory(), gen.getRules());

        Executor executor = new Executor();
        executor.executeOnce(model);
    }

    @Test
    public void find_rule_with_greater_weight_test() throws IOException, ExecutionFailedException {
        RuleLexer ruleLexer = new RuleLexer(
                "-> +pre1 action (0.5)." +
                        "-> +pre1 action (1)." +
                        "pre1-> #goal action." +
                        "");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
        Model model = new Model(gen.getMemory(), gen.getRules());

        Executor executor = new Executor();
        executor.executeOnce(model);
    }

    @Test
    public void find_rule_with_greater_weight2_test() throws IOException, ExecutionFailedException {
        RuleLexer ruleLexer = new RuleLexer(
                        "-> +pre1 action (1)." +
                        "-> +pre1 action (0.5)." +
                        "pre1-> #goal action." +
                        "");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
        Model model = new Model(gen.getMemory(), gen.getRules());

        Executor executor = new Executor();
        executor.executeOnce(model);
    }

    @Test
    public void lower_rule_should_be_used_after_n_executions_test() throws IOException, ExecutionFailedException {
        // This is also not possible in the original algorithm, because it is not possible to delete predicates that you
        // need later on, even thought you could add them again.
        RuleLexer ruleLexer = new RuleLexer(
                "-> +pre1 action (0.5)." +
                        "-> +pre1 action (0.6)." +
                        "pre1-> #goal -pre1 action." +
                        "");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
        Model model = new Model(gen.getMemory(), gen.getRules());

        Executor executor = new Executor();
        executor.executeNTimes(model, 2);
    }


    @Test
    public void example1_test() throws IOException, ExecutionFailedException {
        RuleLexer ruleLexer = new RuleLexer(Paths.get("src/test/resources/compiler/parser/input/pass/example1.rule"));
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Model model = new Model(gen.getMemory(), gen.getRules());

        Executor executor = new Executor();
        executor.executeOnce(model);
    }

    @Test
    public void example2_test() throws IOException, ExecutionFailedException {
        RuleLexer ruleLexer = new RuleLexer(Paths.get("src/test/resources/compiler/parser/input/pass/example2.rule"));
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Model model = new Model(gen.getMemory(), gen.getRules());

        Executor executor = new Executor();
        executor.executeOnce(model);
    }
}
