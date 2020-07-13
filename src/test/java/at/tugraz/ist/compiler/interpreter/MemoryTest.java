package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.helper.TestHelper;
import at.tugraz.ist.compiler.rule.Predicate;
import org.junit.Test;

import java.util.SortedSet;
import java.util.TreeSet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class MemoryTest {

    @Test
    public void Ctor_test() {
        SortedSet<Predicate> predicates = new TreeSet<>();
        predicates.add(new Predicate("pred"));
        Memory mem = new Memory(predicates);

        assertEquals(predicates, mem.getPredicates());

        Memory mem2 = new Memory(mem);
        assertEquals(predicates, mem2.getPredicates());
    }

    @Test
    public void update_test() {
        SortedSet<Predicate> predicates = new TreeSet<>();
        predicates.add(new Predicate("pred a b"));
        Memory mem = new Memory(predicates);

        mem.update(TestHelper.getRule());

        predicates = new TreeSet<>();
        predicates.add(new Predicate("pred2 a b"));

        assertEquals(predicates, mem.getPredicates());
    }

    @Test
    public void removePredicate_test() {
        SortedSet<Predicate> predicates = new TreeSet<>();
        predicates.add(new Predicate("pred a b"));
        Memory mem = new Memory(predicates);
        assertEquals(predicates, mem.getPredicates());

        mem.removePredicate("pred a b");
        assertEquals(new TreeSet<>(), mem.getPredicates());
    }

    @Test
    public void addPredicate_test() {
        SortedSet<Predicate> predicates = new TreeSet<>();
        predicates.add(new Predicate("pred a b"));
        Memory mem = new Memory(predicates);
        assertEquals(predicates, mem.getPredicates());

        mem.addPredicate("pred2 a b");
        predicates.add(new Predicate("pred2 a b"));
        assertEquals(predicates, mem.getPredicates());
    }

    @Test
    public void equals_test() {
        SortedSet<Predicate> predicates = new TreeSet<>();
        predicates.add(new Predicate("pred a b"));
        Memory mem = new Memory(predicates);
        Memory mem2 = new Memory(predicates);

        predicates.add(new Predicate("pred b c"));
        Memory mem3 = new Memory(predicates);

        predicates = new TreeSet<>();
        predicates.add(new Predicate("pred b c"));
        Memory mem4 = new Memory(predicates);

        assertTrue(mem.equals(mem));
        assertTrue(mem.equals(mem2));
        assertFalse(mem.equals(new Object()));
        assertFalse(mem.equals(mem3));
        assertFalse(mem3.equals(mem));
        assertFalse(mem.equals(mem4));
    }

    @Test
    public void hashCode_test() {
        SortedSet<Predicate> predicates = new TreeSet<>();
        predicates.add(new Predicate("pred a b"));
        Memory mem = new Memory(predicates);
        Memory mem2 = new Memory(predicates);

        assertEquals(mem.hashCode(), mem2.hashCode());
    }

    @Test
    public void toString_test() {
        SortedSet<Predicate> predicates = new TreeSet<>();
        predicates.add(new Predicate("pred a b"));
        Memory mem = new Memory(predicates);

        assertEquals("Memory{pred a b}", mem.toString());

        predicates.add(new Predicate("pred b c"));
        Memory mem3 = new Memory(predicates);

        assertEquals("Memory{pred a b, pred b c}", mem3.toString());
    }
}
