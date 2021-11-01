package com.db.edu.commands;

import com.db.edu.Notifier;
import com.db.edu.storage.Reader;
import com.db.edu.storage.Saver;

public class ChangeRoomCommand implements ChatCommand {
    @Override
    public void execute(Saver saver, Reader reader, Notifier notifier) {
        notifier.sendErrorMessage("Command change room is not implemented yet");
    }
}
