package com.unimelb;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class clientRunnable implements Runnable{

    private final dictionary dictionary;
    private final Socket connectionSocket;
    private final JSONinterpretor interpretor = new JSONinterpretor();

    public clientRunnable(com.unimelb.dictionary dictionary, Socket connectionSocket) {
        this.dictionary = dictionary;
        this.connectionSocket = connectionSocket;
    }

    public void run() {


        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));

            String clientMsg = null;

            try {
                while ((clientMsg = in.readLine()) != null)
                {
                    String[] messageArray = interpretor.decodeJSON(clientMsg);

                    if (messageArray == null) {
                        System.out.println("bad input");
                        continue;
                    }
                    String output = interpretor.encodeJSON(sortTask(dictionary, messageArray));
                    System.out.println(output);
                    out.write(output + "\n");
                    out.flush();
                    System.out.println("response sent");

                }
            } catch (SocketException e)
            {
                System.out.println("Closed by exception");
            }

            connectionSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private ArrayList<String> sortTask(dictionary dictionary, String[] contents){
        ArrayList<String> returnString = new ArrayList<>();

        if (!contentValidator(contents)) {
            returnString.add("status");
            returnString.add("fail");
            return returnString;
        }

        switch (contents[0]) {
            case "get":
                returnString.add("status");
                returnString.add("success");
                returnString.add("data");
                returnString.add(dictionary.retrieveEntry(contents[1]));
                return returnString;
            case "delete":
                returnString.add("status");
                returnString.add(dictionary.removeEntry(contents[1]));
                return returnString;
            case "add":
                returnString.add("status");
                returnString.add(dictionary.addNewEntry(contents[1], contents[2]));
            case "update":
                returnString.add("status");
                returnString.add(dictionary.updateEntry(contents[1], contents[2]));
            default:
                returnString.add("status");
                returnString.add("fail");
                return returnString;
        }
    }

    private boolean contentValidator(String[] contents) {
        if (contents[0] == null || contents[1] == null){
            System.out.println("1 or 2 null");
            return false;
        } else {
            if ((contents[0].equals("add") || contents[0].equals("update")) && contents[2] == null) {

                System.out.println("3 null and required");
                return false;
            }

            System.out.println("all g");
            return true;
        }
    }
}
