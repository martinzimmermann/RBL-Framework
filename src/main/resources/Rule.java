import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class Rule extends Atom implements Comparable<Rule> {

    private final List<Predicate> preconditions;
    private final Predicate worldAddition;
    private final String goal;
    private final List<Predicate> worldDeletions;
    private final AlphaList alphaEntries;
    private final BigDecimal ruleGoal;
    private final String actionName;
    private BigDecimal currentActivity = BigDecimal.valueOf(0.5);
    private BigDecimal damping = BigDecimal.valueOf(0.5);

    private DiagnosticPosition diagnosticPosition;

    public Rule(String action, double ruleGoal, AlphaList alphaEntries, List<Predicate> worldDeletions, String goal, Predicate worldAddition, List<Predicate> preconditions, DiagnosticPosition diagnosticPosition) {
        if (action == null)
            throw new IllegalArgumentException("action can not be null");

        if (goal != null && worldAddition != null)
            throw new IllegalArgumentException("goal and worldAddition can't be both not null");

        if (alphaEntries == null)
            throw new IllegalArgumentException("alphaEntries can not be null");

        if (worldDeletions == null)
            throw new IllegalArgumentException("worldDeletions can not be null");

        if (preconditions == null)
            throw new IllegalArgumentException("preconditions can not be null");

        this.ruleGoal = BigDecimal.valueOf(ruleGoal);
        this.alphaEntries = alphaEntries;
        this.worldDeletions = worldDeletions;
        this.goal = goal;
        this.worldAddition = worldAddition;
        this.preconditions = preconditions;
        this.actionName = action;
        this.diagnosticPosition = diagnosticPosition;
    }

    public Rule(Rule rule) {
        preconditions = rule.preconditions;
        worldAddition = rule.worldAddition;
        goal = rule.goal;
        worldDeletions = rule.worldDeletions;
        alphaEntries = rule.alphaEntries;
        ruleGoal = rule.ruleGoal;
        actionName = rule.actionName;
        currentActivity = rule.currentActivity;
        damping = rule.damping;
    }

    public List<Predicate> getPreconditions() {
        return preconditions;
    }

    public Predicate getWorldAddition() {
        return worldAddition;
    }

    public String getGoal() {
        return goal;
    }

    public boolean hasGoal() {
        return goal != null;
    }

    public boolean hasWorldAddition() {
        return worldAddition != null;
    }

    public List<Predicate> getWorldDeletions() {
        return worldDeletions;
    }

    public AlphaList getAlphaList() {
        return alphaEntries;
    }

    public double getRuleGoal() {
        return ruleGoal.doubleValue();
    }

    public String getAction() {
        return actionName;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (Predicate precondition : getPreconditions()) {
            string.append(precondition.getName()).append(",");
        }

        if (getPreconditions().size() != 0)
            string.deleteCharAt(string.length() - 1);

        string.append(" -> ");

        if (hasWorldAddition())
            string.append("+").append(getWorldAddition().getName()).append(" ");

        if (hasGoal())
            string.append("#").append(getGoal()).append(" ");

        for (Predicate deletion : getWorldDeletions()) {
            string.append("-").append(deletion.getName()).append(" ");
        }

        if (getWorldDeletions().size() != 0)
            string.deleteCharAt(string.length() - 1);

        string.append(" " + actionName);

        string.append(".");

        return string.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule rule = (Rule) o;

        if (rule.ruleGoal.compareTo(ruleGoal) != 0) return false;
        if (!preconditions.equals(rule.preconditions)) return false;
        if (worldAddition != null ? !worldAddition.equals(rule.worldAddition) : rule.worldAddition != null)
            return false;
        if (goal != null ? !goal.equals(rule.goal) : rule.goal != null) return false;
        if (!worldDeletions.equals(rule.worldDeletions)) return false;
        if (!alphaEntries.equals(rule.alphaEntries)) return false;
        return actionName.equals(rule.actionName);
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
        temp = ruleGoal.hashCode();
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + actionName.hashCode();
        return result;
    }

    @Override
    public int compareTo(Rule o) {

        BigDecimal otherWeight = o.alphaEntries.calculateWeight(o.currentActivity).multiply(o.ruleGoal.subtract(o.currentActivity)).multiply(new BigDecimal(1).subtract(o.damping));
        BigDecimal thisWeight = alphaEntries.calculateWeight(currentActivity).multiply(ruleGoal.subtract(currentActivity)).multiply(new BigDecimal(1).subtract(damping));

        return otherWeight.compareTo(thisWeight);
    }

    public BigDecimal getWeight() {
        BigDecimal alpha = alphaEntries.calculateWeight(currentActivity);
        BigDecimal activity = ruleGoal.subtract(currentActivity);
        BigDecimal invDamping = new BigDecimal(1).subtract(damping);
        return alpha.multiply(activity).multiply(invDamping);
    }

    public void decreaseDamping() {
        //damping = damping.divide(new BigDecimal(2));
        damping = damping.subtract(new BigDecimal(0.1));
        damping = damping.compareTo(new BigDecimal(0.1)) < 0.1 ? new BigDecimal(0.1) : damping;
    }

    public void increaseDamping() {
        //damping = damping.add(new BigDecimal(1)).divide(new BigDecimal(2));
        damping = damping.add(new BigDecimal(0.1));
        damping = damping.compareTo(new BigDecimal(0.9)) > 0.9 ? new BigDecimal(0.9) : damping;
    }

    public void decreaseActivity() {
        currentActivity = currentActivity.divide(new BigDecimal(2));
    }

    public void increaseActivity() {
        currentActivity = currentActivity.add(ruleGoal).divide(new BigDecimal(2));
    }

    public String getConstructor() {
        StringBuilder builder = new StringBuilder();
        builder.append("new Rule(");
        builder.append("\"" + actionName + "\", ");
        builder.append(ruleGoal + ", ");
        builder.append(alphaEntries.getConstructor() + ", ");
        String params = worldDeletions.stream().map(p -> "new Predicate(\"" + p.getName() + "\")").collect(Collectors.joining(", "));
        builder.append("new ArrayList<Predicate>(Arrays.asList(new Predicate[]{" + params + "})), ");
        builder.append((goal == null ? "null" : "\"" + goal + "\"") + ", ");
        builder.append((worldAddition == null ? "null" : "new Predicate(\"" + worldAddition.getName() + "\")") + ", ");
        params = preconditions.stream().map(p -> "new Predicate(\"" + p.getName() + "\")").collect(Collectors.joining(", "));
        builder.append("new ArrayList<Predicate>(Arrays.asList(new Predicate[]{" + params + "})), ");
        builder.append("new DiagnosticPosition(" + diagnosticPosition.getConstructorParameters() + "))");
        return builder.toString();
    }

    public DiagnosticPosition getDiagnosticPosition() {
        return diagnosticPosition;
    }

    public String getActionConstructor() {
        return "new " + actionName + "()";
    }
}
