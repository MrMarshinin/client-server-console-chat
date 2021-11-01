package com.db.edu.commands;

import com.db.edu.Message;
import com.db.edu.Notifier;
import com.db.edu.User;
import com.db.edu.storage.Saver;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class SendMessageCommand implements ChatCommand {
    Message message;

    SendMessageCommand(String argument) {
        Instant instance = java.time.Instant.ofEpochMilli(System.currentTimeMillis());
        LocalDateTime dateTime = LocalDateTime.ofInstant(instance, ZoneId.of(ZoneId.systemDefault().getId()));
        this.message = new Message(argument, dateTime);
    }

    @Override
    public void execute(Saver saver, Notifier notifier, User user) {
        saver.save(message.toString());
        notifier.sendMessage(message, user);
    }
}
