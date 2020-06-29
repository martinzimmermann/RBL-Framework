package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.helper.TestHelper;
import at.tugraz.ist.compiler.rule.RuleAction;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.*;
import static junit.framework.TestCase.assertEquals;

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

}
