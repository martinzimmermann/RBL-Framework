public interface RuleAction {
    void execute(Memory model) throws ActionFailedException;
    void repair(Memory model);
}
