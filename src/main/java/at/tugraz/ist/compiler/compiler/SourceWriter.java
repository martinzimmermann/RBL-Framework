package at.tugraz.ist.compiler.compiler;

import at.tugraz.ist.compiler.interpreter.Model;
import at.tugraz.ist.compiler.rule.Predicate;
import at.tugraz.ist.compiler.rule.Rule;

import java.io.*;

public class SourceWriter {

    private final String packageName;
    private final String packagePath;

    public SourceWriter(String outputPath, String packageName) {
        this.packageName = packageName;
        packagePath = outputPath + (outputPath.endsWith("\\") ? "" : "\\") + (this.packageName == null ? "" : this.packageName.replace(".", "\\"));

    }

    public void writeSource(Model model) throws IOException {
        createFolder();
        writeExecutor(model);
        writeRest();
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
        OutputStream stream = new FileOutputStream(file);
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
                        "    public void executesTillGoalReached() throws NoPlanFoundException {\n" +
                        "        executesTillGoalReached(10);\n" +
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
                        "    public void executesForever(int n) throws NoPlanFoundException {\n" +
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
                        "}\n");


        writer.close();
    }

    private void writeRest() throws IOException {
        writeActionFailedException();
        writeAlphaEntry();
        writeAlphaList();
        writeAtom();
        writeInterpreterRule();
        writeMemory();
        writeModel();
        writeNoPlanFoundException();
        writePlan();
        writePlanFinder();
        writePredicate();
        writeRule();
        writeRuleAction();
    }

    private void writeRuleAction() throws IOException {
        File file = new File(packagePath, "RuleAction.java");
        if (file.exists())
            file.delete();
        boolean success = file.createNewFile();
        OutputStream stream = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
        if (packageName != null) writer.write("package " + packageName + ";\n");

        writer.write("public interface RuleAction {\n" +
                "    void execute(Memory model) throws ActionFailedException;\n" +
                "    void repair(Memory model);\n" +
                "}\n");
        writer.close();
    }

    private void writeRule() throws IOException {
        File file = new File(packagePath, "Rule.java");
        if (file.exists())
            file.delete();
        boolean success = file.createNewFile();
        OutputStream stream = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
        if (packageName != null) writer.write("package " + packageName + ";\n");

        writer.write("import java.util.List;\n" +
                "\n" +
                "class Rule extends Atom implements Comparable<Rule> {\n" +
                "\n" +
                "    private final List<Predicate> preconditions;\n" +
                "    private final Predicate worldAddition;\n" +
                "    private final String goal;\n" +
                "    private final List<Predicate> worldDeletions;\n" +
                "    private final AlphaList alphaEntries ;\n" +
                "    private final double ruleGoal;\n" +
                "    private final String actionName;\n" +
                "    private double currentActivity = 0;\n" +
                "    private double damping = 0.5;\n" +
                "\n" +
                "    Rule(String action, double ruleGoal, AlphaList alphaEntries, List<Predicate> worldDeletions, String goal, Predicate worldAddition, List<Predicate> preconditions) {\n" +
                "        if(action == null)\n" +
                "            throw new IllegalArgumentException(\"action can not be null\");\n" +
                "\n" +
                "        if(goal != null && worldAddition != null)\n" +
                "            throw new IllegalArgumentException(\"goal and worldAddition can't be both not null\");\n" +
                "\n" +
                "        if(alphaEntries == null)\n" +
                "            throw new IllegalArgumentException(\"alphaEntries can not be null\");\n" +
                "\n" +
                "        if(worldDeletions == null)\n" +
                "            throw new IllegalArgumentException(\"worldDeletions can not be null\");\n" +
                "\n" +
                "        if(preconditions == null)\n" +
                "            throw new IllegalArgumentException(\"preconditions can not be null\");\n" +
                "\n" +
                "        this.ruleGoal = ruleGoal;\n" +
                "        this.alphaEntries = alphaEntries;\n" +
                "        this.worldDeletions = worldDeletions;\n" +
                "        this.goal = goal;\n" +
                "        this.worldAddition = worldAddition;\n" +
                "        this.preconditions = preconditions;\n" +
                "        this.actionName = action;\n" +
                "    }\n" +
                "\n" +
                "    public List<Predicate> getPreconditions()\n" +
                "    {\n" +
                "        return preconditions;\n" +
                "    }\n" +
                "\n" +
                "    public Predicate getWorldAddition()\n" +
                "    {\n" +
                "        return worldAddition;\n" +
                "    }\n" +
                "\n" +
                "    private String getGoal()\n" +
                "    {\n" +
                "        return goal;\n" +
                "    }\n" +
                "\n" +
                "    public boolean hasGoal()\n" +
                "    {\n" +
                "        return goal != null;\n" +
                "    }\n" +
                "\n" +
                "    public boolean hasWorldAddition()\n" +
                "    {\n" +
                "        return worldAddition != null;\n" +
                "    }\n" +
                "\n" +
                "    public List<Predicate> getWorldDeletions()\n" +
                "    {\n" +
                "        return worldDeletions;\n" +
                "    }\n" +
                "\n" +
                "    public AlphaList getAlphaList()\n" +
                "    {\n" +
                "        return alphaEntries;\n" +
                "    }\n" +
                "\n" +
                "    public double getRuleGoal()\n" +
                "    {\n" +
                "        return ruleGoal;\n" +
                "    }\n" +
                "\n" +
                "    String getAction() {\n" +
                "        return actionName;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public String toString() {\n" +
                "        StringBuilder string = new StringBuilder();\n" +
                "\n" +
                "        for (Predicate precondition : getPreconditions()) {\n" +
                "            string.append(precondition.getName()).append(\",\");\n" +
                "        }\n" +
                "\n" +
                "        if(getPreconditions().size() != 0)\n" +
                "            string.deleteCharAt(string.length() - 1);\n" +
                "\n" +
                "        string.append(\" -> \");\n" +
                "\n" +
                "        if (hasWorldAddition())\n" +
                "            string.append(\"+\").append(getWorldAddition().getName()).append(\" \");\n" +
                "\n" +
                "        if (hasGoal())\n" +
                "            string.append(\"#\").append(getGoal()).append(\" \");\n" +
                "\n" +
                "        for (Predicate deletion : getWorldDeletions()) {\n" +
                "            string.append(\"-\").append(deletion.getName()).append(\" \");\n" +
                "        }\n" +
                "\n" +
                "        if(getWorldDeletions().size() != 0)\n" +
                "            string.deleteCharAt(string.length() - 1);\n" +
                "\n" +
                "        string.append(\" \" + actionName);\n" +
                "\n" +
                "        string.append(\".\");\n" +
                "\n" +
                "        return string.toString();\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public boolean equals(Object o) {\n" +
                "        if (this == o) return true;\n" +
                "        if (o == null || getClass() != o.getClass()) return false;\n" +
                "\n" +
                "        Rule rule = (Rule) o;\n" +
                "\n" +
                "        if (Double.compare(rule.ruleGoal, ruleGoal) != 0) return false;\n" +
                "        if (!preconditions.equals(rule.preconditions)) return false;\n" +
                "        if (worldAddition != null ? !worldAddition.equals(rule.worldAddition) : rule.worldAddition != null)\n" +
                "            return false;\n" +
                "        if (goal != null ? !goal.equals(rule.goal) : rule.goal != null) return false;\n" +
                "        if (!worldDeletions.equals(rule.worldDeletions)) return false;\n" +
                "        if (!alphaEntries.equals(rule.alphaEntries)) return false;\n" +
                "        return actionName.equals(rule.actionName);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public int hashCode() {\n" +
                "        int result;\n" +
                "        long temp;\n" +
                "        result = preconditions.hashCode();\n" +
                "        result = 31 * result + (worldAddition != null ? worldAddition.hashCode() : 0);\n" +
                "        result = 31 * result + (goal != null ? goal.hashCode() : 0);\n" +
                "        result = 31 * result + worldDeletions.hashCode();\n" +
                "        result = 31 * result + alphaEntries.hashCode();\n" +
                "        temp = Double.doubleToLongBits(ruleGoal);\n" +
                "        result = 31 * result + (int) (temp ^ (temp >>> 32));\n" +
                "        result = 31 * result + actionName.hashCode();\n" +
                "        return result;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public int compareTo(Rule o) {\n" +
                "\n" +
                "        double otherWeight = o.alphaEntries.calculateWeight(o.currentActivity) * (o.ruleGoal - o.currentActivity) * (1-o.damping);\n" +
                "        double thisWeight = alphaEntries.calculateWeight(currentActivity) * (ruleGoal - currentActivity) * (1-damping);\n" +
                "\n" +
                "        return Double.compare(otherWeight, thisWeight);\n" +
                "    }\n" +
                "\n" +
                "    public void decreaseDamping() {\n" +
                "        damping = damping - 0.1;\n" +
                "        damping = damping < 0.1 ? 0.1 : damping;\n" +
                "    }\n" +
                "\n" +
                "    public void increaseDamping() {\n" +
                "        damping = damping + 0.1;\n" +
                "        damping = damping > 0.9 ? 0.9 : damping;\n" +
                "    }\n" +
                "\n" +
                "    public void increaseActivity() {\n" +
                "        currentActivity = (currentActivity + ruleGoal) / 2;\n" +
                "    }\n" +
                "}\n");
        writer.close();
    }

    private void writePredicate() throws IOException {
        File file = new File(packagePath, "Predicate.java");
        if (file.exists())
            file.delete();
        boolean success = file.createNewFile();
        OutputStream stream = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
        if (packageName != null) writer.write("package " + packageName + ";\n");

        writer.write("public class Predicate extends Atom {\n" +
                "\n" +
                "    private final String name;\n" +
                "\n" +
                "    public Predicate(String name)\n" +
                "    {\n" +
                "        this.name = name;\n" +
                "    }\n" +
                "\n" +
                "    public String getName() {\n" +
                "        return name;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public boolean equals(Object other) {\n" +
                "        if (this == other)\n" +
                "            return true;\n" +
                "\n" +
                "        if (other == null)\n" +
                "            return false;\n" +
                "\n" +
                "        if (other.getClass() != getClass())\n" +
                "            return false;\n" +
                "\n" +
                "        Predicate otherPredicate = (Predicate) other;\n" +
                "        return name.equals(otherPredicate.getName());\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public int hashCode() {\n" +
                "        return name.hashCode();\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public String toString() {\n" +
                "        return name + \".\";\n" +
                "    }\n" +
                "}\n");
        writer.close();
    }

    private void writePlanFinder() throws IOException {
        File file = new File(packagePath, "PlanFinder.java");
        if (file.exists())
            file.delete();
        boolean success = file.createNewFile();
        OutputStream stream = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
        if (packageName != null) writer.write("package " + packageName + ";\n");

        writer.write("import java.util.ArrayList;\n" +
                "import java.util.Collections;\n" +
                "import java.util.List;\n" +
                "import java.util.stream.Collectors;\n" +
                "\n" +
                "class PlanFinder\n" +
                "{\n" +
                "    public static List<Rule> getGoalRules(List<Rule> allRules)\n" +
                "    {\n" +
                "        return allRules.stream().filter(Rule::hasGoal).collect(Collectors.toList());\n" +
                "    }\n" +
                "\n" +
                "    public static List<InterpreterRule> getPlanForRule(Rule goal, Memory memory, List<Rule> allRules)\n" +
                "    {\n" +
                "        List<Rule> plan = getPlanForRule(goal,memory, allRules, new Plan());\n" +
                "        if(plan == null)\n" +
                "            return null;\n" +
                "        Collections.reverse(plan);\n" +
                "        return plan.stream().map(r -> (InterpreterRule) r).collect(Collectors.toList());\n" +
                "    }\n" +
                "\n" +
                "    private static List<Rule> getPlanForRule(Rule goal, Memory memory, List<Rule> allRules, Plan currentPlan)\n" +
                "    {\n" +
                "        Plan newPlan = new Plan(currentPlan);\n" +
                "        newPlan.add(goal);\n" +
                "\n" +
                "        if (newPlan.isComplete(memory))\n" +
                "            return newPlan.getRules();\n" +
                "\n" +
                "        List<Rule> rules = new ArrayList<>(allRules);\n" +
                "        rules.remove(goal);\n" +
                "        rules.sort(Rule::compareTo);\n" +
                "\n" +
                "        for (Rule rule : rules) {\n" +
                "            if (!newPlan.needs(rule, memory))\n" +
                "                continue;\n" +
                "\n" +
                "            if (newPlan.ruleWouldRemoveNeededPrecondition(rule))\n" +
                "                continue;\n" +
                "\n" +
                "            List<Rule> newRules = getPlanForRule(rule, memory, rules, newPlan);\n" +
                "            if(newRules == null) // this path didn't yield a valid plan, try other rule\n" +
                "                continue;\n" +
                "\n" +
                "            return newRules;\n" +
                "        }\n" +
                "\n" +
                "        return null; // plan couldn't be fulfilled on this path\n" +
                "    }\n" +
                "}\n");
        writer.close();
    }

    private void writePlan() throws IOException {
        File file = new File(packagePath, "Plan.java");
        if (file.exists())
            file.delete();
        boolean success = file.createNewFile();
        OutputStream stream = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
        if (packageName != null) writer.write("package " + packageName + ";\n");

        writer.write("import java.util.ArrayList;\n" +
                "import java.util.HashSet;\n" +
                "import java.util.List;\n" +
                "import java.util.Set;\n" +
                "import java.util.stream.Collectors;\n" +
                "\n" +
                "class Plan {\n" +
                "\n" +
                "    private final List<Rule> rules;\n" +
                "\n" +
                "    public Plan() {\n" +
                "        rules  = new ArrayList<>();\n" +
                "    }\n" +
                "\n" +
                "    public Plan(Plan currentPlan) {\n" +
                "        rules = new ArrayList<>(currentPlan.rules);\n" +
                "    }\n" +
                "\n" +
                "    public boolean ruleWouldRemoveNeededPrecondition(Rule rule) {\n" +
                "        return rules.stream().anyMatch(r -> r.getPreconditions().stream().anyMatch(p -> rule.getWorldDeletions().contains(p)));\n" +
                "    }\n" +
                "\n" +
                "    public void add(Rule rule) {\n" +
                "        rules.add(rule);\n" +
                "    }\n" +
                "\n" +
                "    public boolean isComplete(Memory memory) {\n" +
                "        List<Predicate> preconditions = toReach();\n" +
                "        return memory.containsAll(preconditions);\n" +
                "    }\n" +
                "\n" +
                "    public List<Rule> getRules() {\n" +
                "        return rules;\n" +
                "    }\n" +
                "\n" +
                "    public boolean needs(Rule rule, Memory memory) {\n" +
                "        if(!rule.hasWorldAddition()) return false;\n" +
                "\n" +
                "        List<Predicate> toReach = toReach();\n" +
                "        toReach.removeAll(memory.getAllPredicates());\n" +
                "        return toReach.contains(rule.getWorldAddition());\n" +
                "    }\n" +
                "\n" +
                "    private List<Predicate> toReach() {\n" +
                "        Set<Predicate> preconditions = new HashSet<>(rules.stream().flatMap(r -> r.getPreconditions().stream()).collect(Collectors.toList()));\n" +
                "        Set<Predicate> posEffects = new HashSet<>(rules.stream().filter(Rule::hasWorldAddition).map(Rule::getWorldAddition).collect(Collectors.toList()));\n" +
                "\n" +
                "        preconditions.removeAll(posEffects);\n" +
                "        return new ArrayList<>(preconditions);\n" +
                "    }\n" +
                "}\n");
        writer.close();
    }

    private void writeModel() throws IOException {
        File file = new File(packagePath, "Model.java");
        if (file.exists())
            file.delete();
        boolean success = file.createNewFile();
        OutputStream stream = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
        if (packageName != null) writer.write("package " + packageName + ";\n");

        writer.write("import java.util.List;\n" +
                "\n" +
                "class Model {\n" +
                "\n" +
                "    private final Memory memory;\n" +
                "    private final List<Rule> rules;\n" +
                "\n" +
                "    public Model(Memory memory, List<Rule> rules) {\n" +
                "        this.memory = memory;\n" +
                "        this.rules = rules;\n" +
                "    }\n" +
                "\n" +
                "    public Memory getMemory() {\n" +
                "        return memory;\n" +
                "    }\n" +
                "\n" +
                "    public List<Rule> getRules() {\n" +
                "        return rules;\n" +
                "    }\n" +
                "}\n");
        writer.close();
    }

    private void writeNoPlanFoundException() throws IOException {
        File file = new File(packagePath, "NoPlanFoundException.java");
        if (file.exists())
            file.delete();
        boolean success = file.createNewFile();
        OutputStream stream = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
        if (packageName != null) writer.write("package " + packageName + ";\n");

        writer.write("public class NoPlanFoundException extends Throwable {\n" +
                "}\n");
        writer.close();
    }

    private void writeMemory() throws IOException {
        File file = new File(packagePath, "Memory.java");
        if (file.exists())
            file.delete();
        boolean success = file.createNewFile();
        OutputStream stream = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
        if (packageName != null) writer.write("package " + packageName + ";\n");

        writer.write("import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "\n" +
                "public class Memory {\n" +
                "\n" +
                "    private List<Predicate> predicates;\n" +
                "    private final List<Predicate> start_predicates;\n" +
                "\n" +
                "    public Memory(List<Predicate> predicates)\n" +
                "    {\n" +
                "\n" +
                "        this.predicates = new ArrayList<>(predicates);\n" +
                "        this.start_predicates = new ArrayList<>(predicates);\n" +
                "    }\n" +
                "\n" +
                "    public boolean contains(Predicate precondition) {\n" +
                "        return predicates.contains(precondition);\n" +
                "    }\n" +
                "\n" +
                "    public boolean containsAll(List<Predicate> preconditions) {\n" +
                "        return predicates.containsAll(preconditions);\n" +
                "    }\n" +
                "\n" +
                "    public List<Predicate> getAllPredicates()\n" +
                "    {\n" +
                "        return predicates;\n" +
                "    }\n" +
                "\n" +
                "    public void update(Rule rule) {\n" +
                "        predicates.removeAll(rule.getWorldDeletions());\n" +
                "        if(rule.hasWorldAddition())\n" +
                "            predicates.add(rule.getWorldAddition());\n" +
                "    }\n" +
                "\n" +
                "    public void reset() {\n" +
                "        this.predicates = new ArrayList<>(start_predicates);\n" +
                "    }\n" +
                "\n" +
                "    public void remove(String predicate) {\n" +
                "        predicates.remove(new Predicate(predicate));\n" +
                "    }\n" +
                "}\n");
        writer.close();
    }

    private void writeInterpreterRule() throws IOException {
        File file = new File(packagePath, "InterpreterRule.java");
        if (file.exists())
            file.delete();
        boolean success = file.createNewFile();
        OutputStream stream = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
        if (packageName != null) writer.write("package " + packageName + ";\n");

        writer.write("import java.lang.reflect.Constructor;\n" +
                "import java.lang.reflect.InvocationTargetException;\n" +
                "import java.util.List;\n" +
                "\n" +
                "class InterpreterRule extends Rule {\n" +
                "    private final RuleAction action;\n" +
                "\n" +
                "    public InterpreterRule(String action, double ruleGoal, AlphaList alphaEntries, List<Predicate> worldDeletions, String goal, Predicate worldAddition, List<Predicate> preconditions) throws ClassNotFoundException {\n" +
                "        super(action, ruleGoal, alphaEntries, worldDeletions, goal, worldAddition, preconditions);\n" +
                "        try {\n" +
                "            Class actionClass = Class.forName(getAction());\n" +
                "            Constructor constructor = actionClass.getConstructor();\n" +
                "            this.action = (RuleAction) constructor.newInstance(new Object[0]);\n" +
                "        }\n" +
                "        catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e)\n" +
                "        {\n" +
                "            throw new ClassNotFoundException();\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public void execute(Memory memory) throws ActionFailedException {\n" +
                "        assert (memory.containsAll(this.getPreconditions()));\n" +
                "\n" +
                "        action.execute(memory);\n" +
                "    }\n" +
                "\n" +
                "    public void repairMemory(Memory memory)\n" +
                "    {\n" +
                "        action.repair(memory);\n" +
                "    }\n" +
                "}\n");
        writer.close();
    }

    private void writeAtom() throws IOException {
        File file = new File(packagePath, "Atom.java");
        if (file.exists())
            file.delete();
        boolean success = file.createNewFile();
        OutputStream stream = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
        if (packageName != null) writer.write("package " + packageName + ";\n");

        writer.write("abstract class Atom {\n" +
                "}\n");
        writer.close();
    }

    private void writeAlphaList() throws IOException {
        File file = new File(packagePath, "AlphaList.java");
        if (file.exists())
            file.delete();
        boolean success = file.createNewFile();
        OutputStream stream = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
        if (packageName != null) writer.write("package " + packageName + ";\n");

        writer.write("\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "\n" +
                "public class AlphaList {\n" +
                "\n" +
                "    private final List<AlphaEntry> entries;\n" +
                "\n" +
                "    public static AlphaList getDefaultAlphaList()\n" +
                "    {\n" +
                "        List<AlphaEntry> entries = new ArrayList<>();\n" +
                "        entries.add(new AlphaEntry(\"1\"));\n" +
                "        return new AlphaList(entries);\n" +
                "    }\n" +
                "\n" +
                "    public AlphaList(List<AlphaEntry> entries)\n" +
                "    {\n" +
                "        this.entries = entries;\n" +
                "    }\n" +
                "\n" +
                "    public double calculateWeight( double alpha)\n" +
                "    {\n" +
                "        if(alpha < 0)\n" +
                "            throw new IllegalArgumentException(\"alpha must be greater or equal than 0, was \" + alpha);\n" +
                "        if (alpha > 1)\n" +
                "            throw new IllegalArgumentException(\"alpha must be smaller or equal than 1, was \" + alpha);\n" +
                "\n" +
                "        for (AlphaEntry entry : entries) {\n" +
                "            if(entry.isResponsible(alpha))\n" +
                "                return entry.calculateWeight(alpha);\n" +
                "        }\n" +
                "\n" +
                "        throw new IllegalArgumentException(\"This AlphaList has no entry for the provided alpha, alpha was \" + alpha);\n" +
                "    }\n" +
                "}\n");
        writer.close();
    }

    private void writeAlphaEntry() throws IOException {
        File file = new File(packagePath, "AlphaEntry.java");
        if (file.exists())
            file.delete();
        boolean success = file.createNewFile();
        OutputStream stream = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
        if (packageName != null) writer.write("package " + packageName + ";\n");

        writer.write("import javax.script.ScriptEngine;\n" +
                "import javax.script.ScriptEngineManager;\n" +
                "import javax.script.ScriptException;\n" +
                "\n" +
                "class AlphaEntry {\n" +
                "\n" +
                "    private final String expression;\n" +
                "    private final String function;\n" +
                "\n" +
                "    public AlphaEntry(String expression, String function)\n" +
                "    {\n" +
                "        this.expression = expression;\n" +
                "        this.function = function;\n" +
                "    }\n" +
                "\n" +
                "    public AlphaEntry(String function)\n" +
                "    {\n" +
                "        this.expression = \"0 <= a <= 1\";\n" +
                "        this.function = function;\n" +
                "    }\n" +
                "\n" +
                "    public double calculateWeight(double alpha)\n" +
                "    {\n" +
                "\n" +
                "        String eval = \"a = \" + alpha + \"; \" + function;\n" +
                "\n" +
                "        ScriptEngine engine = new ScriptEngineManager().getEngineByName(\"nashorn\");\n" +
                "        Object result;\n" +
                "        try {\n" +
                "            result = engine.eval(eval);\n" +
                "        } catch (ScriptException e) {\n" +
                "            e.printStackTrace();\n" +
                "            throw new IllegalStateException(\"The function provided is not a valid function, function was: \" + function);\n" +
                "        }\n" +
                "\n" +
                "        if(result instanceof Number)\n" +
                "            return ((Number)result).doubleValue();\n" +
                "        else\n" +
                "            throw new IllegalStateException(\"The function provided is not a valid function, function was: \" + function);\n" +
                "    }\n" +
                "\n" +
                "    public boolean isResponsible(double alpha)\n" +
                "    {\n" +
                "        int indexOfA = expression.indexOf(\"a\");\n" +
                "        String les = expression.substring(0, indexOfA + 1);\n" +
                "        String greater = expression.substring(indexOfA, expression.length());\n" +
                "\n" +
                "        String eval = \"a = \" + alpha + \"; \" + les + \" && \" + greater;\n" +
                "\n" +
                "        ScriptEngine engine = new ScriptEngineManager().getEngineByName(\"nashorn\");\n" +
                "        Object result;\n" +
                "        try {\n" +
                "            result = engine.eval(eval);\n" +
                "        } catch (ScriptException e) {\n" +
                "            e.printStackTrace();\n" +
                "            return false;\n" +
                "        }\n" +
                "\n" +
                "        if(result instanceof Boolean)\n" +
                "            return (Boolean)result;\n" +
                "        else\n" +
                "            return false;\n" +
                "    }\n" +
                "}\n");
        writer.close();
    }

    private void writeActionFailedException() throws IOException {
        File file = new File(packagePath, "ActionFailedException.java");
        if (file.exists())
            file.delete();
        boolean success = file.createNewFile();
        OutputStream stream = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
        if (packageName != null) writer.write("package " + packageName + ";\n");

        writer.write("public class ActionFailedException extends Exception{\n" +
                "}\n");
        writer.close();
    }
}
