package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.Setting;

import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassCompiler {
    public static void compileClasses(Setting setting) throws IOException {
        javax.tools.JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager sjfm = jc.getStandardFileManager(null, null, null);
        String path = setting.getPathToJavaFiles();
        List<String> files;

        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            files = paths.filter(Files::isRegularFile).map(Path::toString).collect(Collectors.toList());
        }

        Iterable fileObjects = sjfm.getJavaFileObjects(files.toArray(new String[]{}));

        Path path2 = Files.createTempDirectory("temp" );
        String folderPath = path2.normalize().toString();
        String[] options = new String[]{"-d", folderPath};
        jc.getTask(null, null, null, Arrays.asList(options), null, fileObjects).call();
        sjfm.close();

        URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class<URLClassLoader> urlClass = URLClassLoader.class;
        Method method = null;
        try {
            method = urlClass.getDeclaredMethod("addURL", new Class[]{URL.class});
            method.setAccessible(true);
            method.invoke(urlClassLoader, new Object[]{path2.toUri().toURL()});
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
