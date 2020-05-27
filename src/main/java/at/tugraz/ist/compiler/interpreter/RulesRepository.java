package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.InterpreterRule;
import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RulesRepository {

    private List<Rule> rules;

    public RulesRepository(List<Rule> rules) {
        this.rules = rules;



    }

    public void add(Rule rule) {
        rules.add(rule);
    }

    public void remove(Rule rule) {
        rules.remove(rule);
    }

    public List<Rule> getRules() {
        return new ArrayList<>(rules);
    }

    public List<Rule> getPossibleRules(Set<Predicate> current_memory) {
        return rules.stream()
                .filter(rule -> current_memory.containsAll(rule.getPreconditions()))
                .collect(Collectors.toList());
    }
}
