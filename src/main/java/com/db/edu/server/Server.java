package com.db.edu.server;

import com.db.edu.server.command.Parser;
import com.db.edu.server.entity.UserHandler;
import com.db.edu.server.storage.FileSaver;
import com.db.edu.server.storage.Saver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
    private static final Logger log = LoggerFactory.getLogger(Server.class);
    private static final int PORT = 9999;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Parser parser = new Parser();
        Saver saver = new FileSaver();
        UserHandler factory = new UserHandler();
        Notifier notifier = new Notifier(factory);
        ConnectionHandler handler = new ConnectionHandler(notifier, parser, saver, factory);
        try (ServerSocket socket = new ServerSocket(PORT)) {
            executorService.execute(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Socket connection = socket.accept();
                        handler.handleConnection(connection);
                    } catch (IOException e) {
                        log.error(e.getMessage());
                        return;
                    }
                }
            });
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                if (bufferedReader.readLine().equals("Exit")) {
                    break;
                }
            }
            handler.shutdown();
            executorService.shutdownNow();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
