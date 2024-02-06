package org.lancaster.group77.DisplayComponents;

public class CodeSection extends DisplayComponentBase{

    private String code = "";
    private double font_size = 13; //default value

    public CodeSection() {
        super("codesection");
    }

    public CodeSection(double x, double y, int layer, String code, double font_size) {
        super(x, y, layer, "codesection", 100, 100);
        this.code = code;
        this.font_size = font_size;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getFont_size() {
        return font_size;
    }

    public void setFont_size(double font_size) {
        this.font_size = font_size;
    }
}
