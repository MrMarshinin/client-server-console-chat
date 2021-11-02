package com.db.edu.server.command;

import com.db.edu.server.Notifier;
import com.db.edu.server.entity.User;
import com.db.edu.server.storage.Reader;
import com.db.edu.server.storage.Saver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@Disabled
class GetHistoryCommandTest {

    Reader reader;
    Logger logger;
    GetHistoryCommand command;
    Notifier notifier;
    Saver saver;
    User user;

    @BeforeEach
    void setUp() {
        reader = mock(Reader.class);
        saver = mock(Saver.class);
        notifier = mock(Notifier.class);
        user = mock(User.class);
        logger = mock(Logger.class);
        command = new GetHistoryCommand(reader);
    }

    @Test
    void execute() {
        List<String> messages = new ArrayList<>();
        messages.add("a");
        messages.add("b");

        when(user.getRoom()).thenReturn("");
//        when(reader.readSpecificRoom("")).thenReturn(messages);

        command.execute(saver, notifier, user);

        verify(logger).info("Execute history command");
        verify(notifier, times(messages.size())).sendPersonalMessage("", user);
    }
}