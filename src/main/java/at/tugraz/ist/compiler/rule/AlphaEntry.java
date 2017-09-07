package at.tugraz.ist.compiler.rule;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlphaEntry {

    private final String expression;
    private final String function;

    public AlphaEntry(String expression, String function) {
        this.expression = expression;
        this.function = function;
    }

    public AlphaEntry(String function) {
        this.expression = "0 <= a <= 1";
        this.function = function;
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

    public BigDecimal calculateWeight(BigDecimal alpha) {

        String eval = "a = " + alpha + "; " + function;

        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        Object result;
        try {
            result = engine.eval(eval);
        } catch (ScriptException e) {
            e.printStackTrace();
            throw new IllegalStateException("The function provided is not a valid function, function was: " + function);
        }

        if (result instanceof Number)
            return  BigDecimal.valueOf(((Number) result).doubleValue());
        else
            throw new IllegalStateException("The function provided is not a valid function, function was: " + function);
    }

    public boolean isResponsible(BigDecimal alpha) {
        int indexOfA = expression.indexOf("a");
        String les = expression.substring(0, indexOfA + 1);
        String greater = expression.substring(indexOfA, expression.length());

        String eval = "a = " + alpha + "; " + les + " && " + greater;

        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        Object result;
        try {
            result = engine.eval(eval);
        } catch (ScriptException e) {
            e.printStackTrace();
            return false;
        }

        if (result instanceof Boolean)
            return (Boolean) result;
        else
            return false;
    }

    public String getConstructor() {
        return "new AlphaEntry(\"" + expression + "\"" + "," + "\"" + function + "\")";
    }
}
