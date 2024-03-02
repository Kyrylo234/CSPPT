package org.lancaster.group77.InsertComponents.UML.Lines;

import java.awt.*;

public class ClickableBox {
    private Rectangle rectangle;
    private Color fillColor;
    private Color borderColor;
    private String text;
    //create a clickablebox
    public ClickableBox(int x, int y, int width, int height, Color fillColor, Color borderColor) {
        rectangle = new Rectangle(x, y, width, height);
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.text = "";

    }
    //set text for clickable box
    public void setText(String text){
        this.text = text;


    }
    public String getText(){return this.text; }


    public void setRectangle(int x, int y){
        rectangle.setLocation(x,y);
    }
    //draw the clickablebox
    public void draw(Graphics2D g2) {
        g2.setColor(fillColor);
        g2.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);

        g2.setColor(borderColor);
        g2.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g2.setColor(Color.MAGENTA); // Set font color
        Font font = new Font("Arial", Font.BOLD, 12);
        g2.setFont(font);
        FontMetrics fontMetrics = g2.getFontMetrics(font);
        int textX = rectangle.x + (rectangle.width - fontMetrics.stringWidth(text)) / 2;
        int textY = rectangle.y + (rectangle.height - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
        g2.drawString(text, textX, textY);

    }





}