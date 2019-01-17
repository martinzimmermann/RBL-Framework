import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.rule.InterpreterRule;
import at.tugraz.ist.compiler.rule.RuleAction;

public class collectTemperature implements RuleAction {

    @Override
    public boolean execute(Memory model, InterpreterRule interpreterRule) {
        return true;
    }

    @Override
    public void repair(Memory model) {

    }
}
