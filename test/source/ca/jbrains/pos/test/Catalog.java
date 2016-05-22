package ca.jbrains.pos.test;

import java.util.HashMap;
import java.util.Map;

public class Catalog {
    private final Map<String, String> pricesByBarcode;
    private final Map<String, String> pricesInCentsByBarcode;

    /** @@deprecated */
    Catalog(Map<String, String> pricesByBarcode) {
        this.pricesByBarcode = pricesByBarcode;
    }

    public Catalog(HashMap<String, String> pricesAsTextByBarcode, HashMap<String, Integer> pricesInCentsByBarcode) {
    }


    public String findPrice(String barCode) {
        return pricesByBarcode.get(barCode);
    }
}
