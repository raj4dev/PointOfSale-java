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
                {37, "$0.37"},
                {418976, "$4,189.76"}
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
*
* We divide by 100.0 to get a decimal result & not a rounded off integer;
* Not sure if d is needed. Code works without it;
*
* $ prints the dollar sign as is;
* The % marks the beginning of a 'format specifier' or the format in which a
* a particular argument of format() is to be printed. The format specifier
* ends with a 'converter';
*
* .2f is a 'format specifier'  which means print only two places after a decimal
* of the desired argument. If the arg has only 1 decimal, then a zero will be added.
* The f is a 'converter' which marks the end of the format specifier;
*
* */
