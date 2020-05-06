package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.Rule;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class DijkstraPlanFinder extends PlanFinder {

    @Override
    public List<Rule> getAnyPlan(Memory memory, List<Rule> allRules) {
        return getPlanForGoal(null, memory, allRules); // plan couldn't be fulfilled on this path
    }

    @Override
    public List<Rule> getPlanForGoal(Rule goal, Memory memory, List<Rule> allRules) {
        List<Rule> plan = getPlanForGoal(goal, null, memory, allRules, new Plan());
        return plan == null ? null : plan;
    }

    private List<Node> getNodesThatArePossible(Node current, List<Rule> allRules, List<Node> nodes) {
        List<Rule> rules = allRules.stream()
                .filter(rule -> current.memory.getAllPredicates().containsAll(rule.getPreconditions()))
                .collect(Collectors.toList());
        List<Node> nextNodes = new ArrayList<>();
        for(Rule rule : rules) {
            if(rule.hasGoal()) {
                Node n = new Node();
                Connection con = new Connection(current, n, rule);
                n.totalWeight = current.totalWeight.add(rule.getWeight());
                n.connections_in = con;
                n.memory = new Memory(current.memory);
                n.goal = true;
                nodes.add(n);
                continue;
            }

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
                    || (n.totalWeight.compareTo(current.totalWeight.add(rule.getWeight()))) == 1)) {

                Connection con = new Connection(current, n, rule);
                n.totalWeight = current.totalWeight.add(rule.getWeight());
                n.connections_in = con;
            }
            nextNodes.add(n);
        }
        return nextNodes;
    }

    private List<Rule> getPlanForGoal(Rule goal, Rule currentRule, Memory memory, List<Rule> allRules, Plan currentPlan) {

        Node root = new Node();
        root.memory = new Memory(memory);
        root.totalWeight = BigDecimal.ZERO;
        List<Node> nodes = new ArrayList<>();
        nodes.add(root);
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            List<Node> next_nodes = getNodesThatArePossible(current, allRules, nodes);
            current.visited = true;
            queue.addAll(next_nodes);
            //System.out.println("Current: " + (current.connections_in == null ? "root" : current.connections_in.rule.toString()) + " Added: " + next_nodes.stream().map(n -> n.connections_in.rule.toString()).reduce("", (s1, s2) -> s1 + "; " +s2));
        }
        //System.out.println("All Rules");
        //System.out.println(allRules.stream().map(rule -> rule.toString()).reduce("", (s1, s2) -> s1 + "\n " +s2));

        //System.out.println("Nodes");
        //System.out.println(nodes.stream().map(n -> n.connections_in == null ? "root" : n.connections_in.rule.toString()).reduce("", (s1, s2) -> s1 + "\n " +s2));

        Optional<Node> node = nodes.stream().filter(n -> n.goal).findFirst();
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
        boolean goal = false;

        @Override
        public int compareTo(Node o) {
            return totalWeight.compareTo(o.totalWeight);
        }
    }
}
