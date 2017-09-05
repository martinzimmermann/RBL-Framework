package at.tugraz.ist.compiler.compiler;

import at.tugraz.ist.compiler.interpreter.Model;
import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;

import java.io.*;
import java.nio.file.StandardCopyOption;

public class SourceWriter {

    private final String packageName;
    private final String packagePath;

    public SourceWriter(String outputPath, String packageName) {
        this.packageName = packageName;
        packagePath = outputPath + (outputPath.endsWith("\\") ? "" : "\\") + (this.packageName == null ? "" : this.packageName.replace(".", "\\"));

    }

    public void writeSource(Model model) throws IOException {
        createFolder();
        writeFile("ActionFailedException.java");
        writeFile("AlphaEntry.java");
        writeFile("AlphaList.java");
        writeFile("Atom.java");
        writeExecutor(model);
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

    private void writeExecutor(Model model) throws IOException {
        File file = new File(packagePath, "Executor.java");
        if (file.exists())
            file.delete();
        boolean success = file.createNewFile();
        assert success;
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
        if (packageName != null) writer.write("package " + packageName + ";\n");

        writer.write(
                "import java.util.ArrayList;\n" +
                        "import java.util.Arrays;\n" +
                        "import java.util.List;\n" +
                        "import java.util.stream.Collectors;\n" +
                        "\n" +
                        "public class Executor {\n" +
                        "\n" +
                        "    private Model model;\n" +
                        "\n" +
                        "    public Executor() throws ClassNotFoundException {\n" +
                        "        List<Rule> rules = new ArrayList<>();\n");
        for (Rule rule : model.getRules()) {
            writer.write("          rules.add(new InterpreterRule(" + rule.getConstructorParameters() + "));\n");
        }
        writer.write("          List<Predicate> predicates = new ArrayList<>();");
        for (Predicate predicate : model.getMemory().getAllPredicates()) {
            writer.write("                predicates.add(new Predicate(\"" + predicate.getName() + "\"));\n");
        }

        writer.write(
                "        Memory memory = new Memory(predicates);\n" +
                        "        model = new Model(memory, rules);\n" +
                        "    }\n" +
                        "\n" +
                        "    public boolean executesTillGoalReached() throws NoPlanFoundException {\n" +
                        "        return executesTillGoalReached(10);\n" +
                        "    }\n" +
                        "\n" +
                        "    private boolean executesTillGoalReached(int limit) throws NoPlanFoundException {\n" +
                        "        for (int i = 0; i < limit; i++) {\n" +
                        "            if(executeOnce())\n" +
                        "                return true;\n" +
                        "        }\n" +
                        "        return false;\n" +
                        "    }\n" +
                        "\n" +
                        "    private boolean executeOnce() throws NoPlanFoundException {\n" +
                        "        List<Rule> rules = model.getRules();\n" +
                        "        List<Rule> goals = PlanFinder.getGoalRules(rules);\n" +
                        "        goals.sort(Rule::compareTo);\n" +
                        "        Rule goal = goals.get(0);\n" +
                        "\n" +
                        "        Memory memory = model.getMemory();\n" +
                        "        List<InterpreterRule> plan = PlanFinder.getPlanForRule(goal, memory, rules);\n" +
                        "        if(plan == null)\n" +
                        "            throw new NoPlanFoundException();\n" +
                        "\n" +
                        "        for (InterpreterRule rule : plan) {\n" +
                        "            try {\n" +
                        "                rule.execute(memory);\n" +
                        "                memory.update(rule);\n" +
                        "                rule.decreaseDamping();\n" +
                        "                rule.increaseActivity();\n" +
                        "            } catch (ActionFailedException e) {\n" +
                        "                rule.repairMemory(memory);\n" +
                        "                rule.increaseDamping();\n" +
                        "                return false;\n" +
                        "            }\n" +
                        "        }\n" +
                        "        return true;\n" +
                        "    }\n" +
                        "\n" +
                        "    public void executesNTimes(int n) throws NoPlanFoundException {\n" +
                        "        for (int i = 0; i < n; i++) {\n" +
                        "            executeOnce();\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    public void executesForever() throws NoPlanFoundException {\n" +
                        "        while (true) {\n" +
                        "            executeOnce();\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    public void resetMemory() {\n" +
                        "        model.getMemory().reset();\n" +
                        "    }\n" +
                        "\n" +
                        "    public List<String> getMemory() {\n" +
                        "        return new ArrayList<>(model.getMemory().getAllPredicates().stream().map(Predicate::toString).collect(Collectors.toList()));\n" +
                        "    }\n" +
                        "\n" +
                        "}");


        writer.close();
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
