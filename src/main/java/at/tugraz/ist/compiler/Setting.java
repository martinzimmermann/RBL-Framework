package at.tugraz.ist.compiler;

public class Setting {
    private final String pathToJavaFiles;
    private final String pathToRuleFile;
    private final boolean compiling;
    private final int numberOfRuns;
    private final String packageName;
    private final String outputPath;

    public Setting(String pathToJavaFiles, String pathToRuleFile, boolean compiling, int numberOfRuns, String outputPath, String packageName) {
        this.pathToJavaFiles = pathToJavaFiles;
        this.pathToRuleFile = pathToRuleFile;
        this.compiling = compiling;
        this.numberOfRuns = numberOfRuns;
        this.outputPath = outputPath;
        this.packageName = packageName;
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

    public int getNumberOfRuns() {
        return numberOfRuns;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getOutputPath() {
        return outputPath;
    }
}
