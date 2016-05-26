package ca.jbrains.pos.test;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SellMultipleItemsTest {
    @Test
    public void zeroItems() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display, null);
        sale.onTotal();
        assertEquals("No sale in progress. Try scanning a product.", display.getText());
    }

    @Test
    public void oneItemFound() throws Exception {
        Catalog catalog = new Catalog(new HashMap<String, Integer>() {{
            put("12345", 650);
        }});
        Display display = new Display();
        Sale sale = new Sale(display, catalog);

        sale.onBarcode("12345");
        sale.onTotal();
        assertEquals("Total: $6.50", display.getText());
    }

    @Test
    public void oneItemNotFound() throws Exception {
        Catalog catalog = new Catalog(new HashMap<String, Integer>() {{
            put("12345", 650);
        }});
        Display display = new Display();
        Sale sale = new Sale(display, catalog);

        sale.onBarcode("99999");
        sale.onTotal();
        assertEquals("No sale in progress. Try scanning a product.", display.getText());
    }

    @Test
    public void severalItemsAllNotFound() throws Exception {
        Display display = new Display();
        /*We can have an empty catalog because the items in the test are supposed to be missing from the catalog.*/
        Sale sale = new Sale(display, catalogWithoutBarCodes(
                "product you won't find.",
                "another product you won't find.",
                "yet another product you won't find."
        ));

        /*We can use any random characters for a barcode instead of a number for this test case because
        * we have no rules for making barcodes at the moment. The barcodes below are self documenting.*/
        sale.onBarcode("product you won't find.");
        sale.onBarcode("another product you won't find.");
        sale.onBarcode("yet another product you won't find.");
        sale.onTotal();

        assertEquals("No sale in progress. Try scanning a product.", display.getText());
    }

    @Test
    public void severalItemsAllFound() throws Exception {
        Catalog catalog = new Catalog(new HashMap<String, Integer>() {{
            put("1", 850);
            put("2", 1275);
            put("3", 330);
        }});

        Display display = new Display();
        Sale sale = new Sale(display, catalog);

        sale.onBarcode("1");
        sale.onBarcode("2");
        sale.onBarcode("3");
        sale.onTotal();

        assertEquals("Total: $24.55", display.getText());
    }

    @Test
    public void testName() throws Exception {
        Catalog catalog = new Catalog(new HashMap<String, Integer>() {{
            put("1", 1200);
            put("2", 500);
        }});

        Display display = new Display();
        Sale sale = new Sale(display, catalog);

        sale.onBarcode("1");
        sale.onBarcode("you don't know this product.");
        sale.onBarcode("2");
        sale.onTotal();

        assertEquals("Total: $17.00", display.getText());
    }

    @Test
    public void severalItemsOfWhichOneIsEmpty() throws Exception {
        Catalog catalog = new Catalog(new HashMap<String, Integer>() {{
            put("1", 3100);
            put("2", 460);
        }});

        Display display = new Display();
        Sale sale = new Sale(display, catalog);

        sale.onBarcode("1");
        sale.onBarcode("");
        sale.onBarcode("2");
        sale.onTotal();

        assertEquals("Total: $35.60", display.getText());
    }

    private Catalog catalogWithoutBarCodes(String ...barcodesToExclude) {
        return emptyCatalog();
    }

    private Catalog emptyCatalog() {
        return new Catalog(new HashMap<String, Integer>() {{}});
    }
}
