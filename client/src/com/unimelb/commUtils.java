package com.unimelb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class commUtils {

    BufferedReader reader;
    BufferedWriter writer;

    public commUtils(BufferedReader reader, BufferedWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public String readWrite(String write) {
        try {
            System.out.println("Attempting to write " + write);

            writer.write(write);
            writer.flush();

            return reader.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
