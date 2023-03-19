package com.unimelb;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import static com.unimelb.arrayUtils.*;

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

        if (!contentValidator(contents)) {
            return arrayComposer(tupleMaker(FAILURE_MESSAGE, INVALID_CONTENT_MESSAGE));
        }

        switch (contents[0]) {
            case "get":
                return arrayComposer(dictionary.retrieveEntry(contents[1]));
            case "delete":
                return arrayComposer(dictionary.removeEntry(contents[1]));
            case "add":
                return arrayComposer(dictionary.addNewEntry(contents[1], contents[2]));
            case "update":
                return arrayComposer(dictionary.updateEntry(contents[1], contents[2]));
            default:
                return arrayComposer(tupleMaker(FAILURE_MESSAGE, UNKNOWN_COMMAND_MESSAGE));
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
