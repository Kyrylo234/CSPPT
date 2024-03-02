package org.lancaster.group77.DisplayComponents;

import java.util.Objects;


public class CSLine extends DisplayComponentBase {
    private String box_name_1;
    private String box_name_2;
    private String clickable_str_1;
    private String clickable_str_2;
    private int point_1_x;
    private int point_1_y;
    private int point_2_x;
    private int point_2_y;
    private String line_type;

    public CSLine() {
        super("cs_line");
    }

    public CSLine(double x, double y, int layer, double width1, double height1, String box_name_1, String box_name_2, String clickable_str_1, String clickable_str_2, int point_1_x, int point_1_y, int point_2_x, int point_2_y, String line_type) {
        super(x, y, layer, "cs_line", width1, height1);
        this.box_name_1 = box_name_1;
        this.box_name_2 = box_name_2;
        this.clickable_str_1 = clickable_str_1;
        this.clickable_str_2 = clickable_str_2;
        this.point_1_x = point_1_x;
        this.point_1_y = point_1_y;
        this.point_2_x = point_2_x;
        this.point_2_y = point_2_y;
        this.line_type = line_type;

    }

    public CSLine(double x, double y, int layer, int width1, int height1, String box_name_1, String box_name_2, String clickable_str_1, String clickable_str_2, int point_1_x, int point_1_y, int point_2_x, int point_2_y, String line_type) {
        super(x, y, layer, "cs_line", width1, height1);
        this.box_name_1 = box_name_1;
        this.box_name_2 = box_name_2;
        this.clickable_str_1 = clickable_str_1;
        this.clickable_str_2 = clickable_str_2;
        this.point_1_x = point_1_x;
        this.point_1_y = point_1_y;
        this.point_2_x = point_2_x;
        this.point_2_y = point_2_y;
        this.line_type = line_type;
    }

    public String getBox_name_1() {
        return box_name_1;
    }

    public void setBox_name_1(String box_name_1) {
        this.box_name_1 = box_name_1;
    }

    public String getBox_name_2() {
        return box_name_2;
    }

    public void setBox_name_2(String box_name_2) {
        this.box_name_2 = box_name_2;
    }

    public String getClickable_str_1() {
        return clickable_str_1;
    }

    public void setClickable_str_1(String clickable_str_1) {
        this.clickable_str_1 = clickable_str_1;
    }

    public String getClickable_str_2() {
        return clickable_str_2;
    }

    public void setClickable_str_2(String clickable_str_2) {
        this.clickable_str_2 = clickable_str_2;
    }

    public int getPoint_1_x() {
        return point_1_x;
    }

    public void setPoint_1_x(int point_1_x) {
        this.point_1_x = point_1_x;
    }

    public int getPoint_1_y() {
        return point_1_y;
    }

    public void setPoint_1_y(int point_1_y) {
        this.point_1_y = point_1_y;
    }

    public int getPoint_2_x() {
        return point_2_x;
    }

    public void setPoint_2_x(int point_2_x) {
        this.point_2_x = point_2_x;
    }

    public int getPoint_2_y() {
        return point_2_y;
    }

    public void setPoint_2_y(int point_2_y) {
        this.point_2_y = point_2_y;
    }

    public String getLine_type() {
        return line_type;
    }

    public void setLine_type(String line_type) {
        this.line_type = line_type;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        CSLine csLine = (CSLine) object;
        return getPoint_1_x() == csLine.getPoint_1_x() && getPoint_1_y() == csLine.getPoint_1_y() && getPoint_2_x() == csLine.getPoint_2_x() && getPoint_2_y() == csLine.getPoint_2_y() && Objects.equals(getBox_name_1(), csLine.getBox_name_1()) && Objects.equals(getBox_name_2(), csLine.getBox_name_2()) && Objects.equals(getClickable_str_1(), csLine.getClickable_str_1()) && Objects.equals(getClickable_str_2(), csLine.getClickable_str_2()) && Objects.equals(getLine_type(), csLine.getLine_type());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBox_name_1(), getBox_name_2(), getClickable_str_1(), getClickable_str_2(), getPoint_1_x(), getPoint_1_y(), getPoint_2_x(), getPoint_2_y(), getLine_type());
    }

}
