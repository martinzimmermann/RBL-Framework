package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.ActionFailedException;
import at.tugraz.ist.compiler.rule.InterpreterRule;
import at.tugraz.ist.compiler.rule.Rule;

import java.util.List;
import java.util.stream.Collectors;

public class Executor {

    public void executeOnce(Model model) throws ActionFailedException, NoPlanFoundException {
        List<Rule> rules = toRules(model.getRules());
        List<InterpreterRule> goals = toInterprterRules(PlanFinder.getGoalRules(rules));

        goals.sort(Rule::compareTo);
        InterpreterRule goal = goals.get(0);

        Memory memory = model.getMemory();
        List<InterpreterRule> plan = toInterprterRules(PlanFinder.getPlanForRule(goal, memory, rules));
        if (plan == null)
            throw new NoPlanFoundException();

        for (InterpreterRule rule : plan) {
            try {
                rule.execute(memory);
                memory.update(rule);
                rule.decreaseDamping();
                rule.increaseActivity();
            } catch (ActionFailedException e) {
                rule.repairMemory(memory);
                rule.increaseDamping();
                throw e;
            }
        }
    }

    private List<InterpreterRule> toInterprterRules(List<Rule> goalRules) {
        return goalRules.stream().map(r -> (InterpreterRule)r).collect(Collectors.toList());
    }

    private List<Rule> toRules(List<InterpreterRule> rules) {
        return rules.stream().collect(Collectors.toList());
    }

    public void executeNTimes(Model model, int n) throws ActionFailedException, NoPlanFoundException {
        for (int i = 0; i < n; i++) {
            try {
                executeOnce(model);
            } catch (ActionFailedException e) {
                // Do nothing
            }
        }
    }


}
