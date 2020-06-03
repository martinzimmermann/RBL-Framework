package at.tugraz.ist.compiler.rule;

public class Predicate extends Atom implements Comparable<Predicate> {

    private final String name;
    private final Boolean deletion;

    public Predicate(String name) {
        this(name, false);
    }

    public Predicate(String name, Boolean deletion) {
        this.name = name;
        this.deletion = deletion;
    }

    public String getName() {
        return name;
    }

    public Boolean isDeletion() {return deletion;}
    public Boolean isAddition() {return !deletion;}

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
        return name.hashCode() + deletion.hashCode();
    }

    @Override
    public String toString() {
        return (deletion ? "-" : "+") + name;
    }

    public String getConstructor() {
        return "new Predicate(\"" + name + "\", " + deletion + ")";
    }

    @Override
    public int compareTo(Predicate o) {
        return this.toString().compareTo(o.toString());
    }
}
