package com.db.edu;


import javax.xml.crypto.Data;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Notifier {
    ArrayList <String> listOfUser = new ArrayList() ;
    ArrayList<DataOutputStream> out;

    public Notifier() {
        this.out = new ArrayList<>();
    }
    public void addOutputStream(DataOutputStream dataOutputStream) {
        out.add(dataOutputStream);
    }

    private void addingUser(String newUser){
    }

    public void sendErrorMessage(String error) {
        out.forEach((DataOutputStream out) -> {
            try {
                out.writeUTF(error);
                out.flush();
                System.out.println("Sent error message: " + error);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Couldn't send error to clients");
            }
        } );

    }

    public void sendMessage(Message message) {
        out.forEach((DataOutputStream out) -> {
            try {
                out.writeUTF(message.toString());
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Couldn't send message to clients");
            }
        });

    }
    public void sendMessage(String message) {
        out.forEach((DataOutputStream out) -> {
            try {
                out.writeUTF(message);
                out.flush();
                System.out.println("Sent message: " + message);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Couldn't send message to clients");
            }
        } );

    }

}
