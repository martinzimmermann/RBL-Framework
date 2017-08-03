package at.tugraz.ist.compiler.parser;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class ParserTests {

    @Test
    public void success_simple_predicate() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("src/test/resources/compiler/parser/input/success_simple_rule.rule");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
    }
}
