package at.tugraz.ist.compiler.rule;

import at.tugraz.ist.compiler.interpreter.Memory;

public interface RuleAction {
    void execute(Memory model) throws ActionFailedException;

    void repair(Memory model);
}
