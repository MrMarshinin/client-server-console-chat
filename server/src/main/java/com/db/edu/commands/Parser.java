package com.db.edu.commands;

import com.db.edu.commands.ChatCommand;
import com.db.edu.commands.ChatCommandCreator;

import java.util.HashMap;

public class Parser {
    private HashMap<String, ChatCommandCreator> commandCreators = new HashMap<>();

    public Parser() {
        commandCreators.put("/snd", SendMessageCommand::new);
        commandCreators.put("/hist", (String key) -> new GetHistoryCommand());
    }
    public ChatCommand parse(String command) {
        String[] strings = command.split(" ");
        if (strings.length < 1) {
            throw new IllegalArgumentException("Could not parse.");
        }
        String commandName = strings[0];
        ChatCommandCreator creator = commandCreators.get(commandName);
        if (creator == null) {
            throw new IllegalArgumentException("Could not parse command name.");
        }
        String arguments = "";
        if (strings.length == 2) {
            arguments = strings[1];
        }
        return creator.create(arguments);
    }
}
