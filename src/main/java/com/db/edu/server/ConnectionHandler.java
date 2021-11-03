package com.db.edu.server;

import com.db.edu.server.command.Parser;
import com.db.edu.server.entity.User;
import com.db.edu.server.entity.UserHandler;
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
    private final UserHandler factory;
    private final int MAX_NUMBER_OF_USER_CONNECTIONS = 1001;
    private final ExecutorService executorService = Executors.newFixedThreadPool(MAX_NUMBER_OF_USER_CONNECTIONS);

    private static final Logger log = LoggerFactory.getLogger(ConnectionHandler.class);

    public ConnectionHandler(Notifier notifier, Parser parser, Saver saver, UserHandler factory) {
        this.notifier = notifier;
        this.parser = parser;
        this.saver = saver;
        this.factory = factory;
    }

    public void handleConnection(Socket connection) {
        executorService.execute(() -> this.handle(connection));
    }

    public void shutdown() {
        executorService.shutdownNow();

        synchronized (factory.getUsers()) {
            factory.getUsers().forEach(u -> {
                try {
                    u.getOutput().close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            });
        }
    }

    private void handle(Socket connection) {
        try (
                DataInputStream input = new DataInputStream(new BufferedInputStream(connection.getInputStream()));
                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(connection.getOutputStream()))) {
            User user = factory.createUser(out);
            log.info("New connection");
            readLoop(input, user);
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (IOException ioException) {
                log.error("Tried to close connections with user, but it is already closed.");
            }
        }
    }

    private void readLoop(DataInputStream input, User user) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                final String commandString = input.readUTF();
                log.info("Got command from client: {}", commandString);
                parser.parse(commandString).execute(saver, notifier, user);
            } catch (IOException exception) {
                log.error(exception.getMessage());
                return;
            } catch (IllegalArgumentException exception) {
                log.error(exception.getMessage());
                notifier.sendErrorMessage(exception.getMessage(), user);
            }
        }
    }
}
