package at.tugraz.ist.compiler.ruleGenerator;

import at.tugraz.ist.compiler.antlr.RuleGrammarBaseVisitor;
import at.tugraz.ist.compiler.antlr.RuleGrammarParser.*;
import at.tugraz.ist.compiler.rule.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RuleGeneratorVisitor extends RuleGrammarBaseVisitor<List<Atom>> {

    private boolean interpret;

    public RuleGeneratorVisitor(boolean interpret) {
        this.interpret = interpret;
    }

    @Override
    public List<Atom> visitProgram(ProgramContext ctx) {
        List<Atom> atoms = new ArrayList<>();

        for (ParseTree tree : ctx.children) {
            List<Atom> atom = visit(tree);
            if (atom != null)
                atoms.addAll(atom);
        }
        return atoms;
    }

    @Override
    public List<Atom> visitMemory(MemoryContext ctx) {
        List<Atom> atoms = new ArrayList<>();

        for (PredicateContext predicate : ctx.predicate()) {
            List<Atom> atom = visit(predicate);
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
    public List<Atom> visitR_rules(R_rulesContext ctx) {
        List<Atom> atoms = new ArrayList<>();

        for (R_ruleContext rule : ctx.r_rule()) {
            List<Atom> atom = visit(rule);
            atoms.addAll(atom);
        }
        return atoms;
    }

    @Override
    public List<Atom> visitR_rule(R_ruleContext ctx) {
        List<Atom> atoms = new ArrayList<>();

        RuleBuilder rule = new RuleBuilder();
        rule = rule.setAction(ctx.action().getText())
                .setDiagnosticPosition(new DiagnosticPosition(ctx.getStart().getLine(),
                        ctx.getStart().getCharPositionInLine(),
                        ctx.getStop().getLine(),
                        ctx.getStop().getCharPositionInLine(),
                        ctx.getText()));

        if (ctx.Goal != null) rule = rule.setGoal(ctx.Goal.getText());
        if (ctx.WorldAddtion != null) rule = rule.setWorldAddition(new Predicate(ctx.WorldAddtion.getText()));
        if (ctx.rule_goal() != null) rule = rule.setRuleGoal(Double.parseDouble(ctx.rule_goal().getText()));

        if (ctx.preconditions() != null) {
            List<Atom> preconditions = visit(ctx.preconditions());
            rule = rule.setPreconditions(preconditions.stream().map(obj -> (Predicate) obj).collect(Collectors.toList()));
        }

        if (ctx.alist() != null) {
            AlphaList aList = new AlphaListVisitor().visit(ctx.alist());
            rule = rule.setAlphaList(aList);
        } else {
            rule = rule.setAlphaList(AlphaList.getDefaultAlphaList());
        }

        if (ctx.worldDeletions() != null) {
            List<Atom> worldDeletions = visit(ctx.worldDeletions());
            rule = rule.setWorldDeletions(worldDeletions.stream().map(obj -> (Predicate) obj).collect(Collectors.toList()));
        }

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

    @Override
    public List<Atom> visitWorldDeletions(WorldDeletionsContext ctx) {
        List<Atom> atoms = new ArrayList<>();

        for (PredicateContext predicate : ctx.predicate()) {
            List<Atom> atom = visit(predicate);
            atoms.addAll(atom);
        }
        return atoms;
    }
}
