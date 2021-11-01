package com.db.edu.client;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final String host = "127.0.0.1";
    private static final int port = 9999;

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
            e.printStackTrace();
        }
    }
}
