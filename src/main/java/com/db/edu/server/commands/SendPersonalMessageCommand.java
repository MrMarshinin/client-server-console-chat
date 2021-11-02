package com.db.edu.server.commands;

import com.db.edu.server.Notifier;
import com.db.edu.server.entity.Message;
import com.db.edu.server.entity.PersonalMessage;
import com.db.edu.server.entity.User;
import com.db.edu.server.storage.Saver;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class SendPersonalMessageCommand implements ChatCommand {
    String usernameTo;
    PersonalMessage message;

    public SendPersonalMessageCommand(String argument) {
        Instant instance = java.time.Instant.ofEpochMilli(System.currentTimeMillis());
        LocalDateTime dateTime = LocalDateTime.ofInstant(instance, ZoneId.of(ZoneId.systemDefault().getId()));

        String[] arguments = argument.split(" ");
        if (arguments.length != 2) {
            throw new IllegalArgumentException("Invalid number of arguments.");
        }
        usernameTo = arguments[0];
        String body = arguments[1];

        this.message = new PersonalMessage(body, dateTime,"", "", usernameTo);
    }

    @Override
    public void execute(Saver saver, Notifier notifier, User user) {
        message.setUsernameFrom(user.getNick());
        message.setUsernameTo(usernameTo);
        message.setRoom(user.getRoom());

        //saver.save(message.toString());
        notifier.sendPersonalMessage(message, user);
    }
}
