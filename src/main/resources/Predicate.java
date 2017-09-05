public class Predicate extends Atom {

    private final String name;

    public Predicate(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;

        if (other == null)
            return false;

        if (other.getClass() != getClass())
            return false;

        Predicate otherPredicate = (Predicate) other;
        return name.equals(otherPredicate.getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name + ".";
    }
}
