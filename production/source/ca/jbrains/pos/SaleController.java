package ca.jbrains.pos;

public class SaleController {
    private Display display;
    private Catalog catalog;

    public SaleController(Catalog catalog, Display display) {
        this.display = display;
        this.catalog = catalog;
    }

    public void onBarcode(String barCode) {
        //CODE SMELL Should I get an empty barcode at all?
        if ("".equals(barCode)) {
            display.displayEmptyBarcodeMessage();
            return;
        }

        Price price = catalog.findPrice(barCode);

        if (price == null) {
            display.displayProductNotFoundMessage(barCode);
        } else {
            display.displayPrice(price);
        }
    }
}
