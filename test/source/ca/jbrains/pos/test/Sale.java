package ca.jbrains.pos.test;

import java.util.ArrayList;
import java.util.Collection;

public class Sale {
    private final Catalog catalog;
    private Display display;

    private Collection<Integer> pendingPurchaseItemPrices = new ArrayList<Integer>();

    public Sale(Display display, Catalog catalog) {
        this.display = display;
        this.catalog = catalog;
    }

    public void onBarcode(String barCode) {
        /*###1*/
        if ("".equals(barCode)) {
            display.displayEmptyBarcodeMessage();
            return; /*A guard clause*/
        }

        Integer priceInCents = catalog.findPrice(barCode);/*finding price*/

        if (priceInCents == null) {
            display.displayProductNotFoundMessage(barCode);
        } else {
            //REFACTOR Eventually a shopping cart ?
            pendingPurchaseItemPrices.add(priceInCents);
            display.displayPrice(priceInCents);
        }

        /*No Return value !!! Event handlers do NOT return values*/
    }

    public void onTotal() {
        /*Should have inlined this in the if. Not a problem. Maybe will do in the future.*/
        boolean noSaleInProgress = pendingPurchaseItemPrices.isEmpty();

        if (noSaleInProgress) {
            display.displayNoSaleInProgressMessage();
        } else {
            display.displayPurchaseTotal(pendingPurchaseTotal());
        }
    }

    //REFACTOR looks like model (MVC) behavior to me.
    private Integer pendingPurchaseTotal() {
        return computePurchaseTotal(pendingPurchaseItemPrices);/*This approach works only when every sale has 1 item.*/
    }

    public static Integer computePurchaseTotal(Collection<Integer> purchaseItemPrices) {
        if (false)
            return 0;
        else if(purchaseItemPrices.size() == 1)
            return purchaseItemPrices.iterator().next();
        else
            return purchaseItemPrices.stream().reduce(0, Integer::sum);/*###2*/
    }

}


/*

---------------------------------------------
NOTES
---------------------------------------------

###1 Code smell: "Refused Bequest": move this up the call stack? Perhaps onBarcode
        should not check validity of a barcode & something else should do it. Now,
        the 1st if validates data & the next one processes it. Maybe we should do
        the data validation in another function. Lets see.

###2 Welcome to Java Streams and method references ! This is a crude explanation
        for the code. Stream() lets us consume data from purchaseItemPrices. We
        can select data which meets certain conditions & we can also do something
        with the selected data.
        We want to get the sum of all prices in purchaseItemPrices.
            stream() - gets us selected data from purchaseItemPrices. In our case, we
        get all the data. We can select or filter the data based on some conditions
        using filter(), if needed.

            reduce(0, Integer::sum) - "reduces" the streamed data into a single value.
        "Reduction" can mean what you want it to. If a stream has strings, reduce()
        can be made to concatenate all strings. If a stream has integers, reduce()
        could be made to add them, multiply them, subtract them etc. to yield one int.

        reduce(r, L) - "r" stores the initial value of reduction & the finally the reduced value.
        "L" tells us how we want to reduce the given stream. "L" can be a lambda expression or a
        reference to a method (Integer.sum(a,b) i.e. Integer::sum in our case). Alternately, we
        could express the Integer::sum as a lambda, like this (sum, element) -> sum + element.
        Logically, the reduction works like this:
        r = 0;
        sum = r;
        for each element in the stream {
            sum = Integer.sum(sum, element);
        }
        r = sum;

        References:
        Streams: http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html
        http://tutorials.jenkov.com/java-collections/streams.html
        Reduce: https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html

*/
