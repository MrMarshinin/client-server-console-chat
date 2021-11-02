package com.db.edu.server.storage;

import com.db.edu.server.entity.Message;

import java.util.List;

public interface Reader {
    public List<Message> read();
    public List<Message> readSpecificRoom(String room);
}
