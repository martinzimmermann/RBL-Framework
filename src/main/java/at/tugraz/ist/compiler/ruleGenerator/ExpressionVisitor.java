package at.tugraz.ist.compiler.ruleGenerator;

import at.tugraz.ist.compiler.antlr.RuleGrammarBaseVisitor;
import at.tugraz.ist.compiler.antlr.RuleGrammarParser;

public class ExpressionVisitor extends RuleGrammarBaseVisitor<String> {

    @Override
    public String visitUnarySignExpr(RuleGrammarParser.UnarySignExprContext ctx) {
        if(ctx.sign().getText().equals("-"))
            return "new Big(0).minus(" + visit(ctx.value()) +")";
        else
            return visit(ctx.value());
    }

    @Override
    public String visitMulopExpr(RuleGrammarParser.MulopExprContext ctx) {
        if(ctx.mulop().getText().equals("*"))
            return visit(ctx.LHS) + ".times(" + visit(ctx.RHS) +")";
        else
            return visit(ctx.LHS) + ".div(" + visit(ctx.RHS) +")";
    }

    @Override
    public String visitSignExpr(RuleGrammarParser.SignExprContext ctx) {
        if(ctx.sign().getText().equals("-"))
            return visit(ctx.LHS) + ".minus(" + visit(ctx.RHS) +")";
        else
            return visit(ctx.LHS) + ".plus(" + visit(ctx.RHS) +")";
    }

    @Override
    public String visitBraceValue(RuleGrammarParser.BraceValueContext ctx) {
        return "(" + visit(ctx.expr()) + ")";
    }

    @Override
    public String visitNumValue(RuleGrammarParser.NumValueContext ctx) {
        return "new Big(" + ctx.getText() + ")";
    }

    @Override
    public String visitVarValue(RuleGrammarParser.VarValueContext ctx) {
        return ctx.getText();
    }
}
