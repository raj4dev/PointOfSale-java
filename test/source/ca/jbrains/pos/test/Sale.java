package ca.jbrains.pos.test;

import java.util.ArrayList;
import java.util.Collection;

/*Refer notes ###2*/
public class Sale {
    private final Catalog catalog;
    private Display display;

    private Collection<Integer> pendingPurchaseItemPrices = new ArrayList<Integer>();

    public Sale(Display display, Catalog catalog) {
        this.display = display;
        this.catalog = catalog;
    }

    public void onBarcode(String barCode) {
        /*###3*/
        if ("".equals(barCode)) {
            display.displayEmptyBarcodeMessage();
            return; /*A guard clause*/
        }

        Integer priceInCents = catalog.findPrice(barCode);/*finding price*/

        if (priceInCents == null) {
            display.displayProductNotFoundMessage(barCode);
        } else {
            //REFACTOR Eventually a shopping cart ?
            pendingPurchaseItemPrices.add(priceInCents);
            display.displayPrice(priceInCents);
        }

        /*No Return value !!! Event handlers do NOT return values*/
    }

    public void onTotal() {
        /*Should have inlined this in the if. Not a problem. Maybe will do in the future.*/
        boolean noSaleInProgress = pendingPurchaseItemPrices.isEmpty();

        if (noSaleInProgress) {
            display.displayNoSaleInProgressMessage();
        } else {
            display.displayPurchaseTotal(pendingPurchaseTotal());
        }
    }

    //REFACTOR looks like model (MVC) behavior to me.
    private Integer pendingPurchaseTotal() {
        return computePurchaseTotal(pendingPurchaseItemPrices);/*This approach works only when every sale has 1 item.*/
    }

    public static Integer computePurchaseTotal(Collection<Integer> purchaseItemPrices) {
        return  purchaseItemPrices.iterator().next();
    }

}


/*

---------------------------------------------
NOTES
---------------------------------------------

###2 Created Display as inner class instead of separate for ease of learning. Public
    keyword not needed at the moment. Static needed so that we don't have to
    instantiate the above test class to use Display.

###3 Code smell: "Refused Bequest": move this up the call stack? Perhaps onBarcode
        should not check validity of a barcode & something else should do it. Now,
        the 1st if validates data & the next one processes it. Maybe we should do
        the data validation in another function. Lets see.

*/
