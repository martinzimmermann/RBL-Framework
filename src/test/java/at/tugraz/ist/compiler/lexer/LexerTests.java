package at.tugraz.ist.compiler.lexer;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class LexerTests {

    @Test
    public void success_simple_predicate() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("src/test/resources/compiler/lexer/input/success_simple_predicate.rule");
        assertEquals("Should be no Error", 0,  ruleLexer.getErrorCount());
    }
}
