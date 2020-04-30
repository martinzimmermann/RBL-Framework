package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.interpreter.Model;
import at.tugraz.ist.compiler.rule.RuleAction;

import java.util.Map;

public class goal implements RuleAction {
    @Override
    public boolean execute(Model model, Map<String, String> parameters) {
        return true;
    }

    @Override
    public void repair(Memory memory, Map<String, String> parameters) {
    }
}
