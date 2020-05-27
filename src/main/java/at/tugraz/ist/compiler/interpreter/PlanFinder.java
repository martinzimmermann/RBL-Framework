package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Rule;

import java.util.List;
import java.util.stream.Collectors;

public abstract class PlanFinder {
    public static List<Rule> getGoalRules(List<Rule> allRules) {
        return allRules.stream().filter(Rule::hasGoal).collect(Collectors.toList());
    }

    public abstract List<Rule> getAnyPlan(Memory memory, RulesRepository rulesRepository);
    public abstract List<Rule> getPlanForGoal(Rule goal, Memory memory, RulesRepository rulesRepository);

}
