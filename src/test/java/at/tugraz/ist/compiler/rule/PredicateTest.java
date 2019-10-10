package at.tugraz.ist.compiler.rule;

import org.junit.Test;
import org.junit.Assert;
import static junit.framework.TestCase.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class PredicateTest {

    @Test
    public void Equals_test() throws IOException {
        Predicate rule = new Predicate("pred");
        assertFalse(rule.equals(null));
        assertFalse(rule.equals(new Object()));
        assertTrue(rule.equals(rule));
    }
}
