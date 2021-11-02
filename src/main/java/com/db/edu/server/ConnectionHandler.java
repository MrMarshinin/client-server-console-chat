package com.db.edu.server;

import com.db.edu.server.commands.Parser;
import com.db.edu.server.entity.User;
import com.db.edu.server.storage.Saver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionHandler {
    private final Notifier notifier;
    private final Parser parser;
    private final Saver saver;
    private ExecutorService executorService = Executors.newFixedThreadPool(5000);

    private static final Logger log = LoggerFactory.getLogger(ConnectionHandler.class);

    public ConnectionHandler(Notifier notifier, Parser parser, Saver saver) {
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
            log.error(e.getMessage());
            return;
        }

        User user = new User(out);
        notifier.addUser(user);

        log.info("New connection");

        while (true) {
            try {
                final String commandString = input.readUTF();
                log.info("Got command from client: {}", commandString);
                parser.parse(commandString).execute(saver, notifier, user);
            } catch (IOException exception) {
                log.error(exception.getMessage());
                return;
            } catch(IllegalArgumentException exception) {
                log.error(exception.getMessage());
                notifier.sendErrorMessage(exception.getMessage(), user);
                return;
            }
        }
    }
}
