package com.db.edu.commands;

import com.db.edu.Notifier;
import com.db.edu.Saver;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class SendMessageCommand implements ChatCommand {
    String message;
    LocalDateTime dateTime;

    SendMessageCommand(String argument) {
        this.message = argument;
        Instant instance = java.time.Instant.ofEpochMilli(System.currentTimeMillis());
        dateTime = LocalDateTime.ofInstant(instance, ZoneId.of(ZoneId.systemDefault().getId()));
    }

    @Override
    public void execute(Saver saver, Notifier notifier) {
        saver.save(dateTime.toString() + ", " + message);
        notifier.notify();
    }
}
