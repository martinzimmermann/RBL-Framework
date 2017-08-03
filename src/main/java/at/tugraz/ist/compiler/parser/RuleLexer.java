package at.tugraz.ist.compiler.parser;

import at.tugraz.ist.compiler.antlr.RuleGrammarLexer;
import org.antlr.v4.runtime.*;

import java.io.IOException;

public class RuleLexer {
    String filepath;


    public RuleLexer(String path) {
        filepath = path;
    }

    public int getErrorCount() throws IOException {

        RuleGrammarLexer lexer = new RuleGrammarLexer(new ANTLRFileStream(filepath));

        lexer.removeErrorListeners();
        RuleErrorListner errorListner = new RuleErrorListner();
        lexer.addErrorListener(errorListner);
        lexer.getAllTokens();

        return errorListner.getErrorCount();
    }

    public TokenStream getTokenStream() throws IOException {
        RuleGrammarLexer lexer = new RuleGrammarLexer(new ANTLRFileStream(filepath));
        lexer.removeErrorListeners();

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return tokens;
    }
}
