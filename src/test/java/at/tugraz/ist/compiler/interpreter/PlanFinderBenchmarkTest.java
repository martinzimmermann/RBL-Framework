package at.tugraz.ist.compiler.interpreter;

import at.tugraz.ist.compiler.helper.TestHelper;
import at.tugraz.ist.compiler.parser.RuleLexer;
import at.tugraz.ist.compiler.parser.RuleParser;
import at.tugraz.ist.compiler.rule.Rule;
import at.tugraz.ist.compiler.ruleGenerator.ModelGenerator;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
@Ignore
public class PlanFinderBenchmarkTest {

    static final String resultFileName = "result.csv";
    private final static String folderPath = "src/test/resources/RandomRules/";

    static {
        try {
            generateRandomTestData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final String name;
    private final String path;

    public PlanFinderBenchmarkTest(String path, String name) {
        this.path = path;
        this.name = name.substring(name.lastIndexOf("\\") + 1, name.length());
    }


    @BeforeClass
    public static void setUpClass() throws IOException {
        String firstLine = "File Name; (in)valid); Number of Rules; Number of Goals; Max Number of Conditions; Max Number of Preconditions; Time top-down Plan; Time bottom-up Plan; Time best Plan; Rules top-down Plan; Rules bottom-up Plan; Rules best Plan; Weight top-down Plan; Weight bottom-up Plan; Weight best Plan\n";
        PrintWriter writer = new PrintWriter(folderPath + resultFileName, "UTF-8");
        writer.print(firstLine);
        writer.close();
    }


    @Parameterized.Parameters(name = "{1}")
    public static Iterable<Object[]> data() throws IOException {
        Files.deleteIfExists(new File(folderPath + resultFileName).toPath());
        List<String> paths = TestHelper.getAllFilesInPath(folderPath);
        return paths.stream().map(x -> new Object[]{x, TestHelper.shortPath(x)}).collect(Collectors.toList());
    }

    private static String generateRandomRules(int numberOfRules, int numberOfGoals, int maxNumberOfConditions, int maxNumberOfPreconditions) {
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

        for (int i = 0; i < numberOfGoals; i++) {
            int numberOfPreConditions = Math.abs(rnd.nextInt()) % maxNumberOfPreconditions;
            StringBuilder preconditions = new StringBuilder();
            for (int j = 0; j < numberOfPreConditions; j++) {
                preconditions.append("pre" + Math.abs(rnd.nextInt()) % maxNumberOfConditions + ", ");
            }
            if (preconditions.length() != 0) {
                preconditions.deleteCharAt(preconditions.length() - 1);
                preconditions.deleteCharAt(preconditions.length() - 1);
            }

            builder.append(String.format(template, preconditions.toString(), "#pre" + (Math.abs(rnd.nextInt() % maxNumberOfConditions)), ""));
        }


        return builder.toString();
    }

    private static void generateRandomTestData() throws IOException {
        String path = folderPath + "test_%s%d_%d_%d_%d_%d.rule";
        int[] rules = new int[]{3, 3, 3, 5, 5, 5, 9, 9, 9, 11, 11, 11, 15, 15, 15};
        int[] goals = new int[]{1, 1, 1, 1, 1, 2, 1, 2, 2, 1, 2, 4, 1, 2, 4};

        int[] maxConditions = new int[]{1, 1, 2, 2, 2, 4, 2, 4, 6, 4, 6, 8, 4, 6, 8};
        int[] maxPreconditions = new int[]{1, 2, 3, 1, 2, 3, 2, 4, 4, 2, 4, 6, 2, 4, 6};

        for (int j = 0; j < rules.length; j++) {
            int r = rules[j];
            int g = goals[j];
            int mC = maxConditions[j];
            int mP = maxPreconditions[j];
            for (int i = 0; i < 50; i++) {
                String rulesString = generateRandomRules(r, g, mC, mP);

                File file = new File(String.format(path, "valid", i, r, g, mC, mP));

                file.createNewFile();

                PrintWriter writer = new PrintWriter(file, "UTF-8");
                writer.print(rulesString);
                writer.close();
            }
        }
    }

    @Ignore
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
        int numberOfRules = Integer.parseInt(m.group(3));
        int numberOfGoals = Integer.parseInt(m.group(4));
        int maxNumberOfConditions = Integer.parseInt(m.group(5));
        int maxNumberOfPreconditions = Integer.parseInt(m.group(6));

        RuleLexer ruleLexer = new RuleLexer(Paths.get(path));
        assertEquals("Should be no Error", 0, ruleLexer.getErrorCount());
        RuleParser ruleParser = new RuleParser(ruleLexer.getTokenStream());
        assertEquals("Should be no Error", 0, ruleParser.getErrorCount());
        ModelGenerator gen = new ModelGenerator(ruleParser.getParseTree());

        Memory memory = gen.getMemory();
        List<Rule> rules = gen.getRules();

        long startTime = System.nanoTime();
        List<Rule> topDownPlan = new TopDownPlanFinder().getAnyPlan(memory, rules);
        long endTime = System.nanoTime();
        long topDownPlanDuration = (endTime - startTime);
        BigDecimal topDownPlanWeight = topDownPlan != null ? new Plan(topDownPlan).getWeight() : null;
        int topDownPlanRules = topDownPlan != null ? topDownPlan.size() : 0;

        startTime = System.nanoTime();
        List<Rule> bottomUpPlan = new BottomUpPlanFinder().getAnyPlan(memory, rules);
        endTime = System.nanoTime();
        long bottomUpPlanDuration = (endTime - startTime);
        BigDecimal bottomUpPlanWeight = bottomUpPlan != null ? new Plan(bottomUpPlan).getWeight() : null;
        int bottomUpPlanRules = bottomUpPlan != null ? bottomUpPlan.size() : 0;

        List<Rule> bestPlan = null;
        startTime = System.nanoTime();
        try {
            bestPlan = new BestPlanFinder().getAnyPlan(memory, rules);
        } catch (StackOverflowError | OutOfMemoryError e) {
        }
        endTime = System.nanoTime();
        long bestPlanDuration = (endTime - startTime);
        BigDecimal bestPlanWeight = bestPlan != null ? new Plan(bestPlan).getWeight() : null;
        int bestPlanRules = bestPlan != null ? bestPlan.size() : 0;

        String template = "%s;%s;%d;%d;%d;%d;%d;%d;%d;%d;%d;%d;%s;%s;%s\n";
        String line = String.format(template, name, valid, numberOfRules, numberOfGoals, maxNumberOfConditions, maxNumberOfPreconditions, topDownPlanDuration, bottomUpPlanDuration, bestPlanDuration, topDownPlanRules, bottomUpPlanRules, bestPlanRules, topDownPlanWeight != null ? topDownPlanWeight : "", bottomUpPlanWeight != null ? bottomUpPlanWeight : "", bestPlanWeight != null ? bestPlanWeight : "");
        try {
            Files.write(Paths.get(folderPath + resultFileName), line.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
}
