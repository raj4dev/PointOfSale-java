package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

public class FindPriceInMemoryCatalogTest {
    @Test
    public void productFound() throws Exception {

        Price foundPrice = Price.cents(1250);
        InMemoryCatalog catalog = new InMemoryCatalog(Collections.singletonMap("12345", foundPrice));
        Assert.assertEquals(foundPrice, catalog.findPrice("12345"));
    }

    public static class InMemoryCatalog {
        public InMemoryCatalog(Map<String, Price> pricesByBarcode) {
        }

        public Price findPrice(String barcode) {
            return Price.cents(0);/*Any random price object*/
        }
    }

}
