package com.db.edu.client;

import org.fest.assertions.Assertions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public interface SysoutCaptureAndAssertion {
    ByteArrayOutputStream OUT = new ByteArrayOutputStream();

    default void captureSysout() {
        System.setOut(new PrintStream(OUT));
    }

    default void assertSysoutEquals(String expected) {
        Assertions.assertThat(OUT.toString()).isEqualTo(expected);
    }

    default void assertSysoutContains(String expected) {
        Assertions.assertThat(OUT.toString()).contains(expected);
    }

    default void resetOut() {
        OUT.reset();
    }
}
