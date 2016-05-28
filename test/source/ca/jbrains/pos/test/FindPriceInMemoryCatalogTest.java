package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

public class FindPriceInMemoryCatalogTest {
    @Test
    public void productFound() throws Exception {

        Price foundPrice = Price.cents(1250);
        Catalog catalog = catalogWith("12345", foundPrice);
        Assert.assertEquals(foundPrice, catalog.findPrice("12345"));
    }

    private Catalog catalogWith(String barcode, Price price) {
        return new InMemoryCatalog(Collections.singletonMap(barcode, price));
    }

    @Test
    public void productNotFound() throws Exception {
        Catalog catalog = catalogWithout("12345");
        Assert.assertEquals(null, catalog.findPrice("12345"));
    }

    private Catalog catalogWithout(String barcodeToAvoid) {
        return new InMemoryCatalog(Collections.emptyMap());
    }

    public static class InMemoryCatalog implements Catalog {
        private Map<String, Price> pricesByBarcode;

        public InMemoryCatalog(Map<String, Price> pricesByBarcode) {
            this.pricesByBarcode = pricesByBarcode;
        }

        public Price findPrice(String barcode) {
            return pricesByBarcode.get(barcode);
        }
    }

}
