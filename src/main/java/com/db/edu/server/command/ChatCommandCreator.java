package com.db.edu.server.command;

public interface ChatCommandCreator {
    ChatCommand create(String argument);
}
