package at.tugraz.ist.compiler.rule;

public class Predicate extends Atom implements Comparable<Predicate> {

    private final String identifier;
    private final String expression;
    private final Boolean deletion;
    private final String[] expressionSplit;

    public Predicate(String name) {
        this(name, false);
    }

    public Predicate(String expresion, Boolean deletion) {
        this.identifier = expresion.split(" ")[0];
        this.expression = expresion;
        this.expressionSplit = expresion.split(" ");
        this.deletion = deletion;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getExpression() {
        return expression;
    }

    public String[] getExpressionSplit() {
        return expressionSplit;
    }

    public Boolean isDeletion() {
        return deletion;
    }

    public Boolean isAddition() {
        return !deletion;
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
        return expression.equals(otherPredicate.getExpression());
    }

    @Override
    public int hashCode() {
        return expression.hashCode() ^ deletion.hashCode();
    }

    @Override
    public String toString() {
        return (deletion ? "-" : "") + expression;
    }

    @Override
    public int compareTo(Predicate o) {
        return this.toString().compareTo(o.toString());
    }
}
