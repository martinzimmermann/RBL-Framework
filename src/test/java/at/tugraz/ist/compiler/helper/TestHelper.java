package at.tugraz.ist.compiler.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestHelper {

    private TestHelper() {}

    public static List<String> getAllFilesInPath(String path)
    {
        List<String> paths = new ArrayList<>();

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                paths.add(file.getPath());
            }
        }
        return paths;
    }

    public static String shortPath(String path) {
        return path.replace("src\\test\\resources\\compiler\\", "");
    }
}
