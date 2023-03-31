/*
Server management GUI

Distributed Systems Assignment 1
James Hollingsworth - 915178
jameswh@iinet.net.au
 */

package com.unimelb;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import static com.unimelb.Main.*;

public class GUI {

    private static JFrame frame = new JFrame();
    private JLabel portLabel, maxConnLabel, activeConnLabel;
    private static JTextArea readOut;

    public GUI() {
        frame.setTitle("Sever Page");

        portLabel = new JLabel("Port Number: " + getPortNum());
        maxConnLabel = new JLabel("Maximum Connections: " + getThreadNumber());
        activeConnLabel = new JLabel("Active Connections: ");

        readOut = new JTextArea();
        readOut.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(readOut);

        frame.setLayout(new GridLayout(4, 1));
        frame.add(portLabel);
        frame.add(maxConnLabel);
        frame.add(activeConnLabel);
        frame.add(scrollPane);

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                activeConnLabel.setText("Active Connections: " + getActiveConnections());
            }
        }, 0, 1000);
    }

    public static void addToReadOut(String text) {
        readOut.append(text + "\n");
    }

    public static void showPopUp(String text) {
        if (text == null) {
            return;
        }
        JOptionPane.showMessageDialog(frame, text);
    }


}
