package ca.jbrains.pos.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class LearnHowToHijackSystemOutTest {

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
    public void singleLineOfText() throws Exception {
        //Action of this test should be writing a single line to System.out.
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        System.out.println("Hello, world.");

        //Assertion of this test should be to check the ByteArrayOutputStream & get 'hello world' back.
        assertEquals("Hello, world.\r\n", canvas.toString("UTF-8"));
    }
}
