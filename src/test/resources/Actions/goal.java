import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.rule.RuleAction;

public class goal implements RuleAction {

    @Override
    public boolean execute(Memory model) {
        System.out.println("Goal reached :D.");
        return true;
    }

    @Override
    public void repair(Memory model) {
    }
}
