package org.lancaster.group77.Frame.Buttons.Insert;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.MathChart.ChartComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.chart.*;


public class InsertMathChartButton extends IconButton {
    private MouseHandler handler;
    private String graphType;
    private int categories;
    private JFreeChart chart;
    private String chartTitle;

    public InsertMathChartButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole, MouseHandler inputHandler) {
        super(imageIcon, x, y, width, height, inputButtonRole);
        handler = inputHandler;
    }

    public void createDataset() {
        if (GlobalVariables.cspptFrame.getCurrentSlideInt() != -1) {
            JFrame frame1 = new JFrame("Insert Chart");
            frame1.setBounds(100, 100, 300, 500);
            frame1.setLayout(new GridLayout(3, 0));
            frame1.setIconImage(new ImageIcon("src/main/resources/Icon/csppt_logo.png").getImage());

            JPanel panel = new JPanel();
            //panel.setBounds(10,10,300,100);
            panel.setLayout(null);

            frame1.setVisible(true);
            frame1.add(panel);

            JLabel graphTypeLabel;
            graphTypeLabel = new JLabel("Type of graph:");
            graphTypeLabel.setBounds(10, 10, 150, 20);


            JComboBox<String> graphTypeInput;
            String[] graphTypes = {"Pie Chart", "Bar Chart", "Line Graph"};
            graphTypeInput = new JComboBox<>(graphTypes);
            graphTypeInput.setBounds(150, 10, 100, 20);

            JLabel categoryNumLabel;
            categoryNumLabel = new JLabel("Number of categories:");
            categoryNumLabel.setBounds(10, 30, 150, 20);

            JSpinner categoryNum;
            categoryNum = new JSpinner();
            categoryNum.setBounds(200, 32, 50, 20);

            JButton submitButton = new JButton("Submit");
            submitButton.setBounds(80, 60, 100, 20);

            panel.add(graphTypeLabel);
            panel.add(graphTypeInput);
            panel.add(categoryNumLabel);
            panel.add(categoryNum);
            panel.add(submitButton);

            JPanel panel1 = new JPanel();
            frame1.add(panel1);

            JPanel panel2 = new JPanel();
            frame1.add(panel2);


            JButton createChartButton, cancelButton;
            createChartButton = new JButton("Create Chart");
            createChartButton.setBounds(50, 100, 100, 20);
            createChartButton.setVisible(false);

            cancelButton = new JButton(("Cancel"));
            cancelButton.setBounds(50, 130, 100, 20);
            cancelButton.setVisible(false);


            panel2.add(createChartButton);
            panel2.add(cancelButton);
            panel2.revalidate();

            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame1.dispose();
                }
            });
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Retrieve the user input from the spinners

                    submitButton.setVisible(false);

                    graphType = (String) graphTypeInput.getSelectedItem();
                    categories = (int) categoryNum.getValue();

                    createChartButton.setVisible(true);
                    cancelButton.setVisible(true);

                    panel1.setLayout(new GridLayout(categories + 3, 2));

                    JLabel chartTitleLabel = new JLabel("Title");
                    JTextField titleInput = new JTextField();
                    panel1.add(chartTitleLabel);
                    panel1.add(titleInput);


                    JLabel categoryLabel, valueLabel;
                    categoryLabel = new JLabel("Categories");
                    valueLabel = new JLabel("Values");

                    panel1.add(categoryLabel);
                    panel1.add(valueLabel);

                    for (int i = 0; i < categories * 2; i++) {
                        JTextField textField = new JTextField();
                        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        panel1.add(textField);
                        if ((i & 1) == 1) {
                            ((AbstractDocument) textField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
                        }
                    }
                    panel1.revalidate();

                    createChartButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String[] categoriesArr = new String[categories];
                            int[] valuesArr = new int[categories];
                            chartTitle = (String) titleInput.getText();

                            for (int i = 4; i < panel1.getComponentCount(); i += 2) {
                                JTextField categoryField = (JTextField) panel1.getComponent(i);
                                JTextField valueField = (JTextField) panel1.getComponent(i + 1);

                                categoriesArr[i / 2 - 2] = categoryField.getText();
                                valuesArr[i / 2 - 2] = Integer.parseInt(valueField.getText());
                            }

                            createChart(categoriesArr, valuesArr);
                            frame1.dispose();
                        }
                    });
                }
            });
        }
    }

    private static class NumericDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException, BadLocationException {
            if (string != null && string.matches("\\d*")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text != null && text.matches("\\d*")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }

    public void createChart(String[] cateArray, int[] valueArray) {
        ChartComponent chartComponent = new ChartComponent(graphType, cateArray, valueArray, chartTitle, 100, 100, 300, 200, handler, GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getNewLayer(), GlobalVariables.cspptFrame.getCurrentSlideInt());
        GlobalVariables.handler.resetLastInputted();
        GlobalVariables.cspptFrame.addToFrame(chartComponent);
    }
}