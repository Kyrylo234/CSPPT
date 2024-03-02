package org.lancaster.group77.InsertComponents.Shapes;

import org.lancaster.group77.DisplayComponents.Shape;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.FileChooser;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.InsertComponents.DraggableComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;


public class Circle extends BaseShape {



    public Circle(int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int layer1) {
        super(x, y, width, height, listener, frame, file, "Circle", layer1, GlobalVariables.cspptFrame.getCurrentSlideInt());
    }

    public Circle(Shape shape1, MouseHandler listener, JLayeredPane frame, CSPPTFile file) {
        super(shape1, listener, frame, file, GlobalVariables.cspptFrame.getCurrentSlideInt());
    }

    public Circle(Shape shape1, MouseHandler listener, JLayeredPane frame, CSPPTFile file, boolean isOpeningFile) {
        super(shape1, listener, frame, file, GlobalVariables.cspptFrame.getCurrentSlideInt(), isOpeningFile);
    }

    public void resizePanel(int inputX, int inputY) {
        panel.setSize(inputX - 30, inputY - 30);
        repaint();
    }


    @Override
    public void resizeComponent(int inputX, int inputY) {
        this.setSize(inputX - 14, inputY - 14);
        repaint();
        super.resizeComponent(inputX,inputY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int colour = Integer.parseInt(shape.getColor());
        Color newColour = new Color(colour);
        g.setColor(newColour); // Set the color of the circle
        g.fillOval(7, 7, getWidth() - 15, getHeight() - 15); // Draw the circle
    }
}
