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
}
