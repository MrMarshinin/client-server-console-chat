package com.db.edu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CustomFileReader implements Reader {

    public static void main(String[] args) {
        Reader reader = new CustomFileReader();
        System.out.println(reader.read("text.txt"));
    }

    @Override
    public String read(String roomId) {
        StringBuilder result = new StringBuilder();
        try {
            File file = new File(roomId);

            if (!file.exists()){
                System.out.println("Create new room file: " + roomId);
                file.createNewFile();
                return "";
            }

            BufferedReader br = new BufferedReader(new FileReader(roomId));

            String line = br.readLine();

            while (line != null) {
                result.append(line);
                result.append(System.lineSeparator());
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
