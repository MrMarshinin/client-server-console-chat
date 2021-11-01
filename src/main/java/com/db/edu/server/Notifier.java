package com.db.edu.server;


import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Notifier {
    List<User> listOfUsers = new LinkedList<>() ;

    public Notifier() {
    }

    public void addUser(User user) {
        listOfUsers.add(user);
    }

    public void sendErrorMessage(String error, User user) {
        sendPersonalMessage("error: " + error, user);
    }

    public void sendMessage(Message message, User user) {
        listOfUsers.stream().filter(u -> u.getRoom().equals(user.getRoom()))
                .forEach(u -> sendPersonalMessage(message.toString(), u));
    }

    public void sendPersonalMessage(String message, User user) {
        DataOutputStream out = user.getOutput();
        try {
            out.writeUTF(message);
            out.flush();
            System.out.println("Sent message: " + message);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't send error to clients");
        }
    }
}
