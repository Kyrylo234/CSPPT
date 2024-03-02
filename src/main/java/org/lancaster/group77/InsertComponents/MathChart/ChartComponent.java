package org.lancaster.group77.InsertComponents.MathChart;

import org.jfree.chart.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.lancaster.group77.DisplayComponents.MathChart;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.Tools.GeneralTools;
import org.lancaster.group77.InsertComponents.DraggableComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;

public class ChartComponent extends DraggableComponent {

    private Point initialClick;
    private CSPPTFile file;
    private JLayeredPane frame;
    private boolean resizable = false;
    private JFreeChart chart;
    private ExtendedChartPanel chartPanel;
    private MathChart mathChartData;

    public ChartComponent(String graphType, String[] cateArray, int[] valueArray, String chartTitle, int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int layer, int slideNum) {
        super(x, y, width, height, listener, frame, file, "ChartComponent");

        this.file = file;
        this.frame = frame;
        setBounds(x, y, width, height);

        setLayout(null);
        setOpaque(false);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        chart = createChart(graphType, cateArray, valueArray, chartTitle);
        chartPanel = new ExtendedChartPanel(chart, this);
        chartPanel.setBounds(7, 7, width - 14, height - 14);

        chartPanel.addMouseListener(listener);
        chartPanel.addMouseMotionListener(listener);
        add(chartPanel);
        DraggableComponent.initRightClickMenu(chartPanel);


        initChartData(x, y, layer, graphType, cateArray, valueArray, chartTitle);
        file.getSlides().get(slideNum).addObject(mathChartData);
    }

    public ChartComponent(MathChart mathChart, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int slideNum, boolean isOpeningFile) {
        super((int) (mathChart.getX() * GlobalVariables.FRAME_SIZE_INDEX_X), (int) (mathChart.getY() * GlobalVariables.FRAME_SIZE_INDEX_Y), (int) ((mathChart.getWidth()) * GlobalVariables.FRAME_SIZE_INDEX_X), (int) ((mathChart.getHeight()) * GlobalVariables.FRAME_SIZE_INDEX_Y), listener, frame, file, "ChartComponent");

        this.file = file;
        this.frame = frame;
        setBounds((int) (mathChart.getX() * GlobalVariables.FRAME_SIZE_INDEX_X), (int) (mathChart.getY() * GlobalVariables.FRAME_SIZE_INDEX_Y), (int) ((mathChart.getWidth()) * GlobalVariables.FRAME_SIZE_INDEX_X), (int) ((mathChart.getHeight()) * GlobalVariables.FRAME_SIZE_INDEX_Y));

        setLayout(null);
        setOpaque(false);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        chart = createChart(mathChart.getGraph_type(), GeneralTools.stringToStringArray(mathChart.getCate_array()), GeneralTools.stringToIntArray(mathChart.getValue_array()), mathChart.getChart_title());
        chartPanel = new ExtendedChartPanel(chart, this);
        chartPanel.setBounds(7, 7, (int) ((mathChart.getWidth()) * GlobalVariables.FRAME_SIZE_INDEX_X) - 14, (int) ((mathChart.getHeight()) * GlobalVariables.FRAME_SIZE_INDEX_Y) - 14);

        chartPanel.addMouseListener(listener);
        chartPanel.addMouseMotionListener(listener);
        add(chartPanel);

        mathChartData = mathChart;
        this.setComponentData(mathChartData);
        if (!isOpeningFile) {
            file.getSlides().get(slideNum).addObject(mathChartData);
        }
    }

    private JFreeChart createChart(String graphType, String[] cateArray, int[] valueArray, String chartTitle) {
        switch (graphType) {
            case "Pie Chart":
                DefaultPieDataset dataset = new DefaultPieDataset();
                for (int i = 0; i < cateArray.length; i++) {
                    dataset.setValue(cateArray[i], valueArray[i]);
                }
                return ChartFactory.createPieChart(chartTitle, dataset, true, true, false);
            case "Bar Chart":
                DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
                for (int i = 0; i < cateArray.length; i++) {
                    dataset1.setValue(valueArray[i], " ", cateArray[i]);
                }
                return ChartFactory.createBarChart(chartTitle, "X-Axis", "Y-Axis", dataset1);
            case "Line Graph":
                DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
                for (int i = 0; i < cateArray.length; i++) {
                    dataset2.setValue(valueArray[i], "Line", cateArray[i]);
                }
                return ChartFactory.createLineChart(chartTitle, "X-Axis", "Y-Axis", dataset2);
            default:
                return null;
        }
    }

    private void initChartData(int x, int y, int layer, String graphType, String[] cateArray, int[] valueArray, String chartTitle) {
        mathChartData = new MathChart(x / GlobalVariables.FRAME_SIZE_INDEX_X, y / GlobalVariables.FRAME_SIZE_INDEX_Y, layer, getWidth() / GlobalVariables.FRAME_SIZE_INDEX_X, getHeight() / GlobalVariables.FRAME_SIZE_INDEX_Y, graphType, Arrays.toString(cateArray), Arrays.toString(valueArray), chartTitle);
        this.setComponentData(mathChartData);
    }

    @Override
    public void resizeComponent(int inputX, int inputY, int inputWidth, int inputHeight, int layer) {
        chartPanel.setSize(inputWidth - 14, inputHeight - 14);
        super.resizeComponent(inputX, inputY, inputWidth, inputHeight, layer);
    }

    @Override
    public void resizeComponent(int inputX, int inputY, int inputWidth, int inputHeight) {
        chartPanel.setSize(inputWidth - 14, inputHeight - 14);
        super.resizeComponent(inputX, inputY, inputWidth, inputHeight);
    }
}
