/*
Utility functions for working with JSON for the client

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
import static com.unimelb.arrayUtils.*;

public class JSONutils {

    private static final String GET = "get";
    private static final String DELETE = "delete";
    private static final String UPDATE = "update";
    private static final String NEW = "add";
    private static final String SUCCESS = "success";
    private static final String STATUS = "status";
    private static final String DATA = "data";

    public static String wordLookUp(String word) {
        return encodeJSON(arrayComposer(tupleMaker(GET, word)));
    }

    public static String wordRemove(String word) {
        return encodeJSON(arrayComposer(tupleMaker(DELETE, word)));
    }

    public static String wordUpdate(String word, String def) {
        return encodeJSON(arrayComposer(trupleMaker(UPDATE, word, def)));
    }

    public static String wordNew(String word, String def) {
        return encodeJSON(arrayComposer(trupleMaker(NEW, word, def)));
    }

    private static String encodeJSON(ArrayList<String> encodeArray) {
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

    private static String[] decodeJSON(String json) {
        JSONParser parser = new JSONParser();

        try {
            JSONObject obj = (JSONObject) parser.parse(json);
            String[] returnArray = new String[2];

            returnArray[0] = (String) obj.get(STATUS);
            returnArray[1] = (String) obj.get(DATA);

            return returnArray;
        } catch (ParseException e) {
            return null;
        }
    }

    private static String checkValidResponse(String[] response) {
        if (response[0].equals(SUCCESS)) {
            return response[1];
        } else {
            showPopUp(response[1]);
            return null;
        }
    }

    public static String readResponse(String response) {
        return checkValidResponse(decodeJSON(response));
    }
}
