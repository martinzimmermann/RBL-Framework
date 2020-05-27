package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;

import java.util.*;
import java.util.stream.Collectors;

public class Memory {

    private final SortedSet<Predicate> start_predicates;
    private SortedSet<Predicate> predicates;

    public Memory(List<Predicate> predicates) {

        this.predicates = new TreeSet<>(predicates);
        this.start_predicates = new TreeSet<>(this.predicates);
    }

    public Memory(Memory memory) {
        this.predicates = new TreeSet<>(memory.predicates);
        this.start_predicates = new TreeSet<>(memory.start_predicates);
    }

    public boolean contains(Predicate precondition) {
        return predicates.contains(precondition);
    }

    public boolean containsAll(List<Predicate> preconditions) {
        return predicates.containsAll(preconditions);
    }

    public SortedSet<Predicate> getAllPredicates() {
        return predicates;
    }

    public void update(Rule rule) {
        for(Predicate pred : rule.getPostConditions()) {
            if(pred.isAddition())
                predicates.add(pred);
            else
                predicates.remove(new Predicate(pred.getName()));
        }
    }

    public void reset() {
        this.predicates = new TreeSet<>(start_predicates);
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

    @Override
    public String toString() {
        return "Memory{" + predicates.stream().map(p -> p.toString()).reduce("", (s1, s2) -> s1 + ", " + s2) + "}";
    }
}
