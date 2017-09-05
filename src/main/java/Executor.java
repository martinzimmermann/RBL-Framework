import at.tugraz.ist.compiler.interpreter.NoPlanFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Executor {

    private Model model;

    public Executor() throws ClassNotFoundException {
        List<Rule> rules = new ArrayList<>();
        // replace with rules
        rules.add(new InterpreterRule(null, 0, null, null, null, null, null));

        List<Predicate> predicates = new ArrayList<>();
        // replace with predicates
        predicates.add(null);

        Memory memory = new Memory(predicates);
        model = new Model(memory, rules);
    }

    public void executesTillGoalReached() throws NoPlanFoundException {
        executesTillGoalReached(10);
    }

    private boolean executesTillGoalReached(int limit) throws NoPlanFoundException {
        for (int i = 0; i < limit; i++) {
            if(executeOnce())
                return true;
        }
        return false;
    }

    private boolean executeOnce() throws NoPlanFoundException {
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
            } catch (ActionFailedException e) {
                rule.repairMemory(memory);
                rule.increaseDamping();
                return false;
            }
        }
        return true;
    }

    public void executesNTimes(int n) throws NoPlanFoundException {
        for (int i = 0; i < n; i++) {
            executeOnce();
        }
    }

    public void resetMemory() {
        model.getMemory().reset();
    }

    public List<String> getMemory() {
        return new ArrayList<>(model.getMemory().getAllPredicates().stream().map(Predicate::toString).collect(Collectors.toList()));
    }

}
