package com.db.edu.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageHandler {
    private final BufferedReader reader;
    private final DataInputStream input;
    private final DataOutputStream out;
    private final ExecutorService executor = Executors.newFixedThreadPool(1);

    public MessageHandler(BufferedReader bufferedReader, DataInputStream in, DataOutputStream out) {
        this.input = in;
        this.out = out;
        this.reader = bufferedReader;

        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                try {
                    Printer.print(input.readUTF());
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
    }

    public void handle() throws IOException {
        while (true) {
            String command = reader.readLine();
            if (command.equals("exit")) return;
            out.writeUTF(command);
            out.flush();
        }
    }
}
