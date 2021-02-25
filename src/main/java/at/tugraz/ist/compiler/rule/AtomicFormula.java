package at.tugraz.ist.compiler.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AtomicFormula implements Comparable<AtomicFormula> {
    private final Predicate predicate;
    private final List<String> variables;

    public AtomicFormula(Predicate predicate, List<String> variables) {
        this.predicate = predicate;
        this.variables = new ArrayList<>(variables);
    }

    public Predicate getPredicate() {
        return predicate;
    }

    public List<String> getVariables() {
        return variables;
    }

    public Predicate toGroundedPredicate(HashMap<String, String> parameters) {
        StringBuilder builder = new StringBuilder();
        builder.append(predicate.getIdentifier());
        for(String var : variables) {
            builder.append(" ");
            builder.append(parameters.get(var));
        }
        return new Predicate(builder.toString(), predicate.isDeletion());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(predicate.getIdentifier());
        for(String var : variables) {
            builder.append(" ");
            builder.append(var);
        }
        return (predicate.isDeletion() ? "-" : "+") + builder.toString();
    }

    @Override
    public int compareTo(AtomicFormula o) {
        return this.toString().compareTo(o.toString());
    }

    public boolean canBeGrounded(HashMap<String, String> parameters) {
        for(String var : variables) {
            if (parameters.get(var) == null)
                return false;
        }
        return true;
    }
}
