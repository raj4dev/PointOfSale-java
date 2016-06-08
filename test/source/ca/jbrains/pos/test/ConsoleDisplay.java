package ca.jbrains.pos.test;

public class ConsoleDisplay {

    public static final String PRODUCT_NOT_FOUND_MESSAGE_FORMAT = "Product not found for %s";
    public static final String SCANNING_ERROR_EMPTY_BARCODE_MESSAGE_FORMAT = "Scanning error: empty barcode.";
    public static final String PRICE_IN_DOLLARS_MESSAGE_FORMAT = "$%,.2f";

    private void render(String text) {
        System.out.println(text);
    }

    private String mergeTemplate(String messageTemplate, Object... placeholderValues) {
        return String.format(messageTemplate, placeholderValues);
    }

    public void displayProductNotFoundMessage(String barcodeNotFound) {
        render(mergeTemplate(PRODUCT_NOT_FOUND_MESSAGE_FORMAT, barcodeNotFound));
    }

    public void displayEmptyBarcodeMessage() {
        render(mergeTemplate(SCANNING_ERROR_EMPTY_BARCODE_MESSAGE_FORMAT));
    }

    public void displayPrice(Price price) {
        render(mergeTemplate(PRICE_IN_DOLLARS_MESSAGE_FORMAT, price.dollarValue()));
    }
}
