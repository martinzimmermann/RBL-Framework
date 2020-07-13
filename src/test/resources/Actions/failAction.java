package Actions;

import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.interpreter.Model;
import at.tugraz.ist.compiler.rule.RuleAction;

public class failAction implements RuleAction {

    @Override
    public boolean execute(Model model) {
        System.out.println("Hello from failAction.");
        return false;
    }

    @Override
    public void repair(Memory model) {
    }
}
