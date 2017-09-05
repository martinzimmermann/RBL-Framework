import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

class AlphaEntry {

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

    public double calculateWeight(double alpha) {

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
            return ((Number) result).doubleValue();
        else
            throw new IllegalStateException("The function provided is not a valid function, function was: " + function);
    }

    public boolean isResponsible(double alpha) {
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
}
