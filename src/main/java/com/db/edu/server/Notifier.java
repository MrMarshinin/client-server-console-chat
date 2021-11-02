package com.db.edu.server;


import com.db.edu.server.entity.Message;
import com.db.edu.server.entity.PersonalMessage;
import com.db.edu.server.entity.User;
import com.db.edu.server.entity.UserHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Optional;

public class Notifier {
    private final UserHandler factory;

    private static final Logger log = LoggerFactory.getLogger(Notifier.class);

    public Notifier(UserHandler factory) {
        this.factory = factory;
    }

    public void sendErrorMessage(String error, User user) {
        sendPersonalMessage("error: " + error, user);
    }

    public void sendPersonalMessage(PersonalMessage message, User userFrom) {
        Optional<User> userTo = factory.getUsers().stream().filter((User user) -> (user.getNick().equals(message.getUsernameTo()))).findFirst();
        if (!userTo.isPresent()) {
            throw new IllegalArgumentException("Personal message must contain userTo info.");
        }
        sendPersonalMessage(message.getDecoratedString(), userFrom);
        sendPersonalMessage(message.getDecoratedString(), userTo.get());
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
