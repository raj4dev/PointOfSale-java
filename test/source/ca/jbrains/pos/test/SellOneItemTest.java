package ca.jbrains.pos.test;

import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SellOneItemTest {
    @Test
    public void productFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display);

        sale.onBarcode("12345");/* ###1 */
        assertEquals("$7.95", display.getText());
    }

    @Test
    @Ignore("Refactoring...")
    public void anotherProductFound() throws Exception {
        final Display display = new Display();
        final Sale sale = new Sale(display);

        sale.onBarcode("23456");/* ###1 */
        assertEquals("$12.50", display.getText());
    }

    /*###2*/
    public static class Display {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    /*Refer notes ###2*/
    public static class Sale {
        private Display display;

        public Sale(Display display) {
            this.display = display;
        }

        public void onBarcode(String barCode) {
            display.setText("$7.95");
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