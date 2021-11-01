package com.db.edu;

import com.db.edu.commands.Parser;
import com.db.edu.storage.FileSaver;
import com.db.edu.storage.Saver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5000);
        Parser parser = new Parser();
        Saver saver = new FileSaver();
        try {
            final ServerSocket listener = new ServerSocket(9999);
            while (true) {
                executorService.execute(() -> {
                    try {
                        Socket connection = listener.accept();
                        final DataInputStream input = new DataInputStream(new BufferedInputStream(connection.getInputStream()));
                        final DataOutputStream out = new DataOutputStream(new BufferedOutputStream(connection.getOutputStream()));

                        Notifier notifier = new Notifier(out);

                        while (true) {
                            final String commandString = input.readUTF();
                            System.out.println("Got from client: " + commandString);

                            try {
                                parser.parse(commandString).execute(saver, notifier);
                            } catch(IllegalArgumentException exception) {
                                System.out.println(exception.getMessage());
                                notifier.sendErrorMessage(exception.getMessage());
                            }

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
