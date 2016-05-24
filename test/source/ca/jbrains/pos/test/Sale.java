package ca.jbrains.pos.test;

/*Refer notes ###2*/
public class Sale {
    private final Catalog catalog;
    private Display display;
    private String scannedPrice;

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

        String result;
        Integer priceInCents = catalog.findPrice(barCode);/*finding price*/

        /*formatting price*/
        if (priceInCents == null) {
            result = null;
        } else {
            result = Catalog.format(priceInCents);
        }
        scannedPrice = result;
        if (scannedPrice == null) {
            display.displayProductNotFoundMessage(barCode);
        } else {
            display.displayPrice(scannedPrice);
        }

        /*No Return value !!! Event handlers do NOT return values*/
    }

    public void onTotal() {
        boolean saleInProgress = scannedPrice != null;
        if (saleInProgress) {
            display.displayPurchaseTotal(scannedPrice);
        } else {
            display.displayNoSaleInProgressMessage();
        }
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
