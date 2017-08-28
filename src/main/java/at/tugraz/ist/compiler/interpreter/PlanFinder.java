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

    public static List<Rule> getPlanForGoal(Rule goal, Memory memory, List<Rule> allRules)
    {
        Plan plan = new Plan();
        plan.add(goal);

        List<Rule> rules = new ArrayList<>(allRules);
        rules.remove(goal);

        for (; ; ) {
            boolean planChanged = false;

            for (Rule rule : rules) {
                if (!plan.needs(rule, memory))
                    continue;

                if (plan.ruleWouldRemoveNeededPrecondition(rule))
                    continue;

                planChanged = true;
                plan.add(rule);
            }

            if (!planChanged)
                if (plan.isComplete(memory))
                    return plan.getRules();
                else
                    return null;
        }

    }

    public static List<Rule> searchPlan(Rule currentRule, Memory memory, List<Rule> allRules) {

        List<Rule> plan = new ArrayList<>();

        List<Predicate> preconditions = currentRule.getPreconditions();

        for( Predicate precondition : preconditions)
        {
            if(memory.contains(precondition))
                continue;
            else
            {
                List<Rule> ruleCandidates = getRulesThatSatisfies(precondition, allRules);
                if (ruleCandidates.size() == 0)
                    return null;

                boolean ruleFound = false;
                for(Rule newRule: ruleCandidates)
                {
                    Memory newMemory = new Memory(memory);
                    List<Rule> newPlan = searchPlan(newRule, newMemory, allRules);

                    if(newPlan == null ||
                            !newMemory.containsAll(currentRule.getPreconditions())) {
                        continue;
                    }

                    memory.apply(newMemory);
                    plan.addAll(newPlan);
                    ruleFound = true;
                    break;
                }

                if(ruleFound == false)
                    return null;
            }

        }

        plan.add(currentRule);
        memory.update(currentRule);

        return plan;
    }

    private static List<Rule> getRulesThatSatisfies(Predicate precondition, List<Rule> allRules) {
        return null;
    }

    private static List<Rule> getRulesSortedRules( List<Rule> allRules){
        return allRules;
    }
}
