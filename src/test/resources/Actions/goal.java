import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.rule.ActionFailedException;
import at.tugraz.ist.compiler.rule.RuleAction;

public class goal implements RuleAction {

    @Override
    public void execute(Memory model) throws ActionFailedException {
        System.out.println("Goal reached :D.");
    }

    @Override
    public void repair(Memory model) {
    }
}
