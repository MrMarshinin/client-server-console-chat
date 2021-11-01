package com.db.edu.server.storage;

import java.util.List;

public interface Reader {
    public List<String> read();

    public List<String> readSpecificRoom(String room);
}
