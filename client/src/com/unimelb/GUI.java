/*
Main GUI for the Client to handle inputs and display with the client

Distributed Systems Assignment 1
James Hollingsworth - 915178
jameswh@iinet.net.au
 */

package com.unimelb;

import javax.swing.*;
import java.awt.*;

import static com.unimelb.JSONutils.*;

public class GUI {

    private commUtils communicator;

    private JLabel homeLabel = new JLabel("Hollingsworth Dictionary");
    private JLabel lookupLabel = new JLabel("Search Dictionary");
    private JLabel removeLabel = new JLabel("Remove Word");
    private JLabel updateLabel = new JLabel("Update Definition");
    private JLabel newLabel = new JLabel("Add New Word");

    private JTextField lookupTextField;
    private JTextArea lookupReplyArea;
    private JTextField removeTextField;
    private JTextField updateWordTextField;
    private JTextArea updateDefTextArea;
    private JTextField newWordTextField;
    private JTextArea newDefTextArea;

    private static JFrame frame = new JFrame();

    private JPanel homePanel = new JPanel();
    private JPanel lookupPanel = new JPanel();
    private JPanel removePanel = new JPanel();
    private JPanel updatePanel = new JPanel();
    private JPanel newPanel = new JPanel();

    public GUI(commUtils communicator) {

        this.communicator = communicator;

        //Text Fields
        lookupTextField = new JTextField(20);
        lookupReplyArea = new JTextArea(5, 20);
        lookupReplyArea.setEditable(false);
        removeTextField = new JTextField(20);
        updateWordTextField = new JTextField(20);
        updateDefTextArea = new JTextArea(5, 20);
        newWordTextField = new JTextField(20);
        newDefTextArea = new JTextArea(5,20);

        //Return Button
        JButton returnHomeButton = new JButton("Home");
        returnHomeButton.addActionListener(e -> {
            returnHomeButton();
        });


        //Home Panel
        //Nav Buttons
        JButton navLookupButton = new JButton("Word Lookup");
        navLookupButton.addActionListener(e -> {
            changePanels(homePanel, lookupPanel);
        });
        JButton navRemoveButton = new JButton("Remove Word");
        navRemoveButton.addActionListener(e -> {
            changePanels(homePanel, removePanel);
        });
        JButton navUpdateButton = new JButton("Update Word");
        navUpdateButton.addActionListener(e -> {
            changePanels(homePanel, updatePanel);
        });
        JButton navNewButton = new JButton("New Word");
        navNewButton.addActionListener(e -> {
            changePanels(homePanel, newPanel);
        });
        //Home setup
        homePanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        homePanel.setLayout(new GridLayout(0, 1));
        homePanel.add(homeLabel);
        homePanel.add(navLookupButton);
        homePanel.add(navRemoveButton);
        homePanel.add(navUpdateButton);
        homePanel.add(navNewButton);


        //Lookup Panel
        //Lookup buttons
        JButton lookupButton = new JButton("Lookup");
        JButton lookupReturnHomeButton = new JButton("Home");
        lookupReturnHomeButton.addActionListener(e -> returnHomeButton());
        lookupButton.addActionListener(e -> lookupFunction());
        //Lookup Setup
        lookupPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        lookupPanel.add(lookupLabel);
        lookupPanel.add(lookupTextField);
        lookupPanel.add(lookupReplyArea);
        lookupPanel.add(lookupButton);
        lookupPanel.add(lookupReturnHomeButton);

        //RemovePanel
        //RemoveButtons
        JButton removeReturnHomeButton = new JButton("Home");
        removeReturnHomeButton.addActionListener(e -> returnHomeButton());
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> deleteFunction());
        //Remove Setup
        removePanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        removePanel.add(removeLabel);
        removePanel.add(removeTextField);
        removePanel.add(removeButton);
        removePanel.add(removeReturnHomeButton);

        //Update Panel
        //Update Buttons
        JButton updateButton = new JButton("Update Definition");
        updateButton.addActionListener(e -> updateFunction());
        JButton updateReturnHomeButton = new JButton("Home");
        updateReturnHomeButton.addActionListener(e -> returnHomeButton());
        //Update Setup
        updatePanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        updatePanel.add(updateLabel);
        updatePanel.add(updateWordTextField);
        updatePanel.add(updateDefTextArea);
        updatePanel.add(updateButton);
        updatePanel.add(updateReturnHomeButton);

        //New Panel
        //New Buttons
        JButton newButton = new JButton("Add new word!");
        newButton.addActionListener(e -> newFunction());
        JButton newReturnHomeButton = new JButton("Home");
        newReturnHomeButton.addActionListener(e -> returnHomeButton());
        //New Setup
        newPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        newPanel.add(newLabel);
        newPanel.add(newWordTextField);
        newPanel.add(newDefTextArea);
        newPanel.add(newButton);
        newPanel.add(newReturnHomeButton);



        //Add contents to frame and set visible
        frame.add(homePanel, BorderLayout.CENTER);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Hollingsworth Dictionary");
        frame.pack();
        frame.setVisible(true);
    }

    private String sendResponseGetReply(String text) {
        return readResponse(communicator.readWrite(text + "\n"));
    }

    private void lookupFunction() {
        String text = lookupTextField.getText();
        lookupReplyArea.setText(sendResponseGetReply(wordLookUp(text)));
    }

    private void deleteFunction() {
        String text = removeTextField.getText();
        showPopUp(sendResponseGetReply(wordRemove(text)));
    }

    private void updateFunction() {
        String word = updateWordTextField.getText();
        String def = updateDefTextArea.getText();
        showPopUp(sendResponseGetReply(wordUpdate(word, def)));
    }

    private void newFunction() {
        String word = newWordTextField.getText();
        String def = newDefTextArea.getText();
        showPopUp(sendResponseGetReply(wordNew(word, def)));
    }

    private void changePanels(JPanel toHide, JPanel toShow) {
        frame.getContentPane().remove(toHide);
        frame.getContentPane().add(toShow);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }

    private void returnHomeButton() {
        frame.getContentPane().removeAll();
        frame.add(homePanel);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }

    public static void showPopUp(String text) {
        if (text == null) {
            return;
        }
        JOptionPane.showMessageDialog(frame, text);
    }
}
