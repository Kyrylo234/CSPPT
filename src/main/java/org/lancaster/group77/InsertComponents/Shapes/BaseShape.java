package org.lancaster.group77.InsertComponents.Shapes;

import org.lancaster.group77.DisplayComponents.Shape;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.FileChooser;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.InsertComponents.DraggableComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;

public class BaseShape extends DraggableComponent {
    public Shape shape;
    public JPanel panel;

    public BaseShape(int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame1, CSPPTFile file, String componentType, int layer, int slideNum) {
        super(x, y, width, height, listener, frame1, file, componentType);
        panel = new ShapeBorder(this);
        add(panel);
        panel.addMouseListener(listener);
        panel.addMouseMotionListener(listener);
        panel.setOpaque(false);
        panel.setBounds(15, 15, width - 30, height - 30);
        DraggableComponent.initRightClickMenu(panel);
        shape = new Shape(x,y,width,height, layer, componentType.toLowerCase());
        shape.setColor(String.valueOf(Color.MAGENTA.getRGB()));
        this.setComponentData(shape);
        file.getSlides().get(slideNum).addObject(shape);
    }

    public BaseShape(Shape shape, MouseHandler listener, JLayeredPane frame, CSPPTFile file,int slideNum) {
        super((int) shape.getX(), (int) shape.getY(), (int) shape.getWidth(), (int) shape.getHeight(), listener, frame, file, shape.getShape_name());
        this.shape = shape;
        this.setComponentData(shape);

        panel = new ShapeBorder(this);
        add(panel);
        panel.addMouseListener(listener);
        panel.addMouseMotionListener(listener);
        panel.setOpaque(false);
        DraggableComponent.initRightClickMenu(panel);


        panel.setBounds(15, 15, (int) (shape.getWidth() - 30), (int) (shape.getHeight() - 30));
        file.getSlides().get(slideNum).addObject(shape);
    }

    public BaseShape(Shape shape, MouseHandler listener, JLayeredPane frame, CSPPTFile file,int slideNum,boolean isOpeningFile) {
        super((int) (shape.getX()* GlobalVariables.FRAME_SIZE_INDEX_X), (int) (shape.getY()* GlobalVariables.FRAME_SIZE_INDEX_Y), (int) (shape.getWidth()* GlobalVariables.FRAME_SIZE_INDEX_X), (int) (shape.getHeight()* GlobalVariables.FRAME_SIZE_INDEX_Y), listener, frame, file, shape.getShape_name());
        this.shape = shape;
        this.setComponentData(shape);

        panel = new ShapeBorder(this);
        add(panel);
        panel.addMouseListener(listener);
        panel.addMouseMotionListener(listener);
        panel.setOpaque(false);
        DraggableComponent.initRightClickMenu(panel);
        

        panel.setBounds(15, 15, (int) (shape.getWidth()* GlobalVariables.FRAME_SIZE_INDEX_X - 30), (int) (shape.getHeight()* GlobalVariables.FRAME_SIZE_INDEX_Y - 30));

        if(!isOpeningFile)
            file.getSlides().get(slideNum).addObject(shape);
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void changeBackgroundColour() {
        Color color = JColorChooser.showDialog(FileChooser.getTempFrame(), "Select Colour", Color.RED);
        if(color == null){
            return;
        }
        String colour1 = String.valueOf(color.getRGB());
        shape.setColor(colour1);
        repaint();
    }

    public void setLocation1(int x, int y) {
        shape.setX(x);
        shape.setY(y);
    }

    public void resizeComponent(int inputX, int inputY) {
        shape.setWidth(inputX);
        shape.setHeight(inputY);
        repaint();
    }
    public void resizeComponent(int inputX, int inputY, int inputWidth, int inputHeight, int layer) {
        panel.setSize(inputWidth-30, inputHeight-30);
        shape.setWidth(inputWidth);
        shape.setHeight(inputHeight);
        super.resizeComponent(inputX,inputY,inputWidth,inputHeight,layer);
    }

}
