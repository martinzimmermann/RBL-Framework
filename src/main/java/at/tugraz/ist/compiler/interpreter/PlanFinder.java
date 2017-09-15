package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Rule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlanFinder {
    public static List<Rule> getGoalRules(List<Rule> allRules) {
        return allRules.stream().filter(Rule::hasGoal).collect(Collectors.toList());
    }

    public static List<Rule> getBestPlan(Memory memory, List<Rule> allRules) {
        return getBestPlanForGoal(null, memory, allRules); // plan couldn't be fulfilled on this path
    }

    public static List<Rule> getBestPlanForGoal(Rule goal, Memory memory, List<Rule> allRules) {
        List<Plan> plans = getPlansForGoal(goal, null, memory, allRules, new Plan());

        if (plans.size() == 0)
            return null; // plan couldn't be fulfilled on this path

        Plan bestPlan = plans.stream().max(Comparator.comparing(Plan::getWeight)).get();
        return bestPlan.getRules();
    }

    public static List<Rule> getPlanForGoal(Rule goal, Memory memory, List<Rule> allRules) {
        Plan plan = getPlanForGoal(goal, null, memory, allRules, new Plan());
        return plan == null ? null : plan.getRules();
    }

    public static List<Rule> getPlan(Memory memory, List<Rule> allRules) {
        return getPlanForGoal(null, memory, allRules); // plan couldn't be fulfilled on this path
    }


    private static List<Rule> getRulesThatArePossible(Memory memory, List<Rule> allRules) {
        return allRules.stream().filter(rule -> memory.getAllPredicates().containsAll(rule.getPreconditions())).collect(Collectors.toList());
    }

    private static List<Plan> getPlansForGoal(Rule goal, Rule currentRule, Memory memory, List<Rule> allRules, Plan currentPlan) {

        List<Rule> rules = getRulesThatArePossible(memory, allRules);
        rules.sort(Rule::compareTo);

        List<Plan> plans = new ArrayList<>();
        for (Rule rule : rules) {
            Memory newMemory = new Memory(memory);
            List<Rule> remainingRules = new ArrayList<>(allRules);
            Plan newPlan = new Plan(currentPlan);

            newMemory.update(rule);
            remainingRules.remove(rule);
            newPlan.add(rule);

            if (rule.hasGoal() && (goal == null ? true : currentPlan.equals(goal))) {
                plans.add(newPlan);
            }

            List<Plan> newPlans = getPlansForGoal(goal, rule, newMemory, remainingRules, newPlan);
            plans.addAll(newPlans);
        }

        return plans;
    }

    private static Plan getPlanForGoal(Rule goal, Rule currentRule, Memory memory, List<Rule> allRules, Plan currentPlan) {

        List<Rule> rules = getRulesThatArePossible(memory, allRules);

        // prioritize Goal rules
        Comparator<Rule> comparator = Comparator.comparing(r -> !(r.hasGoal() && (goal == null ? true : goal.equals(r.getGoal()))));
        comparator = comparator.thenComparing(Comparator.comparing(r -> r.getWeight()));
        rules.sort(comparator);

        List<Plan> plans = new ArrayList<>();
        for (Rule rule : rules) {
            Memory newMemory = new Memory(memory);
            List<Rule> remainingRules = new ArrayList<>(allRules);
            Plan newPlan = new Plan(currentPlan);

            newMemory.update(rule);
            remainingRules.remove(rule);
            newPlan.add(rule);

            if (rule.hasGoal() && (goal == null ? true : currentPlan.equals(goal))) {
                return newPlan;
            }

            Plan p = getPlanForGoal(goal, rule, newMemory, remainingRules, newPlan);
            if(p != null)
                return p;
            else
                continue;
        }
        return null;
    }
}
