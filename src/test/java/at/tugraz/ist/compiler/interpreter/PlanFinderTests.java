package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.Setting;
import at.tugraz.ist.compiler.parser.RuleLexer;
import at.tugraz.ist.compiler.parser.RuleParser;
import at.tugraz.ist.compiler.rule.Rule;
import at.tugraz.ist.compiler.ruleGenerator.RuleGenerator;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        Setting setting = new Setting("src/test/resources/Actions", "", true, 0);
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree(), setting);

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);

        List<Rule> plan = PlanFinder.getPlanForRule(goals.get(0), memory, rules);
        assertNotNull(plan);
        assertEquals(1, plan.size());
        assertEquals(rules.get(0), plan.get(0));
    }

    @Test
    public void with_1precondition_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "pre1 -> #goal action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        Setting setting = new Setting("src/test/resources/Actions", "", true, 0);
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree(), setting);

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);

        List<Rule> plan = PlanFinder.getPlanForRule(goals.get(0), memory, rules);
        assertNotNull(plan);
        assertEquals(1, plan.size());
        assertEquals(rules.get(0), plan.get(0));
    }

    @Test
    public void with_2steps_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "pre1 -> +pre2 action." +
                "pre2 -> #goal action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        Setting setting = new Setting("src/test/resources/Actions", "", true, 0);
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree(), setting);

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);

        List<Rule> plan = PlanFinder.getPlanForRule(goals.get(0), memory, rules);
        assertNotNull(plan);
        assertEquals(2, plan.size());
        assertEquals(rules.get(0), plan.get(0));
        assertEquals(rules.get(1), plan.get(1));
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
        Setting setting = new Setting("src/test/resources/Actions", "", true, 0);
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree(), setting);

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);

        List<Rule> plan = PlanFinder.getPlanForRule(goals.get(0), memory, rules);
        assertNotNull(plan);
        assertEquals(3, plan.size());
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
        Setting setting = new Setting("src/test/resources/Actions", "", true, 0);
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree(), setting);

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);

        List<Rule> plan = PlanFinder.getPlanForRule(goals.get(0), memory, rules);
        assertNotNull(plan);
        assertEquals(4, plan.size());
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
        Setting setting = new Setting("src/test/resources/Actions", "", true, 0);
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree(), setting);

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);

        List<Rule> plan = PlanFinder.getPlanForRule(goals.get(0), memory, rules);
        assertNotNull(plan);
        assertEquals(3, plan.size());
    }

    @Test
    public void withDeletions_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "pre1 -> +pre2 action." +
                "pre1 -> +pre2 -pre1 action." +
                "pre1, pre2 -> #goal action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        Setting setting = new Setting("src/test/resources/Actions", "", true, 0);
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree(), setting);

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);

        List<Rule> plan = PlanFinder.getPlanForRule(goals.get(0), memory, rules);
        assertNotNull(plan);
        assertEquals(2, plan.size());
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
        Setting setting = new Setting("src/test/resources/Actions", "", true, 0);
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree(), setting);

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
        Setting setting = new Setting("src/test/resources/Actions", "", true, 0);
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree(), setting);

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);

        List<Rule> plan = PlanFinder.getPlanForRule(goals.get(0), memory, rules);
        assertNull(plan);
    }

    @Test
    public void find_rule_with_greater_weight_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer(
                "-> +pre1 action (0.5)." +
                        "-> +pre1 action (1)." +
                        "pre1-> #goal action." +
                        "");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
                Setting setting = new Setting("src/test/resources/Actions", "", true, 0);
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree(), setting);

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);

        List<Rule> plan = PlanFinder.getPlanForRule(goals.get(0), memory, rules);
        assertNotNull(plan);
        assertEquals(2, plan.size());
        assertEquals(rules.get(1), plan.get(0));
        assertEquals(rules.get(2), plan.get(1));
    }

    @Test
    public void example1_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer(Paths.get("src/test/resources/compiler/parser/input/pass/example1.rule"));
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());

        Setting setting = new Setting("src/test/resources/Actions", "", true, 0);
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree(), setting);

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);

        List<Rule> plan = PlanFinder.getPlanForRule(goals.get(0), memory, rules);
        assertNotNull(plan);
        assertEquals(2, plan.size());
    }

    @Test
    public void example2_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer(Paths.get("src/test/resources/compiler/parser/input/pass/example2.rule"));
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        Setting setting = new Setting("src/test/resources/Actions", "", true, 0);
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree(), setting);

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);

        List<Rule> plan = PlanFinder.getPlanForRule(goals.get(0), memory, rules);
        assertNotNull(plan);
        assertEquals(5, plan.size());
    }
}
