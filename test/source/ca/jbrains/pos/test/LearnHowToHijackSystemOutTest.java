package ca.jbrains.pos.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;

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
        assertEquals(Collections.singletonList("Hello, world."), TextUtilities.lines(canvas.toString("UTF-8")));
    }

    @Test
    public void severalLinesOfText() throws Exception {
        /*
        * This test checks if the code changes we made to avoid the problem of newline character work for
        * multiple line input or not.
        * */
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        for(int i =1; i<= 5; i++){
            System.out.println("Line " + i);
        }

        assertEquals(Arrays.asList("Line 1","Line 2","Line 3","Line 4","Line 5"), TextUtilities.lines(canvas.toString("UTF-8")));
    }

}
