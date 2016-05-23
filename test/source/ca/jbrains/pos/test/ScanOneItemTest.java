package ca.jbrains.pos.test;

import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class ScanOneItemTest {

    @Test
    public void productFound() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display, new Catalog(new HashMap<String, String>() {{
            put("12345", "$7.95");
            put("23456", "$12.50");
        }}, new HashMap<String, Integer>() {{
            put("12345", 795);
            put("23456", 1250);
        }}));

        sale.onBarcode("12345");/* ###1 */
        assertEquals("$7.95", display.getText());
    }

    @Test
    public void anotherProductFound() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display, new Catalog(new HashMap<String, String>() {{
            put("12345", "$7.95");
            put("23456", "$12.50");
        }}, new HashMap<String, Integer>() {{
            put("12345", 795);
            put("23456", 1250);
        }}));

        sale.onBarcode("23456");
        assertEquals("$12.50", display.getText());
    }

    @Test
    public void productNotFound() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display, new Catalog(new HashMap<String, String>() {{
            put("12345", "$7.95");
            put("23456", "$12.50");
        }}, new HashMap<String, Integer>() {{
            put("12345", 795);
            put("23456", 1250);
        }}));

        sale.onBarcode("99999");
        assertEquals("Product not found for 99999", display.getText());
    }

    @Test
    public void emptyBarcode() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display, new Catalog(new HashMap<String, String>() {{
            put("12345", "$7.95");
            put("23456", "$12.50");
        }}, new HashMap<String, Integer>() {{
            put("12345", 795);
            put("23456", 1250);
        }}));

        sale.onBarcode("");
        assertEquals("Scanning error: empty barcode", display.getText());
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

*/
