package com.db.edu.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 9999;

    private static final Logger log = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        try {
            final Socket socket = new Socket(HOST, PORT);
            final DataInputStream input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            final DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            final MessageHandler messageHandler = new MessageHandler(reader, input, out);
            messageHandler.handle();
            messageHandler.shutdown();
            socket.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
