/*
Main class for the dictionary server

Distributed Systems Assignment 1
James Hollingsworth - 915178
jameswh@iinet.net.au
 */

package com.unimelb;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static com.unimelb.GUI.addToReadOut;


public class Main {

    public static Integer getThreadNumber() {
        return THREAD_NUMBER;
    }

    public static Integer getPortNum() {
        return PORT_NUM;
    }

    public static int getActiveConnections() {
        return activeConnections;
    }

    private static Integer THREAD_NUMBER;
    private static Integer PORT_NUM;
    private static int activeConnections = 0;

    public static void main(String[] args) {
        if (!validateArgs(args)) {
            GUIfailedArgs guIfailedArgs = new GUIfailedArgs();
            return;
        }

        dictionary dictionaryDatabase = new dictionary(args[0]);
        THREAD_NUMBER = Integer.parseInt(args[1]);
        PORT_NUM = Integer.parseInt(args[2]);

	    ServerSocket listeningSocket = null;
        Socket clientSocket = null;
        ThreadPool threadPool = new ThreadPool(THREAD_NUMBER);
        dictionaryDatabase.initializeDictionary();

        GUI gui = new GUI();

    try {
        listeningSocket = new ServerSocket(PORT_NUM);

        while (true)
        {
            addToReadOut("Info: Server listening on port " + PORT_NUM);

            clientSocket = listeningSocket.accept();

            if (activeConnections < THREAD_NUMBER) {
                increaseActiveConnections();
                threadPool.addTask(new clientRunnable(dictionaryDatabase, clientSocket));

                addToReadOut("Info: Client connection number " + activeConnections + " accepted:");
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
            addToReadOut("Info: Rejecting Connection");
            connectionSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean validateArgs(String[] args) {
        if (args.length != 3) {
            return false;
        }
        try {
            Integer.parseInt(args[1]);
            Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
