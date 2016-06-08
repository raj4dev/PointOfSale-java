package ca.jbrains.pos.test;

public class ConsoleDisplay {

    public static final String PRODUCT_NOT_FOUND_MESSAGE_FORMAT = "Product not found for %s";
    public static final String SCANNING_ERROR_EMPTY_BARCODE_MESSAGE_FORMAT = "Scanning error: empty barcode.";
    public static final String PRICE_IN_DOLLARS_MESSAGE_FORMAT = "$%,.2f";

    public void displayProductNotFoundMessage(Object... placeholders) {
        displayMessage(PRODUCT_NOT_FOUND_MESSAGE_FORMAT, placeholders);
    }

    private void displayMessage(String messageTemplate, Object... placeholders) {
        renderMessage(messageTemplate, placeholders);
    }

    private void renderMessage(String messageTemplate, Object... placeholders) {
        System.out.println(
                formatMessage(messageTemplate, placeholders));
    }

    private String formatMessage(String messageTemplate, Object... placeholders) {
        return String.format(messageTemplate, placeholders);
    }

    public void displayEmptyBarcodeMessage(Object... placeholders) {
        displayMessage(SCANNING_ERROR_EMPTY_BARCODE_MESSAGE_FORMAT);
    }

    public void displayPriceInDollars(Object... placeholders) {
        displayMessage(PRICE_IN_DOLLARS_MESSAGE_FORMAT, placeholders);
    }
}
