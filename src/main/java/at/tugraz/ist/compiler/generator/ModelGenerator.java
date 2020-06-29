package at.tugraz.ist.compiler.generator;

import static org.junit.Assert.assertTrue;

import java.util.*;
import java.util.stream.Collectors;

import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.interpreter.Model;
import at.tugraz.ist.compiler.rule.*;
import fr.uga.pddl4j.parser.Connective;
import fr.uga.pddl4j.parser.Domain;
import fr.uga.pddl4j.parser.Exp;
import fr.uga.pddl4j.parser.Op;
import fr.uga.pddl4j.parser.Problem;
import fr.uga.pddl4j.parser.Symbol;
import fr.uga.pddl4j.parser.Symbol.Kind;
import fr.uga.pddl4j.parser.TypedSymbol;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ModelGenerator {
    private SortedSet<Predicate> predicates = new TreeSet<>();
    private SortedSet<Predicate> goalState = new TreeSet<>();

    private List<Action> actions = new ArrayList<>();
    private List<String> objects = new ArrayList<>();

    public ModelGenerator(Domain domain, Problem problem) {
        for (Exp exp : problem.getInit()) {
            if (exp.getAtom().get(0).getKind() == Kind.PREDICATE) {
                String initGrounded = String.join(" ",
                        exp.getAtom().stream().map(p -> p.getImage()).collect(Collectors.toList()));
                predicates.add(new Predicate(initGrounded));
            } else {
                throw new NotImplementedException();
            }
        }

        for (TypedSymbol object : problem.getObjects()) {
            objects.add(object.getImage());
        }

        for (Op op : domain.getOperators()) {
            actions.add(getAction(op));
        }

        for (Exp pre : problem.getGoal().getChildren()) {
            if (pre.getAtom().get(0).getKind() == Kind.PREDICATE) {
                List<String> grounded = new ArrayList<>();
                for (Symbol atom : pre.getAtom()) {
                    if (atom.getKind() == Kind.PREDICATE || atom.getKind() == Kind.CONSTANT)
                        grounded.add(atom.getImage());
                    else{
                        throw new NotImplementedException();
                    }
                }
                goalState.add(new Predicate(String.join(" ", grounded)));
            } else {
                throw new NotImplementedException();
            }
        }
    }

    private Action getAction(Op op) {
        String name = op.getName().getImage();
        List<String> parameters = op.getParameters().stream().map(s -> s.getImage()).collect(Collectors.toList());
        List<AtomicFormula> preCond = null;
        List<AtomicFormula> effects = null;

        if (op.getPreconditions().getConnective().equals(Connective.AND)) {
            preCond = getAtomicFormulas(op.getPreconditions(), false);
        } else {
            throw new NotImplementedException();
        }

        if (op.getEffects().getConnective().equals(Connective.AND)) {
            effects = getAtomicFormulas(op.getEffects(), true);
        } else {
            throw new NotImplementedException();
        }
        return new Action(name, new TreeSet<>(parameters), new TreeSet<>(preCond), new TreeSet<>(effects));
    }

    private List<AtomicFormula> getAtomicFormulas(Exp condition, boolean allowNot) {
        List<AtomicFormula> formulas = new ArrayList<>();
        for (Exp pre : condition.getChildren()) {
            boolean deletion = false;
            if (allowNot && pre.getConnective().equals(Connective.NOT)) {
                pre = pre.getChildren().get(0);
                deletion = true;
            }

            List<String> variables = new ArrayList<>();
            String predicate = null;
            if (pre.getAtom().get(0).getKind() == Kind.PREDICATE) {
                if (pre.getAtom().get(0).getKind() == Kind.PREDICATE)
                    predicate = pre.getAtom().get(0).getImage();
                else
                    throw new NotImplementedException();

                for (int i = 1; i < pre.getAtom().size(); i++)  {
                    Symbol atom = pre.getAtom().get(i);
                    if (atom.getKind() == Kind.VARIABLE) {
                        variables.add(atom.getImage());
                    }
                    else {
                        throw new NotImplementedException();
                    }
                }
            } else {
                throw new NotImplementedException();
            }
            formulas.add(new AtomicFormula(new Predicate(predicate, deletion), variables));
        }
        return formulas;
    }

    public Model getModel() {
        return new Model(objects, actions);
    }

    public Memory getMemory() {
        return new Memory(predicates);
    }

    public SortedSet<Predicate> getGoalState() {
        return new TreeSet<>(goalState);
    }
}