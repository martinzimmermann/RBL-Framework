package at.tugraz.ist.compiler.ruleGenerator;

import at.tugraz.ist.compiler.ErrorHandler;
import at.tugraz.ist.compiler.antlr.RuleGrammarBaseVisitor;
import at.tugraz.ist.compiler.antlr.RuleGrammarParser.*;
import at.tugraz.ist.compiler.rule.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RuleGeneratorVisitor extends RuleGrammarBaseVisitor<List<Atom>> {

    @Override
    public List<Atom> visitProgram(ProgramContext ctx) {
        List<Atom> atoms = new ArrayList<>();
        if(ctx.children == null)
            return atoms;

        for (ParseTree tree : ctx.children) {
            List<Atom> atom = visit(tree);
            if (atom != null)
                atoms.addAll(atom);
        }
        return atoms;
    }

    @Override
    public List<Atom> visitPredicate(PredicateContext ctx) {
        Predicate predicate = new Predicate(ctx.ID().getText());
        List<Atom> atoms = new ArrayList<>();
        atoms.add(predicate);
        return atoms;
    }

    @Override
    public List<Atom> visitR_rule(R_ruleContext ctx) {
        List<Atom> atoms = new ArrayList<>();

        RuleBuilder rule = new RuleBuilder();
        DiagnosticPosition diagnosticPosition = new DiagnosticPosition(ctx.getStart().getLine(),
                ctx.getStart().getCharPositionInLine(),
                ctx.getStop().getLine(),
                ctx.getStop().getCharPositionInLine(),
                ctx.getText());

        rule = rule.setAction(ctx.action().getText())
                .setDiagnosticPosition(diagnosticPosition);

        if (ctx.Goal != null) rule = rule.setGoal(ctx.Goal.getText());

        if (ctx.preconditions() != null) {
            List<Atom> preconditions = visit(ctx.preconditions());
            rule = rule.setPreconditions(preconditions.stream().map(obj -> (Predicate) obj).collect(Collectors.toList()));
        }

        List<Predicate> postConditions = new PostConditionVisitor().visit(ctx.postconditions());
        rule = rule.setPostConditions(postConditions);

        atoms.add(rule.createRule());
        return atoms;
    }

    @Override
    public List<Atom> visitPreconditions(PreconditionsContext ctx) {
        List<Atom> atoms = new ArrayList<>();

        for (PredicateContext predicate : ctx.predicate()) {
            List<Atom> atom = visit(predicate);
            atoms.addAll(atom);
        }
        return atoms;
    }
}
