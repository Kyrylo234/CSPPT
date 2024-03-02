package org.lancaster.group77.InsertComponents.UML.Lines;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionSelector extends JFrame {
    private JList<String> leftList;
    private JList<String> rightList;
    private DefaultListModel<String> leftListModel;
    private DefaultListModel<String> rightListModel;
    private JButton confirmButton;

    private String[] options;

    private BaseLine line;

    public OptionSelector(String[] options, BaseLine line) {
        setTitle("Option Selector");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.darkGray); // Set background color
        this.options = options;
        this.line = line;
        leftListModel = new DefaultListModel<>();
        rightListModel = new DefaultListModel<>();

        for (String option : options) {
            leftListModel.addElement(option);
            rightListModel.addElement(option);
        }

        leftList = new JList<>(leftListModel);
        rightList = new JList<>(rightListModel);
        leftList.setBackground(Color.darkGray);
        leftList.setForeground(Color.magenta);
        rightList.setBackground(Color.darkGray);
        rightList.setForeground(Color.magenta);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.magenta);
        leftPanel.add(new JLabel("Left", SwingConstants.CENTER), BorderLayout.NORTH);
        leftPanel.add(new JScrollPane(leftList), BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.magenta);
        rightPanel.add(new JLabel("Right", SwingConstants.CENTER), BorderLayout.NORTH);
        rightPanel.add(new JScrollPane(rightList), BorderLayout.CENTER);

        JPanel optionsPanel = new JPanel(new GridLayout(1, 2));
        optionsPanel.add(leftPanel);
        optionsPanel.add(rightPanel);

        add(optionsPanel, BorderLayout.CENTER);

        confirmButton = new JButton("Confirm");
        confirmButton.setEnabled(false); // Initially disabled
        confirmButton.setBackground(Color.darkGray); // Set button background color
        confirmButton.setForeground(Color.magenta);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String leftOption = leftList.getSelectedValue();
                String rightOption = rightList.getSelectedValue();
                line.setlines(leftOption,rightOption);


                dispose();

            }
        });

        add(confirmButton, BorderLayout.SOUTH);

        // Add ListSelectionListener to check if an option is selected from both sides
        leftList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateConfirmButtonState();
            }
        });

        rightList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateConfirmButtonState();
            }
        });

        setVisible(true);
    }

    private void updateConfirmButtonState() {
        boolean leftSelected = !leftList.isSelectionEmpty();
        boolean rightSelected = !rightList.isSelectionEmpty();
        confirmButton.setEnabled(leftSelected && rightSelected);
    }


}
