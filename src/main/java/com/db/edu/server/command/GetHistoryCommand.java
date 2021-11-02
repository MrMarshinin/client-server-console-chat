package com.db.edu.server.command;

import com.db.edu.server.Notifier;
import com.db.edu.server.entity.Message;
import com.db.edu.server.entity.User;
import com.db.edu.server.storage.Reader;
import com.db.edu.server.storage.Saver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class GetHistoryCommand implements ChatCommand {
    private final Reader reader;
    private static final Logger log = LoggerFactory.getLogger(GetHistoryCommand.class);

    GetHistoryCommand(Reader reader) {
        this.reader = reader;
    }

    @Override
    public void execute(Saver saver, Notifier notifier, User user) {
        log.info("Execute history command");
        String room = user.getRoom();
        List<Message> messages = reader.readSpecificRoom(room);
        if (user.getRoom().equals("all")) {
            messages.addAll(reader.readSpecificRoom(""));
        }
        notifier.sendPersonalMessage(messages.stream().map(Message::getDecoratedString).collect(Collectors.joining(System.lineSeparator())), user);
    }
}
