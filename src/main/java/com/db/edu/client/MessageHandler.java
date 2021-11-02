package com.db.edu.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private volatile boolean isFinished = false;
    private final Printer printer;

    private final Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    public MessageHandler(BufferedReader bufferedReader, DataInputStream in, DataOutputStream out, Printer printer) {
        this.input = in;
        this.out = out;
        this.reader = bufferedReader;
        this.printer = printer;

        executor.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage());
                    Thread.currentThread().interrupt();
                    return;
                }
                try {
                    printer.print(input.readUTF());
                } catch (IOException e) {
                    logger.error("Server closed connection! Type anything to exit.");
                    this.shutdown();
                    isFinished = true;
                    return;
                }
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
    }

    public void handle() throws IOException {
        while (!isFinished) {
            String command = reader.readLine();
            if (command.equals("exit") || isFinished) {
                return;
            }
            out.writeUTF(command);
            out.flush();
        }
    }
}
