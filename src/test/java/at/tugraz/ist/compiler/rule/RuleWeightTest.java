package at.tugraz.ist.compiler.rule;

import at.tugraz.ist.compiler.interpreter.ClassCompiler;
import at.tugraz.ist.compiler.parser.RuleLexer;
import at.tugraz.ist.compiler.parser.RuleParser;
import at.tugraz.ist.compiler.ruleGenerator.RuleGenerator;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import static junit.framework.TestCase.*;

public class RuleWeightTest {

    @Test
    public void basic_weight_test() throws IOException {
        String source = "pre -> action.";
        Rule rule = checkRule(source);

        BigDecimal weight = rule.getWeight();
        assertEquals(0, new BigDecimal(0.25).compareTo(weight));
    }

    @Test
    public void basic_weight_withAlpha_test() throws IOException {
        String source = "pre -> action (0 <= a <= 1 : 1).";
        Rule rule = checkRule(source);

        BigDecimal weight = rule.getWeight();
        assertEquals(0, new BigDecimal(0.25).compareTo(weight));
    }

    @Test
    public void basic_weight_withAlphaChanged_test() throws IOException {
        String source = "pre -> action (0 <= a <= 1 : 1).";
        Rule rule = checkRule(source);

        BigDecimal weight = rule.getWeight();
        assertEquals(0, new BigDecimal(0.25).compareTo(weight));
    }

    @Test
    public void basic_weight_withAlphaA_test() throws IOException {
        String source = "pre -> action (0 <= a <= 1 : a).";
        Rule rule = checkRule(source);

        BigDecimal weight = rule.getWeight();
        assertEquals(0, new BigDecimal(0.125).compareTo(weight));
    }


    @Test
    public void basic_weight_withAlphaMultiplication_test() throws IOException {
        String source = "pre -> action (0 <= a <= 1 : a*2).";
        Rule rule = checkRule(source);

        BigDecimal weight = rule.getWeight();
        assertEquals(0, new BigDecimal(0.25).compareTo(weight));
    }

    @Test
    public void basic_weight_withAlphaDivision_test() throws IOException {
        String source = "pre -> action (0 <= a <= 1 : a/2).";
        Rule rule = checkRule(source);

        BigDecimal weight = rule.getWeight();
        assertEquals(0, new BigDecimal(0.0625).compareTo(weight));
    }


    @Test
    public void basic_weight_withAlphaAddiditon_test() throws IOException {
        String source = "pre -> action (0 <= a <= 1 : a+0.5).";
        Rule rule = checkRule(source);

        BigDecimal weight = rule.getWeight();
        assertEquals(0, new BigDecimal(0.25).compareTo(weight));
    }


    @Test
    public void basic_weight_withAlphaSubtraction_test() throws IOException {
        String source = "pre -> action (0 <= a <= 1 : a-0.25).";
        Rule rule = checkRule(source);

        BigDecimal weight = rule.getWeight();
        assertEquals(0, new BigDecimal(0.0625).compareTo(weight));
    }


    @Test
    public void basic_weight_withAlphaOperatorPresedence_test() throws IOException {
        String source = "pre -> action (0 <= a <= 1 : a/2+0.25).";
        Rule rule = checkRule(source);

        BigDecimal weight = rule.getWeight();
        assertEquals(0, new BigDecimal(0.125).compareTo(weight));
    }

    @Test
    public void basic_weight_withAlphaOperatorPresedence2_test() throws IOException {
        String source = "pre -> action (0 <= a <= 1 : a/2+0.125*(1+-1)).";
        Rule rule = checkRule(source);

        BigDecimal weight = rule.getWeight();
        assertEquals(0, new BigDecimal(0.0625).compareTo(weight));
    }

    @Test
    public void weight_damping_increase() throws IOException {
        String source = "pre -> action.";
        Rule rule = checkRule(source);

        rule.increaseDamping();
        BigDecimal weight = rule.getWeight();
        assertEquals(0, new BigDecimal(0.2).round(new MathContext(10)).compareTo(weight));
    }

