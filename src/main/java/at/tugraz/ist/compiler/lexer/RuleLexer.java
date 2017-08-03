package at.tugraz.ist.compiler.lexer;

import at.tugraz.ist.compiler.antlr.RuleGrammarLexer;
import org.antlr.v4.runtime.*;

import java.io.IOException;

public class RuleLexer {
    int errorCount = 0;

    public RuleLexer(String path) throws IOException {
        RuleGrammarLexer lexer = new RuleGrammarLexer(new ANTLRFileStream(path));

        lexer.removeErrorListeners();
        lexer.addErrorListener(new BaseErrorListener() {

            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                                    int charPositionInLine, String msg, RecognitionException e) {
                errorCount++;
            }
        });
        lexer.getAllTokens();
    }

    public int getErrorCount() {
        return errorCount;
    }
}
