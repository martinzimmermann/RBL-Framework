package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.helper.TestHelper;
import at.tugraz.ist.compiler.rule.Action;
import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertSame;

public class ModelTest {

    @Test
    public void Ctor_test() {
        List<Action> actions = new ArrayList<>();
        actions.add(TestHelper.getAction());

        List<String> objects = new ArrayList<>();
        objects.add("pred a b");
        objects.add("pred2 b c");
        Model model = new Model(objects, actions);

        assertEquals(new ArrayList<Rule>(), model.getRules());
    }

    @Test
    public void GetPossibleRules_test() {
        List<Action> actions = new ArrayList<>();
        actions.add(TestHelper.getAction());

        List<String> objects = new ArrayList<>();
        objects.add("pred a b");
        objects.add("pred2 b c");
        Model model = new Model(objects, actions);

        Memory mem = new Memory(new TreeSet<>());
        assertEquals(new ArrayList<Rule>(), model.getPossibleRules(mem));
        assertEquals(new ArrayList<Rule>(), model.getRules());

        SortedSet<Predicate> predicates = new TreeSet<>();
        predicates.add(new Predicate("pred a b"));
        predicates.add(new Predicate("pred2 b c"));
        mem = new Memory(predicates);

        List<Rule> rules = new ArrayList<>();
        SortedSet<Predicate> postConditions = new TreeSet<>();
        postConditions.add(new Predicate("pred2 a b", true));
        postConditions.add(new Predicate("pred b c"));
        SortedSet<Predicate> preconditions = new TreeSet<>();
        preconditions.add(new Predicate("pred a b"));
        preconditions.add(new Predicate("pred2 b c"));
        Map<String, String> parameters = new HashMap<>();
        parameters.put("?a", "a");
        parameters.put("?b", "b");
        parameters.put("?c", "c");

        rules.add(new Rule("action", postConditions, preconditions, parameters));

        List<Rule> rulesActual = model.getPossibleRules(mem);
        assertEquals(rules, rulesActual);
        assertEquals(rules, model.getRules());

        // second time rule should be already cached
        assertSame(rulesActual.get(0), model.getPossibleRules(mem).get(0));
    }

}
