package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Memory {

    private final Set<Predicate> start_predicates;
    private Set<Predicate> predicates;

    public Memory(List<Predicate> predicates) {

        this.predicates = new HashSet<>(predicates);
        this.start_predicates = new HashSet<>(this.predicates);
    }

    public Memory(Memory memory) {
        this.predicates = new HashSet<>(memory.predicates);
        this.start_predicates = new HashSet<>(memory.start_predicates);
    }

    public boolean contains(Predicate precondition) {
        return predicates.contains(precondition);
    }

    public boolean containsAll(List<Predicate> preconditions) {
        return predicates.containsAll(preconditions);
    }

    public Set<Predicate> getAllPredicates() {
        return predicates;
    }

    public void update(Rule rule) {
        for(Predicate pred : rule.getPostConditions()) {
            if(pred.isAddition())
                predicates.add(pred);
            else
                predicates.remove(pred);
        }
    }

    public void reset() {
        this.predicates = new HashSet<>(start_predicates);
    }

    public void remove(String predicate) {
        predicates.remove(new Predicate(predicate));
    }
    public void add(String predicate) {
        predicates.add(new Predicate(predicate));
    }

    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof Memory) {
            return  predicates.equals(((Memory) anObject).predicates);
        }
        return false;
    }
}
