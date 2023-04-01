/*
Main class for client

Distributed Systems Assignment 1
James Hollingsworth - 915178
jameswh@iinet.net.au
 */

package com.unimelb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Main {

    public static void main(String[] args) {

        Socket socket = null;
        try {
            socket = new Socket("localhost", Integer.parseInt(args[0]));
            System.out.println("Connection established");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            commUtils communicator = new commUtils(in, out);

            GUI gui = new GUI(communicator);

            out.write("testConnection\n");
            out.flush();
            System.out.println(in.readLine());

            while (true){
                // All communication is handled in GUI
            }

        }
        catch (UnknownHostException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
        finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            GUIFail guiFail = new GUIFail();
        }
    }
}
