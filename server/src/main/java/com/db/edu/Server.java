package com.db.edu;

import com.db.edu.commands.Parser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Server {
    public static void main(String[] args) {
        try (final ServerSocket listener = new ServerSocket(9999);
            final Socket connection = listener.accept();
            final DataInputStream input = new DataInputStream(
                    new BufferedInputStream(connection.getInputStream()));
            final DataOutputStream out = new DataOutputStream(
                    new BufferedOutputStream(connection.getOutputStream()));
        ) {
            Parser parser = new Parser();

            Saver saver = new FileSaver();
            Notifier notifier = new Notifier(out);

            while (true) {
                final String commandString = input.readUTF();
                System.out.println(commandString);

                try {
                    parser.parse(commandString).execute(saver, notifier);
                } catch(IllegalArgumentException exception) {
                    notifier.sendErrorMessage(exception.getMessage());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
