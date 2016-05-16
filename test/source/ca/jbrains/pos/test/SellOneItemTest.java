package ca.jbrains.pos.test;

import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class SellOneItemTest {
    @Test
    public void productFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display);

        sale.onBarcode("12345");/* ###1 */
        assertEquals("$7.95", display.getText());
    }

    @Test
    public void anotherProductFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display);

        sale.onBarcode("23456");
        assertEquals("$12.50", display.getText());
    }

    @Test
    public void productNotFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display);

        sale.onBarcode("99999");
        assertEquals("Product not found for 99999", display.getText());
    }

    @Test
    public void emptyBarcode() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display);

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
    }

    /*Refer notes ###2*/
    public static class Sale {
        private Display display;

        public Sale(Display display) {
            this.display = display;
        }

        public void onBarcode(String barCode) {
            if ("".equals(barCode)) {
                display.setText("Scanning error: empty barcode");
            } else {
                //Introduce barcode lookup table
                Map<String, String> pricesByBarcode = new HashMap<String, String>() {{
                    put("12345", "$7.95");
                    put("23456", "$12.50");
                }};

                if (pricesByBarcode.containsKey(barCode)) {
                    display.setText(pricesByBarcode.get(barCode));
                } else {
                    display.setText("Product not found for " + barCode);
                }
            }
            /*No Return value !!! Event handlers do NOT return values*/
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

*/