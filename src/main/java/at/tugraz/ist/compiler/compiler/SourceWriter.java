package at.tugraz.ist.compiler.compiler;

import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;
import at.tugraz.ist.compiler.ruleGenerator.RuleGenerator;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SourceWriter {

    private final String packageName;
    private final String packagePath;

    public SourceWriter(String outputPath, String packageName) {
        this.packageName = packageName;
        packagePath = Paths.get(outputPath).resolve(this.packageName.replace(".", File.separator)).toAbsolutePath().toString();
    }

    public void writeSource(RuleGenerator generator, boolean deferClassGeneration, boolean libraryUsed) throws IOException {
        writeFiles(libraryUsed);
        replaceStubsInExecutor(generator, deferClassGeneration, libraryUsed);
    }

    private void writeFiles(boolean libraryUsed) throws IOException {
        createFolder();
        writeFile("Executor.java");

        if(!libraryUsed) {
            writeFile("AlphaEntry.java");
            writeFile("AlphaList.java");
            writeFile("Atom.java");
            writeFile("BestPlanFinder.java");
            writeFile("BottomUpPlanFinder.java");
            writeFile("DiagnosticPosition.java");
            writeFile("ErrorHandler.java");
            writeFile("InterpreterRule.java");
            writeFile("Memory.java");
            writeFile("Model.java");
            writeFile("NoPlanFoundException.java");
            writeFile("Plan.java");
            writeFile("PlanFinder.java");
            writeFile("Predicate.java");
            writeFile("Rule.java");
            writeFile("RuleAction.java");
            writeFile("TopDownPlanFinder.java");
        }
    }

    private void createFolder() {
        boolean success = (new File(packagePath)).mkdirs();
        assert success;
    }

    private void replaceStubsInExecutor(RuleGenerator gen, boolean deferClassGeneration, boolean libraryUsed) throws IOException {
        File file = new File(packagePath, "Executor.java");
        Path path = Paths.get(file.getPath());
        Charset charset = StandardCharsets.UTF_8;
        String content = new String(Files.readAllBytes(path), charset);

        String rulesFunctions = buildGenerateRuleFunctions(gen.getRules(), deferClassGeneration);
        String rulesFunctionCalls = buildGenerateRuleFunctionCalls((int) Math.ceil(gen.getRules().size()/100.0));
        String predicates = buildPredicateString(gen.getMemory());

        if(libraryUsed)
            content = content.replaceAll("// <replace with import>", getImports());

        content = content.replaceAll("// <replace with generateRules>", rulesFunctions);
        content = content.replaceAll("// <replace with generateRulesX>", rulesFunctionCalls);
        content = content.replaceAll("// <replace with predicates>", predicates);

        Files.write(path, content.getBytes(charset));
    }

    private String buildGenerateRuleFunctionCalls(int size) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append("        generateRules" + i + "();\n");
        }
        return builder.toString();
    }

    private String getImports() {
        return "import at.tugraz.ist.compiler.*;\n" +
                "import at.tugraz.ist.compiler.interpreter.*;\n" +
                "import at.tugraz.ist.compiler.rule.*;";
    }

    private String buildGenerateRuleFunctions(List<Rule> rules, boolean deferClassGeneration) {
        StringBuilder builder = new StringBuilder();
        int functionNumber = 0;
        builder.append("    static void generateRules" + functionNumber + "() {\n");

        for (int i = 0; i < rules.size(); i++) {
            Rule rule = rules.get(i);
            if(deferClassGeneration)
            builder.append("        rules.add(" + rule.getConstructor() + ");\n");
            else
            builder.append("        rules.add(new InterpreterRule("  + rule.getActionConstructor() + ", " + rule.getConstructor() + "));\n");
            
            if ((i+1) % 100 == 0) {
                builder.append("    }\n");
                functionNumber++;
                builder.append("    static void generateRules" + functionNumber + "() {\n");
            }
        }
        
        builder.append("    }");
        return builder.toString();
    }

    private String buildPredicateString(Memory memory) {
        StringBuilder builder = new StringBuilder();
        for (Predicate predicate : memory.getAllPredicates()) {
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
