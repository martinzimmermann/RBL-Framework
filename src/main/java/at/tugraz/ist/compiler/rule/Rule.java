package at.tugraz.ist.compiler.rule;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Rule extends Atom implements Comparable<Rule> {


    private final List<Predicate> preconditions;
    private final List<Predicate> postConditions;
    private final String goal;
    private final String actionName;
    private final Map<String, String> parameters;

    private Queue<Integer> pass_not_executed = new LinkedList<>();
    private Queue<Integer> fail_not_executed = new LinkedList<>();
    private Queue<Integer> pass_executed = new LinkedList<>();
    private Queue<Integer> fail_executed = new LinkedList<>();

    private DiagnosticPosition diagnosticPosition;

    public Rule(String action, String goal, List<Predicate> postConditions, List<Predicate> preconditions, Map<String, String> parameters, DiagnosticPosition diagnosticPosition) {
        if (action == null)
            throw new IllegalArgumentException("action can not be null");

        if (postConditions == null)
            throw new IllegalArgumentException("postConditions can not be null");

        if (preconditions == null)
            throw new IllegalArgumentException("preconditions can not be null");

        if (parameters == null)
            throw new IllegalArgumentException("preconditions can not be null");

        this.postConditions = postConditions;
        this.goal = goal;
        this.preconditions = preconditions;
        this.parameters = parameters;
        this.actionName = action;
        this.diagnosticPosition = diagnosticPosition;
    }

    public Rule(Rule rule) {
        preconditions = rule.preconditions;
        postConditions = rule.postConditions;
        parameters = rule.parameters;
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

    public Map<String, String> getParameters() {
        return parameters;
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

    @Override
    public int compareTo(Rule o) {

        BigDecimal otherWeight = o.getWeight();
        BigDecimal thisWeight = this.getWeight();

        return thisWeight.compareTo(otherWeight);
    }

    public BigDecimal getWeight() {
        int fe = fail_executed.isEmpty() ? 0 : fail_executed.stream().reduce((a,b)->a+b).get();
        int fn = fail_not_executed.isEmpty() ? 0 : fail_not_executed.stream().reduce((a,b)->a+b).get();
        int pe = pass_executed.isEmpty() ? 0 : pass_executed.stream().reduce((a,b)->a+b).get();
        int pn = pass_not_executed.isEmpty() ? 0 : pass_not_executed.stream().reduce((a,b)->a+b).get();

        try {
            // Ochiai
            //return new BigDecimal((1.0 * pe) / (Math.sqrt((fe + fn) * (fe + pe))));

            //Tarantula
            //return new BigDecimal(((1.0 * fe) / 1.0 *(fe + fn)) /
            //        ((1.0 * fe / 1.0 *(fe + fn)) + (1.0 * pe / 1.0 *(pe + pn))));

            // Jaccard
            return new BigDecimal((1.0 * fe) / (fe + fn + pe));

            // SMC
            //return new BigDecimal((1.0 * (fe + pn)) / 1.0 * (fe + fn + pe + pn));
        }
        catch (NumberFormatException e) {
            return new BigDecimal(0.00000000001);
        }
    }

    public void updateRule(boolean failed, boolean executed) {
        if(failed  && executed)
            fail_executed.add(1);
        else
            fail_executed.add(0);

        if(failed  && !executed)
            fail_not_executed.add(1);
        else
            fail_not_executed.add(0);

        if(!failed  && executed)
            pass_executed.add(1);
        else
            pass_executed.add(0);

        if(!failed  && !executed)
            pass_not_executed.add(1);
        else
            pass_not_executed.add(0);

        /*if(fail_executed.size() > 20) {
            fail_executed.remove();
            fail_not_executed.remove();
            pass_executed.remove();
            pass_not_executed.remove();
        }*/
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
        builder.append("Map.ofEntries(" + parameters.entrySet().stream().map( e -> "entry(\"" + e.getKey() + "\", \""+ e.getValue() + "\")").collect(Collectors.joining(", ")) + "), ");
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
