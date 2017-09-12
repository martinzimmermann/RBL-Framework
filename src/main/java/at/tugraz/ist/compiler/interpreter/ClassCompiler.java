package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.AlphaEntry;

import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassCompiler {
    public static void compileClasses(String pathToJavaFiles) throws IOException {
        javax.tools.JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager sjfm = jc.getStandardFileManager(null, null, null);
        List<String> files;

        try (Stream<Path> paths = Files.walk(Paths.get(pathToJavaFiles))) {
            files = paths.filter(Files::isRegularFile).map(Path::toString).collect(Collectors.toList());
        }

        Iterable fileObjects = sjfm.getJavaFileObjects(files.toArray(new String[]{}));

        Path path2 = Files.createTempDirectory("temp");
        String folderPath = path2.normalize().toString();
        String[] options = new String[]{"-d", folderPath};
        jc.getTask(null, null, null, Arrays.asList(options), null, fileObjects).call();
        sjfm.close();

        URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class<URLClassLoader> urlClass = URLClassLoader.class;
        Method method;
        try {
            method = urlClass.getDeclaredMethod("addURL", URL.class);
            method.setAccessible(true);
            method.invoke(urlClassLoader, path2.toUri().toURL());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static Function<BigDecimal, BigDecimal> getFunctionFromLambda(String lambda){
        // ob boy, runtime compilation :D
        String template = "import java.util.function.*;" +
                "import java.math.BigDecimal;\n" +
                "public class LambdaClass {\n" +
                "  public static Function<BigDecimal, BigDecimal> getLambda() {return (%s);}\n" +
                "}";

        String source = String.format(template, lambda);
        try {
        Path path2 = Files.createTempDirectory("temp");
        String folderPath = path2.normalize().toString();

        File root = new File(folderPath); // On Windows running on C:\, this is C:\java.
        File sourceFile = new File(root, "LambdaClass.java");
        sourceFile.getParentFile().mkdirs();
        Files.write(sourceFile.toPath(), source.getBytes(StandardCharsets.UTF_8));

        javax.tools.JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
        jc.run(null, null, null, sourceFile.getPath());

        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
            Class<?> cls = Class.forName("LambdaClass", true, classLoader);
            return (Function<BigDecimal, BigDecimal>) cls.getMethod("getLambda").invoke(null);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | IOException e) {
            e.printStackTrace();
            assert false;
            return null;
        }
    }
}
