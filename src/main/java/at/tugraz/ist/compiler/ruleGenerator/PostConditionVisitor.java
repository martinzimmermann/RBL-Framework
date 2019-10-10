package at.tugraz.ist.compiler.ruleGenerator;

import at.tugraz.ist.compiler.antlr.RuleGrammarBaseVisitor;
import at.tugraz.ist.compiler.antlr.RuleGrammarParser.*;
import at.tugraz.ist.compiler.rule.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class PostConditionVisitor extends RuleGrammarBaseVisitor<List<Predicate>> {

    @Override
    public List<Predicate> visitPostconditions(PostconditionsContext ctx) {
        List<Predicate> postConditions = new ArrayList<>();

        for(PostconditionContext pCtx : ctx.postcondition()) {
            postConditions.addAll(visit(pCtx));
        }
        return postConditions;
    }

    @Override
    public List<Predicate> visitWorldAddition(WorldAdditionContext ctx) {
        return Arrays.asList(new Predicate(ctx.predicate().getText(), false));
    }

    @Override
    public List<Predicate> visitWorldDeletion(WorldDeletionContext ctx) {
        return Arrays.asList(new Predicate(ctx.predicate().getText(), true));
    }
}

