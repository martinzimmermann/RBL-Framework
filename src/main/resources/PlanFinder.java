import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlanFinder {
    public static List<Rule> getGoalRules(List<Rule> allRules) {
        return allRules.stream().filter(Rule::hasGoal).collect(Collectors.toList());
    }

    public static List<Rule> getPlanForRule(Rule goal, Memory memory, List<Rule> allRules) {
        List<Rule> plan = getPlanForRule(goal, memory, allRules, new Plan());
        if (plan == null)
            return null;
        Collections.reverse(plan);
        return plan;
    }

    private static List<Rule> getPlanForRule(Rule goal, Memory memory, List<Rule> allRules, Plan currentPlan) {
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

            List<Rule> newRules = getPlanForRule(rule, memory, rules, newPlan);
            if (newRules == null) // this path didn't yield a valid plan, try other rule
                continue;

            return newRules;
        }

        return null; // plan couldn't be fulfilled on this path
    }
}
