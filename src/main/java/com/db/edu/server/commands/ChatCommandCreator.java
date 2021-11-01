package com.db.edu.server.commands;

import com.db.edu.server.storage.Reader;

public interface ChatCommandCreator {
    ChatCommand create(String argument);
}
