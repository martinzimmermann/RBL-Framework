package at.tugraz.ist.compiler.rule;

import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.interpreter.Model;

import java.util.Map;

public interface RuleAction {
    boolean execute(Model model, Map<String, String> parameters);

    void repair(Memory model, Map<String, String> parameters);
}
