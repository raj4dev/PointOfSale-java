package ca.jbrains.pos.test;

public class ConsoleDisplay {

    public static final String PRODUCT_NOT_FOUND_MESSAGE_FORMAT = "Product not found for %s";
    public static final String SCANNING_ERROR_EMPTY_BARCODE_MESSAGE_FORMAT = "Scanning error: empty barcode.";
    public static final String PRICE_IN_DOLLARS_MESSAGE_FORMAT = "$%,.2f";

    public void displayProductNotFoundMessage(String barcodeNotFound) {
        System.out.println(
                String.format(PRODUCT_NOT_FOUND_MESSAGE_FORMAT, barcodeNotFound));
    }

    public void displayEmptyBarcodeMessage() {
        System.out.println(
                String.format(SCANNING_ERROR_EMPTY_BARCODE_MESSAGE_FORMAT));
    }

    public void displayPrice(Price price) {
        System.out.println(
                String.format(PRICE_IN_DOLLARS_MESSAGE_FORMAT, price.dollarValue()));
    }
}
