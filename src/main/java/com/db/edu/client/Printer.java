package com.db.edu.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class Printer implements AutoCloseable {
    private static final Logger log = LoggerFactory.getLogger(Printer.class);
    private final BufferedWriter out;

    public Printer() throws UnsupportedEncodingException {
        String property = "sun.jnu.encoding";
        out = new BufferedWriter(new OutputStreamWriter(System.out, System.getProperty(property)));
    }

    public void print(String string) throws IOException {
        out.write(string + System.lineSeparator());
        out.flush();
    }

    @Override
    public void close() {
        try {
            out.close();
        } catch (IOException e) {
            log.info("Standard output was closed. That means system was shut down");
        }
    }
}
