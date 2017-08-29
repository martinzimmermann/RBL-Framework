package at.tugraz.ist.compiler.rule;

import java.util.List;

public class Rule extends Atom {

    private final List<Predicate> preconditions;
    private final Predicate worldAddition;
    private final String goal;
    private final List<Predicate> worldDeletions;
    private final AlphaList alphaEntries ;
    private final double ruleGoal;
    private final String action;

    public Rule(String action, double ruleGoal, AlphaList alphaEntries,  List<Predicate> worldDeletions, String goal, Predicate worldAddition,  List<Predicate> preconditions) {

        if(action == null)
            throw new IllegalArgumentException("action can not be null");

        if(goal != null && worldAddition != null)
            throw new IllegalArgumentException("goal and worldAddition can't be both not null");

        this.action = action;
        this.ruleGoal = ruleGoal;
        this.alphaEntries = alphaEntries;
        this.worldDeletions = worldDeletions;
        this.goal = goal;
        this.worldAddition = worldAddition;
        this.preconditions = preconditions;
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
        return action;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (Predicate precondition : getPreconditions()) {
            string.append(precondition.getName() + ",");
        }

        string.deleteCharAt(string.length() - 1);
        string.append(" -> ");

        if (hasWorldAddition())
            string.append("+" + getWorldAddition().getName() + " ");

        if (hasGoal())
            string.append("#" + getGoal() + " ");

        for (Predicate deletion : getWorldDeletions()) {
            string.append("-" + deletion.getName() + " ");
        }

        string.deleteCharAt(string.length() - 1);
        string.append(".");

        return string.toString();
    }

}
