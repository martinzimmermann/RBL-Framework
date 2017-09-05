
import java.util.ArrayList;
import java.util.List;

public class AlphaList {

    private final List<AlphaEntry> entries;

    public static AlphaList getDefaultAlphaList()
    {
        List<AlphaEntry> entries = new ArrayList<>();
        entries.add(new AlphaEntry("1"));
        return new AlphaList(entries);
    }

    public AlphaList(List<AlphaEntry> entries)
    {
        this.entries = entries;
    }

    public double calculateWeight( double alpha)
    {
        if(alpha < 0)
            throw new IllegalArgumentException("alpha must be greater or equal than 0, was " + alpha);
        if (alpha > 1)
            throw new IllegalArgumentException("alpha must be smaller or equal than 1, was " + alpha);

        for (AlphaEntry entry : entries) {
            if(entry.isResponsible(alpha))
                return entry.calculateWeight(alpha);
        }

        throw new IllegalArgumentException("This AlphaList has no entry for the provided alpha, alpha was " + alpha);
    }
}