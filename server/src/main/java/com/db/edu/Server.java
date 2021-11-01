package com.db.edu;

import com.db.edu.commands.Parser;
import com.db.edu.storage.CustomFileReader;
import com.db.edu.storage.FileSaver;
import com.db.edu.storage.Reader;
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
        Notifier notifier = new Notifier();
        Reader reader = new CustomFileReader();
        try {
            final ServerSocket listener = new ServerSocket(9999);
            while (true) {
                Socket connection = listener.accept();

                DataInputStream input;
                DataOutputStream out;

                try {
                    input = new DataInputStream(new BufferedInputStream(connection.getInputStream()));
                    out = new DataOutputStream(new BufferedOutputStream(connection.getOutputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }

                notifier.addOutputStream(out);

                executorService.execute(() -> {
                    System.out.println("New connection");
                    while (true) {
                        try {
                            final String commandString = input.readUTF();
                            System.out.println("Got from client: " + commandString);
                            parser.parse(commandString).execute(saver, reader, notifier);
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        } catch(IllegalArgumentException exception) {
                            System.out.println(exception.getMessage());
                            notifier.sendErrorMessage(exception.getMessage());
                        }

                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
