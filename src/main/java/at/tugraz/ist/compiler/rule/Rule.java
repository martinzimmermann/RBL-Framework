package at.tugraz.ist.compiler.rule;
import at.tugraz.ist.compiler.Setting;
import at.tugraz.ist.compiler.interpreter.ExecutionFailedException;
import at.tugraz.ist.compiler.interpreter.Memory;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Rule extends Atom  implements Comparable<Rule> {

    private final List<Predicate> preconditions;
    private final Predicate worldAddition;
    private final String goal;
    private final List<Predicate> worldDeletions;
    private final AlphaList alphaEntries ;
    private final double ruleGoal;
    private final RuleAction action;
    private double currentActivity = 0;
    private double damping = 0.5;

    public Rule(String action, double ruleGoal, AlphaList alphaEntries, List<Predicate> worldDeletions, String goal, Predicate worldAddition, List<Predicate> preconditions, Setting setting) throws ClassNotFoundException {

        if(action == null)
            throw new IllegalArgumentException("action can not be null");

        if(goal != null && worldAddition != null)
            throw new IllegalArgumentException("goal and worldAddition can't be both not null");

        if(alphaEntries == null)
            throw new IllegalArgumentException("alphaEntries can not be null");

        if(worldDeletions == null)
            throw new IllegalArgumentException("worldDeletions can not be null");

        if(preconditions == null)
            throw new IllegalArgumentException("preconditions can not be null");

        this.ruleGoal = ruleGoal;
        this.alphaEntries = alphaEntries;
        this.worldDeletions = worldDeletions;
        this.goal = goal;
        this.worldAddition = worldAddition;
        this.preconditions = preconditions;

        //oh boy, some dangerous stuff ahead
        try {
            JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager sjfm = jc.getStandardFileManager(null, null, null);
            String path = setting.getPathToJavaFiles() + "\\"  + action + ".java";
            File javaFile = new File(path);
            Iterable fileObjects = sjfm.getJavaFileObjects(javaFile);

            Path path2 = Files.createTempDirectory("temp" );
            String folderPath = path2.normalize().toString();
            String[] options = new String[]{"-d", folderPath};
            jc.getTask(null, null, null, Arrays.asList(options), null, fileObjects).call();
            sjfm.close();

            URL[] urls = new URL[]{ path2.toUri().toURL()};
            URLClassLoader ucl = new URLClassLoader(urls);

            Class actionClass = ucl.loadClass(action);
            Constructor constructor = actionClass.getConstructor(new Class[0]);
            this.action = (RuleAction) constructor.newInstance(new Object[0]);
        }
        catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |  InvocationTargetException | InstantiationException | IOException e)
        {
            throw new ClassNotFoundException();
        }
    }

    public List<Predicate> getPreconditions()
    {
        return preconditions;
    }

    public Predicate getWorldAddition()
    {
        return worldAddition;
    }

    public String getGoal()
    {
        return goal;
    }

    public boolean hasGoal()
    {
        return goal != null;
    }

    public boolean hasWorldAddition()
    {
        return worldAddition != null;
    }

    public List<Predicate> getWorldDeletions()
    {
        return worldDeletions;
    }

    public AlphaList getAlphaList()
    {
        return alphaEntries;
    }

    public double getRuleGoal()
    {
        return ruleGoal;
    }

    public String getAction() {
        return action.getClass().getName();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (Predicate precondition : getPreconditions()) {
            string.append(precondition.getName()).append(",");
        }

        if(getPreconditions().size() != 0)
            string.deleteCharAt(string.length() - 1);

        string.append(" -> ");

        if (hasWorldAddition())
            string.append("+").append(getWorldAddition().getName()).append(" ");

        if (hasGoal())
            string.append("#").append(getGoal()).append(" ");

        for (Predicate deletion : getWorldDeletions()) {
            string.append("-").append(deletion.getName()).append(" ");
        }

        if(getWorldDeletions().size() != 0)
            string.deleteCharAt(string.length() - 1);

        string.deleteCharAt(string.length() - 1);
        string.append(".");

        return string.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule rule = (Rule) o;

        if (Double.compare(rule.ruleGoal, ruleGoal) != 0) return false;
        if (!preconditions.equals(rule.preconditions)) return false;
        if (worldAddition != null ? !worldAddition.equals(rule.worldAddition) : rule.worldAddition != null)
            return false;
        if (goal != null ? !goal.equals(rule.goal) : rule.goal != null) return false;
        if (!worldDeletions.equals(rule.worldDeletions)) return false;
        if (!alphaEntries.equals(rule.alphaEntries)) return false;
        return action.equals(rule.action);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = preconditions.hashCode();
        result = 31 * result + (worldAddition != null ? worldAddition.hashCode() : 0);
        result = 31 * result + (goal != null ? goal.hashCode() : 0);
        result = 31 * result + worldDeletions.hashCode();
        result = 31 * result + alphaEntries.hashCode();
        temp = Double.doubleToLongBits(ruleGoal);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + action.hashCode();
        return result;
    }

    @Override
    public int compareTo(Rule o) {

        double otherWeight = o.alphaEntries.calculateWeight(o.currentActivity) * (o.ruleGoal - o.currentActivity) * (1-o.damping);
        double thisWeight = alphaEntries.calculateWeight(currentActivity) * (ruleGoal - currentActivity) * (1-damping);

        return otherWeight == thisWeight ? 0 : thisWeight > otherWeight ? -1 : 1;
    }

    public void execute(Memory memory) throws ExecutionFailedException{
        if (!memory.containsAll(this.getPreconditions()))
            throw new PreConditionsNotMetException();

        action.execute(memory);
    }

    public void decreaseDamping() {
        damping = damping - 0.1;
        damping = damping < 0.1 ? 0.1 : damping;
    }

    public void increaseDamping() {
        damping = damping + 0.1;
        damping = damping > 0.9 ? 0.9 : damping;
    }

    public void increaseActivity() {
        currentActivity = (currentActivity + ruleGoal) / 2;
    }

    public void repairMemory(Memory memory) {
        action.repair(memory);
    }
}
