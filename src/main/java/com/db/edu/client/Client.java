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
        try (
                Socket socket = new Socket(HOST, PORT);
                DataInputStream input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, System.getProperty("sun.jnu.encoding")));
                Printer printer = new Printer()) {

            final MessageHandler messageHandler = new MessageHandler(reader, input, out, printer);
            messageHandler.handle();
        } catch (IOException e) {
            log.error(e.getMessage());
            log.info("Cannot connect to server! Ensure server is running.");
        }
    }
}
