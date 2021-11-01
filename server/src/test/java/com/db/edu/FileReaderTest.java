package com.db.edu;

import com.db.edu.storage.CustomFileReader;
import com.db.edu.storage.FileSaver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class FileReaderTest {
    private BufferedReader reader;
    private CustomFileReader customFileReader = new CustomFileReader();
    File file = new File("history.txt");
    private DataInputStream input;
    ByteArrayOutputStream OUT = new ByteArrayOutputStream();

    @BeforeEach
    public void SetUp() {
        reader = mock(BufferedReader.class);
        input = new DataInputStream(new ByteArrayInputStream("hello".getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    public void TestFileWriterFileNotExist(){
        customFileReader.read();


    }


}
