package at.tugraz.ist.compiler.rule;

public class Predicate extends Atom {

    private final String name;

    public Predicate(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
