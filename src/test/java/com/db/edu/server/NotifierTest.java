package com.db.edu.server;

import com.db.edu.server.Notifier;
import com.db.edu.server.entity.Message;
import com.db.edu.server.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class NotifierTest {
    private List<User> listOfUsers = new LinkedList<>();
    Notifier notifier = new Notifier();
    private DataOutputStream stream;
    User user;
    Message message;
    ByteArrayOutputStream OUT = new ByteArrayOutputStream();

    @BeforeEach
    public void SetUp() {
        stream = new DataOutputStream(new ByteArrayOutputStream());
        user = new User(stream);
        LocalDateTime localtime = LocalDateTime.of(2012, 6, 30, 12, 00);
        message = new Message("404", localtime, "@Felix", "1");
        captureSysout();
    }

    @Test
    public void testSendPersonalMessage() {

        notifier.addUser(user);
        notifier.sendPersonalMessage("123", user);
        assertThat(OUT.toString()).containsSequence("Sent personal message: 123\r\n");
        notifier.sendPersonalMessage("hello", user);
        assertThat(OUT.toString()).containsSequence("Sent personal message: hello\r\n");
        notifier.sendErrorMessage("404", user);
        assertThat(OUT.toString()).containsSequence("Sent personal message: error: 404\r\n");
    }

    @Test
    public void testSendMessage() {
        notifier.addUser(user);
        notifier.sendMessage(message, user);
        assertThat(OUT.toString()).contains("Sent personal message: 2012-06-30T12:00, 1, @Felix: 404");
    }

    private void captureSysout() {
        System.setOut(new PrintStream(OUT));
    }
}
