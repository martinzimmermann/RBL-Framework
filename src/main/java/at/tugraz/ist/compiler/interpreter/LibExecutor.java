package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.InterpreterRule;
import at.tugraz.ist.compiler.rule.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LibExecutor {

    private final PlanFinder planFinder;
    private Model model;

    public LibExecutor(Model model, PlanFinder planFinder) {
        this.model = model;
        this.planFinder = planFinder;
    }

    public Model getModel() {
        return model;
    }

    public boolean executeOnce() throws NoPlanFoundException {
        List<Rule> rules = toRules(model.getRules());

        Memory memory = model.getMemory();

        List<InterpreterRule> plan = toInterpreterRules(planFinder.getAnyPlan(memory, rules));

        if (plan == null)
            throw new NoPlanFoundException();

        boolean success = interpretRules(plan);

        List<InterpreterRule> plan_complement =  new ArrayList<>(model.getRules());
        plan_complement.removeAll(plan);

        for (Rule rule : plan_complement) {
            rule.updateRule(!success, false);
        }

        return success;
    }

    private boolean interpretRules(List<InterpreterRule> plan) {

    boolean success = true;
        int failed_rule = 0;
        for (InterpreterRule rule : plan) {
            boolean result = rule.execute(model);
            if (result) {
                model.getMemory().update(rule);
            } else {
                rule.repairMemory(model.getMemory());
                success = false;
                break;
            }
            failed_rule++;
        }

        int count = 0;
        for (InterpreterRule rule : plan) {
            if (success)
                rule.updateRule(false, true);
            else
                if (count <= failed_rule)
                    rule.updateRule(true, true);
                else
                    rule.updateRule(true, false);
            count++;
        }

        return success;
    }

    private List<InterpreterRule> toInterpreterRules(List<Rule> goalRules) {
        return goalRules == null ? null : goalRules.stream().map(r -> (InterpreterRule) r).collect(Collectors.toList());
    }

    private List<Rule> toRules(List<InterpreterRule> rules) {
        return rules == null ? null : new ArrayList<>(rules);
    }

    public void executeNTimes(int n) throws NoPlanFoundException {
        for (int i = 0; i < n; i++) {
            executeOnce();
        }
    }
}
