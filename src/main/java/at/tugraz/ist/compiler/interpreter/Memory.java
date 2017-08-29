package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;

import java.util.ArrayList;
import java.util.List;

public class Memory {

    private List<Predicate> predicates;

    public Memory(List<Predicate> predicates)
    {
        this.predicates = predicates;
    }

    public Memory(Memory another) {
        this.predicates = new ArrayList<>(another.predicates);
    }

    public boolean contains(Predicate precondition) {
        return predicates.contains(precondition);
    }

    public void update(Rule currentRule) {

        Predicate addition = currentRule.getWorldAddition();
        List<Predicate> deletions = currentRule.getWorldDeletions();

        if(addition != null)
            predicates.add(addition);
        predicates.removeAll(deletions);
    }

    public boolean containsAll(List<Predicate> preconditions) {
        return predicates.containsAll(preconditions);
    }

    public List<Predicate> getAllPredicates()
    {
        return predicates;
    }

    public void apply(Memory newMemory) {
        predicates.clear();
        predicates.addAll(newMemory.predicates);
    }
}
