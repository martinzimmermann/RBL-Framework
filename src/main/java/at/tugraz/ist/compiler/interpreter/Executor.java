package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.ActionFailedException;
import at.tugraz.ist.compiler.rule.PreConditionsNotMetException;
import at.tugraz.ist.compiler.rule.Rule;

import java.util.List;

public class Executor {

    public void executeOnce(Model model) throws ExecutionFailedException
    {
        List<Rule> rules = model.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);
        goals.sort(Rule::compareTo);
        Rule goal = goals.get(0);

        Memory memory = model.getMemory();
        List<Rule> plan = PlanFinder.getPlanForRule(goal, memory, rules);

        for (Rule rule : plan) {
            try {
                rule.execute(memory);
                memory.update(rule);
                rule.decreaseDamping();
                rule.increaseActivity();
            }
            catch (ActionFailedException e)
            {
                rule.repairMemory(memory);
                rule.increaseDamping();
                throw e;
            }
            catch (PreConditionsNotMetException e)
            {
                assert (false);
            }
        }
    }

    public void executeNTimes(Model model, int n) throws ExecutionFailedException
    {
        for(int i = 0; i < n; i ++)
        {
            try {
                executeOnce(model);
            }
            catch (ActionFailedException e)
            {
                // Do nothing
            }
        }
    }



}
