import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class InterpreterRule extends Rule {
    private final RuleAction action;

    public InterpreterRule(Rule rule) throws ClassNotFoundException {
        super(rule);
        try {
            Class actionClass = Class.forName(getAction());
            Constructor constructor = actionClass.getConstructor();
            this.action = (RuleAction) constructor.newInstance(new Object[0]);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new ClassNotFoundException();
        }
    }

    public InterpreterRule(RuleAction action, Rule rule) throws ClassNotFoundException {
        super(rule);
        this.action = action;
    }

    public boolean execute(Memory memory) {
        assert (memory.containsAll(this.getPreconditions()));
        return action.execute(memory);
    }

    public void repairMemory(Memory memory) {
        action.repair(memory);
    }
}
