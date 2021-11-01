package com.db.edu.server;

import com.db.edu.server.storage.FileSaver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class FileSaverTest {
    private FileSaver fileSaverSub = new FileSaver();
    File file;

    public FileSaverTest() {
    }

    @BeforeEach
    public void SetUp() {
    }

    @Test
    public void TestFileWriterFileExist() {
        file = new File("history.txt");
        fileSaverSub.save("message");
        assertTrue(this.file.length() > 0L);
    }

    @Test
    public void TestFileWriterFileNotExist() throws IOException {
        file = mock(File.class);
        when(file.exists()).thenReturn(false);
        fileSaverSub.checkExist(file);
        verify(file).createNewFile();
    }
}


