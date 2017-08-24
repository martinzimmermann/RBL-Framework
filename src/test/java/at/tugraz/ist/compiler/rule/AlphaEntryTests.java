package at.tugraz.ist.compiler.rule;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class AlphaEntryTests {

    @Test
    public void responsible_test() throws IOException {
        AlphaEntry entry = new AlphaEntry("0 <= a <= 1","");
        Assert.assertTrue(entry.isResponsible(0.5));
    }

    @Test
    public void not_responsible_test() throws IOException {
        AlphaEntry entry = new AlphaEntry("0 <= a < 0.5","");
        Assert.assertFalse(entry.isResponsible(0.5));
    }
}