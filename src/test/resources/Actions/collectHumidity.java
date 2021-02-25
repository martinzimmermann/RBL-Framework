import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.interpreter.Model;
import at.tugraz.ist.compiler.rule.RuleAction;

public class collectHumidity implements RuleAction {

    @Override
    public boolean execute(Model model) {
        return true;
    }

    @Override
    public void repair(Memory model) {

    }
}
