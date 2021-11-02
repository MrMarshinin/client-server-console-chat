package com.db.edu.server.commands;

import com.db.edu.server.Notifier;
import com.db.edu.server.entity.User;
import com.db.edu.server.storage.Saver;

public class ChangeRoomCommand implements ChatCommand {
    private final String room;

    ChangeRoomCommand(String argument) {
        if (argument.contains(" ")) {
            throw new IllegalArgumentException("Chat room can't contain spaces");
        }
        this.room = argument;
    }

    @Override
    public void execute(Saver saver, Notifier notifier, User user) {
        notifier.sendPersonalMessage("You entered room: " + room, user);
        user.setRoom(room);
    }
}
