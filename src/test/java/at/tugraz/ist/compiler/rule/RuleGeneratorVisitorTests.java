package at.tugraz.ist.compiler.rule;

import at.tugraz.ist.compiler.parser.RuleLexer;
import at.tugraz.ist.compiler.parser.RuleParser;
import at.tugraz.ist.compiler.ruleGenerator.RuleGenerator;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class RuleGeneratorVisitorTests {


    @Test
    public void addition_rule_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("pre1. " +
                "pre2." +
                "pre1, pre2 -> +post -pre1 -pre2 action 0.2.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        List<Atom> atoms = gen.getRules();
        Assert.assertNotNull(atoms);
        assertEquals(3, atoms.size());
        Assert.assertTrue(atoms.get(0) instanceof Predicate);
        assertEquals(((Predicate)atoms.get(0)).getName(), "pre1");

        Assert.assertTrue(atoms.get(1) instanceof Predicate);
        assertEquals(((Predicate)atoms.get(1)).getName(), "pre2");

        Assert.assertTrue(atoms.get(2) instanceof Rule);

        Rule rule = (Rule)atoms.get(2);

        assertEquals(rule.getAction(), "action");
        assertEquals(rule.getRuleGoal(), 0.2);
        assertEquals(rule.getWorldAdditon(), "post");
        assertEquals(rule.getGoal(), null);
        assertEquals(rule.hasGoal(), false);
        assertEquals(rule.hasWorldAddition(), true);

        assertNotNull(rule.getPreconditions());
        assertEquals(rule.getPreconditions().size(), 2);
        assertEquals(((Predicate)rule.getPreconditions().get(0)).getName(), "pre1");
        assertEquals(((Predicate)rule.getPreconditions().get(1)).getName(), "pre2");

        assertNotNull(rule.getWorldDeletions());
        assertEquals(rule.getWorldDeletions().size(), 2);
        assertEquals(((Predicate)rule.getWorldDeletions().get(0)).getName(), "pre1");
        assertEquals(((Predicate)rule.getWorldDeletions().get(1)).getName(), "pre2");

        assertNotNull(rule.getAlphaList());
        assertEquals(1.0, rule.getAlphaList().calculateWeight(0.5));
    }

    @Test
    public void goal_rule_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("-> #goal action.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        List<Atom> atoms = gen.getRules();
        Assert.assertNotNull(atoms);
        assertEquals(1, atoms.size());
        Assert.assertTrue(atoms.get(0) instanceof Rule);
        Rule rule = (Rule)atoms.get(0);

        assertEquals(rule.getAction(), "action");
        assertEquals(rule.getRuleGoal(), 1.0);
        assertEquals(rule.getWorldAdditon(), null);
        assertEquals(rule.getGoal(), "goal");
        assertEquals(rule.hasGoal(), true);
        assertEquals(rule.hasWorldAddition(), false);

        assertNotNull(rule.getPreconditions());
        assertEquals(rule.getPreconditions().size(), 0);

        assertNotNull(rule.getWorldDeletions());
        assertEquals(rule.getWorldDeletions().size(), 0);

        assertNotNull(rule.getAlphaList());
        assertEquals(1.0, rule.getAlphaList().calculateWeight(0.5));
    }

    @Test
    public void alphaList_std_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("-> action (1/2).");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        List<Atom> atoms = gen.getRules();
        Assert.assertNotNull(atoms);
        assertEquals(1, atoms.size());
        Assert.assertTrue(atoms.get(0) instanceof Rule);
        Rule rule = (Rule)atoms.get(0);

        assertEquals(rule.getAction(), "action");
        assertEquals(rule.getRuleGoal(), 1.0);
        assertEquals(rule.getWorldAdditon(), null);
        assertEquals(rule.getGoal(), null);
        assertEquals(rule.hasGoal(), false);
        assertEquals(rule.hasWorldAddition(), false);

        assertNotNull(rule.getPreconditions());
        assertEquals(rule.getPreconditions().size(), 0);

        assertNotNull(rule.getWorldDeletions());
        assertEquals(rule.getWorldDeletions().size(), 0);

        assertNotNull(rule.getAlphaList());
        assertEquals(0.5, rule.getAlphaList().calculateWeight(0.5));
    }

    @Test
    public void alphaList_list_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("-> action (0 <= a <= 0.5: a*2, 0.5 < a <= 1: a).");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        List<Atom> atoms = gen.getRules();
        Assert.assertNotNull(atoms);
        assertEquals(1, atoms.size());
        Assert.assertTrue(atoms.get(0) instanceof Rule);
        Rule rule = (Rule)atoms.get(0);

        assertEquals(rule.getAction(), "action");
        assertEquals(rule.getRuleGoal(), 1.0);
        assertEquals(rule.getWorldAdditon(), null);
        assertEquals(rule.getGoal(), null);
        assertEquals(rule.hasGoal(), false);
        assertEquals(rule.hasWorldAddition(), false);

        assertNotNull(rule.getPreconditions());
        assertEquals(rule.getPreconditions().size(), 0);

        assertNotNull(rule.getWorldDeletions());
        assertEquals(rule.getWorldDeletions().size(), 0);

        assertNotNull(rule.getAlphaList());
        assertEquals(0.0, rule.getAlphaList().calculateWeight(0));
        assertEquals(0.5, rule.getAlphaList().calculateWeight(0.25));
        assertEquals(1.0, rule.getAlphaList().calculateWeight(0.5));
        assertEquals(0.75, rule.getAlphaList().calculateWeight(0.75));
        assertEquals(1.0, rule.getAlphaList().calculateWeight(1));
    }


    @Test
    public void alphaList_list_with_std_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("-> action (0 <= a <= 0.25: a*2, 0.5 < a <= 0.75: a, 1).");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        List<Atom> atoms = gen.getRules();
        Assert.assertNotNull(atoms);
        assertEquals(1, atoms.size());
        Assert.assertTrue(atoms.get(0) instanceof Rule);
        Rule rule = (Rule)atoms.get(0);

        assertEquals(rule.getAction(), "action");
        assertEquals(rule.getRuleGoal(), 1.0);
        assertEquals(rule.getWorldAdditon(), null);
        assertEquals(rule.getGoal(), null);
        assertEquals(rule.hasGoal(), false);
        assertEquals(rule.hasWorldAddition(), false);

        assertNotNull(rule.getPreconditions());
        assertEquals(rule.getPreconditions().size(), 0);

        assertNotNull(rule.getWorldDeletions());
        assertEquals(rule.getWorldDeletions().size(), 0);

        assertNotNull(rule.getAlphaList());
        assertEquals(0.0, rule.getAlphaList().calculateWeight(0));
        assertEquals(0.5, rule.getAlphaList().calculateWeight(0.25));
        assertEquals(1.0, rule.getAlphaList().calculateWeight(0.3));
        assertEquals(1.0, rule.getAlphaList().calculateWeight(0.5));
        assertEquals(0.75, rule.getAlphaList().calculateWeight(0.75));
        assertEquals(1.0, rule.getAlphaList().calculateWeight(0.8));
        assertEquals(1.0, rule.getAlphaList().calculateWeight(1));
    }
}
