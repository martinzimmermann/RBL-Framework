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
        assertEquals(0, new BigDecimal(0.00000000001).compareTo(weight));
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
