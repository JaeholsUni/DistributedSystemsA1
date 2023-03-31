/*
Utility functions for dealing with communication between the servers

Distributed Systems Assignment 1
James Hollingsworth - 915178
jameswh@iinet.net.au
 */

package com.unimelb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import static com.unimelb.GUI.showPopUp;

public class commUtils {

    BufferedReader reader;
    BufferedWriter writer;

    private final String LOST_CON_MESSAGE = "Connection with server lost exiting application";

    public commUtils(BufferedReader reader, BufferedWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public String readWrite(String write) {
        try {
            writer.write(write);
            writer.flush();
            return reader.readLine();
        } catch (IOException e){
            showPopUp(LOST_CON_MESSAGE);
            System.exit(0);
            e.printStackTrace();
        }
        return null;
    }
}
