package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

public abstract class FindPriceInCatalogContract {
    @Test
    public void productFound() throws Exception {

        Price foundPrice = Price.cents(1250);
        Catalog catalog = catalogWith("12345", foundPrice);
        Assert.assertEquals(foundPrice, catalog.findPrice("12345"));
    }

    protected abstract Catalog catalogWith(String barcode, Price price);/*factory method*/

    @Test
    public void productNotFound() throws Exception {
        Catalog catalog = catalogWithout("12345");
        Assert.assertEquals(null, catalog.findPrice("12345"));
    }

    protected abstract Catalog catalogWithout(String barcodeToAvoid);/*factory method*/
}


/*
---------------------------------------
NOTES
---------------------------------------
JUnit will not run these tests even though it has test methods. But, it will run
these tests only when a concrete class inherits from this class.
*/