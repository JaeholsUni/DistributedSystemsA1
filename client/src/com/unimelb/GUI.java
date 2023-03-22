package com.unimelb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import static com.unimelb.JSONutils.wordLookUp;
import static com.unimelb.commUtils.readWrite;

public class GUI {

    BufferedWriter out;
    BufferedReader in;
    JLabel label = new JLabel("Hollingsworth Dictionary");
    Integer count = 0;
    private JTextField textField;



    public GUI(BufferedWriter outWriter, BufferedReader inReader) {

        out = outWriter;
        in = inReader;

        JFrame frame = new JFrame();


        textField = new JTextField(20);


        JButton button = new JButton("Test Button");
        button.addActionListener(e -> buttonClick());

        //Create panel and add panel contents
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);
        panel.add(textField);

        //Add contents to frame and set visible
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Hollingsworth Dictionary");
        frame.pack();
        frame.setVisible(true);
    }


    private void buttonClick() {
        String text = textField.getText();
        System.out.println(readWrite(wordLookUp(text)+ "\n", out, in));
    }
}
