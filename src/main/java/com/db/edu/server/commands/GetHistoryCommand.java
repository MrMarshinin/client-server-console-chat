package com.db.edu.server.commands;

import com.db.edu.server.ConnectionHandler;
import com.db.edu.server.Notifier;
import com.db.edu.server.entity.User;
import com.db.edu.server.storage.Reader;
import com.db.edu.server.storage.Saver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetHistoryCommand implements ChatCommand {
    private Reader reader;
    private static final Logger log = LoggerFactory.getLogger(ConnectionHandler.class);

    GetHistoryCommand(Reader reader) {
        this.reader = reader;
    }

    @Override
    public void execute(Saver saver, Notifier notifier, User user) {
        log.info("Execute history command");
        String room = user.getRoom();
        List<String> messages = reader.readSpecificRoom(room);

        messages.forEach(message -> notifier.sendPersonalMessage(message, user));
    }
}
