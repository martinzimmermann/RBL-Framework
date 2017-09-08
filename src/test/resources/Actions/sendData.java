import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.rule.RuleAction;

public class sendData implements RuleAction {

    @Override
    public boolean execute(Memory model) {
        return true;
    }

    @Override
    public void repair(Memory model) {

    }
}
