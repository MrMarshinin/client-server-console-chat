package com.db.edu.server.command;

import com.db.edu.server.Notifier;
import com.db.edu.server.entity.PersonalMessage;
import com.db.edu.server.entity.User;
import com.db.edu.server.storage.Saver;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;

public class SendPersonalMessageCommand implements ChatCommand {
    private String usernameTo;
    private PersonalMessage message;

    public PersonalMessage getMessage(){
        return message;
    }

    public SendPersonalMessageCommand(String argument) {
        Instant instance = java.time.Instant.ofEpochMilli(System.currentTimeMillis());
        LocalDateTime dateTime = LocalDateTime.ofInstant(instance, ZoneId.of(ZoneId.systemDefault().getId()));

        ArrayList<String> arguments = new ArrayList<> (Arrays.asList(argument.split(" ")));
        if (arguments.size() < 2) {
            throw new IllegalArgumentException("Invalid number of arguments.");
        }
        usernameTo = arguments.get(0);
        arguments.remove(0);
        if (usernameTo.equals("default")) {
            throw new IllegalArgumentException("Username of recipient can't be default.");
        }
        String body = String.join(" ", arguments);

        this.message = new PersonalMessage(body, dateTime,"", "", usernameTo);
    }

    @Override
    public void execute(Saver saver, Notifier notifier, User user) {
        if (user.getNick().equals("default")) {
            throw new IllegalArgumentException("Can't send personal message from default nick.");
        }
        message.setUsernameFrom(user.getNick());
        message.setUsernameTo(usernameTo);
        message.setRoom(user.getRoom());

        notifier.sendPersonalMessage(message, user);
    }
}
