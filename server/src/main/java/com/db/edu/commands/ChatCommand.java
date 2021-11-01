package com.db.edu.commands;

import com.db.edu.Notifier;
import com.db.edu.Saver;

public interface ChatCommand {
    void execute(Saver saver, Notifier notifier);
}
