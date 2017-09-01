package at.tugraz.ist.compiler;

public class Setting {
    private final String pathToJavaFiles;
    private final String pathToRuleFile;
    private final boolean compiling;

    public Setting(String pathToJavaFiles, String pathToRuleFile, boolean compiling) {
        this.pathToJavaFiles = pathToJavaFiles;
        this.pathToRuleFile = pathToRuleFile;
        this.compiling = compiling;
    }

    public String getPathToJavaFiles() {
        return pathToJavaFiles;
    }

    public String getPathToRuleFile() {
        return pathToRuleFile;
    }

    public boolean isCompiling() {
        return compiling;
    }
}
