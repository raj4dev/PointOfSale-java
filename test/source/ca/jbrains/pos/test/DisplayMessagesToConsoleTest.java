package ca.jbrains.pos.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class DisplayMessagesToConsoleTest {

    private PrintStream productionSystemOut;

    @Before
    public void rememberSystemOut() {
        productionSystemOut = System.out;
    }

    @After
    public void restoreSystemOut() {
        System.setOut(productionSystemOut);
    }

    @Test
    public void productNotFoundMessage() throws Exception {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        new ConsoleDisplay().displayProductNotFoundMessage("91837248");

        assertEquals(Arrays.asList("Product not found for 91837248"), TextUtilities.lines(canvas.toString("UTF-8")));
    }

    public static class ConsoleDisplay {
        public void displayProductNotFoundMessage(String barcodeNotFound) {
            System.out.println("Product not found for " + barcodeNotFound);
        }
    }
}
