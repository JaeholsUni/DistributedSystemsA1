/*
GUI for failing to connect to the server

Distributed Systems Assignment 1
James Hollingsworth - 915178
jameswh@iinet.net.au
 */

package com.unimelb;
import javax.swing.*;

public class GUIFail {

    private static final String FAILURE_TITLE = "Failed to connect";
    private static final String FAILURE_MESSAGE = "Failed to connect to server, ensure your address and port number is correct, server may be full please try again later";

    public GUIFail() {
        JFrame frame = new JFrame(FAILURE_TITLE);
        frame.setSize(700, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JLabel label = new JLabel(FAILURE_MESSAGE);
        label.setHorizontalAlignment(JLabel.CENTER);
        frame.add(label);
    }
}
