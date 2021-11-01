package com.db.edu;


import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Notifier {
    ArrayList <String> listOfUser = new ArrayList() ;
    DataOutputStream out;

    public Notifier(DataOutputStream out) {
        this.out = out;
    }

    private void addingUser(String newUser){
    }

    public void sendMessage(Message message) {
        try {
            out.writeUTF(message.toString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't send message to clients");
        }
    }
    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't send message to clients");
        }
    }

}
