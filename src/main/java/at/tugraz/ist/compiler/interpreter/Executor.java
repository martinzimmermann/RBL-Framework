package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.InterpreterRule;
import at.tugraz.ist.compiler.rule.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Executor {

    private final PlanFinder planFinder;

    public Executor(PlanFinder planFinder) {
        this.planFinder = planFinder;
    }

    public boolean executeOnce(Model model) throws NoPlanFoundException {
        List<Rule> rules = toRules(model.getRules());
        List<InterpreterRule> goals = toInterpreterRules(PlanFinder.getGoalRules(rules));
        Memory memory = model.getMemory();

        List<InterpreterRule> plan = null;
        goals.sort(Rule::compareTo);
        for (Rule goal : goals) {
            plan = toInterpreterRules(planFinder.getAnyPlan(memory, rules));
            if (plan != null)
                break;
        }
        if (plan == null)
            throw new NoPlanFoundException();

        boolean success = interpretRules(memory, plan);

        for (Rule rule : model.getRules()) {
            if(plan.contains(rule))
                rule.decreaseActivity();
            else
                rule.increaseActivity();
        }
        return success;
    }

    private boolean interpretRules(Memory memory, List<InterpreterRule> plan) {

        for (InterpreterRule rule : plan) {
            boolean result = rule.execute(memory);
            if (result) {
                memory.update(rule);
                rule.increaseDamping();
            } else {
                rule.repairMemory(memory);
                rule.decreaseDamping();
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
