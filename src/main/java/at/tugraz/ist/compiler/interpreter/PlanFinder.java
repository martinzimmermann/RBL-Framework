package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PlanFinder
{
    public static List<Rule> getGoalRules(List<Rule> allRules)
    {
        return allRules.stream().filter(rule -> rule.hasGoal()).collect(Collectors.toList());
    }


    public static List<Rule> searchPlan(Rule currentRule, Memory memory, List<Rule> allRules){
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
                        List<Rule> newPlan = searchPlan(newRule, memory, allRules);
                        if(newPlan == null)
                            continue;

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

    private static List<Rule> getRulesThatSatisfies(Predicate precondition, List<Rule> allRules){
        return allRules.stream().filter(r -> r.hasWorldAddition() ? r.getWorldAddition().equals(precondition) : false).collect(Collectors.toList());
    }
}
