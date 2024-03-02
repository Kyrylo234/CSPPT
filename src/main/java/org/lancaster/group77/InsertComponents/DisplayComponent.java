package org.lancaster.group77.InsertComponents;

import javafx.embed.swing.JFXPanel;
import org.lancaster.group77.DisplayComponents.DisplayComponentBase;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.InsertComponents.CodeSection.CodeSectionEditor;
import org.lancaster.group77.InsertComponents.Shapes.BaseShape;
import org.lancaster.group77.InsertComponents.UML.Lines.BaseLine;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;

public class DisplayComponent extends JFXPanel {
    private JLayeredPane frame;
    private CSPPTFile file;
    boolean visible = false;
    private String componentType;
    private DisplayComponentBase componentData;


    public DisplayComponent(int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame1, CSPPTFile file, String componentType) {
        super();
        this.file = file;
        this.componentType = componentType;
        frame = frame1;
        setBounds(x, y, width, height);
        setLayout(null);
        addMouseListener(listener);
        addMouseMotionListener(listener);
        setOpaque(false);
    }

    public void setVisible() {
        frame.moveToFront(this);
        setLayer(GlobalVariables.cspptFrame.getNewLayer());
        visible = true;
        repaint();
    }

    public void setInvisible() {
        visible = false;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setColor(new Color(GlobalVariables.THEME_COLOR));
        if (visible&& !(this instanceof BaseLine)) {
            //1
            graphics.fillOval(0, 0, 8, 8);
            //2
            graphics.fillOval(getWidth() - 8, 0, 8, 8);
            //5
            graphics.fillOval((getWidth() - 8) / 2, 0, 8, 8);
            //3
            graphics.fillOval(0, getHeight() - 8, 8, 8);
            //6
            graphics.fillOval(0, (getHeight() - 8) / 2, 8, 8);
            //4
            graphics.fillOval(getWidth() - 8, getHeight() - 8, 8, 8);
            //8
            graphics.fillOval((getWidth() - 8) / 2, getHeight() - 8, 8, 8);
            graphics.fillOval(getWidth() - 8, (getHeight() - 8) / 2, 8, 8);

            graphics.drawLine(4, 4, 4, getHeight() - 4);
            graphics.drawLine(4, 4, getWidth() - 4, 4);
            graphics.drawLine(4, getHeight() - 4, getWidth() - 4, getHeight() - 4);
            graphics.drawLine(getWidth() - 4, 4, getWidth() - 4, getHeight() - 4);
        }
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public DisplayComponentBase getComponentData() {
        return componentData;
    }

    public void setComponentData(DisplayComponentBase componentData) {
        this.componentData = componentData;
    }

    public void resizeComponent(int inputX, int inputY, int inputWidth, int inputHeight, int layer) {
        setBounds(inputX, inputY, inputWidth, inputHeight);
        componentData.setX((inputX/GlobalVariables.FRAME_SIZE_INDEX_X));
        componentData.setY((inputY/GlobalVariables.FRAME_SIZE_INDEX_Y));
        componentData.setWidth((inputWidth/GlobalVariables.FRAME_SIZE_INDEX_X));
        componentData.setHeight((inputHeight/GlobalVariables.FRAME_SIZE_INDEX_Y));
        componentData.setLayer(layer);
    }
    public void resizeComponent(int inputX, int inputY, int inputWidth, int inputHeight) {
        setBounds(inputX, inputY, inputWidth, inputHeight);
    }

    public void setLocation1(int inputX, int inputY, int layer) {
        setLocation(inputX, inputY);
        componentData.setX((inputX/GlobalVariables.FRAME_SIZE_INDEX_X));
        componentData.setY((inputY/GlobalVariables.FRAME_SIZE_INDEX_Y));
        componentData.setLayer(layer);
    }

    public void setLayer(int layer){
        componentData.setLayer(layer);
    }

}
