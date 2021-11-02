package com.db.edu.server.commands;

import com.db.edu.server.Notifier;
import com.db.edu.server.entity.User;
import com.db.edu.server.storage.Saver;

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
