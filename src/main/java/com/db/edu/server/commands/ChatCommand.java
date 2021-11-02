package com.db.edu.server.commands;

import com.db.edu.server.Notifier;
import com.db.edu.server.entity.User;
import com.db.edu.server.storage.Saver;

public interface ChatCommand {
    void execute(Saver saver, Notifier notifier, User user);
}
