package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.rule.*;
import at.tugraz.ist.compiler.generator.ModelGenerator;
import fr.uga.pddl4j.parser.Message;
import fr.uga.pddl4j.parser.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class Executor {

    private final PlanFinder planFinder;
    private final Map<String, RuleAction> ruleActions;
    private Model model;
    private Memory currentMemory;

    public Executor(String pddlDomainfile, String pddlProblemfile) {
        this(pddlDomainfile, pddlProblemfile, new DijkstraPlanFinder());
    }

    public Executor(String pddlDomainfile, String pddlProblemfile, PlanFinder planFinder) {
        this.planFinder = planFinder;
        this.ruleActions = new HashMap<>();

        try {
            Parser parser = new Parser();
            parser.parse(pddlDomainfile, pddlProblemfile);
            if(!parser.getErrorManager().isEmpty()) {
                for (Message msg : parser.getErrorManager().getMessages()) {
                    System.out.println(msg.toString());
                }
                throw new IllegalStateException("Error while parsing PDDL File");
            }
            ModelGenerator gen = new ModelGenerator(parser.getDomain(), parser.getProblem());
            model = gen.getModel();
            currentMemory = gen.getMemory();

        } catch (IOException ex) {
            throw new IllegalStateException("Could not read PDDL file for parsing", ex);
        }
    }

    public Model getModel() {
        return model;
    }

    public void registerAction(String action, RuleAction ruleAction){
        ruleActions.put(action, ruleAction);
    }

    public void removeAction(String action) {
        ruleActions.remove(action);
    }

    public boolean execute(List<String> goalState) throws NoPlanFoundException {
        List<Predicate> goalCond = new ArrayList<>();
        for (String pre : goalState) {
            goalCond.add(new Predicate(pre));
        }

        List<Rule> plan = planFinder.getPlanForGoal(goalCond, currentMemory, model);

        if (plan == null)
            throw new NoPlanFoundException();

        boolean success = interpretRules(plan);

        List<Rule> plan_complement = model.getRules();
        plan_complement.removeAll(plan);

        for (Rule rule : plan_complement) {
            rule.updateRule(!success, false, false);
        }

        return success;
    }

    private boolean interpretRules(List<Rule> plan) {
        boolean success = true;
        int failed_rule = 0;
        for (Rule rule : plan) {
            RuleAction action = ruleActions.get(rule.getAction());
            boolean result = rule.execute(model, action);
            if (result) {
                currentMemory.update(rule);
            } else {
                rule.repairMemory(currentMemory, action) ;
                success = false;
                break;
            }
            failed_rule++;
        }

        int count = 0;
        for (Rule rule : plan) {
            if (success)
                rule.updateRule(false, true, true);
            else
                if (count <= failed_rule)
                    rule.updateRule(true, true, count == failed_rule ? true : false);
                else
                    rule.updateRule(true, false, false);
            count++;
        }

        return success;
    }
}
