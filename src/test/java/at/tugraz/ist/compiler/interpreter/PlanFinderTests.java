package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.parser.RuleLexer;
import at.tugraz.ist.compiler.parser.RuleParser;
import at.tugraz.ist.compiler.rule.Rule;
import at.tugraz.ist.compiler.ruleGenerator.RuleGenerator;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.*;

public class PlanFinderTests {

    private String generateRandomRules(int numberOfRules, int numberOfGoals, int maxNumberOfConditions, int maxNumberOfPreconditions) {
        String template = "%s -> %s %s Actions.action.\n";
        StringBuilder builder = new StringBuilder();
        Random rnd = new Random();

        for (int i = 0; i < numberOfRules; i++) {
            int numberOfPreConditions = Math.abs(rnd.nextInt()) % maxNumberOfPreconditions;
            StringBuilder preconditions = new StringBuilder();
            for (int j = 0; j < numberOfPreConditions; j++) {
                preconditions.append("pre" + Math.abs(rnd.nextInt()) % maxNumberOfConditions + ", ");
            }
            if (preconditions.length() != 0) {
                preconditions.deleteCharAt(preconditions.length() - 1);
                preconditions.deleteCharAt(preconditions.length() - 1);
            }

            int numberOfDeletions = Math.abs(rnd.nextInt()) % 1;
            StringBuilder deletions = new StringBuilder();
            for (int j = 0; j < numberOfDeletions; j++) {
                deletions.append("-pre" + Math.abs(rnd.nextInt()) % maxNumberOfConditions + " ");
            }

            builder.append(String.format(template, preconditions.toString(), "+pre" + Math.abs(rnd.nextInt()) % maxNumberOfConditions, deletions.toString()));
        }

        int numberOfPreConditions = Math.abs(rnd.nextInt()) % 4;
        StringBuilder preconditions = new StringBuilder();
        for (int j = 0; j < numberOfPreConditions; j++) {
            preconditions.append("pre" + Math.abs(rnd.nextInt()) % maxNumberOfConditions + ", ");
        }
        if (preconditions.length() != 0) {
            preconditions.deleteCharAt(preconditions.length() - 1);
            preconditions.deleteCharAt(preconditions.length() - 1);
        }

        for (int i = 0; i < numberOfGoals; i++) {
            builder.append(String.format(template, preconditions.toString(), "#pre" + (Math.abs(rnd.nextInt() % maxNumberOfConditions)), ""));
        }


        return builder.toString();
    }


    @Test
    public void simple_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("-> #goal Actions.action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = BestPlanFinder.getGoalRules(rules);

        List<Rule> plan = new TopDownPlanFinder().getAnyPlan(memory, rules);
        assertNotNull(plan);
        assertEquals(1, plan.size());
        assertEquals(rules.get(0), plan.get(0));
    }

    @Test
    public void with_1precondition_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "pre1 -> #goal Actions.action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = BestPlanFinder.getGoalRules(rules);

