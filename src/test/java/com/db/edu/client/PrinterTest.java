package com.db.edu.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static java.lang.System.lineSeparator;

class PrinterTest implements SysoutCaptureAndAssertion {
    private Printer printer;
    public static final String sep = lineSeparator();

    PrinterTest() {
    }

    @BeforeEach
    public void SetUp() throws UnsupportedEncodingException {
        resetOut();
        captureSysout();
        printer = new Printer();
    }

    @Test
    public void print() throws IOException {
        printer.print("some text");
        assertSysoutContains("some text" + sep);
    }
}