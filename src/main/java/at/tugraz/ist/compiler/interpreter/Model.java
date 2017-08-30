package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Rule;

import java.util.List;

public class Model {

    private final Memory memory;
    private final List<Rule> rules;

    public Model(Memory memory, List<Rule> rules) {
        this.memory = memory;
        this.rules = rules;
    }

    public Memory getMemory() {
        return memory;
    }

    public List<Rule> getRules() {
        return rules;
    }
}
