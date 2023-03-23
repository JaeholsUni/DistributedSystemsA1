package com.unimelb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
	// write your code here


        Socket socket = null;
        try {
            socket = new Socket("localhost", 4444);
            System.out.println("Connection established");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            Scanner scanner = new Scanner(System.in);

            commUtils communicator = new commUtils(in, out);

            String inputStr = null;

            GUI gui = new GUI(communicator);

            while (!(inputStr = scanner.nextLine()).equalsIgnoreCase("exit")){


                out.write(inputStr + "\n");
                out.flush();
                System.out.println("Message Sent");

                String recieved = in.readLine();
                System.out.println("Message recieved " + recieved);
            }

            scanner.close();
        }
        catch (UnknownHostException e){
            e.printStackTrace();
        }
        catch (IOException e){
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
        }
    }
}
