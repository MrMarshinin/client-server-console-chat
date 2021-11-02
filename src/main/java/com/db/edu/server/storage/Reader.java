package com.db.edu.server.storage;

import com.db.edu.server.entity.Message;

import java.util.List;

public interface Reader {
    List<Message> read();
    List<Message> readSpecificRoom(String room);
}
