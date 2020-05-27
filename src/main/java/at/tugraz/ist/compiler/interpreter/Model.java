package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.ErrorHandler;
import at.tugraz.ist.compiler.rule.InterpreterRule;
import at.tugraz.ist.compiler.rule.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model {

    private final Memory memory;
    private final RulesRepository rules;
    //private final List<InterpreterRule> rules;

    public Model(Memory memory, List<Rule> rules) {
        this.memory = memory;
        this.rules = new RulesRepository(rules);
        /*if(rules.stream().allMatch(r -> r instanceof InterpreterRule))
            this.rules = rules.stream().map(r -> (InterpreterRule)r).collect(Collectors.toList());
        else {
            this.rules = new ArrayList<>();
            for (Rule rule : rules) {
                try {
                    this.rules.add(new InterpreterRule(rule));
                } catch (ClassNotFoundException e) {
                    ErrorHandler.Instance().reportError(ErrorHandler.Type.Interpreter, rule.getDiagnosticPosition(), "Class for action \"" + rule.getAction() + "\" could not be found");
                }
            }
        }*/
    }

    public Memory getMemory() {
        return memory;
    }

    public RulesRepository getRulesRepository() {
        return rules;
    }
}
