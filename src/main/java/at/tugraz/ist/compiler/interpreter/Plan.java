package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Plan {

    private final List<Rule> rules;

    public Plan() {
        rules  = new ArrayList<>();
    }

    public Plan(Plan currentPlan) {
        rules = new ArrayList<>(currentPlan.rules);
    }

    public boolean ruleWouldRemoveNeededPrecondition(Rule rule) {
        return rules.stream().anyMatch(r -> r.getPreconditions().stream().anyMatch(p -> rule.getWorldDeletions().contains(p)));
    }

    public void add(Rule rule) {
        rules.add(rule);
    }

    public boolean isComplete(Memory memory) {
        List<Predicate> preconditions = toReach();
        return memory.containsAll(preconditions);
    }

    public List<Rule> getRules() {
        return rules;
    }

    public boolean needs(Rule rule, Memory memory) {
        if(!rule.hasWorldAddition()) return false;

        List<Predicate> toReach = toReach();
        toReach.removeAll(memory.getAllPredicates());
        return toReach.contains(rule.getWorldAddition());
    }

    private List<Predicate> toReach() {
        Set<Predicate> preconditions = new HashSet<>(rules.stream().flatMap(r -> r.getPreconditions().stream()).collect(Collectors.toList()));
        Set<Predicate> posEffects = new HashSet<>(rules.stream().filter(r -> r.hasWorldAddition()).map(r -> r.getWorldAddition()).collect(Collectors.toList()));

        preconditions.removeAll(posEffects);
        return new ArrayList<>(preconditions);
    }
}
