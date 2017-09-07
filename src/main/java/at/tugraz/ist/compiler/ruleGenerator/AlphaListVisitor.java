package at.tugraz.ist.compiler.ruleGenerator;

import at.tugraz.ist.compiler.antlr.RuleGrammarBaseVisitor;
import at.tugraz.ist.compiler.antlr.RuleGrammarParser;
import at.tugraz.ist.compiler.rule.AlphaEntry;
import at.tugraz.ist.compiler.rule.AlphaList;

import java.util.ArrayList;
import java.util.List;

public class AlphaListVisitor extends RuleGrammarBaseVisitor<AlphaList> {

    @Override
    public AlphaList visitAlist(RuleGrammarParser.AlistContext ctx) {
        List<AlphaEntry> list = new ArrayList<>();

        for (RuleGrammarParser.AlistentryContext entry : ctx.alistentry()) {
            String[] parts = entry.getText().split(":");
            String expresion = new ExpressionVisitor().visit(entry.expr());
            list.add(new AlphaEntry(parts[0], expresion));
        }

        AlphaEntry defaultEntry = null;
        if (ctx.expr() != null)
        {
            String expresion = new ExpressionVisitor().visit(ctx.expr());
            defaultEntry = new AlphaEntry(expresion);
        }

        return new AlphaList(list, defaultEntry);
    }
}
