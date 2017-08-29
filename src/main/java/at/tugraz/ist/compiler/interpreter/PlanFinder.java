package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlanFinder
{
    public static List<Rule> getGoalRules(List<Rule> allRules)
    {
        return allRules.stream().filter(rule -> rule.hasGoal()).collect(Collectors.toList());
    }

    public static List<Rule> getPlanForRule(Rule goal, Memory memory, List<Rule> allRules)
    {
        return getPlanForRule(goal,memory, allRules, new Plan());
    }

    public static List<Rule> getPlanForRule(Rule goal, Memory memory, List<Rule> allRules, Plan currentPlan)
    {
        Plan newPlan = new Plan(currentPlan);
        newPlan.add(goal);

        if (newPlan.isComplete(memory))
            return newPlan.getRules();

        List<Rule> rules = new ArrayList<>(allRules);
        rules.remove(goal);

        for (Rule rule : rules) {
            if (!newPlan.needs(rule, memory))
                continue;

            if (newPlan.ruleWouldRemoveNeededPrecondition(rule))
                continue;

            List<Rule> newRules = getPlanForRule(rule, memory, rules, newPlan);
            if(newRules == null) // this path didn't yield a valid plan, try other rule
                continue;
            else
                return newRules;
        }

        return null; // plan couldn't be fulfilled on this path
    }

    private static List<Rule> getRulesThatSatisfies(Predicate precondition, List<Rule> allRules) {
        return null;
    }

    private static List<Rule> getRulesSortedRules( List<Rule> allRules){
        return allRules;
    }
}
