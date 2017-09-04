import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Executor {

    Model model;

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

    public void executesTillGoalReached() {
        executesTillGoalReached(10);
    }

    public boolean executesTillGoalReached(int limit) {
        for (int i = 0; i < limit; i++) {
            if(executeOnce())
                return true;
        }
        return false;
    }

    public boolean executeOnce() {
        List<Rule> rules = model.getRules();
        List<Rule> goals = PlanFinder.getGoalRules(rules);
        goals.sort(Rule::compareTo);
        Rule goal = goals.get(0);

        Memory memory = model.getMemory();
        List<InterpreterRule> plan = PlanFinder.getPlanForRule(goal, memory, rules);

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

    public void executesNTimes(int n) {
        for (int i = 0; i < n; i++) {
            executeOnce();
        }
    }

    public void resetMemory() {
        model.getMemory().reset();
    }

    public List<String> getMemory() {
        return new ArrayList<String>(model.getMemory().getAllPredicates().stream().map(Predicate::toString).collect(Collectors.toList()));
    }

}
