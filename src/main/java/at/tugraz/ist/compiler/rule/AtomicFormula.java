package at.tugraz.ist.compiler.rule;

import java.util.HashMap;
import java.util.List;

public class AtomicFormula {
    private final Predicate predicate;
    private final List<String> variables;

    public AtomicFormula(Predicate predicate, List<String> variables) {
        this.predicate = predicate;
        this.variables = variables;
    }

    public Predicate getPredicate() {
        return predicate;
    }

    public List<String> getVariables() {
        return variables;
    }

    public Predicate toGroundedPredicate(HashMap<String, String> parameters) {
        StringBuilder builder = new StringBuilder();
        builder.append(predicate.getName());
        for(String var : variables) {
            builder.append(" ");
            builder.append(parameters.get(var));
        }
        return new Predicate(builder.toString(), predicate.isDeletion());
    }
}
