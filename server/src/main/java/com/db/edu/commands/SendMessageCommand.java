package com.db.edu.commands;

import com.db.edu.Message;
import com.db.edu.Notifier;
import com.db.edu.storage.Reader;
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
    public void execute(Saver saver, Reader reader, Notifier notifier) {
        saver.save(message.toString());
        notifier.sendMessage(message);
    }
}
