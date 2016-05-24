package ca.jbrains.pos.test;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class FormatMonetaryAmountTest {
    @Test
    public void simplest() throws Exception {
        assertEquals("$7.89", format(789));
    }

    private String format(int priceInCents) {
        return null;
    }
}
