/*
GUI for when the GUI fails to initialize

Distributed Systems Assignment 1
James Hollingsworth - 915178
jameswh@iinet.net.au
 */

package com.unimelb;

import javax.swing.*;

public class GUIfailedArgs {

    public GUIfailedArgs() {
        JFrame frameBadArgs = new JFrame("Bad Args");
        JLabel label = new JLabel("Incorrect arguments, format should be: filepath, number of connections, port num: eg ../words.csv 10 4444 ");

        frameBadArgs.getContentPane().add(label);
        frameBadArgs.pack();
        frameBadArgs.setVisible(true);
    }
}
