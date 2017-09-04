package at.tugraz.ist.compiler;

import java.net.URL;

public class Setting {
    private final String pathToJavaFiles;
    private final String pathToRuleFile;
    private final boolean compiling;
    private final int numberOfRuns;
    private String outputPath;
    private URL classPath;

    public Setting(String pathToJavaFiles, String pathToRuleFile, boolean compiling, int numberOfRuns, String outputPath) {
        this.pathToJavaFiles = pathToJavaFiles;
        this.pathToRuleFile = pathToRuleFile;
        this.compiling = compiling;
        this.numberOfRuns = numberOfRuns;
        this.outputPath = outputPath;
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

    public void setClassPath(URL classPath) {
        this.classPath = classPath;
    }

    public URL getClassPath() {
        return classPath;
    }

    public String getPackageName() {
        return "compiler";
    }

    public String getOutputPath() {
        return outputPath;
    }
}
