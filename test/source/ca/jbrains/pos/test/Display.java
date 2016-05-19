package ca.jbrains.pos.test;

public class Display {
    public String text;/*Make this private later*/

    public String getText() {
        return text;
    }

    public void displayPrice(String priceAsText) {
        this.text = priceAsText;
    }

    public void displayProductNotFoundMessage(String barCode) {
        this.text = "Product not found for " + barCode;
    }

    public void displayEmptyBarcodeMessage() {
        this.text = "Scanning error: empty barcode";
    }

    public void displayNoSaleInProgressMessage(){
        this.text = "No sale in progress. Try scanning a product.";
    }

    public void displayPurchaseTotal(String priceAsText) {
        this.text = "Total: " + priceAsText;
    }
}
