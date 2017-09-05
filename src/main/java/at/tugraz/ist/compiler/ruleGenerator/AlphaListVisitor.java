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
            list.add(new AlphaEntry(parts[0], parts[1]));
        }

        if (ctx.expr() != null)
            list.add(new AlphaEntry(ctx.expr().getText()));

        return new AlphaList(list);
    }
}
