package ca.jbrains.pos.test;

import java.util.Arrays;
import java.util.List;

public class TextUtilities {

    //REFACTOR move this into a reusable library OR
    //find a library which implements this more reliably.
    public static List<String> lines(String text) {
       return Arrays.asList(text.split(System.lineSeparator()));
    }
}
