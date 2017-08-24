package at.tugraz.ist.compiler.parser;

import at.tugraz.ist.compiler.antlr.RuleGrammarLexer;
import org.antlr.v4.runtime.*;

import java.io.IOException;
import java.nio.file.Path;

public class RuleLexer {
    final Path  filepath;
    final String source;


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
        RuleErrorListner errorListner = new RuleErrorListner();
        lexer.addErrorListener(errorListner);
        lexer.getAllTokens();

        return errorListner.getErrorCount();
    }

    private RuleGrammarLexer getLexer() throws IOException {
        return filepath == null ?
                new RuleGrammarLexer(CharStreams.fromString(source)) :
                new RuleGrammarLexer(CharStreams.fromPath(filepath));
    }

    public TokenStream getTokenStream() throws IOException {
        RuleGrammarLexer lexer = getLexer();
        lexer.removeErrorListeners();

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return tokens;
    }
}
