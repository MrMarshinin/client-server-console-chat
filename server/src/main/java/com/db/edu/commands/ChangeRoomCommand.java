package com.db.edu.commands;

import com.db.edu.Notifier;
import com.db.edu.User;
import com.db.edu.storage.Reader;
import com.db.edu.storage.Saver;

public class ChangeRoomCommand implements ChatCommand {
    String room;

    ChangeRoomCommand(String argument) {
        this.room = argument;
    }

    @Override
    public void execute(Saver saver, Notifier notifier, User user) {
        notifier.sendPersonalMessage("You entered room: " + room, user);
        user.setRoom(room);
    }
}
