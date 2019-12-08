package com.psy888;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//File read write
public class FileRW {
    private static final String FILE_NAME = "base.txt";
    private static final String LINE_DIVIDER = "\n";
    private static final String WORD_DIVIDER = "-";
//    private byte[] buf = new byte[8];


    // todo write to file method
    public boolean saveBase(HashMap<String, String> base) {
        return writeToFile(fromHashMap(base));
    }

    // todo read from file method
    // string

    public HashMap<String, String> readBase() {
        return toHashMap(readFromFile());
    }


    //Helper methods


    private String fromHashMap(HashMap<String, String> base) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> entry : base.entrySet()) {
            sb.append(entry.getKey() + WORD_DIVIDER + entry.getValue() + LINE_DIVIDER);
        }

        return sb.toString();
    }

    private boolean writeToFile(String str) {
        try {
            FileWriter writer = new FileWriter(FILE_NAME);
            writer.write(str);
            writer.close();
        } catch (IOException ioe) {
            System.out.println("ошибка записи в файл");
            return false;
        }
        System.out.println("сохранение успешно");
        return true;
    }

    private String readFromFile() {
        try {
            FileReader reader = new FileReader(FILE_NAME);
            Scanner scanner = new Scanner(reader);
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNext()) {
                sb.append(scanner.nextLine());
            }
            return sb.toString();
        } catch (IOException ioe) {
            System.out.println("Ошибка чтения файла");
            ioe.printStackTrace();
        }
        return null;
    }

    private HashMap<String, String> toHashMap(String str) {
        HashMap <String,String> base = new HashMap<>();
        String[] wordPairs = str.split(LINE_DIVIDER);
        for(String wp: wordPairs){
            String[] words = wp.split(WORD_DIVIDER);
            base.put(words[0],words[1]);
        }
        return base;
    }
}
