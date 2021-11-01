package com.db.edu;


import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;

public class Notifier {
    ArrayList<String> listOfUser = new ArrayList() ;
    List<UserConnection> outs = new LinkedList<>();

    public Notifier() {
    }

    public void addUser(UserConnection connection) {
        outs.add(connection);
    }

    public void sendErrorMessage(UserConnection user, String error) {
        try {
            user.getConnection().writeUTF(error);
            user.getConnection().flush();
            System.out.println("Sent error message: " + error);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't send error to clients");
        }
    }

    public void sendMessage(UserConnection user, Message message) {
        outs.stream().filter(u -> u.getRoom().equals(user.getRoom())).forEach(u -> {
            try {
                u.getConnection().writeUTF(message.toString());
                u.getConnection().flush();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Couldn't send message to clients");
            }
        });
    }

}
