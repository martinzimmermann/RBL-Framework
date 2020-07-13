package at.tugraz.ist.compiler.helper;

import at.tugraz.ist.compiler.interpreter.Memory;
import at.tugraz.ist.compiler.interpreter.Model;
import at.tugraz.ist.compiler.rule.*;

import java.io.File;
import java.util.*;

public class TestHelper {

    private TestHelper() {
    }

    public static List<String> getAllFilesInPath(String path) {
        List<String> paths = new ArrayList<>();

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for(File file : listOfFiles) {
            if (file.isFile()) {
                paths.add(file.getPath());
            }
        }
        return paths;
    }

    public static String shortPath(String path) {
        return path.replace("src\\test\\resources\\compiler\\", "");
    }

    public static Action getAction() {
        SortedSet<String> parameters = new TreeSet<>();
        parameters.add("?a");
        parameters.add("?b");
        parameters.add("?c");


        List<String> variables = new ArrayList<>();
        variables.add("?a");
        variables.add("?b");

        Predicate pred = new Predicate("pred ?a ?b");
        AtomicFormula form = new AtomicFormula(pred, variables);

        List<String> variables2 = new ArrayList<>();
        variables2.add("?b");
        variables2.add("?c");
        Predicate pred2 = new Predicate("pred2 ?b ?c");
        AtomicFormula form2 = new AtomicFormula(pred2, variables2);

        SortedSet<AtomicFormula> preconditions = new TreeSet<>();
        preconditions.add(form);
        preconditions.add(form2);

        Predicate pred3 = new Predicate("pred2 ?a ?b", true);
        Predicate pred4 = new Predicate("pred ?b ?c");
        AtomicFormula form3 = new AtomicFormula(pred3, variables);
        AtomicFormula form4 = new AtomicFormula(pred4, variables2);
        SortedSet<AtomicFormula> postconditions = new TreeSet<>();
        postconditions.add(form3);
        postconditions.add(form4);

        Action act = new Action(
                "action",
                parameters,
                preconditions,
                postconditions
        );

        return act;
    }

    public static Rule getRule() {
        Predicate pred = new Predicate("pred a b");
        Predicate pred2 = new Predicate("pred2 b c");

        SortedSet<Predicate> preconditions = new TreeSet<>();
        preconditions.add(pred);
        preconditions.add(pred2);

        Predicate pred3 = new Predicate("pred a b", true);
        Predicate pred4 = new Predicate("pred2 a b", false);
        SortedSet<Predicate> postconditions = new TreeSet<>();
        postconditions.add(pred3);
        postconditions.add(pred4);

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("?a", "a");
        parameters.put("?b", "b");
        parameters.put("?c", "c");

        Rule rule = new Rule(
                "action",
                postconditions,
                preconditions,
                parameters
        );
        return rule;
    }

    public static Action getActionWithoutPreCond() {
        SortedSet<String> parameters = new TreeSet<>();
        parameters.add("?a");
        parameters.add("?b");


        List<String> variables = new ArrayList<>();
        variables.add("?a");
        variables.add("?b");

        SortedSet<AtomicFormula> preconditions = new TreeSet<>();

        Predicate pred3 = new Predicate("pred2 ?a ?b", true);
        AtomicFormula form3 = new AtomicFormula(pred3, variables);
        SortedSet<AtomicFormula> postconditions = new TreeSet<>();
        postconditions.add(form3);

        Action act = new Action(
                "action",
                parameters,
                preconditions,
                postconditions
        );

        return act;
    }

    public static class TestAction implements RuleAction {

        public boolean executed = false;
        public boolean repair = false;

        @Override
        public boolean execute(Model model, Map<String, String> parameters) {
            executed = true;
            return true;
        }

        @Override
        public void repair(Memory model, Map<String, String> parameters) {
            repair = true;
        }
    }

    public static class TestActionFail implements RuleAction {

        public boolean executed = false;
        public boolean repair = false;

        @Override
        public boolean execute(Model model, Map<String, String> parameters) {
            executed = true;
            return false;
        }

        @Override
        public void repair(Memory model, Map<String, String> parameters) {
            repair = true;
        }
    }
}
