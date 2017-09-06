public class DiagnosticPosition {
    private int startLineNumber;
    private int startColoum;
    private int endLineNumber;
    private int endColoum;
    private String text;
    private String constructorParameters;

    public DiagnosticPosition(int startLineNumber, int startColoum, int endLineNumber, int endColoum, String text)
    {
        this.startLineNumber = startLineNumber;
        this.startColoum = startColoum;
        this.endLineNumber = endLineNumber;
        this.endColoum = endColoum;
        this.text = text;
    }

    public String getPrettyPrint(){
        return startLineNumber + ":" + startColoum +": " + text;
    }

    public String getConstructorParameters() {
        return startLineNumber + ", " + startColoum + ", " + endLineNumber + ", " + endColoum + ", \"" + text + "\"";
    }
}
