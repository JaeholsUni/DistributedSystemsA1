/*
utility functions for dealing with arrays in the server

Distributed Systems Assignment 1
James Hollingsworth - 915178
jameswh@iinet.net.au
 */

package com.unimelb;

import java.util.ArrayList;

public class arrayUtils {

    public static final String STATUS = "status";
    public static final String DATA = "data";
    public static final String FAILURE_MESSAGE = "fail";
    public static final String SUCCESS_MESSAGE = "success";
    public static final String INVALID_CONTENT_MESSAGE = "Invalid input message";
    public static final String UNKNOWN_COMMAND_MESSAGE = "Unknown command";


    public static ArrayList<String> arrayComposer(String[] inputs) {
        ArrayList<String> returnString = new ArrayList<>();
        returnString.add(STATUS);
        returnString.add(inputs[0]);
        returnString.add(DATA);
        returnString.add(inputs[1]);

        return returnString;
    }

    public static String[] tupleMaker(String x, String y) {
        String[] tuple = new String[2];
        tuple[0] = x;
        tuple[1] = y;
        return tuple;
    }
}
