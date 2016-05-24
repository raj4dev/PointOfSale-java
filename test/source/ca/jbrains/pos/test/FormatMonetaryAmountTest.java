package ca.jbrains.pos.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class FormatMonetaryAmountTest {

    private final int priceInCents;
    private final String expectedFormattedPrice;

    public FormatMonetaryAmountTest(int priceInCents, String expectedFormattedPrice) {
        this.priceInCents = priceInCents;
        this.expectedFormattedPrice = expectedFormattedPrice;
    }

    @Parameters(name = "Monetary amount {0} formats to {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {789, "$7.89"},
                {520, "$5.20"},
                {400, "$4.00"},
                {0, "$0.00"},
                {2, "$0.02"},
                {37, "$0.37"}
        });
    }

    @Test
    public void test() throws Exception {
        assertEquals(expectedFormattedPrice, format(priceInCents));
    }

    private String format(int priceInCents) {
        return String.format("$%.2f", priceInCents/100.0d);/*###1*/
    }
}

/*
* --------------------------------------------------------------
* NOTES
* --------------------------------------------------------------
*
* ###1
* $% means literally print whatever is on the left of %;
* We divide by 100.0 to get a decimal result & not a rounded off integer;
* Not sure if d is needed. Code works without it;
* .2f means print only two places after a decimal of the only argument of the
* method. If the arg has only 1 decimal, then a zero will be added.
* */