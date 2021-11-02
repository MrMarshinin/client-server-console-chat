package com.db.edu.server.storage;

import com.db.edu.server.entity.AllMessage;
import com.db.edu.server.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomFileReader implements Reader {
    private final String historyFileName;

    private static final Logger log = LoggerFactory.getLogger(CustomFileReader.class);

    public CustomFileReader() {
        historyFileName = "history.txt";
    }

    @Override
    public List<Message> read() {
        return readSpecificRoom("");
    }

    @Override
    public List<Message> readSpecificRoom(String room) {
        List<Message> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(historyFileName))) {

            File file = new File(historyFileName);
            checkExist(file);

            return br.lines().map(this::tryCreateMessage).filter(Objects::nonNull).collect(Collectors.toList());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    public Message tryCreateMessage(String line) {
        try {
            return new AllMessage(line);
        } catch (IllegalArgumentException e) {
            log.error("Invalid format for message");
            return null;
        }
    }

    public void checkExist(File file) throws IOException {
        if (!file.exists()){
            if (!file.createNewFile()) {
                throw new IOException("Cannot create file history.txt, but it doesn't exist");
            }
            log.info("Created history file.");
        }
    }
}
