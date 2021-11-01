package com.db.edu.commands;

import com.db.edu.storage.Reader;

public interface ChatCommandCreator {
    ChatCommand create(String argument);
}
