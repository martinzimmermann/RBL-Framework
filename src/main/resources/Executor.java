import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Executor {

    private Model model;

    public Executor() throws ClassNotFoundException {
        Arrays.asList(); // Just so the import won't get removed at a code cleanup
        List<Rule> rules = new ArrayList<>();
// <replace with rules>

        List<Predicate> predicates = new ArrayList<>();
// <replace with predicates>

        Memory memory = new Memory(predicates);
        model = new Model(memory, rules);
        if(ErrorHandler.Instance().hasErrors()) {
            ErrorHandler.Instance().printErrorCount();
            throw new ClassNotFoundException();
        }
    }

    public boolean executesTillGoalReached() throws NoPlanFoundException {
        return executesTillGoalReached(10);
    }

    private boolean executesTillGoalReached(int limit) throws NoPlanFoundException {
        for (int i = 0; i < limit; i++) {
            if (executeOnce())
                return true;
        }
        return false;
    }

    public boolean executeOnce() throws NoPlanFoundException {
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
                rule.increaseActivity();
                return false;
            }
        }
        return true;
    }

    private List<InterpreterRule> toInterprterRules(List<Rule> goalRules) {
        return goalRules.stream().map(r -> (InterpreterRule)r).collect(Collectors.toList());
    }

    private List<Rule> toRules(List<InterpreterRule> rules) {
        return rules.stream().collect(Collectors.toList());
    }


    public void executesNTimes(int n) throws NoPlanFoundException {
        for (int i = 0; i < n; i++) {
            executeOnce();
        }
    }

    public void executesForever() throws NoPlanFoundException {
        while (true) {
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
