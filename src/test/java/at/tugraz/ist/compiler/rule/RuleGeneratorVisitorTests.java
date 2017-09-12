package at.tugraz.ist.compiler.rule;

import at.tugraz.ist.compiler.ErrorHandler;
import at.tugraz.ist.compiler.interpreter.ClassCompiler;
import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.parser.RuleLexer;
import at.tugraz.ist.compiler.parser.RuleParser;
import at.tugraz.ist.compiler.ruleGenerator.RuleGenerator;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.*;

public class RuleGeneratorVisitorTests {


    @Test
    public void aList1_pass_test() throws IOException {
        ErrorHandler.Instance().reset();
        RuleLexer ruleLexer = new RuleLexer("pre -> action (0 <= a <= 1: 1).");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        assertFalse(ErrorHandler.Instance().hasErrors());

    }

    @Test
    public void aList2_pass_test() throws IOException {
        ErrorHandler.Instance().reset();
        RuleLexer ruleLexer = new RuleLexer("pre -> action (0 <= a <= 0.5: 1, 0.5 < a <= 1: 1).");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        assertFalse(ErrorHandler.Instance().hasErrors());

    }

    @Test
    public void aList3_pass_test() throws IOException {
        ErrorHandler.Instance().reset();
        RuleLexer ruleLexer = new RuleLexer("pre -> action (0 <= a < 0.5: 1, 0.5 <= a <= 1: 1).");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        assertFalse(ErrorHandler.Instance().hasErrors());

    }

    @Test
    public void aList4_pass_test() throws IOException {
        ErrorHandler.Instance().reset();
        RuleLexer ruleLexer = new RuleLexer("pre -> action (0.2 < a < 0.8: 1, 1).");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        assertFalse(ErrorHandler.Instance().hasErrors());

    }

    @Test
    public void aList4_fail_test() throws IOException {
        ErrorHandler.Instance().reset();
        RuleLexer ruleLexer = new RuleLexer("pre -> action (0 <= a <= 0.5: 1, 0.5 <= a <= 1: 1, 1).");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        assertTrue(ErrorHandler.Instance().hasErrors());

    }

    @Test
    public void aList5_fail_test() throws IOException {
        ErrorHandler.Instance().reset();
        RuleLexer ruleLexer = new RuleLexer("pre -> action (0 <= a <= 0.5: 1).");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        assertTrue(ErrorHandler.Instance().hasErrors());

    }

    @Test
    public void aList6_fail_test() throws IOException {
        ErrorHandler.Instance().reset();
        RuleLexer ruleLexer = new RuleLexer("pre -> action (0 <= a <= 0.5: 1, 0.4 <= a <= 1: 1).");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        assertTrue(ErrorHandler.Instance().hasErrors());

    }

    @Test
    public void aList7_fail_test() throws IOException {
        ErrorHandler.Instance().reset();
        RuleLexer ruleLexer = new RuleLexer("pre -> action (0 < a <= 1: 1).");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        assertTrue(ErrorHandler.Instance().hasErrors());

    }

    @Test
    public void aList8_fail_test() throws IOException {
        ErrorHandler.Instance().reset();
        RuleLexer ruleLexer = new RuleLexer("pre -> action (0 <= a < 1: 1).");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        assertTrue(ErrorHandler.Instance().hasErrors());

    }

    @Test
    public void aList9_fail_test() throws IOException {
        ErrorHandler.Instance().reset();
        RuleLexer ruleLexer = new RuleLexer("pre -> action (0 <= a < 0.5: 1, 0.5 < a <= 1: 1).");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        assertTrue(ErrorHandler.Instance().hasErrors());

    }

