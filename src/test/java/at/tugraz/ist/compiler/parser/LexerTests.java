package at.tugraz.ist.compiler.parser;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;

public class LexerTests {

    @Test
    public void success_simple_predicate() throws IOException {
        Path path = Paths.get("src/test/resources/compiler/lexer/input/success_simple_predicate.rule");
        RuleLexer ruleLexer = new RuleLexer(path);
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
    }

    @Test
    public void success_simple_string_predicate() throws IOException {
        RuleLexer ruleLexer = new RuleLexer("hello.");
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
    }
}
