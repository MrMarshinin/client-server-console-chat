package com.db.edu;

import com.db.edu.commands.Parser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try(final ServerSocket listener = new ServerSocket(9999);
            final Socket connection = listener.accept();
            final DataInputStream input = new DataInputStream(
                    new BufferedInputStream(connection.getInputStream()));
            final DataOutputStream out = new DataOutputStream(
                    new BufferedOutputStream(connection.getOutputStream()));
        ) {
            Parser parser = new Parser();

            Saver saver = new Saver();
            Notifier notifier = new Notifier();

            while (input.available() > 0) {
                final String commandString = input.readUTF();

                parser.parse(commandString).execute(saver, notifier);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
