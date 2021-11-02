package com.db.edu.server.commands;

import com.db.edu.server.Notifier;
import com.db.edu.server.entity.Message;
import com.db.edu.server.entity.User;
import com.db.edu.server.storage.Reader;
import com.db.edu.server.storage.Saver;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class GetHistoryCommandTest {

    Reader reader;
    GetHistoryCommand command;
    Notifier notifier;
    Saver saver;
    User user;
    String message;

    @BeforeEach
    void setUp() {
        reader = mock(Reader.class);
        saver = mock(Saver.class);
        notifier = mock(Notifier.class);
        user = mock(User.class);
        command = new GetHistoryCommand(reader);
//        message = new ArrayList<String>(Arrays.asList("a", "b"));
        message = "c";
    }

    @Test
    void execute() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("2021-11-01T22:57:29.523, a, a"));
//        messages.add(new Message("2021-11-01T22:57:29.523, a, b"));

        when(user.getRoom()).thenReturn("");
        when(reader.readSpecificRoom("")).thenReturn(messages);

        command.execute(saver, notifier, user);

//        verify(notifier, times(messages.size())).sendPersonalMessage(Matchers.in(messages).toString(), user);
//        verify(notifier).sendPersonalMessage("2021-11-01T22:57:29.523, a, b", user);
        verify(notifier).sendPersonalMessage("2021-11-01T22:57:29.523, a, a", user);
    }
}