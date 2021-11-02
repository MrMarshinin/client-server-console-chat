package com.db.edu.server.command;

import com.db.edu.server.entity.Message;
import com.db.edu.server.Notifier;
import com.db.edu.server.entity.User;
import com.db.edu.server.storage.Saver;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class SendMessageCommand implements ChatCommand {
    Message message;

    SendMessageCommand(String argument) {
        Instant instance = java.time.Instant.ofEpochMilli(System.currentTimeMillis());
        LocalDateTime dateTime = LocalDateTime.ofInstant(instance, ZoneId.of(ZoneId.systemDefault().getId()));

        if (argument.isEmpty()) {
            throw new IllegalArgumentException("Message can't be empty");
        }
        if (argument.length() > 149) {
            throw new IllegalArgumentException("Message can't be longer than 150 symbols.");
        }
        this.message = new Message(argument, dateTime, "default", "");
    }

    @Override
    public void execute(Saver saver, Notifier notifier, User user) {
        message.setUsername(user.getNick());
        message.setRoom(user.getRoom());

        saver.save(message.toString());
        notifier.sendMessage(message, user);
    }
}
