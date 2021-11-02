package com.db.edu.client;

import com.db.edu.server.ConnectionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final String host = "127.0.0.1";
    private static final int port = 9999;

    private static final Logger log = LoggerFactory.getLogger(ConnectionHandler.class);

    public static void main(String[] args) {
        try {
            final Socket socket = new Socket(host, port);
            final DataInputStream input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            final DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            final MessageHandler messageHandler = new MessageHandler(reader, input, out);
            messageHandler.handle();
            messageHandler.shutdown();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
