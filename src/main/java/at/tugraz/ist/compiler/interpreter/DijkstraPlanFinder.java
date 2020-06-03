package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class DijkstraPlanFinder extends PlanFinder {

    @Override
    public List<Rule> getPlanForGoal(List<Predicate> goalState, Memory memory, Model model) {
        Node root = new Node();
        root.memory = new Memory(memory);
        root.totalWeight = BigDecimal.ZERO;
        List<Node> nodes = new ArrayList<>();
        nodes.add(root);
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if(current.visited)
                continue;
            List<Node> next_nodes = getNodesThatArePossible(current, model, nodes);
            current.visited = true;
            queue.addAll(next_nodes);
        }

        Optional<Node> node = nodes.stream().filter(n -> n.memory.getAllPredicates().containsAll(goalState)).findFirst();
        if (node.isPresent()) {
            Node current = node.get();
            List<Rule> rules = new ArrayList<>();
            while (current.connections_in != null) {
                rules.add(current.connections_in.rule);
                current = current.connections_in.start;
            }
            Collections.reverse(rules);
            return rules;
        } else {
            return null;
        }
    }

    private List<Node> getNodesThatArePossible(Node current, Model model, List<Node> nodes) {
        List<Rule> rules = model.getPossibleRules(current.memory);
        List<Node> nextNodes = new ArrayList<>();
        for(Rule rule : rules) {
            /* if( true ) { //FIXME rule.hasGoal()) {
                if(rule.getPreconditions().isEmpty())
                    continue; // TODO: Quickfix till real goal states are implemented
                Node n = new Node();
                Connection con = new Connection(current, n, rule);
                n.totalWeight = current.totalWeight.add(rule.getWeight()).multiply(new BigDecimal(0.5));
                n.connections_in = con;
                n.memory = new Memory(current.memory);
                n.visited = true;
                nodes.add(n);
                continue;
            }*/


            Memory altered_memory = new Memory(current.memory);
            altered_memory.update(rule);
            Optional<Node> node = nodes.stream().filter(n -> n.memory.equals(altered_memory)).findFirst();
            Node n = null;

            if (node.isPresent()) {
                n = node.get();
                if (n.visited) {
                    continue;
                }
            } else {
                n = new Node();
                n.memory = altered_memory;
                nodes.add(n);
            }
            if (n == null)
                continue;

            if (((n.totalWeight == null)
                    || (n.totalWeight.compareTo(current.totalWeight.add(rule.getWeight()).multiply(new BigDecimal(0.5)))) == 1)) {

                Connection con = new Connection(current, n, rule);
                n.totalWeight = current.totalWeight.add(rule.getWeight()).multiply(new BigDecimal(0.5));
                n.connections_in = con;
            }
            nextNodes.add(n);
        }
        return nextNodes;
    }

    class Connection {
        public Node start;
        public Node end;
        public Rule rule;
        public Connection(Node start, Node end, Rule rule) {
            this.start = start;
            this.rule = rule;
            this.end = end;
        }
    }

    class Node implements Comparable<Node> {
        public BigDecimal totalWeight = null;
        public Connection connections_in;
        Memory memory;
        boolean visited = false;

        @Override
        public int compareTo(Node o) {
            return totalWeight.compareTo(o.totalWeight);
        }
    }
}
