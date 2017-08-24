package at.tugraz.ist.compiler.rule;

import java.util.List;

public class Rule extends Atom {

    private final List<Atom> preconditions;
    private final String worldAddition;
    private final String goal;
    private final List<Atom> worldDeletions;
    private final AlphaList alphaEntries ;
    private final double ruleGoal;
    private final String action;

    public Rule(String action, double ruleGoal, AlphaList alphaEntries,  List<Atom> worldDeletions, String goal, String worldAddition,  List<Atom> preconditions) {

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

    public List<Atom> getPreconditions()
    {
        return preconditions;
    }

    public String getWorldAdditon()
    {
        return worldAddition;
    }

    public boolean hasWorldAdditon()
    {
        return worldAddition != null;
    }

    public String getGoal()
    {
        return goal;
    }

    public boolean hasGoal()
    {
        return goal != null;
    }

    public List<Atom> getWorldDeletions()
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
}