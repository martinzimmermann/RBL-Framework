package at.tugraz.ist.compiler.rule;

import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class Action {
    private final String name;
    private final HashMap<String, String> parameters;
    private final SortedSet<AtomicFormula> preconditions;
    private final SortedSet<AtomicFormula> effects;

    public Action (String name, SortedSet<String> parameters, SortedSet<AtomicFormula> preconditions, SortedSet<AtomicFormula> effects) {
        this.name = name;
        this.parameters = new HashMap<>();
        for (String v : parameters) {
            this.parameters.put(v, null);
        }
        this.preconditions = preconditions;
        this.effects = effects;
    }

    public Action(Action other) {
        this.name = other.name;
        this.parameters = new HashMap<>(other.parameters);
        this.preconditions = other.preconditions;
        this.effects = other.effects;
    }

    public boolean isFullyAssigned() {
        boolean fullyAssigned = true;
        for (String s : parameters.values()){
            fullyAssigned &= s != null;
        }
        return fullyAssigned;
    }

    public boolean canConsume(Predicate pred) {
        boolean canConsume = true;
        String[] split = pred.getName().split(" ");

        for(AtomicFormula a : preconditions) {
            if(a.canBeGrounded(parameters))
                continue;
            else if(a.getPredicate().getName().equals(split[0])) {
                for (int i = 1; i < split.length; i++) {
                    String variableName = a.getVariables().get(i-1);
                    if(parameters.get(variableName) != null &&
                            !parameters.get(variableName).equals(split[i])) {
                        return false;
                    }
                }
                return true;
            }
            else
                return false;
        }

        return false;
    }

    public void consume(Predicate pred) {
        String[] split = pred.getName().split(" ");
        for(AtomicFormula a : preconditions) {
            if(a.getPredicate().getName().equals(split[0])) {
                for (int i = 1; i < split.length; i++) {
                    String variableName = a.getVariables().get(i-1);
                    if(parameters.get(variableName) == null) {
                        parameters.put(variableName, split[i]);
                    }
                }
            }
        }
    }

    public Rule createRule() {
        assert (isFullyAssigned());

        SortedSet<Predicate> groundedPreconditions = new TreeSet<>();
        for (AtomicFormula a : preconditions) {
            groundedPreconditions.add(a.toGroundedPredicate(parameters));
        }
        SortedSet<Predicate> groundedEffects = new TreeSet<>();
        for (AtomicFormula a : effects) {
            groundedEffects.add(a.toGroundedPredicate(parameters));
        }
        return new Rule(name, groundedEffects, groundedPreconditions, parameters);
    }
}
