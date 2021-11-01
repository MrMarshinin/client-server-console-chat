package com.db.edu.server;

import com.db.edu.server.storage.FileSaver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileSaverTest {
    private FileSaver fileSaverSub = new FileSaver();
    File file = new File("history.txt");

    @BeforeEach
    public void SetUp() {
    }

    @Test
    public void TestFileWriterFileExist(){
        fileSaverSub.save("message");
        Assertions.assertTrue(file.length() > 0);

    }


}
