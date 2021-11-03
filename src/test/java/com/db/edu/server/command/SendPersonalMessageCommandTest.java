package com.db.edu.server.command;

import com.db.edu.server.Notifier;
import com.db.edu.server.entity.PersonalMessage;
import com.db.edu.server.entity.User;
import com.db.edu.server.storage.Saver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SendPersonalMessageCommandTest {
    private String argument = "Alex test";
    private SendPersonalMessageCommand command;
    private Saver saver;
    private Notifier notifier;
    private User user;
    private PersonalMessage msg;

    @BeforeEach
    public void setUp() {
        saver = mock(Saver.class);
        notifier = mock(Notifier.class);
        user = mock(User.class);
    }

    @Test
    void shouldGenerateCorrectPrivateMessageToUser() {
        command = new SendPersonalMessageCommand(argument);

        assertEquals("Personally from : test",command.getMessage().getDecoratedString());
    }


    @Test
    void shouldThrowMessageWhenEmptyMessage() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> command = new SendPersonalMessageCommand(""));

        String expectedMessage = "Invalid number of arguments.";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowMessageWhenWrongUserInCommand() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> command = new SendPersonalMessageCommand("default test"));

        String expectedMessage = "Username of recipient can't be default.";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowExceptionWhenWrongUserInCommand() {
        command = new SendPersonalMessageCommand(argument);
        when(user.getNick()).thenReturn("default");

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> command.execute(saver, notifier, user));

        String expectedMessage = "Can't send personal message from default nick.";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
        assertEquals("Personally from : test",command.getMessage().getDecoratedString());
    }


}