    @Test
    public void weight_damping_increase_to_max() throws IOException {
        String source = "pre -> action.";
        Rule rule = checkRule(source);
        rule.increaseDamping();
        rule.increaseDamping();
        rule.increaseDamping();
        rule.increaseDamping();
        rule.increaseDamping();
        rule.increaseDamping();
        rule.increaseDamping();

        BigDecimal weight = rule.getWeight();
        assertEquals(0, new BigDecimal(0.05).round(new MathContext(10)).compareTo(weight));
    }

    @Test
    public void weight_damping_decrease() throws IOException {
        String source = "pre -> action.";
        Rule rule = checkRule(source);

        rule.decreaseDamping();
        BigDecimal weight = rule.getWeight();
        assertEquals(0 , new BigDecimal(0.3).round(new MathContext(10)).compareTo(weight));
    }

    @Test
    public void weight_damping_decrease_to_min() throws IOException {
        String source = "pre -> action.";
        Rule rule = checkRule(source);
        rule.decreaseDamping();
        rule.decreaseDamping();
        rule.decreaseDamping();
        rule.decreaseDamping();
        rule.decreaseDamping();
        rule.decreaseDamping();
        rule.decreaseDamping();
        rule.decreaseDamping();
        rule.decreaseDamping();

        BigDecimal weight = rule.getWeight();
        assertEquals(0, new BigDecimal(0.45).round(new MathContext(10)).compareTo(weight));
    }

    @Test
    public void age_std_test() throws IOException {
        String source = "pre -> action.";
        Rule rule = checkRule(source);
        rule.ageRule();

        BigDecimal weight = rule.getWeight();
        assertEquals(new BigDecimal(0.25),weight);
    }

    @Test
    public void age_up_test() throws IOException {
        String source = "pre -> action [0.1,0.1,1,,].";
        Rule rule = checkRule(source);

        rule.ageRule();
        BigDecimal weight = rule.getWeight();
        assertEquals(0, new BigDecimal(0.2).round(new MathContext(10)).compareTo(weight));
    }

    @Test
    public void age_down_test() throws IOException {
        String source = "pre -> action [0.1,0.1,0,,].";
        Rule rule = checkRule(source);

        rule.ageRule();
        BigDecimal weight = rule.getWeight();
        assertEquals(0, new BigDecimal(0.3).round(new MathContext(10)).compareTo(weight));
    }

    @Test
    public void age_up_to_upperBound_test() throws IOException {
        String source = "pre -> action [0.1,0.1,0.7|,,].";
        Rule rule = checkRule(source);

        rule.ageRule();
        rule.ageRule();
        rule.ageRule();
        rule.ageRule();
        rule.ageRule();
        rule.ageRule();
        rule.ageRule();
        rule.ageRule();

        BigDecimal weight = rule.getWeight();
        assertEquals(0, new BigDecimal(0.15).round(new MathContext(10)).compareTo(weight));
    }

    @Test
    public void age_down_to_lowerbound_test() throws IOException {
        String source = "pre -> action [0.1,0.1,|0.3,,].";
        Rule rule = checkRule(source);

        rule.ageRule();
        rule.ageRule();
        rule.ageRule();
        rule.ageRule();
        rule.ageRule();
        rule.ageRule();
        rule.ageRule();
        rule.ageRule();
        rule.ageRule();
        rule.ageRule();

        BigDecimal weight = rule.getWeight();
        assertEquals(0, new BigDecimal(0.35).round(new MathContext(10)).compareTo(weight));
    }



    private Rule checkRule(String source) throws IOException {
        RuleLexer ruleLexer = new RuleLexer(source);
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ClassCompiler.compileClasses("src/test/resources/Actions");
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());
        List<Rule> rules = gen.getRules();
        assertNotNull(rules);
        assertEquals("Should contain one rule", 1,  rules.size());
        return rules.get(0);
    }
}
