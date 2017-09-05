package at.tugraz.ist.compiler;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ErrorHandler {

    enum Type {
        Input, Lexical, Syntactical
    }

    private static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static ErrorHandler instance;
    private ErrorHandler(){}

    public static ErrorHandler Instance()
    {
        if(instance == null)
            instance = new ErrorHandler();
        return instance;
    }

    public void reportWarning(Type type, int lineNumber, int colNumber, String message){
        if(type == Type.Input)
            System.out.println(ANSI_YELLOW + "Warning: " + message + ANSI_RESET);
        else
            throw new NotImplementedException();
    }

    public void reportError(Type type, int lineNumber, int colNumber, String Message){
        throw new NotImplementedException();
    }
}
