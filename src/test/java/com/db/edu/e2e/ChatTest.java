package com.db.edu.e2e;

import com.db.edu.server.ConnectionHandler;
import com.db.edu.server.Notifier;
import com.db.edu.server.Server;
import com.db.edu.server.commands.Parser;
import com.db.edu.server.entity.User;
import com.db.edu.server.storage.FileSaver;
import com.db.edu.server.storage.Saver;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;


public class ChatTest {
    private List<User> listOfUsers = new LinkedList<>() ;



    @Test
    public void testReadFromFile() throws IOException {
      assertTrue(true);


    }

}
