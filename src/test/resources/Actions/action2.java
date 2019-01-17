package Actions;

import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.rule.InterpreterRule;
import at.tugraz.ist.compiler.rule.RuleAction;

public class action2 implements RuleAction {

    @Override
    public boolean execute(Memory model, InterpreterRule interpreterRule) {
        System.out.println("Hello from action2.");
        counter.hello();
        return true;
    }

    @Override
    public void repair(Memory model) {
    }
}
