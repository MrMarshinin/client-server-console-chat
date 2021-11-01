package com.db.edu.commands;

import com.db.edu.Notifier;
import com.db.edu.User;
import com.db.edu.storage.Reader;
import com.db.edu.storage.Saver;

import java.util.List;

public class GetHistoryCommand implements ChatCommand {
    private Reader reader;

    GetHistoryCommand(Reader reader) {
        this.reader = reader;
    }

    @Override
    public void execute(Saver saver, Notifier notifier, User user) {
        System.out.println("Execute history command");
        List<String> messages = reader.read();
        messages.forEach(message -> notifier.sendPersonalMessage(message, user));
    }
}
