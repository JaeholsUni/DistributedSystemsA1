package com.unimelb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class commUtils {


    public static String readWrite(String write, BufferedWriter writer, BufferedReader reader) {
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
