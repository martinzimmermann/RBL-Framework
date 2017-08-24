package at.tugraz.ist.compiler.rule;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class AlphaEntry {

    private final String expression;
    private final String function;

    public AlphaEntry(String expression, String function)
    {
        this.expression = expression;
        this.function = function;
    }

    public AlphaEntry(String function)
    {
        this.expression = null;
        this.function = function;
    }

    public float calculateWeight(double alpha)
    {
        throw new NotImplementedException();
    }

    public boolean isResponsible(double alpha)
    {
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

        if(result instanceof Boolean)
            return (Boolean)result;
        else
            return false;
    }

    public boolean isDefaultEntry()
    {
        throw new NotImplementedException();
    }
}
