package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Memory {

    private final SortedSet<Predicate> predicates;

    public Memory(SortedSet<Predicate> predicates) {
        this.predicates = new TreeSet<>(predicates);
    }

    public Memory(Memory memory) {
        this.predicates = new TreeSet<>(memory.predicates);
    }

    public SortedSet<Predicate> getPredicates() {
        return predicates;
    }

    public void update(Rule rule) {
        for(Predicate pred : rule.getPostConditions()) {
            if (pred.isAddition())
                predicates.add(pred);
            else
                predicates.remove(new Predicate(pred.getExpression()));
        }
    }

    public void addPredicate(String predicate) {
        predicates.add(new Predicate(predicate));
    }

    public void removePredicate(String predicate) {
        predicates.remove(new Predicate(predicate));
    }

    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof Memory) {
            Iterator thisIt = predicates.iterator();
            Iterator otherIt = ((Memory) anObject).predicates.iterator();
            while (true) {
                if (!thisIt.hasNext() && !otherIt.hasNext())
                    return true;
                if (!(thisIt.hasNext() && otherIt.hasNext()))
                    return false;
                if (!thisIt.next().equals(otherIt.next()))
                    return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Memory{" + predicates.stream().map(p -> p.toString()).collect(Collectors.joining(", ")) + "}";
    }

    @Override
    public int hashCode() {
        return predicates.hashCode();
    }
}
