package at.tugraz.ist.compiler.rule;

import java.util.ArrayList;
import java.util.List;

public class RuleBuilder {
    private String action;
    private boolean interpret;
    private double ruleGoal = 1;
    private AlphaList alphaEntries = AlphaList.getDefaultAlphaList();
    private List<Predicate> worldDeletions = new ArrayList<>();
    private String goal = null;
    private Predicate worldAddition = null;
    private List<Predicate> preconditions = new ArrayList<>();

    public RuleBuilder setInterpret(boolean interpret) {
        this.interpret = interpret;
        return this;
    }

    public RuleBuilder setAction(String action) {
        this.action = action;
        return this;
    }

    public RuleBuilder setRuleGoal(double ruleGoal) {
        this.ruleGoal = ruleGoal;
        return this;
    }

    public RuleBuilder setAlphaList(AlphaList alphaEntries) {
        this.alphaEntries = alphaEntries;
        return this;
    }

    public RuleBuilder setWorldDeletions(List<Predicate> worldDeletions) {
        this.worldDeletions = worldDeletions;
        return this;
    }

    public RuleBuilder setGoal(String goal) {
        this.goal = goal;
        return this;
    }

    public RuleBuilder setWorldAddition(Predicate worldAddition) {
        this.worldAddition = worldAddition;
        return this;
    }

    public RuleBuilder setPreconditions(List<Predicate> preconditions) {
        this.preconditions = preconditions;
        return this;
    }

    public Rule createRule() throws ClassNotFoundException {
        if(interpret)
            return new InterpreterRule(action, ruleGoal, alphaEntries, worldDeletions, goal, worldAddition, preconditions);
        else
            return new Rule(action, ruleGoal, alphaEntries, worldDeletions, goal, worldAddition, preconditions);

    }
}