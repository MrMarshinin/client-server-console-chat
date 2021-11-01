package com.db.edu.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomFileReader implements Reader {

    @Override
    public List<String> read() {
        return readSpecificRoom("");
    }

    @Override
    public List<String> readSpecificRoom(String room) {
        List<String> result = new ArrayList<>();
        try {
            String historyFileName = "history.txt";
            File file = new File(historyFileName);

            if (!file.exists()){
                System.out.println("Create history file ...");
                file.createNewFile();
                return new ArrayList<>();
            }

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
}
