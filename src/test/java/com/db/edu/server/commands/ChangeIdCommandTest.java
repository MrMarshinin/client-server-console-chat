package com.db.edu.server.commands;

import com.db.edu.server.Notifier;
import com.db.edu.server.entity.User;
import com.db.edu.server.storage.Saver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChangeIdCommandTest {

    String id = "argument";
    ChangeIdCommand command;
    Saver saver;
    Notifier notifier;
    User user;

    @BeforeEach
    public void setUp() {
        command = new ChangeIdCommand("argument");
        saver = mock(Saver.class);
        notifier = mock(Notifier.class);
        user = mock(User.class);
    }

    @Test
    void execute() {
        command.execute(saver, notifier, user);

        verify(notifier).sendPersonalMessage("You changed your nick to: " + id, user);
        verify(user).setNick(id);
    }
}