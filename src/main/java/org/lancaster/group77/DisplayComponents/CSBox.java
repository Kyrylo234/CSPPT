package org.lancaster.group77.DisplayComponents;

import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.Tools.GeneralTools;

import java.util.Objects;


public class CSBox extends DisplayComponentBase {
    private String text = "";
    private String font = "Arial";
    private String color = "0";
    private String backgroundColor = "0";
    private double font_size = 13; //default value
    private boolean bold = false;
    private boolean italic = false;
    private boolean underline = false;
    private boolean strikethrough = false;
    private boolean right = false;
    private boolean left = true;
    private boolean center = false;
    private boolean bulletpoint = false;
    private String box_name = "";
    private float line_spacing = 0.5f;

    public CSBox() {
        super("cs_box");
    }

    public CSBox(int x, int y, int layer, int width1, int height1, String text, String font, String color, String backgroundColor, double font_size, boolean bold, boolean italic, boolean underline, boolean strikethrough, boolean right, boolean left, boolean center, boolean bulletpoint, String box_name, float line_spacing) {
        super(x, y, layer, "cs_box", width1, height1);
        this.text = text;
        this.font = font;
        this.color = color;
        this.backgroundColor = backgroundColor;
        this.font_size = font_size;
        this.bold = bold;
        this.italic = italic;
        this.underline = underline;
        this.strikethrough = strikethrough;
        this.right = right;
        this.left = left;
        this.center = center;
        this.bulletpoint = bulletpoint;
        this.box_name = box_name;
        this.line_spacing = line_spacing;
    }

    public CSBox(double x, double y, int layer, int width1, int height1, String text, String font, String color, String backgroundColor, double font_size, boolean bold, boolean italic, boolean underline, boolean strikethrough, boolean right, boolean left, boolean center, boolean bulletpoint, String box_name, float line_spacing) {
        super(x, y, layer, "cs_box", width1, height1);
        this.text = text;
        this.font = font;
        this.color = color;
        this.backgroundColor = backgroundColor;
        this.font_size = font_size;
        this.bold = bold;
        this.italic = italic;
        this.underline = underline;
        this.strikethrough = strikethrough;
        this.right = right;
        this.left = left;
        this.center = center;
        this.bulletpoint = bulletpoint;
        this.box_name = box_name;
        this.line_spacing = line_spacing;
    }

    public String getText(CSPPTFile cspptFile) {
        return GeneralTools.getDataFromDuplicate(cspptFile, text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFont(CSPPTFile cspptFile) {
        return GeneralTools.getDataFromDuplicate(cspptFile, font);
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getColor(CSPPTFile cspptFile) {
        return GeneralTools.getDataFromDuplicate(cspptFile, color);
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getFont_size() {
        return font_size;
    }

    public void setFont_size(double font_size) {
        this.font_size = font_size;
    }

    public boolean getBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean getBulletpoint() {
        return bulletpoint;
    }

    public void setBulletpoint(boolean bulletpoint) {
        this.bulletpoint = bulletpoint;
    }

    public boolean getItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public boolean getUnderline() {
        return underline;
    }

    public void setUnderline(boolean underline) {
        this.underline = underline;
    }
    public boolean getRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean getStrikethrough() {
        return strikethrough;
    }

    public void setStrikethrough(boolean strikethrough) {
        this.strikethrough = strikethrough;
    }

    public String getText() {
        return text;
    }

    public String getFont() {
        return font;
    }

    public String getColor() {
        return color;
    }

    public void setBackgroundColor(String color1) {
        this.backgroundColor = color1;
    }
    public String getBackgroundColor() {
        return backgroundColor;
    }

    public boolean getLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean getCenter() {
        return center;
    }

    public void setCenter(boolean center) {
        this.center = center;
    }

    public float getLine_spacing() {
        return line_spacing;
    }

    public void setLine_spacing(float line_spacing) {
        this.line_spacing = line_spacing;
    }

    public String getBox_name() {
        return box_name;
    }

    public void setBox_name(String box_name) {
        this.box_name = box_name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        CSBox csBox = (CSBox) object;
        return Double.compare(getFont_size(), csBox.getFont_size()) == 0 && getBold() == csBox.getBold() && getItalic() == csBox.getItalic() && getUnderline() == csBox.getUnderline() && getStrikethrough() == csBox.getStrikethrough() && getRight() == csBox.getRight() && getLeft() == csBox.getLeft() && getCenter() == csBox.getCenter() && bulletpoint == csBox.bulletpoint && Float.compare(getLine_spacing(), csBox.getLine_spacing()) == 0 && Objects.equals(getText(), csBox.getText()) && Objects.equals(getFont(), csBox.getFont()) && Objects.equals(getColor(), csBox.getColor()) && Objects.equals(getBackgroundColor(), csBox.getBackgroundColor()) && Objects.equals(getBox_name(), csBox.getBox_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getText(), getFont(), getColor(), getBackgroundColor(), getFont_size(), getBold(), getItalic(), getUnderline(), getStrikethrough(), getRight(), getLeft(), getCenter(), bulletpoint, getBox_name(), getLine_spacing());
    }



}
