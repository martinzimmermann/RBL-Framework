package at.tugraz.ist.compiler.rule;

import at.tugraz.ist.compiler.parser.RuleLexer;
import at.tugraz.ist.compiler.parser.RuleParser;
import at.tugraz.ist.compiler.ruleGenerator.RuleGenerator;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class RuleGeneratorVisitorTests {


    @Test
    public void predicate_test() throws IOException {
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
        assertEquals(rule.getWorldAdditon(), "post");
        assertEquals(rule.getRuleGoal(), 0.2);
        assertEquals(rule.getGoal(), null);
        assertEquals(rule.hasGoal(), false);

        assertNotNull(rule.getPreconditions());
        assertEquals(rule.getPreconditions().size(), 2);
        assertEquals(((Predicate)rule.getPreconditions().get(0)).getName(), "pre1");
        assertEquals(((Predicate)rule.getPreconditions().get(1)).getName(), "pre2");

        assertNotNull(rule.getWorldDeletions());
        assertEquals(rule.getWorldDeletions().size(), 2);
        assertEquals(((Predicate)rule.getWorldDeletions().get(0)).getName(), "pre1");
        assertEquals(((Predicate)rule.getWorldDeletions().get(1)).getName(), "pre2");
    }
}
