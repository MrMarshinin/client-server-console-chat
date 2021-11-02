package com.db.edu.server;

import com.db.edu.server.command.Parser;
import com.db.edu.server.entity.UserFactory;
import com.db.edu.server.storage.FileSaver;
import com.db.edu.server.storage.Saver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
    private static final Logger log = LoggerFactory.getLogger(Server.class);
    private static final int port = 9999;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Parser parser = new Parser();
        Saver saver = new FileSaver();
        UserFactory factory = new UserFactory();
        Notifier notifier = new Notifier(factory);
        ConnectionHandler handler = new ConnectionHandler(notifier, parser, saver, factory);
        try (ServerSocket socket = new ServerSocket(port)) {
            executorService.execute(() -> {
                while (true) {
                    try {
                        Socket connection = socket.accept();
                        handler.handleConnection(connection);
                    } catch (IOException e) {
                        log.error(e.getMessage());
                        return;
                    }
                }
            });

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String exitWhenEnterAny = reader.readLine();

            executorService.shutdownNow();
            handler.shutdown();
            Thread.sleep(1000);
            System.exit(exitWhenEnterAny.equals("exit") ? 0 : -1);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
