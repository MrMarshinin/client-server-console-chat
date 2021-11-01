package com.db.edu.commands;

import com.db.edu.Notifier;
import com.db.edu.UserConnection;
import com.db.edu.storage.Saver;

public interface ChatCommand {
    void execute(UserConnection user, Saver saver, Notifier notifier);
}
