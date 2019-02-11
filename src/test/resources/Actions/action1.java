package Actions;

import at.tugraz.ist.compiler.interpreter.Model;
import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.rule.RuleAction;

public class action1 implements RuleAction {

    @Override
    public boolean execute(Model model) {
        System.out.println("Hello from action1.");
        counter.hello();
        return true;
    }

    @Override
    public void repair(Memory model) {
    }
}
