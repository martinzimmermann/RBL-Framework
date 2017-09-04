package at.tugraz.ist.compiler.rule;

import at.tugraz.ist.compiler.Setting;
import at.tugraz.ist.compiler.interpreter.ExecutionFailedException;
import at.tugraz.ist.compiler.interpreter.Memory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class InterpreterRule extends Rule {
    private final RuleAction action;

    public InterpreterRule(String action, double ruleGoal, AlphaList alphaEntries, List<Predicate> worldDeletions, String goal, Predicate worldAddition, List<Predicate> preconditions, Setting setting) throws ClassNotFoundException {
        super(action, ruleGoal, alphaEntries, worldDeletions, goal, worldAddition, preconditions, setting);
        try {
            Class actionClass = Class.forName(getAction());
            Constructor constructor = actionClass.getConstructor(new Class[0]);
            this.action = (RuleAction) constructor.newInstance(new Object[0]);
        }
        catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e)
        {
            throw new ClassNotFoundException();
        }
    }

    @Override
    public void execute(Memory memory) throws ExecutionFailedException {
        if (!memory.containsAll(this.getPreconditions()))
            throw new PreConditionsNotMetException();

        action.execute(memory);
    }

    @Override
    public void repairMemory(Memory memory)
    {
        action.repair(memory);
    }
}
