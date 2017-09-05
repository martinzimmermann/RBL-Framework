package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.ActionFailedException;
import at.tugraz.ist.compiler.rule.InterpreterRule;
import at.tugraz.ist.compiler.rule.Rule;

import java.util.List;

public class Executor {

    public void executeOnce(Model model) throws ActionFailedException, NoPlanFoundException {
        List<Rule> rules = model.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);
        goals.sort(Rule::compareTo);
        Rule goal = goals.get(0);

        Memory memory = model.getMemory();
        List<InterpreterRule> plan = PlanFinder.getPlanForRule(goal, memory, rules);
        if(plan == null)
            throw new NoPlanFoundException();

        for (InterpreterRule rule : plan) {
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
        }
    }

    public void executeNTimes(Model model, int n) throws ActionFailedException, NoPlanFoundException {
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
