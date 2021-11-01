package com.db.edu;

import java.io.*;
import java.net.Socket;

public class Client {


    public static void main(String[] args) throws Exception {
        try {
            final Socket socket = new Socket("127.0.0.1", 9999);
            final DataInputStream input = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            final DataOutputStream out = new DataOutputStream(
                    new BufferedOutputStream(socket.getOutputStream()));

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            final MessageHandler messageHandler = new MessageHandler(reader, input, out);
            messageHandler.handle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}