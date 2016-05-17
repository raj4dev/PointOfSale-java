package ca.jbrains.pos.test;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class SellOneItemTest {

    private Display display;
    private Sale sale;

    @Before
    public void setUp() throws Exception {
        display = new Display();
        sale = new Sale(display, new Catalog(new HashMap<String, String>() {{
            put("12345", "$7.95");
            put("23456", "$12.50");
        }}));
    }

    @Test
    public void productFound() throws Exception {
        sale.onBarcode("12345");/* ###1 */
        assertEquals("$7.95", display.getText());
    }

    @Test
    public void anotherProductFound() throws Exception {
        sale.onBarcode("23456");
        assertEquals("$12.50", display.getText());
    }

    @Test
    public void productNotFound() throws Exception {
        sale.onBarcode("99999");
        assertEquals("Product not found for 99999", display.getText());
    }

    @Test
    public void emptyBarcode() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display, new Catalog(null));

        sale.onBarcode("");
        assertEquals("Scanning error: empty barcode", display.getText());
    }

    /*###2*/
    public static class Display {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void displayPrice(String priceAsText) {
            setText(priceAsText);
        }

        public void displayProductNotFoundMessage(String barCode) {
            setText("Product not found for " + barCode);
        }

        public void displayEmptyBarcodeMessage() {
            setText("Scanning error: empty barcode");
        }
    }

    /*Refer notes ###2*/
    public static class Sale {
        private final Catalog catalog;
        private Display display;
        private Map<String, String> pricesByBarcode;

        public Sale(Display display, Catalog catalog) {
            this.display = display;
            this.catalog = catalog;
            //Introduce barcode lookup table
            this.pricesByBarcode = catalog.getPricesByBarcode();
        }

        public void onBarcode(String barCode) {
            /*###3*/
            if ("".equals(barCode)) {
                display.displayEmptyBarcodeMessage();
                return; /*A guard clause*/
            }

            final String priceAsText = catalog.findPrice(barCode, this);
            if (priceAsText == null) {
                display.displayProductNotFoundMessage(barCode);
            } else {
                display.displayPrice(priceAsText);
            }

            /*No Return value !!! Event handlers do NOT return values*/
        }

    }

    public static class Catalog {
        private final Map<String, String> pricesByBarcode;

        private Catalog(Map<String, String> pricesByBarcode) {
            this.pricesByBarcode = pricesByBarcode;
        }

        /*We created the Catalog class to encapsulate the "thing" which holds
        * the prices-barcode information. So, the "thing" must not be accessible
        * directly to code outside Catalog. Lets fix this soon.*/
        public Map<String, String> getPricesByBarcode() {
            return pricesByBarcode;
        }

        public String findPrice(String barCode, Sale sale) {
            return sale.pricesByBarcode.get(barCode);
        }
    }
}



/*
---------------------------------------------
NOTES
---------------------------------------------

###1 We want to receive a bar code from the outside world. We model this an
    incoming event on some object. Perhaps a "sale" object ? Now, the
    onBarcode name comes from event naming rules of Java, i.e. onSomethingHappening.
    The barcode of 12345 is random. Its a dummy barcode for an item which costs $7.95.


###2 Created Display as inner class instead of separate for ease of learning. Public
    keyword not needed at the moment. Static needed so that we don't have to
    instantiate the above test class to use Display.

###3 Code smell: "Refused Bequest": move this up the call stack? Perhaps onBarcode
     should not check validity of a barcode & something else should do it. Now,
     the 1st if validates data & the next one processes it. Maybe we should do
     the data validation in another function. Lets see.
*/
