package at.tugraz.ist.compiler.ruleGenerator;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.rule.DiagnosticPosition;
import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;
import fr.uga.pddl4j.parser.Connective;
import fr.uga.pddl4j.parser.Domain;
import fr.uga.pddl4j.parser.Exp;
import fr.uga.pddl4j.parser.Op;
import fr.uga.pddl4j.parser.Problem;
import fr.uga.pddl4j.parser.Symbol;
import fr.uga.pddl4j.parser.Symbol.Kind;
import fr.uga.pddl4j.parser.TypedSymbol;

public class RuleGenerator {
    private List<Predicate> predicates = new ArrayList<>();
    private List<Rule> rules = new ArrayList<>();

    /*
     * public RuleGenerator(ParseTree DomainParseTree, ParseTree ProblemParseTree )
     * { List<PddlPredicate> predicates = new
     * DomainPredicateVisitor().visit(DomainParseTree); List<PddlAction> actions =
     * new DomainActionsVisitor(predicates).visit(DomainParseTree);
     * 
     * List<PddlObject> objects = new
     * ProblemObjectVisitor().visit(ProblemParseTree);
     * 
     * List<PddlGroundedPredicate> init = new
     * ProblemInitVisitor().visit(ProblemParseTree); List<PddlGroundedPredicate>
     * goal = new ProblemGoalVisitor().visit(ProblemParseTree); }
     */

    private class SymbolSet {
        HashMap<String, String> replace = new HashMap<String, String>();

        public SymbolSet(HashMap<String, String> replace) {
            this.replace = replace;
        }

        String replace(String variable) {
            return replace.get(variable);
        }
    }

    public RuleGenerator(Domain domain, Problem problem) {
        for (Exp exp : problem.getInit()) {
            if (exp.getAtom().get(0).getKind() == Kind.PREDICATE) {
                String initGrounded = String.join(" ",
                        exp.getAtom().stream().map(p -> p.getImage()).collect(Collectors.toList()));
                predicates.add(new Predicate(initGrounded));
            } else {
                assertTrue(false);
            }
        }

        List<String> objects = new ArrayList<>();
        for (TypedSymbol object : problem.getObjects()) {
            objects.add(object.getImage());
        }

        for (Op op : domain.getOperators()) {
            rules.addAll(getGroundedRule(objects, op));
        }

        List<Predicate> goalCond = new ArrayList<>();
        for (Exp pre : problem.getGoal().getChildren()) {
            if (pre.getAtom().get(0).getKind() == Kind.PREDICATE) {
                List<String> grounded = new ArrayList<>();
                for (Symbol atom : pre.getAtom()) {
                    if (atom.getKind() == Kind.PREDICATE || atom.getKind() == Kind.CONSTANT)
                        grounded.add(atom.getImage());
                    else{
                        assertTrue(false);
                    }
                }
                goalCond.add(new Predicate(String.join(" ", grounded)));
            } else {
                assertTrue(false);
            }
        }
        rules.add(new Rule("goal", "goal", new ArrayList<>(), goalCond, new HashMap<>(), new DiagnosticPosition(0, 0, 0, 0, "")));
    }

    private List<Rule> getGroundedRule(List<String> objects, Op op) {
        List<SymbolSet> symbolSets = getSymbolSets(objects, op);

        String action = op.getName().getImage();
        List<Rule> rules = new ArrayList<>();
        for (SymbolSet symbolSet : symbolSets) {
            List<Predicate> groundedPreCond = new ArrayList<>();
            if (op.getPreconditions().getConnective().equals(Connective.AND)) {
                groundedPreCond = getGroundedCondition(op.getPreconditions(), symbolSet, false);
            } else {
                assertTrue(false);
            }

            List<Predicate> groundedPostCond = new ArrayList<>();
            if (op.getEffects().getConnective().equals(Connective.AND)) {
                groundedPostCond = getGroundedCondition(op.getEffects(), symbolSet, true);
            } else {
                assertTrue(false);
            }
            Map<String, String> parameters = new HashMap<String, String>();

            for(Symbol sym : op.getParameters()) {
                parameters.put(sym.getImage().substring(1), symbolSet.replace(sym.getImage()));
            }
            rules.add(new Rule(action, null, groundedPostCond, groundedPreCond, parameters, new DiagnosticPosition(0, 0, 0, 0, "")));
        }
        return rules;
    }

    private List<SymbolSet> getSymbolSets(List<String> objects, Op op) {
        List<SymbolSet> symbolSets = new ArrayList<>();
        List<TypedSymbol> parameters = op.getParameters();
        int par_size = parameters.size();
        int obj_size = objects.size();

        for (int i = 0; i < Math.pow(obj_size, par_size); i++) {
            HashMap<String, String> replace = new HashMap<String, String>();
            int temp = i;
            for (int parIndex = 0; parIndex < par_size; parIndex++) {
                int objIndex = temp % obj_size;
                replace.put(parameters.get(parIndex).getImage(), objects.get(objIndex));
                temp = temp / obj_size;
            }
            symbolSets.add(new SymbolSet(replace));
        }
        return symbolSets;
    }

    private List<Predicate> getGroundedCondition(Exp condition, SymbolSet symbolSet, boolean allowNot) {
        List<Predicate> groundedCond = new ArrayList<>();
        for (Exp pre : condition.getChildren()) {
            boolean deletion = false;
            if (allowNot && pre.getConnective().equals(Connective.NOT)) {
                pre = pre.getChildren().get(0);
                deletion = true;
            }

            if (pre.getAtom().get(0).getKind() == Kind.PREDICATE) {
                List<String> grounded = new ArrayList<>();
                for (Symbol atom : pre.getAtom()) {
                    if (atom.getKind() == Kind.PREDICATE)
                        grounded.add(atom.getImage());
                    else if (atom.getKind() == Kind.VARIABLE) {
                        grounded.add(symbolSet.replace(atom.getImage()));
                    }
                }
                groundedCond.add(new Predicate(String.join(" ", grounded), deletion));
            } else {
                assertTrue(false);
            }
        }
        return groundedCond;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public Memory getMemory() {
        return new Memory(predicates);
    }
}