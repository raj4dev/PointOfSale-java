package ca.jbrains.pos.test;

import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class LearnHowToHijackSystemOutTest {
    @Test
    public void singleLineOfText() throws Exception {
        //Action of this test should be writing a single line to System.out.
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.out.println("Hello, world.");

        //Assertion of this test should be to check the ByteArrayOutputStream & get 'hello world' back.
        assertEquals("Hello, world.", canvas.toString("UTF-8"));
    }
}
