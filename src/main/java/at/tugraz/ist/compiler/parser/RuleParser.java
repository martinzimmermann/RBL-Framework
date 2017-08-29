package at.tugraz.ist.compiler.parser;

import at.tugraz.ist.compiler.antlr.RuleGrammarParser;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class RuleParser {
    private final TokenStream tokenstream;

    public RuleParser(TokenStream stream) {
        tokenstream = stream;
    }

    public int getErrorCount() {
        RuleGrammarParser parser = new RuleGrammarParser(tokenstream);

        parser.removeErrorListeners();
        RuleErrorListener errorListener = new RuleErrorListener();
        parser.addErrorListener(errorListener);
        parser.program();

        tokenstream.seek(0);

        return errorListener.getErrorCount();
    }

    public ParseTree getParseTree() {
        RuleGrammarParser parser = new RuleGrammarParser(tokenstream);
        parser.removeErrorListeners();

        ParseTree tree = parser.program();

        tokenstream.seek(0);
        return tree;
    }

}
