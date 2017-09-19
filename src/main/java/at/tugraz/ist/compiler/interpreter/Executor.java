package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.InterpreterRule;
import at.tugraz.ist.compiler.rule.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Executor {

    public boolean executeOnce(Model model) throws NoPlanFoundException {
        List<Rule> rules = toRules(model.getRules());
        List<InterpreterRule> goals = toInterpreterRules(PlanFinder.getGoalRules(rules));
        Memory memory = model.getMemory();

        List<InterpreterRule> plan = null;
        goals.sort(Rule::compareTo);
        for (Rule goal : goals) {
            plan = toInterpreterRules(PlanFinder.getBestPlan(memory, rules));
            if (plan != null)
                break;
        }
        if (plan == null)
            throw new NoPlanFoundException();

        return interpreteRules(memory, plan);
    }

    private boolean interpreteRules(Memory memory, List<InterpreterRule> plan) {
        for (InterpreterRule rule : plan) {
            boolean result = rule.execute(memory);
            rule.increaseActivity();

            if (result) {
                memory.update(rule);
                rule.decreaseDamping();
            } else {
                rule.repairMemory(memory);
                rule.increaseDamping();
                return false;
            }
        }
        return true;
    }

    private List<InterpreterRule> toInterpreterRules(List<Rule> goalRules) {
        return goalRules == null ? null : goalRules.stream().map(r -> (InterpreterRule) r).collect(Collectors.toList());
    }

    private List<Rule> toRules(List<InterpreterRule> rules) {
        return rules == null ? null : new ArrayList<>(rules);
    }

    public void executeNTimes(Model model, int n) throws NoPlanFoundException {
        for (int i = 0; i < n; i++) {
                executeOnce(model);
        }
    }
}