        List<Rule> plan = new TopDownPlanFinder().getAnyPlan(memory, rules);
        assertNotNull(plan);
        assertEquals(1, plan.size());
        assertEquals(rules.get(0), plan.get(0));
    }

    @Test
    public void with_2steps_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "pre1 -> +pre2 Actions.action." +
                "pre2 -> #goal Actions.action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = BestPlanFinder.getGoalRules(rules);

        List<Rule> plan = new TopDownPlanFinder().getAnyPlan(memory, rules);
        assertNotNull(plan);
        assertEquals(2, plan.size());
        assertEquals(rules.get(0), plan.get(0));
        assertEquals(rules.get(1), plan.get(1));
    }

    @Test
    public void with_2preconditions_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "pre1 -> +pre2 Actions.action." +
                "pre1 -> +pre3 Actions.action." +
                "pre2, pre3 -> #goal Actions.action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = BestPlanFinder.getGoalRules(rules);

        List<Rule> plan = new TopDownPlanFinder().getAnyPlan(memory, rules);
        assertNotNull(plan);
        assertEquals(3, plan.size());
    }

    @Test
    public void rules_out_of_order_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "pre2 -> +pre4 Actions.action." +
                "pre1 -> +pre2 Actions.action." +
                "pre1 -> +pre3 Actions.action." +
                "pre3, pre4 -> #goal Actions.action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = BestPlanFinder.getGoalRules(rules);

        List<Rule> plan = new TopDownPlanFinder().getAnyPlan(memory, rules);
        assertNotNull(plan);
        assertEquals(4, plan.size());
    }

    @Test
    public void dead_end_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("pre7." +
                "pre2 -> +pre1 Actions.action." +
                "pre4 -> +pre2 Actions.action." +
                "pre7 -> +pre3 Actions.action." +
                "pre6 -> +pre3 Actions.action." +
                "pre3 -> +pre1 Actions.action." +
                "pre1 -> #goal Actions.action." +
                "pre5 -> +pre2 Actions.action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = BestPlanFinder.getGoalRules(rules);

        List<Rule> plan = new TopDownPlanFinder().getAnyPlan(memory, rules);
        assertNotNull(plan);
        assertEquals(3, plan.size());
    }

    @Test
    public void withDeletions_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "pre1 -> +pre2 Actions.action." +
                "pre1 -> +pre2 -pre1 Actions.action." +
                "pre1, pre2 -> #goal Actions.action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = BestPlanFinder.getGoalRules(rules);

        List<Rule> plan = new TopDownPlanFinder().getAnyPlan(memory, rules);
        assertNotNull(plan);
        assertEquals(2, plan.size());
    }

    @Test
    public void not_fulfillable_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "fail -> +pre2 -pre1 Actions.action." +
                "fail -> +pre2 Actions.action." +
                "pre1, pre2 -> #goal Actions.action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();

        List<Rule> plan = new TopDownPlanFinder().getAnyPlan(memory, rules);
        assertNull(plan);
    }

    @Test
    public void delete_but_later_would_add_test() throws IOException {
        // This is also not possible in the original algorithm, because it is not possible to delete predicates that you
        // need later on, even thought you could add them again.
        RuleLexer ruleLexer = new RuleLexer("pre1." +
                "pre1 -> +pre2 -pre1 Actions.action." +
                "pre2 -> +pre1 Actions.action." +
                "pre1, pre2 -> #goal Actions.action." +
                "");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = BestPlanFinder.getGoalRules(rules);

        List<Rule> plan = new TopDownPlanFinder().getAnyPlan(memory, rules);
        assertNotNull(plan);
    }

    @Test
    public void goal_directly_reachable_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer(" -> +pre0  Actions.action.\n" +
                " -> +pre0 -pre0 -pre0  Actions.action.\n" +
                " -> +pre0 -pre0  Actions.action.\n" +
                " -> #pre0  Actions.action.\n");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = BestPlanFinder.getGoalRules(rules);

        List<Rule> plan = new TopDownPlanFinder().getAnyPlan(memory, rules);
        assertNotNull(plan);
        assertEquals(1, plan.size());
    }

    @Test
    public void find_rule_with_greater_weight_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer(
                "-> +pre1 Actions.action (0.5)." +
                        "-> +pre1 Actions.action (1)." +
                        "pre1-> #goal Actions.action." +
                        "");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = BestPlanFinder.getGoalRules(rules);

        List<Rule> plan = new TopDownPlanFinder().getAnyPlan(memory, rules);
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

        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = BestPlanFinder.getGoalRules(rules);

        List<Rule> plan = new BestPlanFinder().getAnyPlan(memory, rules);
        assertNotNull(plan);
        assertEquals(2, plan.size());
    }

    @Test
    public void example2_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer(Paths.get("src/test/resources/compiler/parser/input/pass/example2.rule"));
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = BestPlanFinder.getGoalRules(rules);

        List<Rule> plan = new TopDownPlanFinder().getAnyPlan(memory, rules);
        assertNotNull(plan);
        assertEquals(5, plan.size());
    }

    @Test
    public void large_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer(Paths.get("src/test/resources/compiler/interpreter.input/large.rule"));
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = BestPlanFinder.getGoalRules(rules);

        List<Rule> plan = new TopDownPlanFinder().getAnyPlan(memory, rules);
        assertNotNull(plan);
    }

    @Test
    @Ignore
    public void quick_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer(Paths.get("src/test/resources/test_valid0_9_2_4_4.rule"));
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = BestPlanFinder.getGoalRules(rules);

        List<Rule> topPlan = new TopDownPlanFinder().getAnyPlan(memory, rules);
        List<Rule> bottomPlan = new BottomUpPlanFinder().getAnyPlan(memory, rules);
        List<Rule> bestPlan = new BestPlanFinder().getAnyPlan(memory, rules);
        Assert.assertTrue(true);
    }

    @Test
    @Ignore
    public void simple_quick_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("pre4 -> +pre2  Actions.action.\n" +
                " -> +pre1  Actions.action.\n" +
                "pre2 -> +pre0  Actions.action.\n" +
                " -> +pre4  Actions.action.\n" +
                " -> +pre4  Actions.action.\n" +
                "pre0 -> +pre2  Actions.action.\n" +
                "pre2 -> +pre3  Actions.action.\n" +
                "pre4, pre3, pre1 -> #pre1  Actions.action.\n" +
                "pre4, pre3, pre1 -> #pre3  Actions.action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();
        List<Rule> goals = BestPlanFinder.getGoalRules(rules);

        List<Rule> plan = new TopDownPlanFinder().getAnyPlan(memory, rules);
        assertNotNull(plan);
        assertEquals(1, plan.size());
        assertEquals(rules.get(1), plan.get(0));
    }

    @Test
    public void random_test() throws IOException {
        for (int i = 0; i < 100; i++) {
            String rulesString = generateRandomRules(7, 2, 5, 2);
            RuleLexer ruleLexer = new RuleLexer(rulesString);
            assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
            RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
            assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
            RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

            Memory memory = gen.getMemory();
            List<Rule> rules = gen.getRules();

            List<Rule> topDownPlan = new TopDownPlanFinder().getAnyPlan(memory, rules);
            List<Rule> bottomPlan = new BottomUpPlanFinder().getAnyPlan(memory, rules);
            List<Rule> bestPlan = new BestPlanFinder().getAnyPlan(memory, rules);
            assertTrue(topDownPlan == null ? bestPlan == null : true);
            assertTrue(bestPlan == null ? topDownPlan == null : true);
            assertTrue((bestPlan != null && topDownPlan != null) ? (bestPlan.size() >= topDownPlan.size()) : true);
            assertTrue((bestPlan != null && topDownPlan != null) ? (new Plan(bestPlan).getWeight().compareTo(new Plan(topDownPlan).getWeight())) >= 0 : true);
            //assertTrue(bottomUpPlan != null ? bestPlan != null : true);
        }
    }
}
