package org.lancaster.group77.Frame.Buttons.Insert;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.Table.TableComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertTableButton extends IconButton {
    private MouseHandler handler;
    private int rows;
    private int columns;

    public InsertTableButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole, MouseHandler inputHandler) {
        super(imageIcon, x, y, width, height, inputButtonRole);
        handler = inputHandler;
    }

    public void makeTable() {
        if (GlobalVariables.cspptFrame.getCurrentSlideInt() != -1) {
            JFrame frame = new JFrame("Insert Table");
            frame.setBounds(100, 100, 300, 180);
            frame.setLayout(new BorderLayout());
            frame.setVisible(true);
            frame.setIconImage(new ImageIcon("src/main/resources/Icon/csppt_logo.png").getImage());


            JPanel panel1;
            panel1 = new JPanel();
            panel1.setLayout(null);

            frame.add(panel1);

            JLabel rowLabel, columnLabel;
            JSpinner rowSpinner, columnSpinner;
            JButton submit;

            rowLabel = new JLabel("Number of rows:");
            columnLabel = new JLabel("Number of columns:");
            rowLabel.setBounds(10, 10, 150, 20);
            columnLabel.setBounds(10, 30, 150, 20);

            rowSpinner = new JSpinner();
            columnSpinner = new JSpinner();
            rowSpinner.setBounds(150, 10, 100, 20);
            columnSpinner.setBounds(150, 35, 100, 20);

            submit = new JButton("Submit");
            submit.setBounds(80, 60, 100, 20);

            panel1.add(rowLabel);
            panel1.add(columnLabel);
            panel1.add(rowSpinner);
            panel1.add(columnSpinner);
            panel1.add(submit);


            frame.repaint();

            submit.addActionListener((new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    rows = (int) rowSpinner.getValue();
                    columns = (int) columnSpinner.getValue();

                    int width = columns * 50 ;
                    int height = rows * 30;

                    TableComponent table = new TableComponent(100, 100, width, height , handler, GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), rows, columns, GlobalVariables.cspptFrame.getNewLayer(), GlobalVariables.cspptFrame.getCurrentSlideInt());
                    GlobalVariables.handler.resetLastInputted();
                    GlobalVariables.cspptFrame.addToFrame(table);

                    frame.dispose();
                }
            }));
        }
    }

}
