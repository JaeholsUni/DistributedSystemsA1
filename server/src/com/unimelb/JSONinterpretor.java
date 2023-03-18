package com.unimelb;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONinterpretor {

    public String[] decodeJSON(String json) {
        JSONParser parser = new JSONParser();

        try {
            JSONObject obj = (JSONObject) parser.parse(json);

            String[] returnArray = new String[3];

            returnArray[0] = (String) obj.get("command");
            returnArray[1] = (String) obj.get("word");
            returnArray[2] = (String) obj.get("definition");

            System.out.println(returnArray);

           return returnArray;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