    @Test
    public void addition_rule_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("pre1. " +
                "pre2." +
                "pre1, pre2 -> +post -pre1 -pre2 Actions.action 0.2.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        Assert.assertNotNull(memory);
        Assert.assertTrue(memory.contains(new Predicate("pre1")));
        Assert.assertTrue(memory.contains(new Predicate("pre2")));

        List<Rule> rules = gen.getRules();
        Assert.assertNotNull(rules);
        assertEquals(1, rules.size());
        Rule rule = rules.get(0);

        assertEquals(rule.getAction(), "Actions.action");
        assertEquals(rule.getRuleGoal(), 0.2);
        assertEquals(rule.getWorldAddition(), new Predicate("post"));
        assertEquals(rule.getGoal(), null);
        assertEquals(rule.hasGoal(), false);
        assertEquals(rule.hasWorldAddition(), true);

        assertNotNull(rule.getPreconditions());
        assertEquals(rule.getPreconditions().size(), 2);
        assertEquals(rule.getPreconditions().get(0), new Predicate("pre1"));
        assertEquals(rule.getPreconditions().get(1), new Predicate("pre2"));

        assertNotNull(rule.getWorldDeletions());
        assertEquals(rule.getWorldDeletions().size(), 2);
        assertEquals(rule.getWorldDeletions().get(0), new Predicate("pre1"));
        assertEquals(rule.getWorldDeletions().get(1), new Predicate("pre2"));

        assertNotNull(rule.getAlphaList());
        assertTrue(new BigDecimal(1.0).compareTo(rule.getAlphaList().calculateWeight(new BigDecimal(0.5))) == 0);
    }

    @Test
    public void goal_rule_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("-> #goal Actions.action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        List<Rule> atoms = gen.getRules();
        Assert.assertNotNull(atoms);
        assertEquals(1, atoms.size());
        Rule rule = atoms.get(0);

        assertEquals(rule.getAction(), "Actions.action");
        assertEquals(rule.getRuleGoal(), 1.0);
        assertEquals(rule.getWorldAddition(), null);
        assertEquals(rule.getGoal(), "goal");
        assertEquals(rule.hasGoal(), true);
        assertEquals(rule.hasWorldAddition(), false);

        assertNotNull(rule.getPreconditions());
        assertEquals(rule.getPreconditions().size(), 0);

        assertNotNull(rule.getWorldDeletions());
        assertEquals(rule.getWorldDeletions().size(), 0);

        assertNotNull(rule.getAlphaList());
        assertTrue(new BigDecimal(1.0).compareTo(rule.getAlphaList().calculateWeight(new BigDecimal(0.5))) == 0);
    }

    @Test
    public void alphaList_std_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("-> Actions.action (1/2).");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        List<Rule> rules = gen.getRules();
        Assert.assertNotNull(rules);
        assertEquals(1, rules.size());
        Rule rule = rules.get(0);

        assertEquals(rule.getAction(), "Actions.action");
        assertEquals(rule.getRuleGoal(), 1.0);
        assertEquals(rule.getWorldAddition(), null);
        assertEquals(rule.getGoal(), null);
        assertEquals(rule.hasGoal(), false);
        assertEquals(rule.hasWorldAddition(), false);

        assertNotNull(rule.getPreconditions());
        assertEquals(rule.getPreconditions().size(), 0);

        assertNotNull(rule.getWorldDeletions());
        assertEquals(rule.getWorldDeletions().size(), 0);

