package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.helper.TestHelper;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static junit.framework.Assert.*;

public class ExecutorTest {

    @Test
    public void Ctor_test() {
        Path pddlDomainfile = Paths.get("src/test/resources/PDDL/Simple/simpleDomain.pddl");
        Path pddlProblemfile = Paths.get("src/test/resources/PDDL/Simple/simpleProblem.pddl");
        Executor executor = new Executor(pddlDomainfile.toString(), pddlProblemfile.toString());
        assertNotNull(executor.getModel());
    }

    @Test(expected = IllegalStateException.class)
    public void Ctor_wrongPath_test() {
        Path pddlDomainfile = Paths.get("src/test/resources/PDDL/Simple/simpleDomain.pddl_wrong");
        Path pddlProblemfile = Paths.get("src/test/resources/PDDL/Simple/simpleProblem.pddl");
        Executor executor = new Executor(pddlDomainfile.toString(), pddlProblemfile.toString());
        assertNotNull(executor.getModel());
    }

    @Test(expected = IllegalStateException.class)
    public void Ctor_wrongFormat_test() {
        Path pddlDomainfile = Paths.get("src/test/resources/PDDL/Simple/simpleDomain_withError.pddl");
        Path pddlProblemfile = Paths.get("src/test/resources/PDDL/Simple/simpleProblem.pddl");
        Executor executor = new Executor(pddlDomainfile.toString(), pddlProblemfile.toString());
        assertNotNull(executor.getModel());
    }

    @Test
    public void register_remove_action_test() {
        Path pddlDomainfile = Paths.get("src/test/resources/PDDL/Simple/simpleDomain.pddl");
        Path pddlProblemfile = Paths.get("src/test/resources/PDDL/Simple/simpleProblem.pddl");
        Executor executor = new Executor(pddlDomainfile.toString(), pddlProblemfile.toString());
        TestHelper.TestAction action = new TestHelper.TestAction();
        executor.registerAction("move", action);
        executor.removeAction("move");
    }

    @Test
    public void execute_test() throws NoPlanFoundException {
        Path pddlDomainfile = Paths.get("src/test/resources/PDDL/Simple/simpleDomain.pddl");
        Path pddlProblemfile = Paths.get("src/test/resources/PDDL/Simple/simpleProblem.pddl");
        Executor executor = new Executor(pddlDomainfile.toString(), pddlProblemfile.toString());

        TestHelper.TestAction action = new TestHelper.TestAction();

        executor.registerAction("move", action);
        List<String> goals = new ArrayList<>();
        goals.add("at room_1_1");
        boolean res = executor.execute(goals);
        assertTrue(res);

        assertTrue(action.executed);
        assertFalse(action.repair);
    }

    @Test
    public void removePredicate_test() {
        Path pddlDomainfile = Paths.get("src/test/resources/PDDL/Simple/simpleDomain.pddl");
        Path pddlProblemfile = Paths.get("src/test/resources/PDDL/Simple/simpleProblem.pddl");
        Executor executor = new Executor(pddlDomainfile.toString(), pddlProblemfile.toString());
        assertTrue(executor.getPredicates().contains("at room_0_0"));

        executor.removePredicate("at room_0_0");

        assertFalse(executor.getPredicates().contains("at room_0_0"));
    }

    @Test
    public void addPredicate_test() {
        Path pddlDomainfile = Paths.get("src/test/resources/PDDL/Simple/simpleDomain.pddl");
        Path pddlProblemfile = Paths.get("src/test/resources/PDDL/Simple/simpleProblem.pddl");
        Executor executor = new Executor(pddlDomainfile.toString(), pddlProblemfile.toString());
        assertFalse(executor.getPredicates().contains("at2 room_0_0"));
        executor.addPredicate("at2 room_0_0");
        assertTrue(executor.getPredicates().contains("at2 room_0_0"));
    }

    @Test
    public void executeFail_test() throws NoPlanFoundException {
        Path pddlDomainfile = Paths.get("src/test/resources/PDDL/Simple/simpleDomain.pddl");
        Path pddlProblemfile = Paths.get("src/test/resources/PDDL/Simple/simpleProblem.pddl");
        Executor executor = new Executor(pddlDomainfile.toString(), pddlProblemfile.toString());

        TestHelper.TestActionFail action = new TestHelper.TestActionFail();

        executor.registerAction("move", action);
        List<String> goals = new ArrayList<>();
        goals.add("at room_1_1");
        boolean res = executor.execute(goals);
        assertFalse(res);

        assertTrue(action.executed);
        assertTrue(action.repair);
    }

    @Test(expected = NoPlanFoundException.class)
    public void executeNoPlanFound_test() throws NoPlanFoundException {
        Path pddlDomainfile = Paths.get("src/test/resources/PDDL/Simple/simpleDomain.pddl");
        Path pddlProblemfile = Paths.get("src/test/resources/PDDL/Simple/simpleProblem.pddl");
        Executor executor = new Executor(pddlDomainfile.toString(), pddlProblemfile.toString());

        TestHelper.TestAction action = new TestHelper.TestAction();

        executor.registerAction("move", action);
        List<String> goals = new ArrayList<>();
        goals.add("at room_2_2");
        boolean res = executor.execute(goals);
    }

    @Test(expected = NoSuchElementException.class)
    public void execute_without_register_test() throws NoPlanFoundException {
        Path pddlDomainfile = Paths.get("src/test/resources/PDDL/Simple/simpleDomain.pddl");
        Path pddlProblemfile = Paths.get("src/test/resources/PDDL/Simple/simpleProblem.pddl");
        Executor executor = new Executor(pddlDomainfile.toString(), pddlProblemfile.toString());

        TestHelper.TestAction action = new TestHelper.TestAction();

        List<String> goals = new ArrayList<>();
        goals.add("at room_1_1");
        boolean res = executor.execute(goals);
    }
}
