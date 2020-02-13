package at.tugraz.ist.compiler.ruleGenerator;

import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.pddl.DomainActionsVisitor;
import at.tugraz.ist.compiler.pddl.DomainPredicateVisitor;
import at.tugraz.ist.compiler.pddl.PddlAction;
import at.tugraz.ist.compiler.pddl.PddlGroundedPredicate;
import at.tugraz.ist.compiler.pddl.PddlObject;
import at.tugraz.ist.compiler.pddl.PddlPredicate;
import at.tugraz.ist.compiler.pddl.ProblemGoalVisitor;
import at.tugraz.ist.compiler.pddl.ProblemInitVisitor;
import at.tugraz.ist.compiler.pddl.ProblemObjectVisitor;
import at.tugraz.ist.compiler.rule.Atom;
import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;
import fr.uga.pddl4j.parser.Domain;
import fr.uga.pddl4j.parser.Exp;
import fr.uga.pddl4j.parser.Problem;
import fr.uga.pddl4j.parser.Symbol.Kind;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RuleGenerator {
    private List<Predicate> predicates = new ArrayList<>();

    /*public RuleGenerator(ParseTree DomainParseTree, ParseTree ProblemParseTree ) {
        List<PddlPredicate> predicates = new DomainPredicateVisitor().visit(DomainParseTree);
        List<PddlAction> actions = new DomainActionsVisitor(predicates).visit(DomainParseTree);

        List<PddlObject> objects = new ProblemObjectVisitor().visit(ProblemParseTree);

        List<PddlGroundedPredicate> init = new ProblemInitVisitor().visit(ProblemParseTree);
        List<PddlGroundedPredicate> goal = new ProblemGoalVisitor().visit(ProblemParseTree);
    }*/

	public RuleGenerator(Domain domain, Problem problem) {
        for (Exp exp : problem.getInit()) {
            if(exp.getAtom().get(0).getKind() == Kind.PREDICATE) {
                String initGrounded = String.join("_", exp.getAtom().stream().map(p -> p.getImage()).collect(Collectors.toList()));
                predicates.add(new Predicate(initGrounded));
            }
        }

        

	}

	public List<Rule> getRules() {
        return null;
    }

    public Memory getMemory() {
        return new Memory(predicates);
    }
}