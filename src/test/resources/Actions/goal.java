import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.interpreter.Model;
import at.tugraz.ist.compiler.rule.RuleAction;

public class goal implements RuleAction {

    @Override
    public boolean execute(Model model) {
        System.out.println("Goal reached :D.");
        return true;
    }

    @Override
    public void repair(Memory model) {
    }
}
