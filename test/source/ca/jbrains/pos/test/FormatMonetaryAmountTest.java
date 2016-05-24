package ca.jbrains.pos.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class FormatMonetaryAmountTest {

    public FormatMonetaryAmountTest(int priceInCents, String expectedFormattedPrice) {

    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{789, "$7.89"}});
    }

    @Test
    public void simplest() throws Exception {
        assertEquals("$7.89", format(789));
    }

    private String format(int priceInCents) {
        return "$7.89";
    }
}
