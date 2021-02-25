package at.tugraz.ist.compiler.rule;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static junit.framework.TestCase.*;

public class AtomicFormularTest {

    @Test
    public void Ctor_test() {
        List<String> variables = new ArrayList<>();
        variables.add("?a");
        variables.add("?b");

        Predicate pred = new Predicate("pred ?a ?b");
        AtomicFormula form = new AtomicFormula(pred, variables);
        assertEquals(pred, form.getPredicate());
        assertEquals(variables, form.getVariables());
    }

    @Test
    public void toGroundedPredicate_test() {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("?a", "a");
        parameters.put("?b", "b");

        List<String> variables = new ArrayList<>();
        variables.add("?a");
        variables.add("?b");

        Predicate pred = new Predicate("pred ?a ?b");
        AtomicFormula form = new AtomicFormula(pred, variables);
        Predicate grounded = form.toGroundedPredicate(parameters);

        assertEquals(new Predicate("pred a b"), grounded);
    }

    @Test
    public void canBeGrounded_test() {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("?a", "a");

        List<String> variables = new ArrayList<>();
        variables.add("?a");
        variables.add("?b");

        Predicate pred = new Predicate("pred ?a ?b");
        AtomicFormula form = new AtomicFormula(pred, variables);
        assertFalse(form.canBeGrounded(parameters));

        parameters.put("?b", "b");
        assertTrue(form.canBeGrounded(parameters));
    }

    @Test
    public void toString_test() {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("?a", "a");

        List<String> variables = new ArrayList<>();
        variables.add("?a");
        variables.add("?b");

        Predicate pred = new Predicate("pred ?a ?b");
        AtomicFormula form = new AtomicFormula(pred, variables);
        assertEquals("+pred ?a ?b", form.toString());
    }

    @Test
    public void compareTo_test() {
        List<String> variables = new ArrayList<>();
        variables.add("?a");
        variables.add("?b");

        Predicate pred1 = new Predicate("apred ?a ?b");
        AtomicFormula form1 = new AtomicFormula(pred1, variables);

        Predicate pred2 = new Predicate("bpred ?a ?b");
        AtomicFormula form2 = new AtomicFormula(pred2, variables);

        assertEquals(-1, form1.compareTo(form2));
        assertEquals(0, form1.compareTo(form1));
        assertEquals(1, form2.compareTo(form1));
    }
}
