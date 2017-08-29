package at.tugraz.ist.compiler.parser;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

class RuleErrorListener extends BaseErrorListener {
    private int errorCount = 0;

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                            int charPositionInLine, String msg, RecognitionException e) {
        errorCount++;
    }

    public int getErrorCount() {
        return errorCount;
    }
}