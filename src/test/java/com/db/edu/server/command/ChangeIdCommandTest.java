package com.db.edu.server.command;

import com.db.edu.server.Notifier;
import com.db.edu.server.WrongNickException;
import com.db.edu.server.entity.User;
import com.db.edu.server.storage.Saver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ChangeIdCommandTest {
    private String id = "argument";
    private ChangeIdCommand command;
    private Saver saver;
    private Notifier notifier;
    private User user;

    @BeforeEach
    public void setUp() {
        saver = mock(Saver.class);
        notifier = mock(Notifier.class);
        user = mock(User.class);
    }

    @Test
    void shouldExecute() throws WrongNickException {
        command = new ChangeIdCommand(id);
        command.execute(saver, notifier, user);

        verify(notifier).sendPersonalMessage("You changed your nick to: " + id, user);
        verify(user).changeNick(id);
    }


    @Test
    void shouldThrowMessageWhenWrongCommand() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> command = new ChangeIdCommand(""));

        String expectedMessage = "User's nick can't be empty.";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowMessageWhenWrongUserName() throws WrongNickException {
        command = new ChangeIdCommand(id);
        doThrow(new WrongNickException("Nick cannot be empty")).when(user).changeNick(any());
        command.execute(saver, notifier, user);
        verify(notifier).sendPersonalMessage("Nick cannot be empty",user);
    }

}
