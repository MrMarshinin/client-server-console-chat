package com.db.edu.server.commands;

import com.db.edu.server.Notifier;
import com.db.edu.server.entity.User;
import com.db.edu.server.storage.Saver;

public class ChangeIdCommand implements ChatCommand {
    private final String id;

    ChangeIdCommand(String argument) {
        if (argument.isEmpty()) {
            throw new IllegalArgumentException("User's nick can't be empty.");
        }
        this.id = argument;
    }

    @Override
    public void execute(Saver saver, Notifier notifier, User user) {
        notifier.sendPersonalMessage("You changed your nick to: " + id, user);
        user.setNick(id);
    }
}
