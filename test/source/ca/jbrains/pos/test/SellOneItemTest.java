package ca.jbrains.pos.test;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SellOneItemTest {
    @Test
    public void productFound() throws Exception {
        sale.onBarcode("12345");/* ###1 */
        assertEquals("$7.95", display.getText());
    }
}



/*
---------------------------------------------
NOTES
---------------------------------------------

###1 We want to receive a bar code from the outside world. We model this an
    incoming event on some object. Perhaps a "sale" object ? Now, the
    onBarcode name comes from event naming rules of Java, i.e. onSomethingHappening.
    The barcode of 12345 is random. Its a dummy barcode for an item which costs $7.95.
*/