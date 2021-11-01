package com.db.edu;

import java.io.*;

public class FileSaver implements Saver {

    @Override
    public void push(String message){
        try {
            File file = new File("history.txt");
            if(!file.exists()){
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(message);
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
