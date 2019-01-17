package Actions;

import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.rule.InterpreterRule;
import at.tugraz.ist.compiler.rule.RuleAction;

public class action implements RuleAction {

    @Override
    public boolean execute(Memory model, InterpreterRule interpreterRule) {
        System.out.println("Hello from action.");
        return true;
    }

    @Override
    public void repair(Memory model) {
    }
}
