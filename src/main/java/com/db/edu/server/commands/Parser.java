package com.db.edu.server.commands;

import com.db.edu.server.storage.CustomFileReader;

import java.util.Arrays;
import java.util.HashMap;

public class Parser {
    private final HashMap<String, ChatCommandCreator> commandCreators = new HashMap<>();

    public Parser() {
        commandCreators.put("/snd", SendMessageCommand::new);
        commandCreators.put("/hist", (String key) -> new GetHistoryCommand(new CustomFileReader()));
        commandCreators.put("/chroom", ChangeRoomCommand::new);
        commandCreators.put("/chid", ChangeIdCommand::new);
        commandCreators.put("/sndp", SendPersonalMessageCommand::new);
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
        StringBuilder arguments = new StringBuilder();
        Arrays.stream(strings).filter(s -> !s.equals(strings[0])).forEach(s -> arguments.append(s).append(" "));
        if (arguments.length() > 0) {
            arguments.replace(arguments.length() - 1, arguments.length(), "");
        }
        return creator.create(arguments.toString());
    }
}
