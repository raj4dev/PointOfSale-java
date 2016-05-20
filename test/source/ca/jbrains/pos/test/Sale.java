package ca.jbrains.pos.test;

/*Refer notes ###2*/
public class Sale {
    private final Catalog catalog;
    private Display display;
    private String priceAsText;

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

        priceAsText = catalog.findPrice(barCode);
        if (priceAsText == null) {
            display.displayProductNotFoundMessage(barCode);
        } else {
            display.displayPrice(priceAsText);
        }

        /*No Return value !!! Event handlers do NOT return values*/
    }

    public void onTotal() {
        boolean saleInProgress = priceAsText != null;
        if (saleInProgress) {
            display.displayPurchaseTotal(priceAsText);
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
