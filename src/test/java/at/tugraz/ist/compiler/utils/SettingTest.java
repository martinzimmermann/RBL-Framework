package at.tugraz.ist.compiler.utils;

import at.tugraz.ist.compiler.Setting;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SettingTest {

    @Test
    public void ctor_test() {
        final String pathToJavaFiles = "abc";
        final String pathToRuleFile = "xyz";
        final boolean compiling = true;
        final int numberOfRuns = 10;
        final String outputPath = "def";
        final  String packageName = "package";
        final boolean deferred = true;
        final boolean libraryUsed = false;
        final Setting.PlanFinder planFinder = Setting.PlanFinder.Best;

        Setting setting = new Setting(pathToJavaFiles, pathToRuleFile, compiling, numberOfRuns, outputPath, packageName, deferred, libraryUsed, planFinder);

        assertEquals(pathToJavaFiles, setting.getPathToJavaFiles());
        assertEquals(pathToRuleFile, setting.getPathToRuleFile());
        assertEquals(compiling, setting.isCompiling());
        assertEquals(numberOfRuns, setting.getNumberOfRuns());
        assertEquals(outputPath, setting.getOutputPath());
        assertEquals(packageName, setting.getPackageName());
        assertEquals(deferred, setting.isDeferred());
        assertEquals(libraryUsed, setting.isLibraryUsed());
    }
}
