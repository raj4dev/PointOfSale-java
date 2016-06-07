package ca.jbrains.pos.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DisplayPricesToConsoleTest {

    private final int priceInCents;
    private final String expectedFormattedPrice;

    public DisplayPricesToConsoleTest(int priceInCents, String expectedFormattedPrice) {
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
                {418976, "$4,189.76"},
                {210832281, "$2,108,322.81"}
        });
    }

    @Test
    public void test() throws Exception {
        assertEquals(expectedFormattedPrice, format(priceInCents));
    }

    public static String format(int priceInCents) {
        return String.format("$%,.2f", priceInCents / 100.0d);/*###1*/
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
* The % marks the beginning of a 'formatMonetaryAmount specifier' or the formatMonetaryAmount in which a
* a particular argument of formatMonetaryAmount() is to be printed. The formatMonetaryAmount specifier
* ends with a 'converter';
*
* ,.2f is a 'formatMonetaryAmount specifier'  which means print only two places after a decimal
* of the desired argument. If the arg has only 1 decimal, then a zero will be added.
* The f is a 'converter' which marks the end of the formatMonetaryAmount specifier. The comma
* tells formatMonetaryAmount() to group the whole number part of the argument per locale-specific
* grouping. That is, Americans will get "4,189.76", Germans will get '4.189.76'
* & Swiss Germans will get 4'189'76 etc.
*
* */
