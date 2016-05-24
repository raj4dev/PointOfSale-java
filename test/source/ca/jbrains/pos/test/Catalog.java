package ca.jbrains.pos.test;

import java.util.HashMap;
import java.util.Map;

public class Catalog {
    private Map<String, Integer> pricesInCentsByBarcode;

    Catalog(HashMap<String, Integer> pricesInCentsByBarcode) {
        this.pricesInCentsByBarcode = pricesInCentsByBarcode;
    }

    public Integer findPrice(String barCode) {
        return pricesInCentsByBarcode.get(barCode);
    }
}
