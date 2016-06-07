package ca.jbrains.pos.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

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

    }
}
