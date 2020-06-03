package at.tugraz.ist.compiler.rule;

import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.interpreter.Model;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Rule extends Atom implements Comparable<Rule> {

    private final SortedSet<Predicate> preconditions;
    private final SortedSet<Predicate> postConditions;
    private final String actionName;
    private final Map<String, String> parameters;

    //private Queue<Integer> pass_not_executed = new LinkedList<>();
    //private Queue<Integer> fail_not_executed = new LinkedList<>();
    //private Queue<Integer> pass_executed = new LinkedList<>();
    //private Queue<Integer> fail_executed = new LinkedList<>();
    private double pass_not_executed = 0;
    private double fail_not_executed = 0;
    private double pass_executed = 0;
    private double fail_executed = 0;

    public Rule(String action, SortedSet<Predicate> postConditions, SortedSet<Predicate> preconditions, Map<String, String> parameters) {
        if (action == null)
            throw new IllegalArgumentException("action can not be null");

        if (postConditions == null)
            throw new IllegalArgumentException("postConditions can not be null");

        if (preconditions == null)
            throw new IllegalArgumentException("preconditions can not be null");

        if (parameters == null)
            throw new IllegalArgumentException("preconditions can not be null");

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

    public List<Predicate> getWorldAdditions() {
        return postConditions.stream().filter(p -> p.isAddition()).collect(Collectors.toList());
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

        string.append(actionName + " ");
        for (Map.Entry<String,String> entry : getParameters().entrySet()) {
            string.append("?");
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
        for (Predicate precondition : getPreconditions()) {
            string.append(precondition.getName()).append(", ");
        }

        if (getPreconditions().size() != 0) {
            string.deleteCharAt(string.length() - 1);
            string.deleteCharAt(string.length() - 1);
        }

        string.append(" -> ");

        for (Predicate deletion : this.getPostConditions()) {
            string.append(deletion.toString()).append(" ");
        }

        if (getWorldDeletions().size() != 0)
            string.deleteCharAt(string.length() - 1);


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
        double fe = fail_executed; //.isEmpty() ? 0 : fail_executed.stream().reduce((a,b)->a+b).get();
        double fn = fail_not_executed; //.isEmpty() ? 0 : fail_not_executed.stream().reduce((a,b)->a+b).get();
        double pe = pass_executed; //.isEmpty() ? 0 : pass_executed.stream().reduce((a,b)->a+b).get();
        double pn = pass_not_executed; //.isEmpty() ? 0 : pass_not_executed.stream().reduce((a,b)->a+b).get();

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
            return new BigDecimal(0.5);
        }
    }

    public void updateRule(boolean failed, boolean executed, boolean last) {
        if(failed  && executed)
            fail_executed += last ? 1 : 0.9;
            //fail_executed.add(1);
        else
            fail_executed += 0;
            //fail_executed.add(0);

        if(failed  && !executed)
            fail_not_executed += 1;
            //fail_not_executed.add(1);
        else
            fail_not_executed += 0;
            //fail_not_executed.add(0);

        if(!failed  && executed)
            pass_executed += last ? 1 : 0.9;
            //pass_executed.add(1);
        else
            pass_executed += 0;
            //pass_executed.add(0);

        if(!failed  && !executed)
            pass_not_executed += 1;
            //pass_not_executed.add(1);
        else
            pass_not_executed += 0;
            //pass_not_executed.add(0);

        /*if(fail_executed.size() > 20) {
            fail_executed.remove();
            fail_not_executed.remove();
            pass_executed.remove();
            pass_not_executed.remove();
        }*/
    }

    public boolean execute(Model model) {
        //FIXME
        //assert (model.getMemory().containsAll(this.getPreconditions()));
        return true; //action.execute(model, this.getParameters());
    }

    public void repairMemory(Memory memory) {
        return; //action.repair(memory, this.getParameters());
    }
}
