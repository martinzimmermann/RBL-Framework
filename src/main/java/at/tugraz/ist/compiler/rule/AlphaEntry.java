package at.tugraz.ist.compiler.rule;

import at.tugraz.ist.compiler.interpreter.ClassCompiler;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlphaEntry {

    private final String expression;
    private final String JSfunction;
    private final String JavaFunction;
    private final ResponsibleFunction responsibleFunction;
    private final Function<BigDecimal, BigDecimal> weightFunction;

    public AlphaEntry(String expression, Function<BigDecimal, BigDecimal> weightFunction) {
        this.expression = expression;
        this.weightFunction = weightFunction;
        JSfunction = null;
        JavaFunction = null;

        final double start = getStart();
        final double end = getEnd();

        final CompareFunction first;
        if (isStartSmallerEquals())
            first = (a, b) -> a.compareTo(b) <= 0;
        else
            first = (a, b) -> a.compareTo(b) < 0;

        final CompareFunction second;
        if (isEndSmallerEquals())
            second = (a, b) -> a.compareTo(b) <= 0;
        else
            second = (a, b) -> a.compareTo(b) < 0;

        responsibleFunction = (a) -> first.inRange(new BigDecimal(start), a) && second.inRange(a, new BigDecimal(end));
    }

    public AlphaEntry(String expression, String JSfunction, String JavaFunction) {
        this.expression = expression;
        this.JSfunction = JSfunction;
        this.JavaFunction = JavaFunction;
        weightFunction = ClassCompiler.getFunctionFromLambda("(a) -> " + JavaFunction);

        final double start = getStart();
        final double end = getEnd();

        final CompareFunction first;
        if (isStartSmallerEquals())
            first = (a, b) -> a.compareTo(b) <= 0;
        else
            first = (a, b) -> a.compareTo(b) < 0;

        final CompareFunction second;
        if (isEndSmallerEquals())
            second = (a, b) -> a.compareTo(b) <= 0;
        else
            second = (a, b) -> a.compareTo(b) < 0;

        responsibleFunction = (a) -> first.inRange(new BigDecimal(start), a) && second.inRange(a, new BigDecimal(end));
    }
    public AlphaEntry(String JSfunction, String JavaFunction) {
        this.expression = "0 <= a <= 1";
        this.JSfunction = JSfunction;
        this.JavaFunction = JavaFunction;
        responsibleFunction = (a) -> new BigDecimal(0).compareTo(a) != -1 && a.compareTo(a) != 1;
        weightFunction = ClassCompiler.getFunctionFromLambda("(a) -> " + JavaFunction);
    }

    public double getStart() {
        String patternString = "(\\d+(\\.\\d+)?)";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(expression);
        matcher.find();
        String start = matcher.group();
        return Double.parseDouble(start);
    }

    public boolean isStartSmallerEquals() {
        int indexOfA = expression.indexOf("a");
        String les = expression.substring(0, indexOfA + 1);
        String patternString = "(<=)";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(les);
        return matcher.find();
    }

    public boolean isEndSmallerEquals() {
        int indexOfA = expression.indexOf("a");
        String greater = expression.substring(indexOfA, expression.length());
        String patternString = "(<=)";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(greater);
        return matcher.find();
    }

    public double getEnd() {
        String patternString = "(\\d+(\\.\\d+)?)";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(expression);
        matcher.find();
        matcher.find();
        String end = matcher.group();
        return Double.parseDouble(end);
    }

    public BigDecimal calculateWeight(BigDecimal a) {
        return weightFunction.apply(a);
    }

    public boolean isResponsible(BigDecimal alpha) {
        return responsibleFunction.isResponsible(alpha);
    }

    public String getConstructor() {
        return "new AlphaEntry(\"" + expression + "\"" + "," + "(a) -> " + JavaFunction + ")";
    }

    public interface ResponsibleFunction {
        boolean isResponsible(BigDecimal a);
    }

    private interface CompareFunction {
        boolean inRange(BigDecimal a, BigDecimal b);
    }
}
