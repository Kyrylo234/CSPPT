package org.lancaster.group77.InsertComponents.Shapes;

import org.lancaster.group77.DisplayComponents.Shape;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.InsertComponents.DraggableComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;


public class Circle extends DraggableComponent {

    private JPanel panel;
    private Shape shape;


    public Circle(int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int layer1){
        super(x,y,width,height,listener,frame, file );
        panel = new ShapeBorder(this);
        add(panel);
        panel.addMouseListener(listener);
        panel.addMouseMotionListener(listener);
        panel.setOpaque(false);
        panel.setBounds(15, 15, width - 30, height - 30);
        shape = new Shape(x,y,width,height, layer1, "circle");
        file.getSlides().get(0).addObject(shape);
    }

    public Circle(Shape shape1, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int layer1){
        super((int)shape1.getX(), (int)shape1.getY(), (int)shape1.getWidth(), (int)shape1.getHeight(),listener,frame, file );
        panel = new ShapeBorder(this);
        add(panel);
        panel.addMouseListener(listener);
        panel.addMouseMotionListener(listener);
        panel.setOpaque(false);
        panel.setBounds(15, 15, (int)shape1.getWidth() - 30, (int)shape1.getHeight() - 30);
        shape = new Shape((int)shape1.getX(), (int)shape1.getY(), (int)shape1.getWidth(), (int)shape1.getHeight(), layer1, "circle");
        file.getSlides().get(0).addObject(shape);
    }

    public void resizePanel(int inputX, int inputY) {
        panel.setSize(inputX - 30, inputY - 30);
        repaint();
    }
    public void setLocation1(int x, int y){
        shape.setX(x);
        shape.setY(y);
    }



    public void resizeCircle(int inputX, int inputY) {
        this.setSize(inputX-14, inputY-14);
        shape.setWidth(inputX);
        shape.setHeight(inputY);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int colour = Integer.parseInt(shape.getColor());
        Color newColour = new Color(colour);
        g.setColor(newColour); // Set the color of the circle
        g.fillOval(0, 0, getWidth(), getHeight()); // Draw the circle
    }

    public void changeBackgroundColour(){
        JColorChooser colorChooser = new JColorChooser();
        Color  color = JColorChooser.showDialog(null, "select", Color.RED);
        String colour1 = String.valueOf(color.getRGB());
        shape.setColor(colour1);
        repaint();
    }
}
