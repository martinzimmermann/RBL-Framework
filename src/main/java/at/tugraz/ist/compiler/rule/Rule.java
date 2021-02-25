package at.tugraz.ist.compiler.rule;

import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.interpreter.Model;

import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedSet;

public class Rule extends Atom implements Comparable<Rule> {

    private final SortedSet<Predicate> preconditions;
    private final SortedSet<Predicate> postConditions;
    private final String actionName;
    private final Map<String, String> parameters;

    private double pass_not_executed = 0;
    private double fail_not_executed = 0;
    private double pass_executed = 0;
    private double fail_executed = 0;

    public Rule(String action, SortedSet<Predicate> postConditions, SortedSet<Predicate> preconditions, Map<String, String> parameters) {
        this.postConditions = postConditions;
        this.preconditions = preconditions;
        this.parameters = parameters;
        this.actionName = action;
    }

    public Rule(Rule rule) {
        preconditions = rule.preconditions;
        postConditions = rule.postConditions;
        parameters = rule.parameters;
        actionName = rule.actionName;
    }

    public SortedSet<Predicate> getPreconditions() {
        return preconditions;
    }

    public SortedSet<Predicate> getPostConditions() {
        return postConditions;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public String getAction() {
        return actionName;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        string.append(actionName + " ");
        for(Map.Entry<String, String> entry : getParameters().entrySet()) {
            string.append(entry.getKey());
            string.append(" - ");
            string.append(entry.getValue());
            string.append(", ");
        }

        if (getParameters().size() != 0) {
            string.deleteCharAt(string.length() - 1);
            string.deleteCharAt(string.length() - 1);
        }

        string.append(": ");
        for(Predicate precondition : getPreconditions()) {
            string.append(precondition.toString()).append(", ");
        }

        if (getPreconditions().size() != 0) {
            string.deleteCharAt(string.length() - 1);
            string.deleteCharAt(string.length() - 1);
        }

        string.append(" -> ");

        for(Predicate postCond : this.getPostConditions()) {
            string.append(postCond.toString()).append(", ");
        }

        if (getPostConditions().size() != 0) {
            string.deleteCharAt(string.length() - 1);
            string.deleteCharAt(string.length() - 1);
        }


        return string.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule rule = (Rule) o;

        if (!preconditions.equals(rule.preconditions)) return false;
        if (!postConditions.equals(rule.postConditions)) return false;
        return actionName.equals(rule.actionName);
    }

    @Override
    public int compareTo(Rule o) {

        BigDecimal otherWeight = o.getWeight();
        BigDecimal thisWeight = this.getWeight();

        return thisWeight.compareTo(otherWeight);
    }

    public BigDecimal getWeight() {
        double fe = fail_executed;
        double fn = fail_not_executed;
        double pe = pass_executed;
        double pn = pass_not_executed;

        // Ochiai
        //return new BigDecimal((1.0 * pe) / (Math.sqrt((fe + fn) * (fe + pe))));

        //Tarantula
        //return new BigDecimal(((1.0 * fe) / 1.0 *(fe + fn)) /
        //        ((1.0 * fe / 1.0 *(fe + fn)) + (1.0 * pe / 1.0 *(pe + pn))));

        // Jaccard
        if (fe == 0)
            return new BigDecimal(0.00001);
        return new BigDecimal((1.0 * fe) / (fe + fn + pe));

        // SMC
        //if(fe + pn == 0)
        //    return new BigDecimal(0.00001);
        //return new BigDecimal((1.0 * (fe + pn)) / 1.0 * (fe + fn + pe + pn));
    }

    public void updateRule(boolean failed, boolean executed, boolean last) {
        if (failed && executed)
            fail_executed += 1;
        else
            fail_executed += 0;

        if (failed && !executed)
            fail_not_executed += 1;
        else
            fail_not_executed += 0;

        if (!failed && executed)
            pass_executed += 1;
        else
            pass_executed += 0;

        if (!failed && !executed)
            pass_not_executed += 1;
        else
            pass_not_executed += 0;
    }

    public boolean execute(Model model, RuleAction action) {
        return action.execute(model, this.getParameters());
    }

    public void repairMemory(Memory memory, RuleAction action) {
        action.repair(memory, this.getParameters());
    }
}
