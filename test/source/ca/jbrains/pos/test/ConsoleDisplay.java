package ca.jbrains.pos.test;

public class ConsoleDisplay {

    public static final String PRODUCT_NOT_FOUND_MESSAGE_FORMAT = "Product not found for %s";
    public static final String SCANNING_ERROR_EMPTY_BARCODE_MESSAGE_FORMAT = "Scanning error: empty barcode.";
    public static final String PRICE_IN_DOLLARS_MESSAGE_FORMAT = "$%,.2f";

    public void displayProductNotFoundMessage(Object... placeholders) {
        System.out.println(
                String.format(PRODUCT_NOT_FOUND_MESSAGE_FORMAT, placeholders));
    }

    public void displayEmptyBarcodeMessage(Object... placeholders) {
        System.out.println(
                String.format(SCANNING_ERROR_EMPTY_BARCODE_MESSAGE_FORMAT));
    }

    public void displayPriceInDollars(Price price) {
        System.out.println(
                String.format(PRICE_IN_DOLLARS_MESSAGE_FORMAT, price.dollarValue()));
    }
}
