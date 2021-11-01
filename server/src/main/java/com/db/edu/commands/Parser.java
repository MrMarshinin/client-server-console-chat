package com.db.edu.commands;

import com.db.edu.UserConnection;
import com.db.edu.commands.ChatCommand;
import com.db.edu.commands.ChatCommandCreator;

import java.util.Arrays;
import java.util.HashMap;

public class Parser {
    private HashMap<String, ChatCommandCreator> commandCreators = new HashMap<>();

    public Parser() {
        commandCreators.put("/snd", SendMessageCommand::new);
        commandCreators.put("/hist", (String key) -> new GetHistoryCommand());
        commandCreators.put("/chroom", (String key) -> new ChangeRoomCommand());
    }

    public ChatCommand parse(UserConnection user, String command) {
        String[] strings = command.split(" ");
        if (strings.length < 1) {
            throw new IllegalArgumentException("Could not parse.");
        }
        String commandName = strings[0];
        ChatCommandCreator creator = commandCreators.get(commandName);
        if (creator == null) {
            throw new IllegalArgumentException("Could not parse command name.");
        }
        StringBuilder arguments = new StringBuilder();
        Arrays.stream(strings).filter(s -> !s.equals(strings[0])).forEach(arguments::append);
        if (commandName.equals("/chroom")) {
            user.setRoom(strings[1]);
        }
        return creator.create(arguments.toString());
    }
}
