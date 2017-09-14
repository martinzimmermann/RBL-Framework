package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Rule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PlanFinder {
    public static List<Rule> getGoalRules(List<Rule> allRules) {
        return allRules.stream().filter(Rule::hasGoal).collect(Collectors.toList());
    }

    public static List<Rule> getPlan(Memory memory, List<Rule> allRules) {
        return getPlanForGoal(null, memory, allRules); // plan couldn't be fulfilled on this path
    }

    public static List<Rule> getPlanForGoal(Rule goal, Memory memory, List<Rule> allRules) {
        return getPlanForGoal(goal, null, memory, allRules, new Plan());
    }

    private static List<Rule> getRulesThatArePossible(Memory memory, List<Rule> allRules)
    {
        return allRules.stream().filter(rule -> memory.getAllPredicates().containsAll(rule.getPreconditions())).collect(Collectors.toList());
    }

    private static List<Rule> getPlanForGoal(Rule goal, Rule currentRule, Memory memory, List<Rule> allRules, Plan currentPlan) {

        if((currentRule == null ? false : currentRule.hasGoal()) && goal == null ? true : currentPlan.equals(goal))
            return currentPlan.getRules();

        List<Rule> rules = getRulesThatArePossible(memory, allRules);
        rules.sort(Rule::compareTo);

        List<List<Rule>> plans = new ArrayList<>();
        for(Rule rule : rules)
        {
            Memory newMemory = new Memory(memory);
            List<Rule> remainingRules = new ArrayList<>(allRules);
            Plan newPlan = new Plan(currentPlan);

            newMemory.update(rule);
            remainingRules.remove(rule);
            newPlan.add(rule);
            List<Rule> plan = getPlanForGoal(goal, rule, newMemory, remainingRules, newPlan);
            if(plan != null)
                plans.add(plan);
            else
                continue;
        }

        if(plans.size() == 0)
            return null; // plan couldn't be fulfilled on this path

        List<Rule> bestPlan = plans.stream().max((r1, r2) ->
        {
            BigDecimal weight1 = r1.stream().reduce(new BigDecimal(0), (BigDecimal a, Rule b) -> b.getWeight(), (BigDecimal a, BigDecimal b) -> a.add(b));
            BigDecimal weight2 = r2.stream().reduce(new BigDecimal(0), (BigDecimal a, Rule b) -> b.getWeight(), (BigDecimal a, BigDecimal b) -> a.add(b));

            return weight1.compareTo(weight2);
        }).get();

        return bestPlan;
    }
}
