package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;

import java.util.List;

public abstract class PlanFinder {
    public abstract List<Rule> getPlanForGoal(List<Predicate> goalState, Memory memory, Model rulesRepository);
}
