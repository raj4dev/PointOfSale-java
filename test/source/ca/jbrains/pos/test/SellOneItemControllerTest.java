package ca.jbrains.pos.test;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class SellOneItemControllerTest {

    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void productNotFound() throws Exception {

        final Catalog catalog = context.mock(Catalog.class);
        final Display display = context.mock(Display.class);
        final Price cents = Price.cents(795);

        /* Assertion part of the test.*/
        /* With JMock, assertion comes before the action. Weird :)*/
        context.checking(new Expectations() {{
            allowing(catalog).findPrice(with("12345"));/*###2*/
            will(returnValue(cents));/*###2*/

            oneOf(display).displayPrice(with(cents));/*###1*/
        }});

        /* Action part of the test*/
        SaleController saleController = new SaleController();
        saleController.onBarcode("12345");
    }

    public interface Catalog {
        void findPrice(String barCode);
    }

    public interface Display {
        void displayPrice(Price price);
    }

    public static class SaleController {
        private Display display;
        private Catalog catalog;

        public void onBarcode(String barCode) {
            display.displayPrice(catalog.findPrice(barCode));/*We use whichever price the catalog gives us.*/
        }
    }

    public static class Price {
        public static Price cents(int centsValue) {
            return new Price();
        }

        @Override
        public String toString() {
            return "a Price";
        }
    }

}

/*
----------------------------------------
NOTES
----------------------------------------

###1

MEANING - Something in this test needs to invoke 'displayPrice' on the 'display',
'with' the parameters 7$ & 95cents only once ('oneOf'). This line is like an
assertion. Fun fact: If you did this with the Ruby based RSpec library, then it
would be expect(display).to receive(:display_price).with(Price.cents(795))

Now, there is a little bit of design upfront i.e., Price.cents(795) because we sort
of know what we want . We could replace it by just 795 if we weren't so sure.

###2
MEANING - It means that JMock does not mind if we actually call findPrice on a catalog
or not. But, if we call findPrice with "12345", then returned value should be 795 cents.
Btw, the first line is a method stub.  Test stubs are programs that simulate the behaviors
of software components (or modules) that a module undergoing tests depends on.

###Test output
java.lang.AssertionError: not all expectations were satisfied expectations:
allowed, never invoked: catalog.findPrice("12345"); returns <a Price>
    Meaning: You said you'd call findPrice(), but you did not.
! expected once, never invoked: display.displayPrice(<a Price>)
what happened before this: nothing!...etc.
    Meaning: You said you'd call displayPrice(), but you did not.

*/
