package at.tugraz.ist.compiler.rule;

public class DiagnosticPosition {
    private final int startLineNumber;
    private final int startColumn;
    private final int endLineNumber;
    private final int endColumn;
    private final String text;

    public DiagnosticPosition(int startLineNumber, int startColumn, int endLineNumber, int endColumn, String text)
    {
        this.startLineNumber = startLineNumber;
        this.startColumn = startColumn;
        this.endLineNumber = endLineNumber;
        this.endColumn = endColumn;
        this.text = text;
    }

    public String getPrettyPrint(){
        return startLineNumber + ":" + startColumn +": " + text;
    }

    public String getConstructorParameters() {
        return startLineNumber + ", " + startColumn + ", " + endLineNumber + ", " + endColumn + ", \"" + text + "\"";
    }
}
