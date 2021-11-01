package com.db.edu.server;

import com.db.edu.server.commands.Parser;
import com.db.edu.server.storage.FileSaver;
import com.db.edu.server.storage.Saver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Parser parser = new Parser();
        Saver saver = new FileSaver();
        Notifier notifier = new Notifier();
        ConnectionHandler handler = new ConnectionHandler(notifier, parser, saver);
        final ServerSocket listener = new ServerSocket(9999);
        executorService.execute(() -> {
            while (true) {
                try {
                    Socket connection = listener.accept();
                    handler.handleConnection(connection);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        });

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();

        executorService.shutdownNow();
        handler.shutdown();
        Thread.sleep(1000);
        System.exit(0);
    }
}
