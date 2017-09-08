package at.tugraz.ist.compiler.rule;

import at.tugraz.ist.compiler.interpreter.Memory;

public interface RuleAction {
    boolean execute(Memory model);
    void repair(Memory model);
}
