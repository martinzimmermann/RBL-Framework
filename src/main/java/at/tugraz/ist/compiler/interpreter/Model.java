package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Action;
import at.tugraz.ist.compiler.rule.AtomicFormula;
import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;

import java.util.*;

public class Model {

    private final List<String> objects;
    private final List<Action> actions;
    private final List<Node> roots = new ArrayList<>();

    public Model(List<String> objects, List<Action> actions) {
        this.objects = objects;
        this.actions = actions;
        for(Action a : actions) {
            roots.add(new Node(a));
        }
    }

    public List<Rule> getRules() {
        List<Rule> rules = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.addAll(roots);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.preconditionsFulfilled())
                rules.add(current.getRule());

            queue.addAll(current.nextNodes.values());
        }
        return rules;
    }

    public List<Rule> getPossibleRules(Memory memory) {
        List<Node> horizond = new ArrayList<>();
        horizond.addAll(roots);

        for(Predicate pred : memory.getPredicates()) {
            List<Node> toAdd = new ArrayList<>();
            for(Node node : horizond) {
                if (node.action.preconditionsFulfilled())
                    continue;
                Node new_node = node.getNextNode(pred);
                if (new_node != null)
                    toAdd.add(new_node);
            }
            horizond.addAll(toAdd);
        }

        List<Rule> rules = new ArrayList<>();
        for(Node node : horizond) {
            if (node.preconditionsFulfilled()) {
                rules.add(node.getRule());
            }
        }
        return rules;
    }

    class Node {

        HashMap<Predicate, Node> nextNodes = new HashMap<>();
        Action action;
        Rule rule;

        public Node(Action action) {
            this.action = action;
        }

        public AtomicFormula canBeConsumedBy(Predicate pred) {
            return action.canBeConsumedBy(pred);
        }

        public Rule getRule() {
            if (rule == null)
                rule = action.createRule();
            return rule;
        }

        public Node getNextNode(Predicate pred) {
            if (nextNodes.containsKey(pred)) {
                return nextNodes.get(pred);
            }
            AtomicFormula a = canBeConsumedBy(pred);
            if (a != null) {
                Action newAction = new Action(this.action);
                newAction.consume(a, pred);
                Node node = new Node(newAction);
                nextNodes.put(pred, node);
                return node;
            }
            return null;
        }

        public boolean preconditionsFulfilled() {
            return action.preconditionsFulfilled();
        }
    }
}
