package com.db.edu;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MessageHandler {
    private final BufferedReader reader;
    private final DataInputStream input;
    private final DataOutputStream out;

    public MessageHandler(BufferedReader bufferedReader, DataInputStream in, DataOutputStream out) {
        this.input = in;
        this.out = out;
        this.reader = bufferedReader;
        Executor executor = Executors.newFixedThreadPool(1);

        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Printer.print(input.readUTF());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void handle() throws IOException, InterruptedException {
        while (true) {
            String command = reader.readLine();
            Printer.print(command);
            out.writeUTF(command);
            out.flush();
        }
    }
}
