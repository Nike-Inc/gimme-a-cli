package com.nike.gimme.a.cli;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class OutputCapture {

    private static final PrintStream systemOut = System.out;
    private static ByteArrayOutputStream outputStream;

    public static void begin() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    public static String getStdOut() {
        return outputStream.toString();
    }

    public static void restoreSystemOut() {
        System.setOut(systemOut);
    }
}
