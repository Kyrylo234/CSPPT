package org.lancaster.group77.InsertComponents;

import org.jfree.chart.*;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ChartComponent extends JPanel implements MouseListener, MouseMotionListener {

    private Point initialClick;
    private CSPPTFile file;
    private JLayeredPane frame;
    private boolean resizable = false;

    public ChartComponent(ChartPanel chartPanel, int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame, CSPPTFile file){
        //super(x,y,width,height,listener,frame,file);

        this.file=file;
        this.frame = frame;
        setBounds(x,y,width,height);

        setLayout(new BorderLayout());
        setOpaque(false);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //chartPanel.setBounds(7,7,width-14,height-14);
        chartPanel.addMouseListener(this);
        chartPanel.addMouseMotionListener(this);
        add(chartPanel);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        initialClick = e.getPoint();

        Point point = e.getPoint();

        if(point.x >= getWidth() - 10 && point.y >= getHeight() - 10){
            this.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
            resizable = true;
        }else{
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            resizable = false;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(resizable){
            Point currentPoint = e.getPoint();
            int deltaX = currentPoint.x - initialClick.x;
            int deltaY = currentPoint.y - initialClick.y;

            int newWidth = getWidth() + deltaX/50;
            int newHeight = getHeight() + deltaY/50;

            setBounds(getX(),getY(),newWidth,newHeight);
            revalidate();
            repaint();
        }else{

        Point currentPoint = e.getPoint();
        int deltaX = currentPoint.x - initialClick.x;
        int deltaY = currentPoint.y - initialClick.y;
        setLocation(getX() + deltaX, getY() + deltaY);}

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
