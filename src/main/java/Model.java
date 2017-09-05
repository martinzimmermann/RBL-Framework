import java.util.List;

class Model {

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
