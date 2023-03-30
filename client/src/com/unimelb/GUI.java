package com.unimelb;

import javax.swing.*;
import java.awt.*;

import static com.unimelb.JSONutils.*;

public class GUI {

    commUtils communicator;

    JLabel homeLabel = new JLabel("Hollingsworth Dictionary");
    JLabel lookupLabel = new JLabel("Search Dictionary");
    JLabel removeLabel = new JLabel("Remove Word");
    JLabel updateLabel = new JLabel("Update Definition");
    JLabel newLabel = new JLabel("Add New Word");

    private JTextField lookupTextField;
    private JTextArea lookupReplyArea;
    private JTextField removeTextField;
    private JTextField updateWordTextField;
    private JTextArea updateDefTextArea;
    private JTextField newWordTextField;
    private JTextArea newDefTextArea;

    private JFrame frame = new JFrame();

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


    public void lookupFunction() {
        String text = lookupTextField.getText();
        System.out.println(communicator.readWrite(wordLookUp(text)+ "\n"));
    }

    public void deleteFunction() {
        String text = removeTextField.getText();
        System.out.println(communicator.readWrite(wordRemove(text)+ "\n"));
    }

    public void updateFunction() {
        String word = updateWordTextField.getText();
        String def = updateDefTextArea.getText();
        System.out.println(communicator.readWrite(wordUpdate(word, def)+ "\n"));
    }

    public void newFunction() {
        String word = newWordTextField.getText();
        String def = newDefTextArea.getText();
        System.out.println(communicator.readWrite(wordNew(word, def)+ "\n"));
    }

    public void changePanels(JPanel toHide, JPanel toShow) {
        frame.getContentPane().remove(toHide);
        frame.getContentPane().add(toShow);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }

    public void returnHomeButton() {
        frame.getContentPane().removeAll();
        frame.add(homePanel);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }
}
