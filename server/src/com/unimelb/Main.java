package com.unimelb;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Main {

    public static final Integer THREAD_NUMBER = 2;
    private static int activeConnections = 0;

    public static void main(String[] args) {
	ServerSocket listeningSocket = null;
    Socket clientSocket = null;
    ThreadPool threadPool = new ThreadPool(THREAD_NUMBER);
    dictionary dictionaryDatabase = new dictionary(args[0]);
    dictionaryDatabase.initializeDictionary();

    try {
        listeningSocket = new ServerSocket(4444);

        while (true)
        {
            System.out.println("Server listening on port 4444");


            clientSocket = listeningSocket.accept();

            if (activeConnections < THREAD_NUMBER) {
                increaseActiveConnections();
                threadPool.addTask(new clientRunnable(dictionaryDatabase, clientSocket));
                System.out.println("Client connection number " + activeConnections + " accepted:");
                System.out.println("Remote Hostname: " + clientSocket.getInetAddress().getHostName());
                System.out.println("Local Port " + clientSocket.getLocalPort());
            } else {
                rejectConnection(clientSocket);
            }
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

    public static void increaseActiveConnections() {
        activeConnections++;
    }

    public static void decreaseActiveConnections() {
        activeConnections--;
    }

    private static void rejectConnection(Socket connectionSocket) {
        try {
            System.out.println("Rejecting Connection");
            connectionSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
