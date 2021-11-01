package com.db.edu;

public class Printer {
    public static void print(String string) {
        if (string.equals("exit")) System.exit(0);
        System.out.println(string);
    }
}
