package com.db.edu;

import com.db.edu.commands.Parser;
import com.db.edu.storage.Saver;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionHandler {
    private final Notifier notifier;
    private final Parser parser;
    private final Saver saver;
    ExecutorService executorService = Executors.newFixedThreadPool(5000);

    ConnectionHandler(Notifier notifier, Parser parser, Saver saver) {
        this.notifier = notifier;
        this.parser = parser;
        this.saver = saver;
    }

    public void handleConnection(Socket connection) {
        executorService.execute(() -> this.handle(connection));
    }

    public void shutdown() {
        executorService.shutdownNow();
    }

    private void handle(Socket connection) {
        DataInputStream input;
        DataOutputStream out;

        try {
            input = new DataInputStream(new BufferedInputStream(connection.getInputStream()));
            out = new DataOutputStream(new BufferedOutputStream(connection.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        User user = new User(out);
        notifier.addUser(user);

        System.out.println("New connection");

        while (true) {
            try {
                final String commandString = input.readUTF();
                System.out.println("Got from client: " + commandString);
                parser.parse(commandString).execute(saver, notifier, user);
            } catch (IOException exception) {
                exception.printStackTrace();
                return;
            } catch(IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
                notifier.sendErrorMessage(exception.getMessage(), user);
                return;
            }
        }
    }
}
