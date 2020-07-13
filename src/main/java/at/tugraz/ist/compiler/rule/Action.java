package at.tugraz.ist.compiler.rule;

import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class Action {
    private final String name;
    private final HashMap<String, String> parameters;
    private final SortedSet<AtomicFormula> preconditions;
    private final HashMap<AtomicFormula, Boolean> atomicFormulaFilled;
    private final SortedSet<AtomicFormula> effects;

    public Action(String name, SortedSet<String> parameters, SortedSet<AtomicFormula> preconditions, SortedSet<AtomicFormula> effects) {
        this.name = name;
        this.parameters = new HashMap<>();
        this.atomicFormulaFilled = new HashMap<>();
        for(String v : parameters) {
            this.parameters.put(v, null);
        }
        this.preconditions = preconditions;
        this.effects = effects;
    }

    public Action(Action other) {
        this.name = other.name;
        this.parameters = new HashMap<>(other.parameters);
        this.atomicFormulaFilled = new HashMap<>(other.atomicFormulaFilled);
        this.preconditions = other.preconditions;
        this.effects = other.effects;
    }

    public boolean preconditionsFulfilled() {
        for(AtomicFormula precondition : preconditions) {
            if (!atomicFormulaFilled.containsKey(precondition))
                return false;
        }
        return true;
    }

    public AtomicFormula canBeConsumedBy(Predicate pred) {

        for(AtomicFormula a : preconditions) {
            if (filled(a))
                continue;
            String[] split = pred.getExpressionSplit();
            if (a.getPredicate().getIdentifier().equals(split[0])) {
                for(int i = 1; i < split.length; i++) {
                    String variableName = a.getVariables().get(i - 1);
                    if (!(parameters.get(variableName) == null ||
                            parameters.get(variableName).equals(split[i]))) {
                        return null;
                    }
                }
                return a;
            }
        }
        return null;
    }

    private boolean filled(AtomicFormula a) {
        return atomicFormulaFilled.containsKey(a);
    }

    public void consume(AtomicFormula a, Predicate pred) {
        String[] split = pred.getExpressionSplit();
        for(int i = 1; i < split.length; i++) {
            String variableName = a.getVariables().get(i - 1);
            if (parameters.get(variableName) == null) {
                parameters.put(variableName, split[i]);
            }
        }
        atomicFormulaFilled.put(a, true);
    }

    public Rule createRule() {
        assert (preconditionsFulfilled());

        SortedSet<Predicate> groundedPreconditions = new TreeSet<>();
        for(AtomicFormula a : preconditions) {
            groundedPreconditions.add(a.toGroundedPredicate(parameters));
        }
        SortedSet<Predicate> groundedEffects = new TreeSet<>();
        for(AtomicFormula a : effects) {
            groundedEffects.add(a.toGroundedPredicate(parameters));
        }
        return new Rule(name, groundedEffects, groundedPreconditions, parameters);
    }
}
