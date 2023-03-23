package com.unimelb;

import java.util.ArrayList;

public class arrayUtils {

    public static final String COMMAND = "command";
    public static final String WORD = "word";


    public static ArrayList<String> arrayComposer(String[] inputs) {
        ArrayList<String> returnString = new ArrayList<>();
        returnString.add(COMMAND);
        returnString.add(inputs[0]);
        returnString.add(WORD);
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
