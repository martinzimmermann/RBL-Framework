package at.tugraz.ist.compiler.rule;

import at.tugraz.ist.compiler.interpreter.ClassCompiler;
import at.tugraz.ist.compiler.parser.RuleLexer;
import at.tugraz.ist.compiler.parser.RuleParser;
import at.tugraz.ist.compiler.ruleGenerator.ModelGenerator;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class RuleTest {

    @Test(expected = ClassNotFoundException.class)
    public void noValidaction_test() throws IOException, ClassNotFoundException {
        Rule rule = new InterpreterRule(new Rule("notanaction", null, new ArrayList<Predicate>(Arrays.asList(new Predicate[]{})), new ArrayList<Predicate>(Arrays.asList(new Predicate[]{new Predicate("pre", false)})), new DiagnosticPosition(1, 0, 1, 7, "pre->action")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void Constructor_noAction_test() throws IOException {
        Rule rule = new Rule(null, null, new ArrayList<Predicate>(Arrays.asList(new Predicate[]{})), new ArrayList<Predicate>(Arrays.asList(new Predicate[]{new Predicate("pre", false)})), new DiagnosticPosition(1, 0, 1, 7, "pre->action"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void Constructor_PreCondition_null_test() throws IOException {
        Rule rule = new Rule("action", null, null, new ArrayList<Predicate>(Arrays.asList(new Predicate[]{new Predicate("pre", false)})), new DiagnosticPosition(1, 0, 1, 7, "pre->action"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void Constructor_PostCondition_null_test() throws IOException {
        Rule rule = new Rule("action", null, new ArrayList<Predicate>(Arrays.asList(new Predicate[]{})), null, new DiagnosticPosition(1, 0, 1, 7, "pre->action"));

    }

    @Test
    public void ToString1_test() throws IOException {
        String source = "pre -> action.";
        Rule rule = checkRule(source);

        assertEquals("pre -> action.", rule.toString());
    }

    @Test
    public void ToString2_test() throws IOException {
        String source = "pre -> #goal +pos -neg action.";
        Rule rule = checkRule(source);

        assertEquals("pre -> #goal +pos -neg action.", rule.toString());
    }

    @Test
    public void GetConstructor_test() throws IOException {
        String source = "pre -> action.";
        Rule rule = checkRule(source);

        assertEquals("new Rule(\"action\", null, new ArrayList<Predicate>(Arrays.asList(new Predicate[]{})), new ArrayList<Predicate>(Arrays.asList(new Predicate[]{new Predicate(\"pre\", false)})), new DiagnosticPosition(1, 0, 1, 7, \"pre->action\"))", rule.getConstructor());
    }

    @Test
    public void GetActionConstructor_test() throws IOException {
        String source = "pre -> action.";
        Rule rule = checkRule(source);

        assertEquals("new action()", rule.getActionConstructor());
    }

    @Test
    public void GetDiagnosticPosition_test() throws IOException {
        String source = "pre -> action.";
        Rule rule = checkRule(source);
        DiagnosticPosition diag = rule.getDiagnosticPosition();

        assertNotNull(diag);
        assertEquals("1:0: pre->action", diag.getPrettyPrint());
    }

    private Rule checkRule(String source) throws IOException {
        RuleLexer ruleLexer = new RuleLexer(source);
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        ModelGenerator gen = new ModelGenerator(ruleParser.getParseTree());
        List<Rule> rules = gen.getRules();
        assertNotNull(rules);
        assertEquals("Should contain one rule", 1,  rules.size());
        return rules.get(0);
    }
}
