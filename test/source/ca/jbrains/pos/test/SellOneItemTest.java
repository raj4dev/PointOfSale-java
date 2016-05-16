package ca.jbrains.pos.test;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SellOneItemTest {
    @Test
    public void productFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale();

        sale.onBarcode("12345");/* ###1 */
        assertEquals("$7.95", display.getText());
    }

    /*###2*/
    public static class Display {
        public String getText() {
            return "$7.95";
        }
    }

    /*Refer notes ###2*/
    public static class Sale {
        public void onBarcode(String barCode) {
            /*No Return value !!! Event handlers do NOT return values*/
        }
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


###2 Created Display as inner class instead of separate for ease of learning. Public
    keyword not needed at the moment. Static needed so that we don't have to
    instantiate the above test class to use Display.

*/