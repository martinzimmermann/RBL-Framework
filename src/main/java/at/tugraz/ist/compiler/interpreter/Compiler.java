package at.tugraz.ist.compiler.interpreter;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Compiler {
    static void compileClassesAndAddToClassPath(String path)
    {
        /*JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager sjfm = jc.getStandardFileManager(null, null, null);
        String path = path + "\\"  + action + ".java";
        File javaFile = new File(path);
        Iterable fileObjects = sjfm.getJavaFileObjects(javaFile);

        Path path2 = Files.createTempDirectory("temp" );
        String folderPath = path2.normalize().toString();
        String[] options = new String[]{"-d", folderPath};
        jc.getTask(null, null, null, Arrays.asList(options), null, fileObjects).call();
        sjfm.close();

        URL[] urls = new URL[]{ path2.toUri().toURL()};
        URLClassLoader ucl = new URLClassLoader(urls);*/
    }
}
