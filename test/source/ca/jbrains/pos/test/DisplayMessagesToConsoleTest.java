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

    @Test
    public void emptyBarcodeMessage() throws Exception {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        new ConsoleDisplay().displayEmptyBarcodeMessage();

        assertEquals(Arrays.asList("Scanning error: empty barcode."), TextUtilities.lines(canvas.toString("UTF-8")));
    }

    @Test
    public void multipleMessages() throws Exception {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        ConsoleDisplay consoleDisplay = new ConsoleDisplay();
        consoleDisplay.displayProductNotFoundMessage("91837248");
        consoleDisplay.displayEmptyBarcodeMessage();
        consoleDisplay.displayProductNotFoundMessage("32871");
        consoleDisplay.displayEmptyBarcodeMessage();

        assertEquals(Arrays.asList("Product not found for 91837248",
                "Scanning error: empty barcode.",
                "Product not found for 32871",
                "Scanning error: empty barcode."), TextUtilities.lines(canvas.toString("UTF-8")));
    }

}
