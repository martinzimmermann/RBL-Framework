import java.util.ArrayList;
import java.util.List;

public class Memory {

    private List<Predicate> predicates;
    private final List<Predicate> start_predicates;

    public Memory(List<Predicate> predicates)
    {

        this.predicates = new ArrayList<>(predicates);
        this.start_predicates = new ArrayList<>(predicates);
    }

    public boolean contains(Predicate precondition) {
        return predicates.contains(precondition);
    }

    public boolean containsAll(List<Predicate> preconditions) {
        return predicates.containsAll(preconditions);
    }

    public List<Predicate> getAllPredicates()
    {
        return predicates;
    }

    public void update(Rule rule) {
        predicates.removeAll(rule.getWorldDeletions());
        if(rule.hasWorldAddition())
            predicates.add(rule.getWorldAddition());
    }

    public void reset() {
        this.predicates = new ArrayList<>(start_predicates);
    }

    public void remove(String predicate) {
        predicates.remove(new Predicate(predicate));
    }
}
