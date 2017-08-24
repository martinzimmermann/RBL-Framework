package at.tugraz.ist.compiler.rule;

import java.util.ArrayList;
import java.util.List;

public class RuleBuilder {
    private String action;
    private double ruleGoal = 1;
    private AlphaList alphaEntries = AlphaList.getDefaultAlphaList();
    private List<Atom> worldDeletions = new ArrayList<>();
    private String goal = null;
    private String worldAddition = null;
    private List<Atom> preconditions = new ArrayList<>();

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

    public RuleBuilder setWorldDeletions(List<Atom> worldDeletions) {
        this.worldDeletions = worldDeletions;
        return this;
    }

    public RuleBuilder setGoal(String goal) {
        this.goal = goal;
        return this;
    }

    public RuleBuilder setWorldAddition(String worldAddition) {
        this.worldAddition = worldAddition;
        return this;
    }

    public RuleBuilder setPreconditions(List<Atom> preconditions) {
        this.preconditions = preconditions;
        return this;
    }

    public Rule createRule() {
        return new Rule(action, ruleGoal, alphaEntries, worldDeletions, goal, worldAddition, preconditions);
    }
}