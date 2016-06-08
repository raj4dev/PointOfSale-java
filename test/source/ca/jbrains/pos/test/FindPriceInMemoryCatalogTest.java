package ca.jbrains.pos.test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.InMemoryCatalog;
import ca.jbrains.pos.Price;

import java.util.Collections;
import java.util.HashMap;

public class FindPriceInMemoryCatalogTest extends FindPriceInCatalogContract {

    @Override
    protected Catalog catalogWith(final String barcode, final Price price) {
        return new InMemoryCatalog(new HashMap<String, Price>() {{
            put("definitely not " + barcode, Price.cents(0));
            put(barcode, price);
            put("once again, definitely not " + barcode, Price.cents(1000000));
        }});
    }

    @Override
    protected Catalog catalogWithout(String barcodeToAvoid) {
        return new InMemoryCatalog(Collections.singletonMap("anything but " + barcodeToAvoid, Price.cents(0)));
    }

}
