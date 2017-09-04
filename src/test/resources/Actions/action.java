import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.rule.ActionFailedException;
import at.tugraz.ist.compiler.rule.RuleAction;


public class action implements RuleAction {
    static int counter = 0;

    @Override
    public void execute(Memory model) throws ActionFailedException {
        System.out.println("Hello from action. Counter: " + counter++);
    }

    @Override
    public void repair(Memory model) {
    }
}
