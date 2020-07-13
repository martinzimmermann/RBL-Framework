package at.tugraz.ist.compiler.rule;

import at.tugraz.ist.compiler.helper.TestHelper;
import org.junit.Test;

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
        assertNotNull(act.canBeConsumedBy(pred));

        Predicate pred2 = new Predicate("pred3 a b");
        assertNull(act.canBeConsumedBy(pred2));

        Action act2 = TestHelper.getActionWithoutPreCond();
        assertNull(act2.canBeConsumedBy(pred));
    }

    @Test
    public void consume_test() {
        Action act = TestHelper.getAction();
        Predicate pred = new Predicate("pred a b");
        Predicate pred2 = new Predicate("pred2 b c");

        AtomicFormula a = act.canBeConsumedBy(pred);
        AtomicFormula a2 = act.canBeConsumedBy(pred2);
        assertNotNull(a);
        assertNotNull(a2);
        assertNotSame(a, a2);

        act.consume(a, pred);
        assertNull(act.canBeConsumedBy(pred));
        assertNotNull(act.canBeConsumedBy(pred2));

        act.consume(a2, pred2);

        Predicate pred3 = new Predicate("pred2 d c");
        assertNull(act.canBeConsumedBy(pred3));
    }

    @Test
    public void isFullyAssigned_test() {
        Action act = TestHelper.getAction();
        assertFalse(act.preconditionsFulfilled());

        Predicate pred = new Predicate("pred a b");
        AtomicFormula a = act.canBeConsumedBy(pred);

        act.consume(a, pred);
        assertFalse(act.preconditionsFulfilled());

        Predicate pred2 = new Predicate("pred2 b c");
        a = act.canBeConsumedBy(pred2);
        act.consume(a, pred2);
        assertTrue(act.preconditionsFulfilled());
    }

    @Test
    public void createRule_test() {
        Action act = TestHelper.getAction();
        Predicate pred = new Predicate("pred a b");
        AtomicFormula a = act.canBeConsumedBy(pred);
        act.consume(a, pred);
        Predicate pred2 = new Predicate("pred2 b c");
        a = act.canBeConsumedBy(pred2);
        act.consume(a, pred2);

        Rule rule = act.createRule();
        assertEquals("action", rule.getAction());
    }
}
