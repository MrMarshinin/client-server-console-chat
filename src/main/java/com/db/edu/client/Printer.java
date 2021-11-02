package com.db.edu.client;

import java.io.*;

public class Printer {
    private final BufferedWriter out;

    public Printer() throws UnsupportedEncodingException {
       out = new BufferedWriter(new OutputStreamWriter(System.out, System.getProperty("sun.jnu.encoding")));
    }

    public void print(String string) throws IOException {
        out.write(string + System.lineSeparator());
        out.flush();
    }
}
