package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Action;
import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;

import java.util.*;

public class Model {

    class Node {

        HashMap<Predicate, Node> nextNodes = new HashMap<>();
        Action action;
        Rule rule;

        public Node(Action action) {
            this.action = action;
        }

        public boolean canConsume(Predicate pred) {
            return action.canConsume(pred);
        }

        public Rule getRule() {
            if(rule == null)
                rule = action.createRule();
            return rule;
        }

        public Node getNextNode(Predicate pred) {

            if(canConsume(pred)) {
                if(nextNodes.containsKey(pred)) {
                    return nextNodes.get(pred);
                }
                else {
                    Action newAction = new Action(this.action);
                    newAction.consume(pred);
                    Node node = new Node(action);
                    nextNodes.put(pred, node);
                    return node;
                }
            }
            return null;
        }

        public boolean isActionFullyAssigned() {
            return action.isFullyAssigned();
        }
    }

    private final List<String> objects;
    private final List<Action> actions;
    private List<Node> roots = new ArrayList<>();

    public Model(List<String> objects, List<Action> actions) {
        this.objects = objects;
        this.actions = actions;
        for(Action a : actions) {
            roots.add(new Node(a));
        }
    }

    public List<Rule> getRules() {
        return null;
    }

    public List<Rule> getPossibleRules(Memory memory) {
        List<Node> horizond = new ArrayList<>();
        horizond.addAll(roots);

        for(Predicate pred : memory.getAllPredicates()) {
            List<Node> toAdd = new ArrayList<>();
            for(Node node : horizond) {
                Node new_node = node.getNextNode(pred);
                if(new_node != null)
                    toAdd.add(new_node);
            }
            horizond.addAll(toAdd);
        }

        List<Rule> rules = new ArrayList<>();
        for(Node node : horizond) {
            if(node.isActionFullyAssigned()){
                rules.add(node.getRule());
            }
        }
        return rules;
    }
}
