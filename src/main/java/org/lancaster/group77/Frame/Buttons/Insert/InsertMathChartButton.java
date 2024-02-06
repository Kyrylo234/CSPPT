package org.lancaster.group77.Frame.Buttons.Insert;

import javafx.scene.chart.PieChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.lancaster.group77.Frame.Bars.InsertBar;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.ChartComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.util.Objects;
import org.jfree.chart.*;
import org.jfree.data.*;


public class InsertMathChartButton extends IconButton {

    private JLayeredPane frame;
    private MouseHandler handler;
    private String graphType;
    private int categories;
    JFreeChart chart;
    private String chartTitle;
    private InsertBar bar;
    public InsertMathChartButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole, MouseHandler inputHandler, InsertBar bar){
        super(imageIcon,x,y,width,height,inputButtonRole);
        this.bar = bar;
        handler = inputHandler;
    }

    public void createDataset(){
        JFrame frame1 = new JFrame();
        frame1.setBounds(100,100,300,500);
        frame1.setLayout(new GridLayout(3,0));
        JPanel panel = new JPanel();
        //panel.setBounds(10,10,300,100);
        panel.setLayout(null);

        frame1.setVisible(true);
        frame1.add(panel);

        JLabel graphTypeLabel;
        graphTypeLabel = new JLabel("Type of graph:");
        graphTypeLabel.setBounds(10,10,150,20);


        JComboBox<String> graphTypeInput;
        String[] graphTypes = {"Pie Chart", "Bar Chart", "Line Graph"};
        graphTypeInput = new JComboBox<>(graphTypes);
        graphTypeInput.setBounds(150,10,100,20);

        JLabel categoryNumLabel;
        categoryNumLabel = new JLabel("Number of categories:");
        categoryNumLabel.setBounds(10,30,150,20);

        JSpinner categoryNum;
        categoryNum = new JSpinner();
        categoryNum.setBounds(200,32,50,20);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(80,60,100,20);

        panel.add(graphTypeLabel);
        panel.add(graphTypeInput);
        panel.add(categoryNumLabel);
        panel.add(categoryNum);
        panel.add(submitButton);

        JPanel panel1 = new JPanel();
        frame1.add(panel1);

        JPanel panel2 = new JPanel();
        frame1.add(panel2);

        JButton createChartButton = new JButton("Create Chart");
        createChartButton.setBounds(50,100,100,20);
        createChartButton.setVisible(false);
        panel2.add(createChartButton);
        panel2.revalidate();
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the user input from the spinners
                graphType = (String) graphTypeInput.getSelectedItem();
                categories = (int) categoryNum.getValue();

                createChartButton.setVisible(true);

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
                }
                panel1.revalidate();

                createChartButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String[] categoriesArr = new String[categories];
                        int[] valuesArr = new int[categories];
                        chartTitle = (String) titleInput.getText();

                        for(int i = 4; i < panel1.getComponentCount();i+=2){
                            JTextField categoryField = (JTextField) panel1.getComponent(i);
                            JTextField valueField = (JTextField) panel1.getComponent(i+1);

                            categoriesArr[i/2-2] = categoryField.getText();
                            valuesArr[i/2-2] = Integer.parseInt(valueField.getText());
                        }

                        createChart(categoriesArr,valuesArr);
                        frame1.dispose();
                    }
                });
            }
        });




    }

    public void createChart(String[] cateArray, int[] valueArray ){

        switch(graphType){
            case "Pie Chart":
                DefaultPieDataset dataset = new DefaultPieDataset();
                for (int i = 0; i < cateArray.length;i++){
                    dataset.setValue(cateArray[i],valueArray[i]);
                }

                chart = ChartFactory.createPieChart(chartTitle,dataset,true,true,false);
                ChartPanel chartPanel = new ChartPanel(chart);
                ChartComponent chartComponent = new ChartComponent(chartPanel,100,100,300,200,handler,bar.getFrame().getPane(),bar.getFrame().getFile());
                bar.getFrame().getPane().add(chartComponent);
                bar.getFrame().getPane().moveToFront(chartComponent);
                bar.getFrame().getPane().repaint();
                break;
            case "Bar Chart":
                DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
                for (int i = 0; i < cateArray.length; i++){
                    dataset1.setValue(valueArray[i]," ",cateArray[i]);
                }

                chart = ChartFactory.createBarChart(chartTitle,"X-Axis","Y-Axis",dataset1);
                ChartPanel chartPanel1 = new ChartPanel(chart);
                ChartComponent chartComponent1 = new ChartComponent(chartPanel1,100,100,300,200,handler,bar.getFrame().getPane(),bar.getFrame().getFile());
                bar.getFrame().getPane().add(chartComponent1);
                bar.getFrame().getPane().moveToFront(chartComponent1);
                bar.getFrame().getPane().repaint();
                break;

            case "Line Graph":
                DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
                for (int i = 0; i < cateArray.length; i++){
                    dataset2.setValue(valueArray[i],"Line",cateArray[i]);
                }

                chart = ChartFactory.createLineChart(chartTitle,"X-Axis", "Y-Axis",dataset2);
                ChartPanel chartPanel2 = new ChartPanel(chart);
                ChartComponent chartComponent2 = new ChartComponent(chartPanel2,100,100,300,200,handler,bar.getFrame().getPane(),bar.getFrame().getFile());
                bar.getFrame().getPane().add(chartComponent2);
                bar.getFrame().getPane().moveToFront(chartComponent2);
                bar.getFrame().getPane().repaint();
                break;

        }
    }
    public void addFrame(JLayeredPane inputFrame){
        frame = inputFrame;
    }
}