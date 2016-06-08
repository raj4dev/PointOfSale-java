package ca.jbrains.pos.test;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class SellOneItemControllerTest {

    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void productFound() throws Exception {

        final Catalog catalog = context.mock(Catalog.class);
        final Display display = context.mock(Display.class);
        final Price irrelevantPrice = Price.cents(795);

        /* Assertion part of the test.*/
        /* With JMock, assertion comes before the action. Weird :)*/
        context.checking(new Expectations() {{
            allowing(catalog).findPrice(with("::product found::"));/*###2*/
            will(returnValue(irrelevantPrice));/*###2*/

            oneOf(display).displayPrice(with(irrelevantPrice));/*###1*/
        }});

        /* Action part of the test*/
        SaleController saleController = new SaleController(display, catalog);
        saleController.onBarcode("::product found::");
    }

    @Test
    public void productNotFound() throws Exception {

        final Catalog catalog = context.mock(Catalog.class);
        final Display display = context.mock(Display.class);

        context.checking(new Expectations() {{
            /*1. pretend there is no price for this 'barcode'....*/
            allowing(catalog).findPrice(with("::product not found::"));
            will(returnValue(null));

            /*3...something should display the message 'product not found' & mention that barcode.*/
            oneOf(display).displayProductNotFoundMessage(with("::product not found::"));
        }});

        SaleController saleController = new SaleController(display, catalog);
        /*2...then when I scan that 'barcode'...*/
        saleController.onBarcode("::product not found::");
    }

    @Test
    public void emptyBarcode() throws Exception {
        final Display display = context.mock(Display.class);

        context.checking(new Expectations() {{
            oneOf(display).displayEmptyBarcodeMessage();
        }});

        SaleController saleController = new SaleController(display, null);
        saleController.onBarcode("");
    }

    public interface Display {
        void displayPrice(Price price);

        void displayProductNotFoundMessage(String barcodeNotFound);

        void displayEmptyBarcodeMessage();
    }

    public static class SaleController {
        private Display display;
        private Catalog catalog;

        public SaleController(Display display, Catalog catalog) {
            this.display = display;
            this.catalog = catalog;
        }

        public void onBarcode(String barCode) {
            //CODE SMELL Should I get an empty barcode at all?
            if ("".equals(barCode)) {
                display.displayEmptyBarcodeMessage();
                return;
            }

            Price price = catalog.findPrice(barCode);

            if (price == null) {
                display.displayProductNotFoundMessage(barCode);
            } else {
                display.displayPrice(price);
            }
        }
    }

}

/*
----------------------------------------
NOTES
----------------------------------------

###1

MEANING - Something in this test needs to invoke 'displayPriceInDollars' on the 'display',
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

###Test output: productFound

java.lang.AssertionError: not all expectations were satisfied expectations:
allowed, never invoked: catalog.findPrice("12345"); returns <a Price>
----Meaning: You said you'd call findPrice(), but you did not.

! expected once, never invoked: display.displayPriceInDollars(<a Price>)
what happened before this: nothing!...etc.
----Meaning: You said you'd call displayPriceInDollars(), but you did not.

###Test output: productNotFound

java.lang.AssertionError: unexpected invocation: display.displayPriceInDollars(null)
expectations:
---- jmock tells us that we invoked something other than the method we were supposed to
invoke (display.displayProductNotFoundMessage).

  allowed, already invoked 1 time: catalog.findPrice("::product not found::"); returns null
  expected once, never invoked: display.displayProductNotFoundMessage("::product not found::")
what happened before this:
  catalog.findPrice("::product not found::")


*/
