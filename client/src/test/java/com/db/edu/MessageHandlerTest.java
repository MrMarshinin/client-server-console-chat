package com.db.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import static org.mockito.Mockito.mock;

public class MessageHandlerTest {
    private BufferedReader reader;
    private DataInputStream input;
    private DataOutputStream out;
    private MessageHandler handler;

    @BeforeEach
    public void setUpLoggerController() {
        reader = mock(BufferedReader.class);
        input = mock(DataInputStream.class);
        out = mock(DataOutputStream.class);
        handler = new MessageHandler(reader, input, out);
    }

    @Test
    public void shouldSendMessage() {

    }
}
