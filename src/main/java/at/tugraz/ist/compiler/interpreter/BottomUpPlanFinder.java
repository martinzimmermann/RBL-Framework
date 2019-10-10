package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BottomUpPlanFinder extends PlanFinder {

    private List<Rule> getPlanForGoalBottomUp(Rule goal, Memory memory, List<Rule> allRules, Plan currentPlan) {
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

    @Override
    public List<Rule> getAnyPlan(Memory memory, List<Rule> allRules) {
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

    @Override
    public List<Rule> getPlanForGoal(Rule goal, Memory memory, List<Rule> allRules) {
        List<Rule> plan = getPlanForGoalBottomUp(goal, memory, allRules, new Plan());
        if (plan == null)
            return null;
        Collections.reverse(plan);
        return plan;
    }
}
