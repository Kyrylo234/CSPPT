package org.lancaster.group77.InsertComponents;

import java.awt.*;
import javax.swing.*;

import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;


public class DraggableComponent extends JPanel{
    private JLayeredPane frame;
    private CSPPTFile file;
    boolean visible = false;

    public DraggableComponent(int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame1,CSPPTFile file) {
        super();
        this.file=file;
        frame = frame1;
        setBounds(x,y,width,height);
        setLayout(null);
        addMouseListener(listener);
        addMouseMotionListener(listener);
        setOpaque(false);
    }

    public void setVisible(){
        frame.moveToFront(this);
        visible=true;
        repaint();
    }

    public void setInvisible(){
        setBorder(null);
        visible= false;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        if(visible){
            //1
            graphics.fillOval(0, 0, 8, 8);
            //2
            graphics.fillOval(getWidth()-8, 0, 8, 8);
            //5
            graphics.fillOval((getWidth()-8)/2, 0, 8, 8);
            //3
            graphics.fillOval(0, getHeight()-8, 8, 8);
            //6
            graphics.fillOval(0, (getHeight()-8)/2, 8, 8);
            //4
            graphics.fillOval(getWidth()-8, getHeight()-8, 8, 8);
            //8
            graphics.fillOval((getWidth()-8)/2, getHeight()-8, 8, 8);
            graphics.fillOval(getWidth()-8, (getHeight()-8)/2, 8, 8);

            graphics.drawLine(4,4,4,getHeight()-4);
            graphics.drawLine(4,4,getWidth()-4,4);
            graphics.drawLine(4,getHeight()-4,getWidth()-4,getHeight()-4);
            graphics.drawLine(getWidth()-4,4,getWidth()-4,getHeight()-4);
        }
    }
}

