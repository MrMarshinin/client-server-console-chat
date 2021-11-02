package com.db.edu.server;


import com.db.edu.server.entity.Message;
import com.db.edu.server.entity.User;
import com.db.edu.server.entity.UserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;

public class Notifier {
    private final UserFactory factory;

    private static final Logger log = LoggerFactory.getLogger(Notifier.class);

    public Notifier(UserFactory factory) {
        this.factory = factory;
    }

    public void sendErrorMessage(String error, User user) {
        sendPersonalMessage("error: " + error, user);
    }

    public void sendMessage(Message message, User user) {
        factory.getUsers().stream().filter(u -> u.getRoom().equals(user.getRoom()))
                .forEach(u -> sendPersonalMessage(message.getDecoratedString(), u));
    }

    public void sendPersonalMessage(String message, User user) {
        DataOutputStream out = user.getOutput();
        try {
            out.writeUTF(message);
            out.flush();
            log.info("Sent personal message: {}", message);
        } catch (IOException e) {
            log.error("Couldn't send personal message: {}", message);
        }
    }
}
