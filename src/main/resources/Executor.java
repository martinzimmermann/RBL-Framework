// <replace with import>
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.tugraz.ist.compiler.interpreter.LibExecutor;
import at.tugraz.ist.compiler.interpreter.goal;

public class Executor extends LibExecutor {

    static Model generateModel() throws ClassNotFoundException {
        Arrays.asList(); // Just so the import won't get removed at a code cleanup
        new BigDecimal(0); // Just so the import won't get removed at a code cleanup
        List<Rule> rules = new ArrayList<>();
// <replace with rules>

        List<Predicate> predicates = new ArrayList<>();
// <replace with predicates>

        Memory memory = new Memory(predicates);
        Model model = new Model(memory, rules);
        if(ErrorHandler.Instance().hasErrors()) {
            ErrorHandler.Instance().printErrorCount();
            throw new ClassNotFoundException();
        }
        return model;
    }

    public Executor(PlanFinder planFinder) throws ClassNotFoundException {
        super(generateModel(), planFinder);
    }

}