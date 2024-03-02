package org.lancaster.group77.InsertComponents.MathChart;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class ExtendedChartPanel extends ChartPanel {
    private ChartComponent chartComponent;

    public ExtendedChartPanel(JFreeChart chart, ChartComponent component) {
        super(chart);
        chartComponent = component;
    }

    public ChartComponent getChartComponent() {
        return chartComponent;
    }
}
