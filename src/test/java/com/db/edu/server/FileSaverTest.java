package com.db.edu.server;

import com.db.edu.server.storage.FileSaver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class FileSaverTest {
    private FileSaver fileSaverSub = new FileSaver();
    private File file;

    public FileSaverTest() {
    }

    @BeforeEach
    public void SetUp() {
    }


    @Test
    public void TestFileWriterFileNotExist() throws IOException {
        file = mock(File.class);
        when(file.exists()).thenReturn(false);
        fileSaverSub.checkExist(file);
        verify(file).createNewFile();
    }
}


