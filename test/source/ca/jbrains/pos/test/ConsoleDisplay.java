package ca.jbrains.pos.test;

public class ConsoleDisplay {

    public static final String PRODUCT_NOT_FOUND_MESSAGE_FORMAT = "Product not found for %s";
    public static final String SCANNING_ERROR_EMPTY_BARCODE_MESSAGE_FORMAT = "Scanning error: empty barcode.";
    public static final String PRICE_IN_DOLLARS_MESSAGE_FORMAT = "$%,.2f";

    private void displayMessage(String messageTemplate, Object... placeholders) {
        render(formatMessage(messageTemplate, placeholders));
    }

    private void render(String text) {
        System.out.println(
                text);
    }

    private String formatMessage(String messageTemplate, Object... placeholders) {
        return String.format(messageTemplate, placeholders);
    }

    public void displayProductNotFoundMessage(Object... placeholderValues) {
        displayMessage(PRODUCT_NOT_FOUND_MESSAGE_FORMAT, placeholderValues);
    }

    public void displayEmptyBarcodeMessage(Object... placeholderValues) {
        displayMessage(SCANNING_ERROR_EMPTY_BARCODE_MESSAGE_FORMAT);
    }

    public void displayPriceInDollars(Object... placeholderValues) {
        displayMessage(PRICE_IN_DOLLARS_MESSAGE_FORMAT, placeholderValues);
    }
}
