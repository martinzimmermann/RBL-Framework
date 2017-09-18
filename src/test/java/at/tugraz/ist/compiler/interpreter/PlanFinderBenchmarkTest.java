package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.helper.TestHelper;
import at.tugraz.ist.compiler.parser.RuleLexer;
import at.tugraz.ist.compiler.parser.RuleParser;
import at.tugraz.ist.compiler.rule.Rule;
import at.tugraz.ist.compiler.ruleGenerator.RuleGenerator;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class PlanFinderBenchmarkTest {

    static final String resultFileName = "result.csv";
    private final static String folderPath = "src/test/resources/RandomRules/";
    private final String name;
    private final String path;

    public PlanFinderBenchmarkTest(String path, String name) {
        this.path = path;
        this.name = name.substring(name.lastIndexOf("\\") + 1,name.length());
    }

    @BeforeClass
    public static void setUpClass() throws FileNotFoundException, UnsupportedEncodingException {
        String firstLine = "File Name; (in)valid); Number of Rules; Number of Goals; Max Number of Conditions; Max Number of Preconditions; Time any Plan; Time best Plan; Weight any Plan; Weight best Plan\n";
        PrintWriter writer = new PrintWriter(folderPath + resultFileName, "UTF-8");
        writer.print(firstLine);
        writer.close();
    }


    @Parameterized.Parameters(name = "{1}")
    public static Iterable<Object[]> data() {
        List<String> paths = TestHelper.getAllFilesInPath(folderPath);
        return paths.stream().map(x -> new Object[]{x, TestHelper.shortPath(x)}).collect(Collectors.toList());
    }

    private String generateRandomRules(int numberOfRules, int numberOfGoals, int maxNumberOfConditions, int maxNumberOfPreconditions) {
        String template = "%s -> %s %s Actions.action.\n";
        StringBuilder builder = new StringBuilder();
        Random rnd = new Random();

        for (int i = 0; i < numberOfRules; i++) {
            int numberOfPreConditions = Math.abs(rnd.nextInt()) % maxNumberOfPreconditions;
            StringBuilder preconditions = new StringBuilder();
            for (int j = 0; j < numberOfPreConditions; j++) {
                preconditions.append("pre" + Math.abs(rnd.nextInt()) % maxNumberOfConditions + ", ");
            }
            if (preconditions.length() != 0) {
                preconditions.deleteCharAt(preconditions.length() - 1);
                preconditions.deleteCharAt(preconditions.length() - 1);
            }

            int numberOfDeletions = Math.abs(rnd.nextInt()) % 4;
            StringBuilder deletions = new StringBuilder();
            for (int j = 0; j < numberOfDeletions; j++) {
                deletions.append("-pre" + Math.abs(rnd.nextInt()) % maxNumberOfConditions + " ");
            }

            builder.append(String.format(template, preconditions.toString(), "+pre" + Math.abs(rnd.nextInt()) % maxNumberOfConditions, deletions.toString()));
        }

        int numberOfPreConditions = Math.abs(rnd.nextInt()) % 4;
        StringBuilder preconditions = new StringBuilder();
        for (int j = 0; j < numberOfPreConditions; j++) {
            preconditions.append("pre" + Math.abs(rnd.nextInt()) % maxNumberOfConditions + ", ");
        }
        if (preconditions.length() != 0) {
            preconditions.deleteCharAt(preconditions.length() - 1);
            preconditions.deleteCharAt(preconditions.length() - 1);
        }

        for (int i = 0; i < numberOfGoals; i++) {
            builder.append(String.format(template, preconditions.toString(), "#pre" + (Math.abs(rnd.nextInt() % maxNumberOfConditions)), ""));
        }


        return builder.toString();
    }

    @Test
    public void test() throws IOException {
        String pattern = "test_(invalid|valid)(\\d+)_(\\d+)_(\\d+)_(\\d+)_(\\d+)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(name);
        m.find();

        String valid = m.group(1);
        int testNumber = Integer.parseInt(m.group(2));
        if (testNumber != 0)
            return;
        int numberOfRules = Integer.parseInt(m.group(3));
        int numberOfGoals = Integer.parseInt(m.group(4));
        int maxNumberOfConditions = Integer.parseInt(m.group(5));
        int maxNumberOfPreconditions = Integer.parseInt(m.group(6));

        String rulesString = generateRandomRules(numberOfRules, numberOfGoals, maxNumberOfConditions, maxNumberOfPreconditions);
        RuleLexer ruleLexer = new RuleLexer(rulesString);
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();

        long startTime = System.nanoTime();
        List<Rule> anyPlan = PlanFinder.getPlan(memory, rules);
        long endTime = System.nanoTime();
        long anyRuleDuration = (endTime - startTime);
        BigDecimal anyPlanWeight = anyPlan != null ? calculateWeight(anyPlan) : null;

        startTime = System.nanoTime();
        List<Rule> bestPlan = PlanFinder.getBestPlan(memory, rules);
        endTime = System.nanoTime();
        long bestPlanDuration = (endTime - startTime);
        BigDecimal bestPlanWeight = bestPlan != null ? calculateWeight(bestPlan) : null;

        String template = "%s; %s; %d; %d; %d; %d; %d; %d; %s; %s\n";
        String line = String.format(template, name, valid, numberOfRules, numberOfGoals, maxNumberOfConditions, maxNumberOfPreconditions, anyRuleDuration, bestPlanDuration, anyPlanWeight != null ? anyPlanWeight : "", bestPlanWeight != null ? bestPlanWeight : "");
        try {
            Files.write(Paths.get(folderPath + resultFileName), line.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    private BigDecimal calculateWeight(List<Rule> rules) {
        BigDecimal sum = new BigDecimal(0);

        for (Rule rule : rules) {
            BigDecimal weight = rule.getWeight();
            sum = sum.add(weight);
        }
        return sum;
    }

    public void generateRandomTestData() throws IOException {
        String path = "src/test/resources/RandomRules/test_%s%d_%d_%d_%d_%d.rule";
        int[] rules = new int[]{5, 5, 5, 10, 10, 10, 20, 20, 20};
        int[] goals = new int[]{1, 1, 2, 1, 2, 5, 1, 2, 5};

        int[] maxConditions = new int[]{2, 4, 4, 2, 4, 6, 5, 10, 15};
        int[] maxPreconditons = new int[]{2, 2, 4, 2, 4, 4, 2, 4, 6};

        for (int j = 0; j < rules.length; j++) {
            int r = rules[j];
            int g = goals[j];
            int mC = maxConditions[j];
            int mP = maxPreconditons[j];
            for (int i = 0; i < 10; i++) {
                String rulesString = generateRandomRules(r, g, mC, mP);
                RuleLexer ruleLexer = new RuleLexer(rulesString);
                assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
                RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
                assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
                RuleGenerator gen = new RuleGenerator(ruleParser.getParseTree());

                List<Rule> anyPlan = PlanFinder.getPlan(gen.getMemory(), gen.getRules());
                if (anyPlan != null) {
                    i--;
                    continue;
                }

                PrintWriter writer = new PrintWriter(String.format(path, "invalid", i, r, g, mC, mP), "UTF-8");
                writer.print(rulesString);
                writer.close();
            }
        }

    }
}
