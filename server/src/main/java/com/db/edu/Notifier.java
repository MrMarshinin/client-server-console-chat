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

    private void sendErrorMessage(){
    }

    public void sendMessage(Message message) {
        try {
            out.writeUTF(message.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't send message to clients");
        }
    }

}
