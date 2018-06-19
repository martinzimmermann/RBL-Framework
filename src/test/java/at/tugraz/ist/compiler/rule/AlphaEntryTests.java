package at.tugraz.ist.compiler.rule;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class AlphaEntryTests {

    @Test
    public void responsible_test() {
        AlphaEntry entry = new AlphaEntry("0 <= a <= 1","", null);
        Assert.assertTrue(entry.isResponsible(new BigDecimal(0.5)));
    }

    @Test
    public void not_responsible_test() {
        AlphaEntry entry = new AlphaEntry("0 <= a < 0.5","", null);
        Assert.assertFalse(entry.isResponsible(new BigDecimal(0.5)));
    }
}
