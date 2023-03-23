package com.unimelb;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import static com.unimelb.arrayUtils.*;

public class JSONutils {

    public static final String GET = "get";
    public static final String DELETE = "delete";
    private static final String UPDATE = "update";

    public static String wordLookUp(String word) {
        return encodeJSON(arrayComposer(tupleMaker(GET, word)));
    }

    public static String wordRemove(String word) {
        return encodeJSON(arrayComposer(tupleMaker(DELETE, word)));
    }

    public static String wordUpdate(String word, String def) {
        return encodeJSON(arrayComposer(trupleMaker(UPDATE, word, def)));
    }

    public static String encodeJSON(ArrayList<String> encodeArray) {
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
