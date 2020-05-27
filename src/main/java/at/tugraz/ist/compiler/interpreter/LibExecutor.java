package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.DiagnosticPosition;
import at.tugraz.ist.compiler.rule.InterpreterRule;
import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;
import fr.uga.pddl4j.parser.Exp;
import fr.uga.pddl4j.parser.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

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

    public boolean execute(List<String> goalState) throws NoPlanFoundException {

        List<Predicate> goalCond = new ArrayList<>();
        for (String pre : goalState) {
            goalCond.add(new Predicate(pre));
        }

        InterpreterRule iRule = new InterpreterRule(new goal(), new Rule("goal", "goal", new ArrayList<>(), goalCond, new HashMap<>(), new DiagnosticPosition(0, 0, 0, 0, "")));
        model.getRulesRepository().add(iRule);

        Memory memory = model.getMemory();
        RulesRepository repository = model.getRulesRepository();

        List<InterpreterRule> plan = toInterpreterRules(planFinder.getPlanForGoal(iRule, memory, repository));
        repository.remove(iRule);

        if (plan == null)
            throw new NoPlanFoundException();

        boolean success = interpretRules(plan);

        List<Rule> plan_complement = repository.getRules();
        plan_complement.removeAll(plan);

        for (Rule rule : plan_complement) {
            rule.updateRule(!success, false);
        }

        return success;
    }

    public boolean execute() throws NoPlanFoundException {

        Memory memory = model.getMemory();
        RulesRepository repository = model.getRulesRepository();

        List<InterpreterRule> plan = toInterpreterRules(planFinder.getAnyPlan(memory, repository));

        if (plan == null)
            throw new NoPlanFoundException();

        boolean success = interpretRules(plan);

        List<Rule> plan_complement = repository.getRules();
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

    //public void executeNTimes(int n) throws NoPlanFoundException {
    //    for (int i = 0; i < n; i++) {
    //        executeOnce();
    //    }
    //}
}
