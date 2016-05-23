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

    public String findPriceThenFormatPrice(String barCode) {
        return pricesAsTextByBarcode.get(barCode);
    }
}