        assertNotNull(rule.getAlphaList());
        assertTrue(new BigDecimal(0.5).compareTo(rule.getAlphaList().calculateWeight(new BigDecimal(0.5))) == 0);
    }

    @Test
    public void alphaList_list_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("-> Actions.action (0 <= a <= 0.5: a*2, 0.5 < a <= 1: a).");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        List<Rule> atoms = gen.getRules();
        Assert.assertNotNull(atoms);
        assertEquals(1, atoms.size());
        Rule rule = atoms.get(0);

        assertEquals(rule.getAction(), "Actions.action");
        assertEquals(rule.getRuleGoal(), 1.0);
        assertEquals(rule.getWorldAddition(), null);
        assertEquals(rule.getGoal(), null);
        assertEquals(rule.hasGoal(), false);
        assertEquals(rule.hasWorldAddition(), false);

        assertNotNull(rule.getPreconditions());
        assertEquals(rule.getPreconditions().size(), 0);

        assertNotNull(rule.getWorldDeletions());
        assertEquals(rule.getWorldDeletions().size(), 0);

        assertNotNull(rule.getAlphaList());
        assertTrue(new BigDecimal(0.0).compareTo(rule.getAlphaList().calculateWeight(new BigDecimal(0.0))) == 0);
        assertTrue(new BigDecimal(0.5).compareTo(rule.getAlphaList().calculateWeight(new BigDecimal(0.25))) == 0);
        assertTrue(new BigDecimal(1.0).compareTo(rule.getAlphaList().calculateWeight(new BigDecimal(0.5))) == 0);
        assertTrue(new BigDecimal(0.75).compareTo(rule.getAlphaList().calculateWeight(new BigDecimal(0.75))) == 0);
        assertTrue(new BigDecimal(1.0).compareTo(rule.getAlphaList().calculateWeight(new BigDecimal(1.0))) == 0);
    }


    @Test
    public void alphaList_list_with_std_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("-> Actions.action (0 <= a <= 0.25: a*2, 0.5 < a <= 0.75: a, 1).");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        List<Rule> atoms = gen.getRules();
        Assert.assertNotNull(atoms);
        assertEquals(1, atoms.size());
        Rule rule = atoms.get(0);

        assertEquals(rule.getAction(), "Actions.action");
        assertEquals(rule.getRuleGoal(), 1.0);
        assertEquals(rule.getWorldAddition(), null);
        assertEquals(rule.getGoal(), null);
        assertEquals(rule.hasGoal(), false);
        assertEquals(rule.hasWorldAddition(), false);

        assertNotNull(rule.getPreconditions());
        assertEquals(rule.getPreconditions().size(), 0);

        assertNotNull(rule.getWorldDeletions());
        assertEquals(rule.getWorldDeletions().size(), 0);

        assertNotNull(rule.getAlphaList());
        assertTrue(new BigDecimal(0.0).compareTo(rule.getAlphaList().calculateWeight(new BigDecimal(0))) == 0);
        assertTrue(new BigDecimal(0.5).compareTo(rule.getAlphaList().calculateWeight(new BigDecimal(0.25))) == 0);
        assertTrue(new BigDecimal(1.0).compareTo(rule.getAlphaList().calculateWeight(new BigDecimal(0.3))) == 0);
        assertTrue(new BigDecimal(1.0).compareTo(rule.getAlphaList().calculateWeight(new BigDecimal(0.5))) == 0);
        assertTrue(new BigDecimal(0.75).compareTo(rule.getAlphaList().calculateWeight(new BigDecimal(0.75))) == 0);
        assertTrue(new BigDecimal(1).compareTo(rule.getAlphaList().calculateWeight(new BigDecimal(0.8))) == 0);
        assertTrue(new BigDecimal(1).compareTo(rule.getAlphaList().calculateWeight(new BigDecimal(1))) == 0);
    }

    @Test
    public void complicated_alphaEntry_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("-> Actions.action (0 <= a <= 1: 3 + (1 + 2 / 1 + 2 * (+2 + 2 + 2 + (2 + 2)))).");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        List<Rule> atoms = gen.getRules();
        Assert.assertNotNull(atoms);
        assertEquals(1, atoms.size());
        Rule rule = atoms.get(0);

        assertEquals(rule.getAction(), "Actions.action");
        assertEquals(rule.getRuleGoal(), 1.0);
        assertEquals(rule.getWorldAddition(), null);
        assertEquals(rule.getGoal(), null);
        assertEquals(rule.hasGoal(), false);
        assertEquals(rule.hasWorldAddition(), false);

        assertNotNull(rule.getPreconditions());
        assertEquals(rule.getPreconditions().size(), 0);

        assertNotNull(rule.getWorldDeletions());
        assertEquals(rule.getWorldDeletions().size(), 0);

        assertNotNull(rule.getAlphaList());
        assertTrue(new BigDecimal(26).compareTo(rule.getAlphaList().calculateWeight(new BigDecimal(1))) == 0);
    }
}
