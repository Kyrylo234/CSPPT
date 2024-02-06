package org.lancaster.group77.InsertComponents.Shapes;

import org.lancaster.group77.DisplayComponents.Shape;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.InsertComponents.DraggableComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;

public class Hexagon extends DraggableComponent {
    private JPanel panel;
    private Shape shape;

    public Hexagon(int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int layer1){
        super(x,y,width,height,listener,frame, file );
        panel = new ShapeBorder(this);
        add(panel);
        panel.addMouseListener(listener);
        panel.addMouseMotionListener(listener);
        panel.setOpaque(false);

        panel.setBounds(15, 15, width - 30, height - 30);
        shape = new Shape(x,y,width,height, layer1, "hexagon");
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



    public void resizeHexagon(int inputX, int inputY) {

        this.setSize(inputX-14, inputY-14);
        repaint();
        shape.setWidth(inputX);
        shape.setHeight(inputY);
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int colour = Integer.parseInt(shape.getColor());
        Color newColour = new Color(colour);
        g.setColor(newColour);
        int sides = 6;
        Polygon polygon = new Polygon();
        for (int i = 0; i < sides; i++) {
            double angle = i * 2 * Math.PI / sides - Math.PI / 2;
            int x = (int) (getWidth() / 2 + getWidth() / 2 * Math.cos(angle));
            int y = (int) (getHeight() / 2 + getHeight() / 2 * Math.sin(angle));
            polygon.addPoint(x, y);
        }

        g.fillPolygon(polygon);

        g.setColor(Color.BLACK);
        g.drawPolygon(polygon);
    }

    public void changeBackgroundColour(){
        JColorChooser colorChooser = new JColorChooser();
        Color  color = JColorChooser.showDialog(null, "select", Color.RED);
        String colour1 = String.valueOf(color.getRGB());
        shape.setColor(colour1);
        repaint();
    }

}
