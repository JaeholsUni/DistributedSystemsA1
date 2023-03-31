package com.unimelb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import static com.unimelb.GUI.showPopUp;

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
            showPopUp("Connection with server lost please restart application");
            e.printStackTrace();
        }

        return null;
    }
}
