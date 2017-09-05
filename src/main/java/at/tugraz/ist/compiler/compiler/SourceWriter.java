package at.tugraz.ist.compiler.compiler;

import at.tugraz.ist.compiler.interpreter.Model;
import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class SourceWriter {

    private final String packageName;
    private final String packagePath;

    public SourceWriter(String outputPath, String packageName) {
        this.packageName = packageName;
        packagePath = outputPath + (outputPath.endsWith("\\") ? "" : "\\") + (this.packageName == null ? "" : this.packageName.replace(".", "\\"));

    }

    public void writeSource(Model model) throws IOException {
        writeFiles();
        replaceStupsInExecutor(model);
    }

    private void writeFiles() throws IOException {
        createFolder();
        writeFile("ActionFailedException.java");
        writeFile("AlphaEntry.java");
        writeFile("AlphaList.java");
        writeFile("Atom.java");
        writeFile("Executor.java");
        writeFile("InterpreterRule.java");
        writeFile("Memory.java");
        writeFile("Model.java");
        writeFile("NoPlanFoundException.java");
        writeFile("Plan.java");
        writeFile("PlanFinder.java");
        writeFile("Predicate.java");
        writeFile("Rule.java");
        writeFile("RuleAction.java");
    }

    private void createFolder() {
        boolean success = (new File(packagePath)).mkdirs();
        assert success;
    }

    private void replaceStupsInExecutor(Model model) throws IOException {
        File file = new File(packagePath, "Executor.java");
        Path path = Paths.get(file.getPath());
        Charset charset = StandardCharsets.UTF_8;
        String content = new String(Files.readAllBytes(path), charset);

        String rules = buildRuleString(model);
        content = content.replaceAll("// <replace with rules>", rules);

        String predicates = buildPredicateString(model);
        content = content.replaceAll("// <replace with predicates>", predicates);

        Files.write(path, content.getBytes(charset));
    }

    private String buildRuleString(Model model) {
        StringBuilder builder = new StringBuilder();
        for (Rule rule : model.getRules()) {
            builder.append("        rules.add(new InterpreterRule(" + rule.getConstructorParameters() + "));\n");
        }
        return builder.toString();
    }

    private String buildPredicateString(Model model) {
        StringBuilder builder = new StringBuilder();
        for (Predicate predicate : model.getMemory().getAllPredicates()) {
            builder.append("        predicates.add(new Predicate(\"" + predicate.getName() + "\"));\n");
        }
        return builder.toString();
    }

    private void writeFile(String name) throws IOException {
        File file = new File(packagePath, name);

        if (file.exists())
            file.delete();
        boolean success = file.createNewFile();
        assert success;

        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
        if (packageName != null) writer.write("package " + packageName + ";\n");
        writer.close();

        InputStream is = getClass().getClassLoader().getResourceAsStream(name);

        byte[] buffer = new byte[is.available()];
        is.read(buffer);
        OutputStream outStream = new FileOutputStream(file, true);
        outStream.write(buffer);
        outStream.close();
        is.close();
    }
}
