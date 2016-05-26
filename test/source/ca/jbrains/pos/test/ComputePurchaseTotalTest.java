package ca.jbrains.pos.test;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ComputePurchaseTotalTest {
    @Test
    public void zeroItems() throws Exception {
        assertEquals(0, computePurchaseTotal(Collections.<Integer>emptyList()));
    }

    /*Added this method because both args of assertEquals must be long or int*/
    private int computePurchaseTotal(List<Integer> purchaseItemPrices) {
        return Sale.computePurchaseTotal(purchaseItemPrices).intValue();
    }
}
