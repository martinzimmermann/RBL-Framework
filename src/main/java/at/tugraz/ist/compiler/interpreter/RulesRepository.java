package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;

import java.util.*;

public class RulesRepository {

    class Node {
        HashMap<String, Node> ruleNodes = new HashMap<>();
        List<Rule> rules = new ArrayList<>();

        public Node getNode(String name) {
            return ruleNodes.get(name);
        }

        public void addRule(Rule rule) {
            rules.add(rule);
        }

        public Node getOrCreateNode(String name) {
            if(!ruleNodes.containsKey(name))
                ruleNodes.put(name, new Node());

            return ruleNodes.get(name);
        }

        public boolean hasRules() {
            return !rules.isEmpty();
        }

        public boolean containsNode(String name) {
            return ruleNodes.containsKey(name);
        }

        public List<Rule> getRules() {
            return new ArrayList<>(rules);
        }

        public void removeRule(Rule rule) {
            rules.remove(rule);
        }
    }

    private List<Rule> rules;
    Node root;;

    public RulesRepository(List<Rule> rules) {
        this.root = new Node();
        this.rules = rules;

        for(Rule rule : rules) {
            Node current = root;
            for(Predicate pred : rule.getPreconditions()) {
                current = current.getOrCreateNode(pred.getName());
            }
            current.addRule(rule);
        }
    }

    public void add(Rule rule) {
        this.rules.add(rule);

        Node current = root;
        for(Predicate pred : rule.getPreconditions()) {
            current = current.getOrCreateNode(pred.getName());
        }
        current.addRule(rule);
    }

    public void remove(Rule rule) {
        this.rules.remove(rule);

        Node current = root;
        for(Predicate pred : rule.getPreconditions()) {
            current = current.getOrCreateNode(pred.getName());
        }
        current.removeRule(rule);
        //TODO: implement pruning
    }

    public List<Rule> getRules() {
        return new ArrayList<>(rules);
    }

    public List<Rule> getPossibleRules(Set<Predicate> current_memory) {
        List<Node> horizond = new ArrayList<>();
        horizond.add(root);


        for(Predicate pred: current_memory ) {
            List<Node> toAdd = new ArrayList<>();
            for(Node node : horizond) {
                if(node.containsNode(pred.getName()))
                    toAdd.add(node.getNode(pred.getName()));
            }
            horizond.addAll(toAdd);
        }

        List<Rule> rules = new ArrayList<>();
        for(Node node : horizond) {
            if(node.hasRules()){
                rules.addAll(node.getRules());
            }
        }
        return rules;
    }
}
