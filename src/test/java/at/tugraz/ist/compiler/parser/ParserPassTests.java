package at.tugraz.ist.compiler.parser;

import at.tugraz.ist.compiler.helper.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class ParserPassTests {
    private static final String folderPath = "src/test/resources/compiler/parser/input/pass";

    @Parameterized.Parameters(name = "{1}")
    public static Iterable<Object[]> data() {
        List<String> paths = TestHelper.getAllFilesInPath(folderPath);
        return paths.stream().map(x -> new Object[]{x, TestHelper.shortPath(x)}).collect(Collectors.toList());
    }

    private final String path;

    public ParserPassTests(String path, String name) {
        this.path = path;
    }

    @Test
    public void success_simple_predicate() throws IOException {
        Path path = Paths.get(this.path);
        RuleLexer ruleLexer = new RuleLexer(path);
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
    }
}
