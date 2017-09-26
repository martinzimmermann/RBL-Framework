package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        List<Plan> reasonablePaht = plans.stream().filter(p -> !isReduceable(p, memory)).collect(Collectors.toList());

        Plan bestPlan = reasonablePaht.stream().max(Comparator.comparing(Plan::getWeight)).get();
        return bestPlan.getRules();
    }

    public static List<Rule> getPlanForGoalTopDown(Rule goal, Memory memory, List<Rule> allRules) {
        Plan plan = getPlanForGoalTopDown(goal, null, memory, allRules, new Plan());
        return plan == null ? null : CleanUp2(CleanUp(plan), memory);
    }

    public static boolean isReduceable(Plan plan, Memory memory) {
        for (Rule rule : plan.getRules()) {
            Plan newPlan = new Plan(plan);
            newPlan.remove(rule);
            if (isInterpreadable(newPlan, new Memory(memory)))
                return true;
        }
        return false;
    }

    private static boolean isInterpreadable(Plan plan, Memory memory) {
        for (Rule rule : plan.getRules()) {
            boolean result = memory.containsAll(rule.getPreconditions());
            if (result) {
                memory.update(rule);
                if (rule.hasGoal())
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    private static List<Rule> CleanUp2(List<Rule> rules, Memory memory) {

        List<Rule> newRules = new ArrayList<>(rules);
        List<Predicate> conditions = new ArrayList<>(memory.getAllPredicates());

        for (Rule rule : rules) {
            if (!rule.hasWorldAddition())
                newRules.remove(rule);

            if (conditions.contains(rule.getWorldAddition())) {
                newRules.remove(rule);
            } else {
                conditions.add(rule.getWorldAddition());
                conditions.removeAll(rule.getWorldDeletions());
            }
        }
        return newRules;
    }

    private static List<Rule> CleanUp(Plan plan) {
        List<Rule> rules = plan.getRules();
        Collections.reverse(rules);
        boolean[] checked = new boolean[rules.size()];

        checked[0] = true;
        for (Predicate precondition : rules.get(0).getPreconditions()) {
            checkRule(rules, 1, checked, precondition);
        }

        List<Rule> filteredRules = IntStream.range(0, rules.size())
                .filter(i -> checked[i])
                .mapToObj(i -> rules.get(i))
                .collect(Collectors.toList());
        Collections.reverse(filteredRules);
        return filteredRules;
    }

    private static void checkRule(List<Rule> rules, int currentIndex, boolean[] checked, Predicate precondition) {
        for (int i = currentIndex; i < rules.size(); i++) {
            if (rules.get(i).hasWorldAddition() && rules.get(i).getWorldAddition().equals(precondition)) {
                if (checked[i] == true)
                    continue;

                checked[i] = true;
                for (Predicate pre : rules.get(i).getPreconditions()) {
                    checkRule(rules, i + 1, checked, pre);
                }
            }
        }
    }

    public static List<Rule> getPlanTopDown(Memory memory, List<Rule> allRules) {
        return getPlanForGoalTopDown(null, memory, allRules); // plan couldn't be fulfilled on this path
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

    private static Plan getPlanForGoalTopDown(Rule goal, Rule currentRule, Memory memory, List<Rule> allRules, Plan currentPlan) {

        List<Rule> rules = getRulesThatArePossible(memory, allRules);

        // prioritize Goal rules
        Comparator<Rule> comparator = Comparator.comparing(r -> !(r.hasGoal() && (goal == null ? true : goal.equals(r.getGoal()))));
        comparator = comparator.thenComparing(Rule::compareTo);
        rules.sort(comparator);

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

            Plan p = getPlanForGoalTopDown(goal, rule, newMemory, remainingRules, newPlan);
            if (p != null)
                return p;
            else
                continue;
        }
        return null;
    }

    public static List<Rule> getPlanForGoalBottomUp(Rule goal, Memory memory, List<Rule> allRules) {
        List<Rule> plan = getPlanForGoalBottomUp(goal, memory, allRules, new Plan());
        if (plan == null)
            return null;
        Collections.reverse(plan);
        return plan;
    }

    public static List<Rule> getPlanBottomUp(Memory memory, List<Rule> allRules) {

        List<Rule> goals = getGoalRules(allRules);
        if (goals.size() == 0)
            return null;

        goals.sort(Rule::compareTo);

        List<Rule> plan = getPlanForGoalBottomUp(goals.get(0), memory, allRules, new Plan());
        if (plan == null)
            return null;
        Collections.reverse(plan);
        return plan;
    }

    private static List<Rule> getPlanForGoalBottomUp(Rule goal, Memory memory, List<Rule> allRules, Plan currentPlan) {
        Plan newPlan = new Plan(currentPlan);
        newPlan.add(goal);

        if (newPlan.isComplete(memory))
            return newPlan.getRules();

        List<Rule> rules = new ArrayList<>(allRules);
        rules.remove(goal);
        rules.sort(Rule::compareTo);

        for (Rule rule : rules) {
            if (!newPlan.needs(rule, memory))
                continue;

            if (newPlan.ruleWouldRemoveNeededPrecondition(rule))
                continue;

            List<Rule> newRules = getPlanForGoalBottomUp(rule, memory, rules, newPlan);
            if (newRules == null) // this path didn't yield a valid plan, try other rule
                continue;

            return newRules;
        }

        return null; // plan couldn't be fulfilled on this path
    }
}
