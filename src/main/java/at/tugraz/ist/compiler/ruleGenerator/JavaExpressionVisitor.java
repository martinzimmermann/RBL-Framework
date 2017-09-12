package at.tugraz.ist.compiler.ruleGenerator;

import at.tugraz.ist.compiler.antlr.RuleGrammarBaseVisitor;
import at.tugraz.ist.compiler.antlr.RuleGrammarParser;

public class JavaExpressionVisitor extends RuleGrammarBaseVisitor<String> {

    @Override
    public String visitUnarySignExpr(RuleGrammarParser.UnarySignExprContext ctx) {
        if(ctx.sign().getText().equals("-"))
            return visit(ctx.value()) +".negate()";
        else
            return visit(ctx.value());
    }

    @Override
    public String visitMulopExpr(RuleGrammarParser.MulopExprContext ctx) {
        if(ctx.mulop().getText().equals("*"))
            return visit(ctx.LHS) + ".multiply(" + visit(ctx.RHS) +")";
        else
            return visit(ctx.LHS) + ".divide(" + visit(ctx.RHS) +")";
    }

    @Override
    public String visitSignExpr(RuleGrammarParser.SignExprContext ctx) {
        if(ctx.sign().getText().equals("-"))
            return visit(ctx.LHS) + ".subtract(" + visit(ctx.RHS) +")";
        else
            return visit(ctx.LHS) + ".add(" + visit(ctx.RHS) +")";
    }

    @Override
    public String visitBraceValue(RuleGrammarParser.BraceValueContext ctx) {
        return "(" + visit(ctx.expr()) + ")";
    }

    @Override
    public String visitNumValue(RuleGrammarParser.NumValueContext ctx) {
        return "new BigDecimal(" + ctx.getText() + ")";
    }

    @Override
    public String visitVarValue(RuleGrammarParser.VarValueContext ctx) {
        return ctx.getText();
    }
}
