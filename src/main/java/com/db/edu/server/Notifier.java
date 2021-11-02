package com.db.edu.server;


import com.db.edu.server.entity.Message;
import com.db.edu.server.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Notifier {
    private List<User> listOfUsers = new LinkedList<>() ;

    private static final Logger log = LoggerFactory.getLogger(Notifier.class);

    public void addUser(User user) {
        listOfUsers.add(user);
    }

    public void sendErrorMessage(String error, User user) {
        sendPersonalMessage("error: " + error, user);
    }

    public void sendMessage(Message message, User user) {
        listOfUsers.stream().filter(u -> u.getRoom().equals(user.getRoom()))
                .forEach(u -> sendPersonalMessage(message.toString(user), u));
    }

    public void sendPersonalMessage(String message, User user) {
        DataOutputStream out = user.getOutput();
        try {
            out.writeUTF(message);
            out.flush();
            log.info("Sent personal message: {}", message);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Couldn't send personal message: {}", message);
        }
    }
}
