package org.lancaster.group77.InsertComponents.UML.Lines;

import org.lancaster.group77.InsertComponents.UML.ClassDiagrams.ClassDiagram;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LineOptions extends JFrame {
    private JList<String>  List;
    private DefaultListModel<String> displayList;

    private JLabel relationshipLabel;

    private String selectedDiagram = "Selected diagram";
    private String connectingDiagram = "Connecting diagram";
    private String selectedRelation;
    private boolean mode = true;
    private ClassDiagram diagram;
    private ClassDiagram diagram1;



    public LineOptions(String[] Options, ClassDiagram diagram, ClassDiagram diagram1){
        this.diagram = diagram;
        this.diagram1 =diagram1;
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.darkGray);
        displayList = new DefaultListModel<>();
        for(String option: Options) {
            displayList.addElement(option);
        }

        List = new JList<>(displayList);
        List.setBackground(Color.DARK_GRAY);
        List.setForeground(Color.magenta);
        this.setBackground(Color.darkGray);

        List.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedRelation = List.getSelectedValue();
                    if (selectedRelation != null) {
                        relationshipLabel.setText("<html>Relationship: Selected diagram is " + selectedRelation + " to connecting Diagram.<br></html>");
                    }
                }
            }
        });


        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.magenta);
        leftPanel.add(new JLabel("Left", SwingConstants.CENTER), BorderLayout.NORTH);
        leftPanel.add(new JScrollPane(List), BorderLayout.CENTER);


        relationshipLabel = new JLabel("<html>Relationship: Selected diagram is " + Options[0] + " to connecting Diagram.<br></html>");

        relationshipLabel.setForeground(Color.WHITE);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBackground(Color.darkGray);
        confirmButton.setForeground(Color.magenta);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the CreateLine(mode) method from the ClassDiagram class
                diagram.CreateLine(mode,selectedRelation, diagram1);
                dispose();
            }
        });


        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.darkGray);
        rightPanel.add(relationshipLabel, BorderLayout.NORTH);



        JPanel confirmButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Panel for the confirm button
        confirmButtonPanel.add(confirmButton);
        rightPanel.add(confirmButtonPanel, BorderLayout.SOUTH);


        JPanel optionsPanel = new JPanel(new GridLayout(1, 2));
        optionsPanel.add(leftPanel);
        optionsPanel.add(rightPanel);



        add(optionsPanel, BorderLayout.CENTER);
        setVisible(true);


    }




}
