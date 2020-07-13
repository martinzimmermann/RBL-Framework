package at.tugraz.ist.compiler.generator;

import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.interpreter.Model;
import at.tugraz.ist.compiler.rule.Action;
import at.tugraz.ist.compiler.rule.AtomicFormula;
import at.tugraz.ist.compiler.rule.Predicate;
import fr.uga.pddl4j.parser.Message;
import fr.uga.pddl4j.parser.Parser;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static junit.framework.Assert.assertEquals;

public class ModelGeneratorTest {

    @Test
    public void Ctor_test() throws FileNotFoundException {
        Path pddlDomainfile = Paths.get("src/test/resources/PDDL/Simple/simpleDomain.pddl");
        Path pddlProblemfile = Paths.get("src/test/resources/PDDL/Simple/simpleProblem.pddl");

        Parser parser = new Parser();
        parser.parse(pddlDomainfile.toString(), pddlProblemfile.toString());
        if (!parser.getErrorManager().isEmpty()) {
            for(Message msg : parser.getErrorManager().getMessages()) {
                System.out.println(msg.toString());
            }
        }
        ModelGenerator gen = new ModelGenerator(parser.getDomain(), parser.getProblem());

        Model actualModel = gen.getModel();
        Memory actualMemory = gen.getMemory();
        SortedSet actualGoal = gen.getGoalState();

        SortedSet<Predicate> predicates = new TreeSet<>();
        predicates.add(new Predicate("at room_0_0"));
        predicates.add(new Predicate("connected room_0_0 room_0_1"));
        predicates.add(new Predicate("connected room_0_1 room_0_0"));
        predicates.add(new Predicate("connected room_1_0 room_1_1"));
        predicates.add(new Predicate("connected room_1_1 room_1_0"));
        predicates.add(new Predicate("connected room_0_0 room_1_0"));
        predicates.add(new Predicate("connected room_1_0 room_0_0"));
        predicates.add(new Predicate("connected room_0_1 room_1_1"));
        predicates.add(new Predicate("connected room_1_1 room_0_1"));
        Memory expectedMemory = new Memory(predicates);

        List<String> objects = new ArrayList<>();
        objects.add("room_0_0");
        objects.add("room_0_1");
        objects.add("room_1_0");
        objects.add("room_1_1");

        List<Action> actions = new ArrayList<>();
        SortedSet<String> parameters = new TreeSet<>();
        parameters.add("?to");
        parameters.add("?from");
        SortedSet<AtomicFormula> preconditions = new TreeSet<>();
        List<String> variables = new ArrayList<>();
        variables.add("?from");
        preconditions.add(new AtomicFormula(new Predicate("at ?from"), variables));
        variables.add("?to");
        preconditions.add(new AtomicFormula(new Predicate("connected ?from ?to"), variables));

        SortedSet<AtomicFormula> effects = new TreeSet<>();
        variables = new ArrayList<>();
        variables.add("?from");
        effects.add(new AtomicFormula(new Predicate("at ?from", true), variables));
        variables = new ArrayList<>();
        variables.add("?to");
        effects.add(new AtomicFormula(new Predicate("at ?to"), variables));

        actions.add(new Action("move", parameters, preconditions, effects));

        Model expectedModel = new Model(objects, actions);

        SortedSet<Predicate> expectedGoal = new TreeSet<>();
        expectedGoal.add(new Predicate("at room_1_1"));

        assertEquals(expectedMemory, actualMemory);
        assertEquals(expectedModel.getRules(), actualModel.getRules());
        assertEquals(expectedModel.getPossibleRules(expectedMemory), actualModel.getPossibleRules(actualMemory));
        assertEquals(expectedModel.getRules(), actualModel.getRules());
        assertEquals(expectedGoal, actualGoal);
    }
}
