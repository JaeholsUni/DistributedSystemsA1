/*
Utility functions for encoding and decoding JSON for the server

Distributed Systems Assignment 1
James Hollingsworth - 915178
jameswh@iinet.net.au
 */

package com.unimelb;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import static com.unimelb.GUI.showPopUp;

public class JSONinterpretor {

    private static final String COMMAND = "command";
    private static final String WORD = "word";
    private static final String DEFINITION = "definition";

    private static final String JSON_EXCEPTION_MESSAGE = "JSON Parse Exception Occurred, should not happen";

    public String[] decodeJSON(String json) {
        JSONParser parser = new JSONParser();

        try {
            JSONObject obj = (JSONObject) parser.parse(json);

            String[] returnArray = new String[3];

            returnArray[0] = (String) obj.get(COMMAND);
            returnArray[1] = (String) obj.get(WORD);
            returnArray[2] = (String) obj.get(DEFINITION);

           return returnArray;

        } catch (ParseException e) {
            showPopUp(JSON_EXCEPTION_MESSAGE);
            return null;
        }
    }

    public String encodeJSON(ArrayList<String> encodeArray) {
        JSONObject obj = new JSONObject();

        if ((encodeArray.size() % 2) != 0) {
            return null;
        } else {
            for (int i = 0; i < encodeArray.size(); i=i+2) {
                obj.put(encodeArray.get(i), encodeArray.get(i+1));
            }
        }

        StringWriter out = new StringWriter();
        try {
            obj.writeJSONString(out);
            return out.toString();
        } catch (IOException e) {
            return null;
        }
    }
}
