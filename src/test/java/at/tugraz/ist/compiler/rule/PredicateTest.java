package at.tugraz.ist.compiler.rule;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class PredicateTest {

    @Test
    public void Equals_test() {
        Predicate pred = new Predicate("pred");
        assertFalse(pred.equals(null));
        assertFalse(pred.equals(new Object()));
        assertTrue(pred.equals(pred));

        Predicate pred2 = new Predicate("pred");
        assertTrue(pred.equals(pred2));

        pred2 = new Predicate("pred", false);
        assertTrue(pred.equals(pred2));

        assertFalse(pred.equals(null));

        assertFalse(pred.equals(new Object()));
    }

    @Test
    public void GetName_test() {
        Predicate pred = new Predicate("pred");
        assertEquals("pred", pred.getIdentifier());
    }

    @Test
    public void IsAddition_test() {
        Predicate pred = new Predicate("pred");
        assertTrue(pred.isAddition());

        pred = new Predicate("pred", false);
        assertTrue(pred.isAddition());

        pred = new Predicate("pred", true);
        assertFalse(pred.isAddition());
    }

    @Test
    public void IsDeletion_test() {
        Predicate pred = new Predicate("pred");
        assertFalse(pred.isDeletion());

        pred = new Predicate("pred", false);
        assertFalse(pred.isDeletion());

        pred = new Predicate("pred", true);
        assertTrue(pred.isDeletion());
    }

    @Test
    public void HashCode_test() {
        Predicate pred = new Predicate("pred");
        Predicate pred2 = new Predicate("pred");
        assertEquals(pred.hashCode(), pred2.hashCode());
    }

    @Test
    public void toString_test() {
        Predicate pred = new Predicate("pred");
        assertEquals("pred", pred.toString());

        pred = new Predicate("pred", true);
        assertEquals("-pred", pred.toString());
    }

    @Test
    public void compareTo_test() {
        Predicate pred = new Predicate("apred");
        Predicate pred2 = new Predicate("bpred");
        assertEquals(-1, pred.compareTo(pred2));
        assertEquals(1, pred2.compareTo(pred));
        assertEquals(0, pred.compareTo(pred));
    }

    @Test
    public void getExpression_test() {
        Predicate pred = new Predicate("pred a b");
        String[] expr = pred.getExpressionSplit();
        assertEquals("pred", expr[0]);
        assertEquals("a", expr[1]);
        assertEquals("b", expr[2]);
    }
}
