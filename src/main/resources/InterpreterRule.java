import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

class InterpreterRule extends Rule {
    private final RuleAction action;

    public InterpreterRule(String action, double ruleGoal, AlphaList alphaEntries, List<Predicate> worldDeletions, String goal, Predicate worldAddition, List<Predicate> preconditions) throws ClassNotFoundException {
        super(action, ruleGoal, alphaEntries, worldDeletions, goal, worldAddition, preconditions);
        try {
            Class actionClass = Class.forName(getAction());
            Constructor constructor = actionClass.getConstructor();
            this.action = (RuleAction) constructor.newInstance(new Object[0]);
        }
        catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e)
        {
            throw new ClassNotFoundException();
        }
    }

    public void execute(Memory memory) throws ActionFailedException {
        assert (memory.containsAll(this.getPreconditions()));

        action.execute(memory);
    }

    public void repairMemory(Memory memory)
    {
        action.repair(memory);
    }
}
