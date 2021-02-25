package Actions;

import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.interpreter.Model;
import at.tugraz.ist.compiler.rule.RuleAction;

public class action implements RuleAction {

    @Override
    public boolean execute(Model model) {
        System.out.println("Hello from action.");
        return true;
    }

    @Override
    public void repair(Memory model) {
    }
}
