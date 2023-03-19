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
    ThreadPool threadPool = new ThreadPool(10);
    dictionary dictionaryDatabase = new dictionary(args[0]);
    JSONinterpretor interpretor = new JSONinterpretor();
    dictionaryDatabase.initializeDictionary();

    try {
        listeningSocket = new ServerSocket(4444);
        int i = 0; //Count the number of clients

        while (true)
        {
            System.out.println("Server listening on port 4444");

            clientSocket = listeningSocket.accept();
            i++;

            System.out.println("Assigning client number " + i + "To new thread");
            threadPool.addTask(new clientRunnable(dictionaryDatabase, clientSocket));

            System.out.println("Client connection number" + i + "accepted:");
            System.out.println("Remote Hostname: " + clientSocket.getInetAddress().getHostName());
            System.out.println("Local Port " + clientSocket.getLocalPort());
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
