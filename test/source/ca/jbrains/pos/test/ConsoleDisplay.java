package ca.jbrains.pos.test;

public class ConsoleDisplay {

    public void displayProductNotFoundMessage(String barcodeNotFound) {
        System.out.println("Product not found for " + barcodeNotFound);
    }

    public void displayEmptyBarcodeMessage() {
        System.out.println("Scanning error: empty barcode.");
    }

    public void displayPrice(Price price) {
        System.out.println(String.format("$%,.2f", price.dollarValue()));
    }
}
