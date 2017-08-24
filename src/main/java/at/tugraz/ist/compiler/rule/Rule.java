package at.tugraz.ist.compiler.rule;

import java.util.List;

public class Rule extends Atom {

    private final List<String> preconditions = null;
    private final String worldAddition = null;
    private final String goal = null;
    private final List<String> worldDeletions = null;
    private final AlphaList alphaEntries = null;
    private final float ruleGoal = 0;

    public List<String> getPreconditions()
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

    public List<String> getWorldDeletions()
    {
        return worldDeletions;
    }

    public AlphaList getAlphaList()
    {
        return alphaEntries;
    }

    public float getRuleGoal()
    {
        return ruleGoal;
    }
}
