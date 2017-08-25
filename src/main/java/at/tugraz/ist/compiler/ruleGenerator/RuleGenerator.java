package at.tugraz.ist.compiler.ruleGenerator;

import at.tugraz.ist.compiler.rule.Atom;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

public class RuleGenerator {
    private final ParseTree parseTree;

    public RuleGenerator(ParseTree parseTree)
    {
        this.parseTree = parseTree;
    }

    public List<Atom> getRules()
    {
        List<Atom> list = new RuleGeneratorVisitor().visit(parseTree);
        return list;
    }
}
