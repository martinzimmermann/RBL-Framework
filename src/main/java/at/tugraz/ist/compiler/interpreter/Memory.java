package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Predicate;
import java.util.List;

public class Memory {

    private final List<Predicate> predicates;

    public Memory(List<Predicate> predicates)
    {
        this.predicates = predicates;
    }

    public boolean contains(Predicate precondition) {
        return predicates.contains(precondition);
    }

    public boolean containsAll(List<Predicate> preconditions) {
        return predicates.containsAll(preconditions);
    }

    public List<Predicate> getAllPredicates()
    {
        return predicates;
    }
}
