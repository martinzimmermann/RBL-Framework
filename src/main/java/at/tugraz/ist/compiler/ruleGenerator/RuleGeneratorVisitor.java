package at.tugraz.ist.compiler.ruleGenerator;

import at.tugraz.ist.compiler.Setting;
import at.tugraz.ist.compiler.antlr.RuleGrammarBaseVisitor;
import at.tugraz.ist.compiler.antlr.RuleGrammarParser.*;
import at.tugraz.ist.compiler.rule.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RuleGeneratorVisitor extends RuleGrammarBaseVisitor<List<Atom>> {

    private Setting setting;

    public RuleGeneratorVisitor(Setting setting) {
        this.setting = setting;
    }

    @Override
    public List<Atom> visitProgram(ProgramContext ctx) {
        List<Atom> atoms = new ArrayList<>();

        for (ParseTree tree : ctx.children ) {
            List<Atom> atom = visit(tree);
            if(atom != null)
                atoms.addAll(atom);
        }
        return atoms;
    }

    @Override
    public List<Atom> visitMemory(MemoryContext ctx) {
        List<Atom> atoms = new ArrayList<>();

        for (PredicateContext predicate : ctx.predicate() ) {
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

        for (R_ruleContext rule : ctx.r_rule() ) {
            List<Atom> atom = visit(rule);
            atoms.addAll(atom);
        }
        return atoms;
    }

    @Override
    public List<Atom> visitR_rule(R_ruleContext ctx) {
        List<Atom> atoms = new ArrayList<>();

        RuleBuilder rule = new RuleBuilder();
        rule = rule.setSetting(setting)
                .setAction(ctx.action().getText());

        if(ctx.Goal != null) rule = rule.setGoal(ctx.Goal.getText());
        if(ctx.WorldAddtion != null) rule = rule.setWorldAddition(new Predicate(ctx.WorldAddtion.getText()));
        if(ctx.rule_goal() != null) rule = rule.setRuleGoal(Double.parseDouble(ctx.rule_goal().getText()));

        if(ctx.preconditions() != null) {
            List<Atom> preconditions  = visit(ctx.preconditions());
            rule = rule.setPreconditions(preconditions.stream().map(obj -> (Predicate) obj).collect(Collectors.toList()));
        }

        if(ctx.alist() != null) {
            AlphaList aList = new AlphaListVisitor().visit(ctx.alist());
            rule = rule.setAlphaList(aList);
        }
        else {
            rule = rule.setAlphaList(AlphaList.getDefaultAlphaList());
        }

        if(ctx.worldDeletions() != null){
            List<Atom> worldDeletions = visit(ctx.worldDeletions());
            rule = rule.setWorldDeletions(worldDeletions.stream().map(obj -> (Predicate) obj).collect(Collectors.toList()));
        }

        Rule concreteRule = null;
        try {
            concreteRule = rule.createRule();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            //TODO: report error
        }

        atoms.add(concreteRule);
        return atoms;
    }

    @Override
    public List<Atom> visitPreconditions(PreconditionsContext ctx) {
        List<Atom> atoms = new ArrayList<>();

        for (PredicateContext predicate : ctx.predicate() ) {
            List<Atom> atom = visit(predicate);
            atoms.addAll(atom);
        }
        return atoms;
    }

    @Override
    public List<Atom> visitWorldDeletions(WorldDeletionsContext ctx) {
        List<Atom> atoms = new ArrayList<>();

        for (PredicateContext predicate : ctx.predicate() ) {
            List<Atom> atom = visit(predicate);
            atoms.addAll(atom);
        }
        return atoms;
    }
}
