package org.lancaster.group77.DisplayComponents;

import org.lancaster.group77.FileSystem.GlobalVariables;

import java.util.Objects;

public class Shape extends DisplayComponentBase {
    private String shape_name;
    private String color = "0";


    /**
     * Constructor
     * Create a new empty object
     */
    public Shape() {
        super("shape");
    }

    public Shape(double x, double y, double width, double height, int layer, String shapeName1) {
        super(x/ GlobalVariables.FRAME_SIZE_INDEX_X, y/GlobalVariables.FRAME_SIZE_INDEX_Y, layer, "shape",height/GlobalVariables.FRAME_SIZE_INDEX_X,width/GlobalVariables.FRAME_SIZE_INDEX_Y);
        shape_name = shapeName1;
    }


    public String getShape_name() {
        return shape_name;
    }

    public void setShape_name(String shape_name) {
        this.shape_name = shape_name;
    }

    public void setColor(String colour){
        this.color = colour;
    }

    public String getColor(){
        return color;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Shape shape = (Shape) object;
        return Objects.equals(getShape_name(), shape.getShape_name()) && Objects.equals(getColor(), shape.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getShape_name(), getColor());
    }
}
