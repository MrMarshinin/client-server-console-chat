package com.db.edu;

import java.io.*;

public class FileHistorySaver implements HistorySaver {

    @Override
    public void push(String message) {
        try (final BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(
                        new BufferedOutputStream(
                                new FileOutputStream("text.txt")), "windows-1251"))) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
