package at.tugraz.ist.compiler.ruleGenerator;

import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.rule.Atom;
import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RuleGenerator {
    private List<Atom> atoms = null;

    public RuleGenerator(ParseTree parseTree) {
        atoms = new RuleGeneratorVisitor().visit(parseTree);
    }

    public List<Rule> getRules() {
        return atoms.stream().filter(atom -> atom instanceof Rule).map(atom -> (Rule) atom).collect(Collectors.toList());
    }

    public Memory getMemory() {
        List<Predicate> predicates = atoms.stream().filter(atom -> atom instanceof Predicate).map(atom -> (Predicate) atom).collect(Collectors.toList());
        return new Memory(predicates);
    }
}