package org.lancaster.group77.DisplayComponents;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.Tools.GeneralTools;

import java.util.Objects;

public class MathChart extends DisplayComponentBase{
    private String graph_type;
    private String cate_array;
    private String value_array;
    private String chart_title;

    public MathChart() {
        super("mathchart");
    }

    public MathChart(double x, double y, int layer, double width1, double height1, String graph_type, String cate_array, String value_array, String chart_title) {
        super(x, y, layer, "mathchart", width1, height1);
        this.graph_type = graph_type;
        this.cate_array = cate_array;
        this.value_array = value_array;
        this.chart_title = chart_title;
    }

    public MathChart(double x, double y, int layer,int width1, int height1, String graph_type, String cate_array, String value_array, String chart_title) {
        super(x, y, layer, "mathchart", width1, height1);
        this.graph_type = graph_type;
        this.cate_array = cate_array;
        this.value_array = value_array;
        this.chart_title = chart_title;
    }

    public String getGraph_type() {
        return GeneralTools.getDataFromDuplicate(GlobalVariables.cspptFrame.getFile(), graph_type);

    }

    public void setGraph_type(String graph_type) {
        this.graph_type = graph_type;
    }

    public String getCate_array() {
        return GeneralTools.getDataFromDuplicate(GlobalVariables.cspptFrame.getFile(), cate_array);

    }

    public void setCate_array(String cate_array) {
        this.cate_array = cate_array;
    }

    public String getValue_array() {
        return GeneralTools.getDataFromDuplicate(GlobalVariables.cspptFrame.getFile(), value_array);

    }

    public void setValue_array(String value_array) {
        this.value_array = value_array;
    }

    public String getChart_title() {
        return GeneralTools.getDataFromDuplicate(GlobalVariables.cspptFrame.getFile(), chart_title);

    }

    public void setChart_title(String chart_title) {
        this.chart_title = chart_title;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        MathChart mathChart = (MathChart) object;
        return Objects.equals(getGraph_type(), mathChart.getGraph_type()) && Objects.equals(getCate_array(), mathChart.getCate_array()) && Objects.equals(getValue_array(), mathChart.getValue_array()) && Objects.equals(getChart_title(), mathChart.getChart_title());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGraph_type(), getCate_array(), getValue_array(), getChart_title());
    }
}
