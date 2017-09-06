package at.tugraz.ist.compiler.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.Comparator.comparing;

public class AlphaList {

    private final List<AlphaEntry> entries;
    private final AlphaEntry defaultEntry;

    public AlphaList(List<AlphaEntry> entries, AlphaEntry defaultEntry) {
        this.entries = entries;
        this.defaultEntry = defaultEntry;
    }

    public static AlphaList getDefaultAlphaList() {
        return new AlphaList(new ArrayList<>(), new AlphaEntry("1"));
    }

    public boolean isValidRange()
    {
        List<AlphaEntry> sorted = entries.stream().sorted(comparing(AlphaEntry::getStart)).collect(Collectors.toList());
        double start = 0;
        boolean lastSmallerEquals = false;

        for(AlphaEntry entry : sorted)
        {
            if((entry.getStart() != start || entry.isStartSmallerEquals() == lastSmallerEquals) && defaultEntry == null)
                return false;

            if(entry.getStart() != start && defaultEntry != null && entry.getStart() < start)
                return false;

            if(entry.getStart() == start && entry.isStartSmallerEquals() == true && lastSmallerEquals == true)
                return false;

            start = entry.getEnd();
            lastSmallerEquals = entry.isEndSmallerEquals();
        }

        if(start != 1 || lastSmallerEquals == false)
            return false;

        return true;
    }

    public double calculateWeight(double alpha) {
        if (alpha < 0)
            throw new IllegalArgumentException("alpha must be greater or equal than 0, was " + alpha);
        if (alpha > 1)
            throw new IllegalArgumentException("alpha must be smaller or equal than 1, was " + alpha);

        for (AlphaEntry entry : entries) {
            if (entry.isResponsible(alpha))
                return entry.calculateWeight(alpha);
        }

        if(defaultEntry != null)
            return defaultEntry.calculateWeight(alpha);

        throw new IllegalArgumentException("This AlphaList has no entry for the provided alpha, alpha was " + alpha);
    }

    public String getConstructorParameter() {
        String params = entries.stream().map(e -> "new AlphaEntry(" + e.getConstructorParameter() + ")").collect(Collectors.joining(", "));
        return "new ArrayList<AlphaEntry>(Arrays.asList(new AlphaEntry[]{" + params + "}))";
    }
}
