package com.db.edu.commands;

import com.db.edu.Notifier;
import com.db.edu.storage.Reader;
import com.db.edu.storage.Saver;

public interface ChatCommand {
    void execute(Saver saver, Reader reader, Notifier notifier);
}
