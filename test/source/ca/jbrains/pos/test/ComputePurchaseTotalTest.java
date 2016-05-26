package ca.jbrains.pos.test;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class ComputePurchaseTotalTest {
    @Test
    public void zeroItems() throws Exception {
        assertEquals(0, computePurchaseTotal());
    }

    /*Added this method because both args of assertEquals must be long or int*/
    private int computePurchaseTotal() {
        return Sale.computePurchaseTotal(Collections.<Integer>emptyList()).intValue();
    }
}
