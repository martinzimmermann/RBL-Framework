import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TopDownPlanFinder extends PlanFinder {

    @Override
    public List<Rule> getAnyPlan(Memory memory, List<Rule> allRules) {
        return getPlanForGoal(null, memory, allRules); // plan couldn't be fulfilled on this path
    }

    @Override
    public List<Rule> getPlanForGoal(Rule goal, Memory memory, List<Rule> allRules) {
        Plan plan = getPlanForGoalTopDown(goal, null, memory, allRules, new Plan());
        return plan == null ? null : reduce(plan, memory).getRules();
    }

    private boolean isInterpreadable(Plan plan, Memory memory) {
        Memory newMemory = new Memory(memory);
        for (Rule rule : plan.getRules()) {
            boolean result = newMemory.containsAll(rule.getPreconditions());
            if (result) {
                newMemory.update(rule);
                if (rule.hasGoal())
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }


    private Plan reduce(Plan plan, Memory memory)
    {
        boolean reducable = false;
        int firstReduction = 0;

        for(int i = 0; i < plan.getRules().size(); i++)
        {
            Rule rule = plan.getRules().get(i);
            Plan newPlan = new Plan(plan);
            newPlan.remove(rule);
            if(isInterpreadable(newPlan, memory)) {
                reducable = true;
                firstReduction = i;
                break;
            }
        }

        if(!reducable)
            return plan;
        else
        {
            Plan newPlan = new Plan(plan);
            newPlan.remove(plan.getRules().get(firstReduction));
            return reduce(newPlan, memory);
        }
    }

    private List<Rule> getRulesThatArePossible(Memory memory, List<Rule> allRules) {
        return allRules.stream().filter(rule -> memory.getAllPredicates().containsAll(rule.getPreconditions())).collect(Collectors.toList());
    }

    private Plan getPlanForGoalTopDown(Rule goal, Rule currentRule, Memory memory, List<Rule> allRules, Plan currentPlan) {

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

            if (rule.hasGoal() && (goal == null ? true : rule.equals(goal))) {
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
}
