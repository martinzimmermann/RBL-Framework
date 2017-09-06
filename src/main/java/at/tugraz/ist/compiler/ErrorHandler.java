package at.tugraz.ist.compiler;

import at.tugraz.ist.compiler.rule.DiagnosticPosition;

public class ErrorHandler {

    private static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static ErrorHandler instance;
    private int errorCount = 0;

    private ErrorHandler() {
    }

    public static ErrorHandler Instance() {
        if (instance == null)
            instance = new ErrorHandler();
        return instance;
    }

    public void reportWarning(Type type, String message) {
        if (type == Type.Input)
            System.out.println(ANSI_YELLOW + "Warning: " + message + ANSI_RESET);
    }

    public void reportWarning(Type type, DiagnosticPosition diagnosticPosition, String message) {
        if (type == Type.Input)
            System.out.println(ANSI_YELLOW + "Warning: " + message + ANSI_RESET);
    }

    public void reportError(Type type, DiagnosticPosition diagnosticPosition, String Message) {
        errorCount++;
        System.out.println(ANSI_RED + Message);
        System.out.println(diagnosticPosition.getPrettyPrint() + ANSI_RESET);
    }

    public boolean hasErrors() {
        return errorCount != 0;
    }

    public enum Type {
        Input, Lexical, Syntactical, Interpreter
    }

    public void printErrorCount() {
        System.out.println(ANSI_RED + errorCount + " Errors found" + ANSI_RESET);
    }

    public void reset()
    {
        errorCount = 0;
    }
}
