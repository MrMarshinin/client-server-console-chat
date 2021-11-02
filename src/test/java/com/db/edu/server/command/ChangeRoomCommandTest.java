package com.db.edu.server.command;

import com.db.edu.server.Notifier;
import com.db.edu.server.entity.User;
import com.db.edu.server.storage.Saver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ChangeRoomCommandTest {

    String room = "argument";
    ChangeRoomCommand command;
    Saver saver;
    Notifier notifier;
    User user;

    @BeforeEach
    void setUp() {
        command = new ChangeRoomCommand("argument");
        saver = mock(Saver.class);
        notifier = mock(Notifier.class);
        user = mock(User.class);
    }

    @Test
    void execute() {
        command.execute(saver, notifier, user);

        verify(notifier).sendPersonalMessage("You entered room: " + room, user);
        verify(user).setRoom(room);
    }
}