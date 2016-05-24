package ca.jbrains.pos.test;

import java.util.HashMap;
import java.util.Map;

public class Catalog {
    private Map<String, String> pricesAsTextByBarcode;
    private Map<String, Integer> pricesInCentsByBarcode;

    Catalog(HashMap<String, String> pricesAsTextByBarcode, HashMap<String, Integer> pricesInCentsByBarcode) {
        this.pricesAsTextByBarcode = pricesAsTextByBarcode;
        this.pricesInCentsByBarcode = pricesInCentsByBarcode;
    }

    public static String format(int priceInCents) {
        return String.format("$%,.2f", priceInCents/100.0d);/*###1*/
    }

    public String findPriceThenFormatPrice(String barCode) {
        return pricesAsTextByBarcode.get(barCode);
    }
}
