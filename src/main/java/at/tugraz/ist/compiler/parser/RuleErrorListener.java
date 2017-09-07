package at.tugraz.ist.compiler.parser;

import at.tugraz.ist.compiler.ErrorHandler;
import at.tugraz.ist.compiler.rule.DiagnosticPosition;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

class RuleErrorListener extends BaseErrorListener {
    private int errorCount = 0;

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                            int charPositionInLine, String msg, RecognitionException e) {
        errorCount++;
        ErrorHandler.Instance().reportError(ErrorHandler.Type.Lexical, new DiagnosticPosition(line, charPositionInLine, line, charPositionInLine, msg), null);
    }

    public int getErrorCount() {
        return errorCount;
    }
}