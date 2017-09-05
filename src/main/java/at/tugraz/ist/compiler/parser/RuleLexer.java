package at.tugraz.ist.compiler.parser;

import at.tugraz.ist.compiler.antlr.RuleGrammarLexer;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.IOException;
import java.nio.file.Path;

public class RuleLexer {
    private final Path filepath;
    private final String source;


    public RuleLexer(Path path) {
        filepath = path;
        source = null;
    }

    public RuleLexer(String source) {
        filepath = null;
        this.source = source;
    }

    public int getErrorCount() throws IOException {

        RuleGrammarLexer lexer = getLexer();

        lexer.removeErrorListeners();
        RuleErrorListener errorListener = new RuleErrorListener();
        lexer.addErrorListener(errorListener);
        lexer.getAllTokens();

        return errorListener.getErrorCount();
    }

    private RuleGrammarLexer getLexer() throws IOException {
        return filepath == null ?
                new RuleGrammarLexer(CharStreams.fromString(source)) :
                new RuleGrammarLexer(CharStreams.fromPath(filepath));
    }

    public TokenStream getTokenStream() throws IOException {
        RuleGrammarLexer lexer = getLexer();
        lexer.removeErrorListeners();

        return new CommonTokenStream(lexer);
    }
}
