package at.tugraz.ist.compiler.rule;

import at.tugraz.ist.compiler.helper.TestHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static junit.framework.Assert.*;

public class ActionTest {
    @Test
    public void Ctor_test() {
        Action act = TestHelper.getAction();
        Action act2 = new Action(act);
    }

    @Test
    public void canConsume_test() {
        Action act = TestHelper.getAction();
        Predicate pred = new Predicate("pred a b");
        assertTrue(act.canConsume(pred));

        Predicate pred2 = new Predicate("pred2 a b");
        assertFalse(act.canConsume(pred2));

        Action act2 = TestHelper.getActionWithoutPreCond();
        assertFalse(act2.canConsume(pred));
    }

    @Test
    public void consume_test() {
        Action act = TestHelper.getAction();
        Predicate pred = new Predicate("pred a b");
        act.consume(pred);
        assertFalse(act.canConsume(pred));

        Predicate pred2 = new Predicate("pred2 b c");
        assertTrue(act.canConsume(pred2));

        Predicate pred3 = new Predicate("pred2 d c");
        assertFalse(act.canConsume(pred3));
    }

    @Test
    public void isFullyAssigned_test() {
        Action act = TestHelper.getAction();
        assertFalse(act.isFullyAssigned());

        Predicate pred = new Predicate("pred a b");
        act.consume(pred);
        assertFalse(act.isFullyAssigned());

        Predicate pred2 = new Predicate("pred2 b c");
        act.consume(pred2);
        assertTrue(act.isFullyAssigned());
    }

    @Test
    public void createRule_test() {
        Action act = TestHelper.getAction();
        Predicate pred = new Predicate("pred a b");
        act.consume(pred);
        Predicate pred2 = new Predicate("pred2 b c");
        act.consume(pred2);

        Rule rule = act.createRule();
        assertEquals("action", rule.getAction());
    }
}
