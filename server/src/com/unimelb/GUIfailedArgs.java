package com.unimelb;

import javax.swing.*;

public class GUIfailedArgs {

    public GUIfailedArgs() {
        JFrame frameBadArgs = new JFrame("Bad Args");
        JLabel label = new JLabel("Incorrect arguments, format should be: filepath, number of connections, port num \n eg ../words.csv 10 4444 ");

        frameBadArgs.getContentPane().add(label);
        frameBadArgs.pack();
        frameBadArgs.setVisible(true);
    }
}
