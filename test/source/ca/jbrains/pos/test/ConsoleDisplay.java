package ca.jbrains.pos.test;

public class ConsoleDisplay {
    private static String formatPrice(Price price) {
        return String.format("$%,.2f", price.dollarValue());
    }

    public void displayProductNotFoundMessage(String barcodeNotFound) {
        System.out.println("Product not found for " + barcodeNotFound);
    }

    public void displayEmptyBarcodeMessage() {
        System.out.println("Scanning error: empty barcode.");
    }

    public void displayPrice(Price price) {
        System.out.println(formatPrice(price));
    }
}
