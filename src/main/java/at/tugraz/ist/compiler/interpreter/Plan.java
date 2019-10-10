package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Plan {

    private final List<Rule> rules;

    public Plan() {
        rules = new ArrayList<>();
    }

    public Plan(Plan currentPlan) {
        rules = new ArrayList<>(currentPlan.rules);
    }
    public Plan(List<Rule> currentRules) {
        rules = new ArrayList<>(currentRules);
    }

    public boolean ruleWouldRemoveNeededPrecondition(Rule rule) {
        List<Predicate> deletions =  rule.getWorldDeletions();
        List<Predicate> laterAdded;
        if(rules.size() <= 1)
            laterAdded = new ArrayList<>();
        else
            laterAdded= rules.subList(1, rules.size() - 1).stream().flatMap(r -> r.getWorldAdditions().stream()).collect(Collectors.toList());

        deletions.removeAll(laterAdded);

        return rules.stream().anyMatch(r -> r.getPreconditions().stream().anyMatch(deletions::contains));
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
        if (rule.getWorldAdditions().size() == 0) return false;

        List<Predicate> toReach = toReach();
        toReach.removeAll(memory.getAllPredicates());
        return toReach.stream().anyMatch(p -> rule.getWorldAdditions().contains(p));
    }

    private List<Predicate> toReach() {
        Set<Predicate> preconditions = rules.stream().flatMap(r -> r.getPreconditions().stream()).collect(Collectors.toSet());
        Set<Predicate> posEffects = new HashSet<>(rules.stream().flatMap(r -> r.getWorldAdditions().stream()).collect(Collectors.toList()));

        preconditions.removeAll(posEffects);
        return new ArrayList<>(preconditions);
    }

    public BigDecimal getWeight() {
        BigDecimal sum = new BigDecimal(0);

        for (Rule rule : rules) {
            sum = sum.add(rule.getWeight());
        }
        return sum;
    }

    public void remove(Rule rule) {
        rules.remove(rule);
    }
}
