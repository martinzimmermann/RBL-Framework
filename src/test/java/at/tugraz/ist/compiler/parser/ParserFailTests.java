package at.tugraz.ist.compiler.parser;

import at.tugraz.ist.compiler.helper.TestHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class ParserFailTests {
    private static String folderPath = "src/test/resources/compiler/parser/input/fail";

    @Parameterized.Parameters(name = "{1}")
    public static Iterable<Object[]> data() {
        List<String> paths = TestHelper.getAllFilesInPath(folderPath);
        List<Object[]> data = paths.stream().map(x -> new Object[]{x, TestHelper.shortPath(x)}).collect(Collectors.toList());
        return data;
    }

    private String path;

    public ParserFailTests(String path, String name) {
        this.path = path;
    }

    @Test
    public void fail() throws IOException {
        Path path = Paths.get(this.path);
        RuleLexer ruleLexer = new RuleLexer(path);
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        Assert.assertNotEquals("Should be no Error", 0, ruleParser.getErrorCount());
    }
}
