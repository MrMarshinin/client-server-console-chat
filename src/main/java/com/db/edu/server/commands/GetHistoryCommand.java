package com.db.edu.server.commands;

import com.db.edu.server.Notifier;
import com.db.edu.server.entity.User;
import com.db.edu.server.storage.Reader;
import com.db.edu.server.storage.Saver;

import java.util.List;

public class GetHistoryCommand implements ChatCommand {
    private Reader reader;

    GetHistoryCommand(Reader reader) {
        this.reader = reader;
    }

    @Override
    public void execute(Saver saver, Notifier notifier, User user) {
        System.out.println("Execute history command");
        List<String> messages = reader.readSpecificRoom(user.getRoom());
        messages.forEach(message -> notifier.sendPersonalMessage(message, user));
    }
}
