package at.tugraz.ist.compiler.rule;

import java.util.ArrayList;
import java.util.List;

public class RuleBuilder {
    private String action;
    private DiagnosticPosition diagnosticPosition;
    private double ruleGoal = 1;
    private AlphaList alphaEntries = AlphaList.getDefaultAlphaList();
    private List<Predicate> worldDeletions = new ArrayList<>();
    private String goal = null;
    private Predicate worldAddition = null;
    private List<Predicate> preconditions = new ArrayList<>();
    private double damping = 0.1;
    private double aging = 0;
    private double maxAging = 0;
    private boolean agingUpperBound = false;
    private boolean agingLowerBound = false;

    public RuleBuilder setDiagnosticPosition(DiagnosticPosition diagnosticPosition) {
        this.diagnosticPosition = diagnosticPosition;
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

    public RuleBuilder setDamping(double damping) {
        this.damping = damping;
        return this;
    }

    public RuleBuilder setAging(double aging) {
        this.aging = aging;
        return this;
    }

    public RuleBuilder setMaxAging(double maxAging) {
        this.maxAging = maxAging;
        return this;
    }

    public RuleBuilder setAgingUpperBound(boolean agingUpperBound) {
        this.agingUpperBound = agingUpperBound;
        return this;
    }

    public RuleBuilder setAgingLowerBound(boolean agingLowerBound) {
        this.agingLowerBound = agingLowerBound;
        return this;
    }

    public Rule createRule(){
            return new Rule(action, ruleGoal, alphaEntries, worldDeletions, goal, worldAddition, preconditions, damping, aging, maxAging, agingUpperBound, agingLowerBound, diagnosticPosition);
    }
}