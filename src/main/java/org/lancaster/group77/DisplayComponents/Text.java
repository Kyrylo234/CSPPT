package org.lancaster.group77.DisplayComponents;

import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.Tools.GeneralTools;

public class Text extends DisplayComponentBase {
    private String text = "";
    private String font = "Arial";
    private String color = "0";
    private String backgroundColor = "0";
    private double font_size = 13; //default value
    private boolean bold = false;
    private boolean italic = false;
    private boolean underline = false;
    private boolean strikethrough = false;

    /**
     * Constructor
     * Create a new empty Text object
     */
    public Text() {
        super("text");
    }

    /**
     * Constructor
     *
     * @param text
     * @param font
     * @param color
     * @param font_size
     * @param bold
     * @param italic
     * @param underline
     * @param strikethrough
     * @param x
     * @param y
     */
    public Text(String text, String font, String color, double font_size, boolean bold, boolean italic, boolean underline, boolean strikethrough, int x, int y) {
        super(x, y, 0, "text",100,100);
        this.text = text;
        this.font = font;
        this.color = color;
        this.font_size = font_size;
        this.bold = bold;
        this.italic = italic;
        this.underline = underline;
        this.strikethrough = strikethrough;
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
}
