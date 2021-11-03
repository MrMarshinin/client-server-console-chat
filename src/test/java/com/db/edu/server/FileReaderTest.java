package com.db.edu.server;

import com.db.edu.server.entity.AllMessage;
import com.db.edu.server.entity.Message;
import com.db.edu.server.storage.CustomFileReader;
import org.fest.assertions.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FileReaderTest {
    private BufferedReader reader;
    private CustomFileReader customFileReader = new CustomFileReader();
    private File file;
    private DataInputStream input;
    private ByteArrayOutputStream OUT = new ByteArrayOutputStream();


    public FileReaderTest() {
    }

    @BeforeEach
    public void SetUp() {
        reader = mock(BufferedReader.class);
        input = new DataInputStream(new ByteArrayInputStream("hello".getBytes(StandardCharsets.UTF_8)));
        captureSysout();
    }


    @Test
    public void TestRead() {
        assertEquals(customFileReader.read(), customFileReader.readSpecificRoom(""));
    }

    @Test
    public void TestFileWriterFileNotExist() {
        file = mock(File.class);
        when(file.exists()).thenReturn(false);
        assertThrows(IOException.class, () -> customFileReader.checkExist(file));
    }

    @Test
    public void TestTryCreateMessage() {
        AllMessage message = new AllMessage("2012-06-30T12:00, hi, room, user");
        assertThat((customFileReader.tryCreateMessage("2012-06-30T12:00, hi, room, user")).toString()).isEqualTo(message.toString());
    }

    private void captureSysout() {
        System.setOut(new PrintStream(OUT));
    }
}
