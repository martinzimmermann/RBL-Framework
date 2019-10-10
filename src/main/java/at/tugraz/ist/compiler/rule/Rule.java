package at.tugraz.ist.compiler.rule;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Rule extends Atom{

    private final List<Predicate> preconditions;
    private final List<Predicate> postConditions;
    private final String goal;
    private final String actionName;

    private int pass_not_executed = 0;
    private int fail_not_executed = 0;
    private int pass_executed = 0;
    private int fail_executed = 0;

    private DiagnosticPosition diagnosticPosition;

    public Rule(String action, String goal, List<Predicate> postConditions, List<Predicate> preconditions, DiagnosticPosition diagnosticPosition) {
        if (action == null)
            throw new IllegalArgumentException("action can not be null");

        if (postConditions == null)
            throw new IllegalArgumentException("postConditions can not be null");

        if (preconditions == null)
            throw new IllegalArgumentException("preconditions can not be null");

        this.postConditions = postConditions;
        this.goal = goal;
        this.preconditions = preconditions;
        this.actionName = action;
        this.diagnosticPosition = diagnosticPosition;
    }

    public Rule(Rule rule) {
        preconditions = rule.preconditions;
        postConditions = rule.postConditions;
        goal = rule.goal;
        actionName = rule.actionName;
        diagnosticPosition = rule.diagnosticPosition;
    }

    public List<Predicate> getPreconditions() {
        return preconditions;
    }

    public List<Predicate> getPostConditions() {
        return postConditions;
    }

    public List<Predicate> getWorldAdditions() {
        return postConditions.stream().filter(p -> p.isAddition()).collect(Collectors.toList());
    }

    public String getGoal() {
        return goal;
    }

    public boolean hasGoal() {
        return goal != null;
    }

    public List<Predicate> getWorldDeletions() {
        return postConditions.stream().filter(p -> p.isDeletion()).collect(Collectors.toList());
    }

    public String getAction() {
        return actionName;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (Predicate precondition : getPreconditions()) {
            string.append(precondition.getName()).append(",");
        }

        if (getPreconditions().size() != 0)
            string.deleteCharAt(string.length() - 1);

        string.append(" -> ");

        if (hasGoal())
            string.append("#").append(getGoal()).append(" ");

        for (Predicate deletion : this.getPostConditions()) {
            string.append(deletion.toString()).append(" ");
        }

        if (getWorldDeletions().size() != 0)
            string.deleteCharAt(string.length() - 1);

        string.append((string.charAt(string.length()-1) == ' ' ? "" : " ") + actionName);

        string.append(".");

        return string.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule rule = (Rule) o;

        if (!preconditions.equals(rule.preconditions)) return false;
        if (!postConditions.equals(rule.postConditions)) return false;
        if (!Objects.equals(goal, rule.goal)) return false;
        return actionName.equals(rule.actionName);
    }

    /*
    @Override
    public int hashCode() {
        int result;
        long temp = 0;
        result = preconditions.hashCode();
        result = 31 * result + postConditions.hashCode();
        result = 31 * result + (goal != null ? goal.hashCode() : 0);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + actionName.hashCode();
        return result;
    }
    */

    public BigDecimal getWeight() {
        if ( fail_executed == 0 || (fail_executed + fail_not_executed) == 0 ||(fail_executed + pass_executed) == 0)
            return new BigDecimal(0.00000000001);
        return new BigDecimal((1.0 * fail_executed) / (Math.sqrt((fail_executed + fail_not_executed) * (fail_executed + pass_executed))));
        //return new BigDecimal(((1.0 * fail_executed) / (fail_executed + fail_not_executed)) /
        //        ((1.0 * fail_executed / (fail_executed + fail_not_executed)) + (1.0 * pass_executed / (pass_executed + pass_not_executed))));
        //return new BigDecimal((1.0 * fail_executed) / (fail_executed + fail_not_executed + pass_executed));
    }

    public void updateRule(boolean failed, boolean executed) {
        if(failed  && executed)
            fail_executed++;
        if(failed  && !executed)
            fail_not_executed++;
        if(!failed  && executed)
            pass_executed++;
        if(!failed  && !executed)
            pass_not_executed++;
    }

    public String getConstructor() {
        StringBuilder builder = new StringBuilder();
        builder.append("new Rule(");
        builder.append("\"" + actionName + "\", ");
        builder.append((goal == null ? "null" : "\"" + goal + "\"") + ", ");
        String params = postConditions.stream().map(p -> p.getConstructor()).collect(Collectors.joining(", "));
        builder.append("new ArrayList<Predicate>(Arrays.asList(new Predicate[]{" + params + "})), ");
        params = preconditions.stream().map(p -> p.getConstructor()).collect(Collectors.joining(", "));
        builder.append("new ArrayList<Predicate>(Arrays.asList(new Predicate[]{" + params + "})), ");
        builder.append("new DiagnosticPosition(" + diagnosticPosition.getConstructorParameters() + "))");
        return builder.toString();
    }

    public DiagnosticPosition getDiagnosticPosition() {
        return diagnosticPosition;
    }

    public String getActionConstructor() {
        return "new " + actionName + "()";
    }

}
