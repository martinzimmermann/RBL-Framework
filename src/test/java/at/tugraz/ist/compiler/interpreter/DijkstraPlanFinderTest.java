package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.helper.TestHelper;
import at.tugraz.ist.compiler.rule.Action;
import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;

public class DijkstraPlanFinderTest {
    @Test
    public void getPlanForGoal_test() {
        List<Action> actions = new ArrayList<>();
        actions.add(TestHelper.getAction());

        List<String> objects = new ArrayList<>();
        objects.add("pred a b");
        objects.add("pred b c");
        objects.add("pred2 b c");
        Model model = new Model(objects, actions);

        Memory mem = new Memory(new TreeSet<>());
        assertEquals(new ArrayList<Rule>(), model.getPossibleRules(mem));
        assertEquals(new ArrayList<Rule>(), model.getRules());

        SortedSet<Predicate> predicates = new TreeSet<>();
        predicates.add(new Predicate("pred a b"));
        predicates.add(new Predicate("pred2 b c"));
        mem = new Memory(predicates);

        List<Predicate> goal = new ArrayList<>();
        goal.add(new Predicate("pred b c"));
        DijkstraPlanFinder finder = new DijkstraPlanFinder();

        List<Rule> rules = finder.getPlanForGoal(goal, mem, model);

        List<Rule> expected_rules = new ArrayList<>();
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

        expected_rules.add(new Rule("action", postConditions, preconditions, parameters));

        assertEquals(expected_rules, rules);
    }

    @Test
    public void getPlanForGoal_notPossible_test() {
        List<Action> actions = new ArrayList<>();
        actions.add(TestHelper.getAction());

        List<String> objects = new ArrayList<>();
        objects.add("pred a b");
        objects.add("pred b c");
        objects.add("pred2 b c");
        Model model = new Model(objects, actions);

        Memory mem = new Memory(new TreeSet<>());
        assertEquals(new ArrayList<Rule>(), model.getPossibleRules(mem));
        assertEquals(new ArrayList<Rule>(), model.getRules());

        SortedSet<Predicate> predicates = new TreeSet<>();
        predicates.add(new Predicate("pred a b"));
        predicates.add(new Predicate("pred2 b c"));
        mem = new Memory(predicates);

        List<Predicate> goal = new ArrayList<>();
        goal.add(new Predicate("pred a c"));
        DijkstraPlanFinder finder = new DijkstraPlanFinder();

        List<Rule> rules = finder.getPlanForGoal(goal, mem, model);

        assertEquals(null, rules);
    }

    @Test
    public void getPlanForGoal_multipleRules_test() {
        List<Action> actions = new ArrayList<>();
        actions.add(TestHelper.getAction());

        List<String> objects = new ArrayList<>();
        objects.add("pred a b");
        objects.add("pred s t");
        objects.add("pred x a");
        objects.add("pred b c");
        objects.add("pred y z");
        objects.add("pred2 b c");
        objects.add("pred2 a b");
        Model model = new Model(objects, actions);

        Memory mem = new Memory(new TreeSet<>());
        assertEquals(new ArrayList<Rule>(), model.getPossibleRules(mem));
        assertEquals(new ArrayList<Rule>(), model.getRules());

        SortedSet<Predicate> predicates = new TreeSet<>();
        predicates.add(new Predicate("pred a b"));
        predicates.add(new Predicate("pred s t"));
        predicates.add(new Predicate("pred x a"));
        predicates.add(new Predicate("pred2 b c"));
        predicates.add(new Predicate("pred2 a b"));
        mem = new Memory(predicates);

        List<Predicate> goal = new ArrayList<>();
        goal.add(new Predicate("pred b c"));
        DijkstraPlanFinder finder = new DijkstraPlanFinder();

        List<Rule> rules = finder.getPlanForGoal(goal, mem, model);

        List<Rule> expected_rules = new ArrayList<>();
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

        expected_rules.add(new Rule("action", postConditions, preconditions, parameters));

        assertEquals(expected_rules, rules);
    }
}
