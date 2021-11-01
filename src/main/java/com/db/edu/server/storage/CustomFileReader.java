package com.db.edu.server.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomFileReader implements Reader {
    String historyFileName = "history.txt";
    File file;

    @Override
    public List<String> read() {
        return readSpecificRoom("");
    }

    @Override
    public List<String> readSpecificRoom(String room) {
        List<String> result = new ArrayList<>();
        try {

            file = new File(historyFileName);
            checkExist(file);

            BufferedReader br = new BufferedReader(new FileReader(historyFileName));

            String line = br.readLine();

            while (line != null) {
                if (line.startsWith(room)) {
                    result.add(line);
                }
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<?> checkExist(File file) throws IOException {
        if (!file.exists()){
            System.out.println("Create history file ...");
            file.createNewFile();
            return new ArrayList<>();
        }
        return null;
    }
}
