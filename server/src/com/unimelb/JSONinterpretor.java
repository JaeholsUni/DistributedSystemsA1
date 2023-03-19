package com.unimelb;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

public class JSONinterpretor {

    public String[] decodeJSON(String json) {
        JSONParser parser = new JSONParser();

        try {
            JSONObject obj = (JSONObject) parser.parse(json);

            String[] returnArray = new String[3];

            returnArray[0] = (String) obj.get("command");
            returnArray[1] = (String) obj.get("word");
            returnArray[2] = (String) obj.get("definition");

           return returnArray;

        } catch (ParseException e) {
            System.out.println("Oh no JSON exception!");
            return null;
        }
    }

    public String encodeJSON(ArrayList<String> encodeArray) {
        JSONObject obj = new JSONObject();

        if ((encodeArray.size() % 2) != 0) {
            return "bad bad bad";
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
