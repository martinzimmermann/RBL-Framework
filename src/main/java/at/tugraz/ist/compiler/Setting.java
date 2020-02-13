package at.tugraz.ist.compiler;

public class Setting {
    private final String pathToDomainFile;
    private final String pathToProblemFile;
    private final String packageName;
    private final String outputPath;
    private final boolean deferred;

    public enum PlanFinder { BottomUp, TopDown, Best}

    public Setting(String pathToDomainFile, String pathToProblemFile, String outputPath, String packageName, boolean deferred) {
        this.pathToDomainFile = pathToDomainFile;
        this.pathToProblemFile = pathToProblemFile;
        this.outputPath = outputPath;
        this.packageName = packageName;
        this.deferred = deferred;
    }

    public String getPathToDomainFile() {
        return pathToDomainFile;
    }

    public String getPathToProblemFile() {
        return pathToProblemFile;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public boolean isDeferred() {
        return deferred;
    }
}
