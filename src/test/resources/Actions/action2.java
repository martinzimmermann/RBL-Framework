package Actions;

import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.rule.ActionFailedException;
import at.tugraz.ist.compiler.rule.RuleAction;

public class action2 implements RuleAction {

    @Override
    public void execute(Memory model) throws ActionFailedException {
        System.out.println("Hello from action2.");
        counter.hello();
    }

    @Override
    public void repair(Memory model) {
    }
}