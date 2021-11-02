package com.db.edu.client;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.*;

@Disabled
public class MessageHandlerTest implements SysoutCaptureAndAssertion {
    private BufferedReader reader;
    private DataInputStream input;
    private DataOutputStream out;
    private MessageHandler handler;

    @BeforeEach
    public void setUpLoggerController() {
        resetOut();
        captureSysout();
        reader = mock(BufferedReader.class);
        input = new DataInputStream(new ByteArrayInputStream("hi".getBytes(StandardCharsets.UTF_8)));
        out = mock(DataOutputStream.class);
        handler = new MessageHandler(reader, input, out);
    }

    @AfterEach
    public void tearDown() {
        resetOut();
    }

    @Test
    public void shouldSendMessage() throws IOException, InterruptedException {
        when(reader.readLine()).thenReturn("write this");
        when(input.readUTF()).thenReturn("nothing");

        handler.handle();

        verify(out).writeUTF("write this");
        verify(out).flush();
    }

    @Test
    public
}
