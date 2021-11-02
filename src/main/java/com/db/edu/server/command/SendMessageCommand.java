package com.db.edu.server.command;

import com.db.edu.server.entity.AllMessage;
import com.db.edu.server.entity.Message;
import com.db.edu.server.Notifier;
import com.db.edu.server.entity.User;
import com.db.edu.server.storage.Saver;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class SendMessageCommand implements ChatCommand {
    AllMessage message;

    SendMessageCommand(String argument) {
        Instant instance = java.time.Instant.ofEpochMilli(System.currentTimeMillis());
        LocalDateTime dateTime = LocalDateTime.ofInstant(instance, ZoneId.of(ZoneId.systemDefault().getId()));

        this.message = new AllMessage(argument, dateTime, "default", "");
    }

    @Override
    public void execute(Saver saver, Notifier notifier, User user) {
        message.setUsernameFrom(user.getNick());
        message.setRoom(user.getRoom());

        saver.save(message.toString());
        notifier.sendMessage(message, user);
    }
}
