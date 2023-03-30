package com.unimelb;
import javax.swing.*;

public class GUIFail {

    public static final String FAILURE_TITLE = "Failed to connect";
    public static final String FAILURE_MESSAGE = "Failed to connect to server, server may be full please try again later";

    public GUIFail() {
        JFrame frame = new JFrame(FAILURE_TITLE);
        frame.setSize(500, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JLabel label = new JLabel(FAILURE_MESSAGE);
        label.setHorizontalAlignment(JLabel.CENTER);
        frame.add(label);
    }
}
