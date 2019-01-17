package at.tugraz.ist.compiler.rule;

import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.interpreter.Model;

public interface RuleAction {
    boolean execute(Model model);
    void repair(Memory model);
}
