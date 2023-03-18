package com.unimelb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


public class Main {

    public static void main(String[] args) {
	ServerSocket listeningSocket = null;
    Socket clientSocket = null;
    dictionary dictionaryDatabase = new dictionary(args[0]);
    JSONinterpretor interpretor = new JSONinterpretor();
    worker tempWorker = new worker();
    dictionaryDatabase.initializeDictionary();

    try {
        listeningSocket = new ServerSocket(4444);
        int i = 0; //Count the number of clients

        while (true)
        {
            System.out.println("Server listening on port 4444");

            clientSocket = listeningSocket.accept();
            i++;

            System.out.println("Client connection number" + i + "accepted:");
            System.out.println("Remote Hostname: " + clientSocket.getInetAddress().getHostName());
            System.out.println("Local Port " + clientSocket.getLocalPort());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));


            String clientMsg = null;
            try {
                while ((clientMsg = in.readLine()) != null)
                {
                    System.out.println("Message from client " + i + ": " + clientMsg);
                    out.write("Server Ack " + clientMsg + "\n");
                    String[] messageArray = interpretor.decodeJSON(clientMsg);
                    out.flush();
                    System.out.println("Response Sent");

                    System.out.println(tempWorker.sortTask(dictionaryDatabase, messageArray));

                }
                System.out.println("Sever Closed the Client Connection - Recieved null");
            }
            catch (SocketException e)
            {
                System.out.println("Closed by exception");
            }

            clientSocket.close();
        }
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }
    finally {
        if (listeningSocket != null) {
            try {
                listeningSocket.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    }
}
