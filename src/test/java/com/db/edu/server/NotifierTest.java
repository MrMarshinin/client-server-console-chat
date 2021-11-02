package com.db.edu.server;

import com.db.edu.server.entity.AllMessage;
import com.db.edu.server.entity.Message;
import com.db.edu.server.entity.User;
import com.db.edu.server.entity.UserHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


public class NotifierTest {
    private UserHandler factory = new UserHandler();
    private Notifier notifier = new Notifier(factory);
    private DataOutputStream stream;
    private User user;
    private Message message;
    private ByteArrayOutputStream OUT = new ByteArrayOutputStream();

    @BeforeEach
    public void SetUp() {
        stream = new DataOutputStream(new ByteArrayOutputStream());
        user = factory.createUser(stream);
        LocalDateTime localtime = LocalDateTime.of(2012, 6, 30, 12, 00);
        message = new AllMessage("404", localtime, "@Felix", "1");
        captureSysout();
    }

    @Test
    public void testSendPersonalMessage() {
        notifier.sendPersonalMessage("123", user);
        assertThat(OUT.toString()).containsSequence("Sent personal message: 123" + System.lineSeparator());
        notifier.sendPersonalMessage("hello", user);
        assertThat(OUT.toString()).containsSequence("Sent personal message: hello" + System.lineSeparator());
        notifier.sendErrorMessage("404", user);
        assertThat(OUT.toString()).containsSequence("Sent personal message: error: 404" + System.lineSeparator());
    }

    @Test
    public void testSendMessage() {
        notifier.sendMessage(message, user);
        assertThat(OUT.toString()).contains("Sent personal message: 2012-06-30T12:00, 1, @Felix: 404");
    }

    private void captureSysout() {
        System.setOut(new PrintStream(OUT));
    }
}
