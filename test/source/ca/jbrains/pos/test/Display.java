package ca.jbrains.pos.test;

public class Display {
    public String text;/*Make this private later*/

    public static String format(int priceInCents) {
        return String.format("$%,.2f", priceInCents / 100.0d);/*###1*/
    }

    public String getText() {
        return text;
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

    public void displayPurchaseTotal(Integer purchaseTotal) {
        this.text = "Total: " + format(purchaseTotal);
    }

    public void displayPrice(Integer priceInCents) {
        this.text = format(priceInCents);
    }
}

/*
* --------------------------------------------------------------
* NOTES
* --------------------------------------------------------------
*
* ###1
*
* We divide by 100.0 to get a decimal result & not a rounded off integer;
* Not sure if d is needed. Code works without it;
*
* $ prints the dollar sign as is;
* The % marks the beginning of a 'format specifier' or the format in which a
* a particular argument of format() is to be printed. The format specifier
* ends with a 'converter';
*
* ,.2f is a 'format specifier'  which means print only two places after a decimal
* of the desired argument. If the arg has only 1 decimal, then a zero will be added.
* The f is a 'converter' which marks the end of the format specifier. The comma
* tells format() to group the whole number part of the argument per locale-specific
* grouping. That is, Americans will get "4,189.76", Germans will get '4.189.76'
* & Swiss Germans will get 4'189'76 etc.
*
* */
