package at.tugraz.ist.compiler.rule;

import at.tugraz.ist.compiler.helper.TestHelper;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import static junit.framework.Assert.*;

public class RuleTest {

    @Test
    public void Ctr_test() {
        Predicate pred = new Predicate("pred a b");
        Predicate pred2 = new Predicate("pred2 b c");

        SortedSet<Predicate> preconditions = new TreeSet<>();
        preconditions.add(pred);
        preconditions.add(pred2);

        Predicate pred3 = new Predicate("pred a b", true);
        Predicate pred4 = new Predicate("pred2 a b", false);
        SortedSet<Predicate> postconditions = new TreeSet<>();
        postconditions.add(pred3);
        postconditions.add(pred4);

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("?a", "a");
        parameters.put("?b", "b");
        parameters.put("?c", "c");

        Rule rule = TestHelper.getRule();
        assertEquals("action", rule.getAction());
        assertEquals(preconditions, rule.getPreconditions());
        assertEquals(postconditions, rule.getPostConditions());
        assertEquals(parameters, rule.getParameters());


        Rule rule2 = new Rule(rule);
        assertEquals("action", rule2.getAction());
        assertEquals(preconditions, rule2.getPreconditions());
        assertEquals(postconditions, rule2.getPostConditions());
        assertEquals(parameters, rule2.getParameters());
    }

    @Test
    public void Equals_test() {
        Rule rule = TestHelper.getRule();

        assertTrue(rule.equals(rule));
        assertFalse(rule.equals(null));
        assertFalse(rule.equals(new Object()));

        Predicate pred = new Predicate("pred a b");
        Predicate pred2 = new Predicate("pred2 b c");

        SortedSet<Predicate> preconditions = new TreeSet<>();
        preconditions.add(pred);

        Predicate pred3 = new Predicate("pred a b", true);
        Predicate pred4 = new Predicate("pred2 a b", false);
        SortedSet<Predicate> postconditions = new TreeSet<>();
        postconditions.add(pred3);
        postconditions.add(pred4);

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("?a", "a");
        parameters.put("?b", "b");
        parameters.put("?c", "c");

        Rule rule2 = new Rule(
                "action",
                postconditions,
                preconditions,
                parameters
        );

        assertFalse(rule.equals(rule2));

        preconditions = new TreeSet<>();
        preconditions.add(pred);
        preconditions.add(pred2);

        postconditions = new TreeSet<>();
        rule2 = new Rule(
                "action",
                postconditions,
                preconditions,
                parameters
        );

        assertFalse(rule.equals(rule2));

        postconditions = new TreeSet<>();
        postconditions.add(pred3);
        postconditions.add(pred4);

        rule2 = new Rule(
                "action2",
                postconditions,
                preconditions,
                parameters
        );
        assertFalse(rule.equals(rule2));

        rule2 = new Rule(
                "action",
                postconditions,
                preconditions,
                parameters
        );
        assertTrue(rule.equals(rule2));
    }

    @Test
    public void compareTo_test() {
        Rule rule = TestHelper.getRule();
        Rule rule2 = TestHelper.getRule();

        rule.updateRule(false, true, true);
        rule2.updateRule(true, true, true);

        assertEquals(-1, rule.compareTo(rule2));
        assertEquals(0, rule.compareTo(rule));
        assertEquals(1, rule2.compareTo(rule));
    }

    @Test
    public void updateTest_test() {
        Rule rule = TestHelper.getRule();
        Rule rule2 = TestHelper.getRule();
        Rule rule3 = TestHelper.getRule();
        Rule rule4 = TestHelper.getRule();

        rule.updateRule(false, true, true);
        rule2.updateRule(true, true, true);
        rule3.updateRule(false, false, true);
        rule4.updateRule(true, false, true);

        // if executed succeeding rule should be better
        assertEquals(-1, rule.compareTo(rule2));

        // if not executed both rules should be the same
        assertEquals(0, rule3.compareTo(rule4));

        // if succeeded both rule should be the same
        assertEquals(0, rule.compareTo(rule3));

        // if failed executed rule should be worse
        assertEquals(1, rule2.compareTo(rule4));
    }

    @Test
    public void getWeight_test() {
        Rule rule = TestHelper.getRule();
        assertEquals(new BigDecimal(0.00001), rule.getWeight());
    }

    @Test
    public void toString_test() {
        Rule rule = TestHelper.getRule();
        assertEquals("action ?a - a, ?b - b, ?c - c: pred a b, pred2 b c -> -pred a b, pred2 a b", rule.toString());
    }

    @Test
    public void exeution_test() {
        Rule rule = TestHelper.getRule();
        TestHelper.TestAction action = new TestHelper.TestAction();

        assertFalse(action.executed);
        assertTrue(rule.execute(null, action));
        assertTrue(action.executed);
    }

    @Test
    public void repair_test() {
        Rule rule = TestHelper.getRule();
        TestHelper.TestAction action = new TestHelper.TestAction();

        assertFalse(action.repair);
        rule.repairMemory(null, action);
        assertTrue(action.repair);
    }
}
