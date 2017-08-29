package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.parser.RuleLexer;
import at.tugraz.ist.compiler.parser.RuleParser;
import at.tugraz.ist.compiler.rule.Rule;
import at.tugraz.ist.compiler.ruleGenerator.RuleGenerator;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class PlanFinderTests {

    @Test
    public void simple_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("-> #goal action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);

        List<Rule> plan = PlanFinder.getPlanForRule(goals.get(0), memory, rules);
        assertNotNull(plan);
        assertEquals(plan.size(), 1);
        assertEquals(plan.get(0), rules.get(0));
    }

    @Test
    public void with_1precondition_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "pre1 -> #goal action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);

        List<Rule> plan = PlanFinder.getPlanForRule(goals.get(0), memory, rules);
        assertNotNull(plan);
        assertEquals(plan.size(), 1);
    }

    @Test
    public void with_2steps_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "pre1 -> +pre2 action." +
                "pre2 -> #goal action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);

        List<Rule> plan = PlanFinder.getPlanForRule(goals.get(0), memory, rules);
        assertNotNull(plan);
        assertEquals(plan.size(), 2);
    }

    @Test
    public void with_2preconditions_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "pre1 -> +pre2 action." +
                "pre1 -> +pre3 action." +
                "pre2, pre3 -> #goal action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);

        List<Rule> plan = PlanFinder.getPlanForRule(goals.get(0), memory, rules);
        assertNotNull(plan);
        assertEquals(plan.size(), 3);
    }

    @Test
    public void rules_out_of_order_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "pre2 -> +pre4 action." +
                "pre1 -> +pre2 action." +
                "pre1 -> +pre3 action." +
                "pre3, pre4 -> #goal action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);

        List<Rule> plan = PlanFinder.getPlanForRule(goals.get(0), memory, rules);
        assertNotNull(plan);
        assertEquals(plan.size(), 4);
    }

    @Test
    public void dead_end_test() throws IOException {
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

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);

        List<Rule> plan = PlanFinder.getPlanForRule(goals.get(0), memory, rules);
        assertNotNull(plan);
        assertEquals(plan.size(), 3);
    }

    @Test
    public void withDelitions_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "pre1 -> +pre2 action." +
                "pre1 -> +pre2 -pre1 action." +
                "pre1, pre2 -> #goal action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);

        List<Rule> plan = PlanFinder.getPlanForRule(goals.get(0), memory, rules);
        assertNotNull(plan);
        assertEquals(plan.size(), 2);
    }

    @Test
    public void not_fulfillable_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "fail -> +pre2 -pre1 action." +
                "fail -> +pre2 action." +
                "pre1, pre2 -> #goal action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);

        List<Rule> plan = PlanFinder.getPlanForRule(goals.get(0), memory, rules);
        assertNull(plan);
    }

    @Test
    public void delete_but_later_would_add_test() throws IOException {
        // This is also not possible in the original algorithm, because it is not possible to delete predicates that you
        // need later on, even thought you could add them again.
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "pre1 -> +pre2 -pre1 action." +
                "pre2 -> +pre1 action." +
                "pre1, pre2 -> #goal action." +
                "");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);

        List<Rule> plan = PlanFinder.getPlanForRule(goals.get(0), memory, rules);
        assertNull(plan);
    }
}
