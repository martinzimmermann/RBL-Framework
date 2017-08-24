package at.tugraz.ist.compiler.ruleGenerator;

import at.tugraz.ist.compiler.antlr.RuleGrammarBaseVisitor;
import at.tugraz.ist.compiler.antlr.RuleGrammarParser;
import at.tugraz.ist.compiler.antlr.RuleGrammarParser.*;
import at.tugraz.ist.compiler.rule.Atom;
import at.tugraz.ist.compiler.rule.Predicate;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class RuleGeneratorVisitor extends RuleGrammarBaseVisitor<List<Atom>> {

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
        Predicate pred = new Predicate(ctx.ID().getText());
        List<Atom> atoms = new ArrayList<>();
        atoms.add(pred);
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


        return super.visitR_rule(ctx);
    }
}
