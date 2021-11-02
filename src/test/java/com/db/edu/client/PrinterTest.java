package com.db.edu.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static java.lang.System.lineSeparator;

class PrinterTest implements SysoutCaptureAndAssertion {
    private Printer printer;
    public static final String sep = lineSeparator();

    @BeforeEach
    public void SetUp() {
        resetOut();
        captureSysout();
    }

    @Test
    public void print() {
        Printer.print("some text");
        assertSysoutEquals("some text" + sep);
    }
}