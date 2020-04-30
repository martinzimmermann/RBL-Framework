package at.tugraz.ist.compiler.rule;

import java.util.ArrayList;
import java.util.List;

public class RuleBuilder {
    private String action;
    private DiagnosticPosition diagnosticPosition;
    private String goal = null;
    private List<Predicate> postConditions = new ArrayList<>();
    private List<Predicate> preconditions = new ArrayList<>();

    public RuleBuilder setDiagnosticPosition(DiagnosticPosition diagnosticPosition) {
        this.diagnosticPosition = diagnosticPosition;
        return this;
    }

    public RuleBuilder setAction(String action) {
        this.action = action;
        return this;
    }

    public RuleBuilder setPostConditions(List<Predicate> postConditions) {
        this.postConditions = postConditions;
        return this;
    }

    public RuleBuilder setGoal(String goal) {
        this.goal = goal;
        return this;
    }

    public RuleBuilder setPreconditions(List<Predicate> preconditions) {
        this.preconditions = preconditions;
        return this;
    }

    //public Rule createRule(){
    //        return new Rule(action, goal, postConditions, preconditions, diagnosticPosition);
    //}
}