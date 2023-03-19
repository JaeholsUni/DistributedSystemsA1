package com.unimelb;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

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
                    out.write("Server Ack " + clientMsg + "\n");
                    String[] messageArray = interpretor.decodeJSON(clientMsg);
                    out.flush();
                    System.out.println("Response Sent");

                    System.out.println(sortTask(dictionary, messageArray));

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

    private String sortTask(dictionary dictionary, String[] contents){
        if (!contentValidator(contents)) {
            return "Failure";
        }

        switch (contents[0]) {
            case "get":
                return (dictionary.retrieveEntry(contents[1]));
            case "delete":
                return dictionary.removeEntry(contents[1]);
            case "add":
                return dictionary.addNewEntry(contents[1], contents[2]);
            case "update":
                return dictionary.updateEntry(contents[1], contents[2]);
            default:
                return "Failure";
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
