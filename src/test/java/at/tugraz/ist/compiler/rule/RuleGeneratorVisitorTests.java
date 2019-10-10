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
    public void addition_rule_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("pre1.\n" +
                "pre2.\n" +
                "pre1, pre2 -> +post -pre1 -pre2 Actions.action.");
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
        assertEquals(rule.getWorldAdditions().get(0), new Predicate("post"));
        assertNull(rule.getGoal());
        assertFalse(rule.hasGoal());

        assertNotNull(rule.getPreconditions());
        assertEquals(rule.getPreconditions().size(), 2);
        assertEquals(rule.getPreconditions().get(0), new Predicate("pre1"));
        assertEquals(rule.getPreconditions().get(1), new Predicate("pre2"));

        assertNotNull(rule.getWorldDeletions());
        assertEquals(rule.getWorldDeletions().size(), 2);
        assertEquals(rule.getWorldDeletions().get(0), new Predicate("pre1"));
        assertEquals(rule.getWorldDeletions().get(1), new Predicate("pre2"));
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
        assertTrue(rule.getWorldAdditions().isEmpty());
        assertEquals(rule.getGoal(), "goal");
        assertTrue(rule.hasGoal());

        assertNotNull(rule.getPreconditions());
        assertEquals(rule.getPreconditions().size(), 0);

        assertNotNull(rule.getWorldDeletions());
        assertEquals(rule.getWorldDeletions().size(), 0);
    }

    @Test
    public void alphaList_std_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("-> Actions.action.");
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
        assertTrue(rule.getWorldAdditions().isEmpty());
        assertNull(rule.getGoal());
        assertFalse(rule.hasGoal());

        assertNotNull(rule.getPreconditions());
        assertEquals(rule.getPreconditions().size(), 0);

        assertNotNull(rule.getWorldDeletions());
        assertEquals(rule.getWorldDeletions().size(), 0);
    }

    @Test
    public void alphaList_list_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("-> Actions.action.");
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
        assertTrue(rule.getWorldAdditions().isEmpty());
        assertNull(rule.getGoal());
        assertFalse(rule.hasGoal());

        assertNotNull(rule.getPreconditions());
        assertEquals(rule.getPreconditions().size(), 0);

        assertNotNull(rule.getWorldDeletions());
        assertEquals(rule.getWorldDeletions().size(), 0);
    }


    @Test
    public void alphaList_list_with_std_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("-> Actions.action.");
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
        assertTrue(rule.getWorldAdditions().isEmpty());
        assertNull(rule.getGoal());
        assertFalse(rule.hasGoal());
        assertNotNull(rule.getPreconditions());
        assertEquals(rule.getPreconditions().size(), 0);

        assertNotNull(rule.getWorldDeletions());
        assertEquals(rule.getWorldDeletions().size(), 0);
    }


    @Test
    public void empty_file_test() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        List<Rule> atoms = gen.getRules();
        Assert.assertNotNull(atoms);
        assertEquals(0, atoms.size());
    }
}
