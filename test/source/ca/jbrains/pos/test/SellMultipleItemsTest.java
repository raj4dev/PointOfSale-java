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
        Catalog catalog = new Catalog(new HashMap<String, String>() {{
            put("12345", "$6.50");
        }}, new HashMap<String, Integer>() {{
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
        Catalog catalog = new Catalog(new HashMap<String, String>() {{
            put("12345", "$6.50");
        }}, new HashMap<String, Integer>() {{
            put("12345", 650);
        }});
        Display display = new Display();
        Sale sale = new Sale(display, catalog);

        sale.onBarcode("99999");
        sale.onTotal();
        assertEquals("No sale in progress. Try scanning a product.", display.getText());
    }
}
