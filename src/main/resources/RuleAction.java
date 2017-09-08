public interface RuleAction {
    boolean execute(Memory model);
    void repair(Memory model);
}
