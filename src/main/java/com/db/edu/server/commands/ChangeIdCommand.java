package com.db.edu.server.commands;

import com.db.edu.server.Notifier;
import com.db.edu.server.entity.User;
import com.db.edu.server.storage.Saver;

public class ChangeIdCommand implements ChatCommand {
    String id;

    ChangeIdCommand(String argument) {
        this.id = argument;
    }

    @Override
    public void execute(Saver saver, Notifier notifier, User user) {
        notifier.sendPersonalMessage("You changed your nick to: " + id, user);
        user.setNick(id);
    }
}
