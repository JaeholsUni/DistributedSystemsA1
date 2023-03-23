package com.unimelb;

import java.util.ArrayList;

public class arrayUtils {

    public static final String COMMAND = "command";
    public static final String WORD = "word";
    public static final String DEFINITION = "definition";


    //These need to be refactored
    public static ArrayList<String> arrayComposer(String[] inputs) {
        ArrayList<String> returnString = new ArrayList<>();
        returnString.add(COMMAND);
        returnString.add(inputs[0]);
        returnString.add(WORD);
        returnString.add(inputs[1]);
        if (inputs.length > 2) {
            returnString.add(DEFINITION);
            returnString.add(inputs[2]);
        }

        return returnString;
    }

    public static String[] tupleMaker(String x, String y) {
        String[] tuple = new String[2];
        tuple[0] = x;
        tuple[1] = y;
        return tuple;
    }

    public static String[] trupleMaker(String x, String y, String z) {
        String[] truple = new String[3];
        truple[0] = x;
        truple[1] = y;
        truple[2] = z;
        return truple;
    }
}
