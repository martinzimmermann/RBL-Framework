package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BestPlanFinder extends PlanFinder {

    @Override
    public List<Rule> getAnyPlan(Memory memory, List<Rule> allRules) {
        return getPlanForGoal(null, memory, allRules);
    }

    @Override
    public List<Rule> getPlanForGoal(Rule goal, Memory memory, List<Rule> allRules) {

        List<Plan> plans = getPlansForGoal(goal, memory, allRules, new Plan());

        if (plans.size() == 0)
            return null; // plan couldn't be fulfilled on this path

        Plan bestPlan = plans.stream().min(Comparator.comparing(Plan::getWeight)).get();

        return bestPlan.getRules();
    }

    private List<Rule> getRulesThatArePossible(Memory memory, List<Rule> allRules) {
        return allRules.stream().filter(rule -> memory.getAllPredicates().containsAll(rule.getPreconditions())).collect(Collectors.toList());
    }

    private List<Plan> getPlansForGoal(Rule goal, Memory memory, List<Rule> allRules, Plan currentPlan) {

        List<Rule> rules = getRulesThatArePossible(memory, allRules);

        List<Plan> plans = new ArrayList<>();
        for (Rule rule : rules) {
            Plan newPlan = new Plan(currentPlan);
            newPlan.add(rule);

            if (rule.hasGoal() && (goal == null || rule.equals(goal))) {
                plans.add(newPlan);
                continue;
            }

            Memory newMemory = new Memory(memory);
            newMemory.update(rule);

            List<Rule> remainingRules = new ArrayList<>(allRules);
            remainingRules.remove(rule);


            List<Plan> newPlans = getPlansForGoal(goal, newMemory, remainingRules, newPlan);
            plans.addAll(newPlans);
        }

        return plans;
    }
}
