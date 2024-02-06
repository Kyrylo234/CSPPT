package org.lancaster.group77.DisplayComponents;

public class Shape extends DisplayComponentBase {
    private String shapeName;
    private String color = "0";


    /**
     * Constructor
     * Create a new empty object
     */
    public Shape() {
        super("shape");
    }

    public Shape(int x, int y, int width, int height, int layer, String shapeName1) {
        super(x, y, layer, "shape",height,width);
        shapeName = shapeName1;
    }

    public String getShapeName(){
        return shapeName;
    }

    public void setColor(String colour){
        this.color = colour;
    }

    public String getColor(){
        return color;
    }
}
