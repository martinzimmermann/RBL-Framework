package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;

import java.util.*;
import java.util.stream.Collectors;

public class Memory {

    private SortedSet<Predicate> predicates;

    public Memory(SortedSet<Predicate> predicates) {
        this.predicates = new TreeSet<>(predicates);
    }

    public Memory(Memory memory) {
        this.predicates = new TreeSet<>(memory.predicates);
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
            Iterator thisIt = predicates.iterator();
            Iterator otherIt = ((Memory) anObject).predicates.iterator();
            for(int i = 0; i < predicates.size(); i++) {
                if(!(thisIt.hasNext() && otherIt.hasNext()))
                    return false;
                if(!thisIt.next().equals(otherIt.next()))
                    return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Memory{" + predicates.stream().map(p -> p.toString()).reduce("", (s1, s2) -> s1 + ", " + s2) + "}";
    }

    @Override
    public int hashCode() {
        return predicates.hashCode();
    }
}
