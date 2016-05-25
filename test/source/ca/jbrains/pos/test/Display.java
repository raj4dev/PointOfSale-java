package ca.jbrains.pos.test;

public class Display {
    public String text;/*Make this private later*/

    //SMELL move this behavior somewhere else.
    public static String format(int priceInCents) {
        return String.format("$%,.2f", priceInCents / 100.0d);/*###1*/
    }

    public String getText() {
        return text;
    }

    public void displayText(String priceAsText) {
        this.text = priceAsText;
    }

    public void displayProductNotFoundMessage(String barCode) {
        this.text = "Product not found for " + barCode;
    }

    public void displayEmptyBarcodeMessage() {
        this.text = "Scanning error: empty barcode";
    }

    public void displayNoSaleInProgressMessage() {
        this.text = "No sale in progress. Try scanning a product.";
    }

    public void displayPurchaseTotal(String priceAsText, Integer purchaseTotal) {
        this.text = "Total: " + priceAsText;
    }

    public void displayPrice(Integer priceInCents) {
        displayText(format(priceInCents));
    }
}
