package org.lancaster.group77.InsertComponents.UML.Lines;

import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.InsertComponents.UML.ClassDiagrams.ClassDiagram;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;

public class AggregationLine  extends BaseLine{

    private Point point1;
    private Point point2;

    private int leftBoxX, leftBoxY, rightBoxX, rightBoxY;


    private ClickableBox clickableBoxRight;
    private ClickableBox clickableBoxLeft;


    private ClassDiagram connectDiagram;
    private ClassDiagram mainDiagram;


    private  final OptionSelector optionSelector;
    private boolean flipped;



    public AggregationLine(Point point1, Point point2, ClassDiagram connectDiagram, ClassDiagram mainDiagram, int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame1, String type, CSPPTFile file , int layer, boolean flipped, int slideNumber,String lineType){
        super(point1,point2,connectDiagram, mainDiagram,x,y,width,height,listener,frame1,type,file,layer,flipped,slideNumber,lineType);
        this.flipped =flipped;
        this.connectDiagram = connectDiagram;
        this.point1 = point1;
        this.point2 = point2;
        this.mainDiagram = mainDiagram;

        String[] options = {"1", "0..1", "0..*", "1..*", "n", "0..n", "1..n"};


        optionSelector =  new OptionSelector(options, this);
        int boxWidth = 30;
        int boxHeight = 30;
        leftBoxX = point1.x - (boxWidth / 2);
        leftBoxY = point1.y - (boxHeight / 2);
        rightBoxX = point2.x - (boxWidth / 2);
        rightBoxY = point2.y - (boxHeight / 2);
        leftBoxX += (point2.x - point1.x) / 4;
        leftBoxY += (point2.y - point1.y) / 4;
        rightBoxX -= (point2.x - point1.x) / 4;
        rightBoxY -= (point2.y - point1.y) / 4;

        clickableBoxLeft = new ClickableBox(leftBoxX, leftBoxY, boxWidth, boxHeight, Color.DARK_GRAY, Color.magenta);
        clickableBoxRight = new ClickableBox(rightBoxX, rightBoxY, boxWidth, boxHeight, Color.DARK_GRAY, Color.magenta);



    }
    public void resizeLine(Point point1, Point point2){
        this.point1 = point1;
        this.point2 = point2;
        recalculate();
        clickableBoxLeft.setRectangle(leftBoxX,leftBoxY);
        clickableBoxRight.setRectangle(rightBoxX,rightBoxY);

        repaint();

    }

    public void setLeft(String option){
        clickableBoxLeft.setText(option);
        System.out.println(option);
        repaint();

    }
    public void setRight(String option){
        clickableBoxRight.setText(option);
        System.out.println(option);
        repaint();
    }


    public void recalculate(){

        int boxWidth = 30;
        int boxHeight = 30;
        leftBoxX = point1.x - (boxWidth / 2);
        leftBoxY = point1.y - (boxHeight / 2);

        rightBoxX = point2.x - (boxWidth / 2);
        rightBoxY = point2.y - (boxHeight / 2);
        leftBoxX += (point2.x - point1.x) / 4;
        leftBoxY += (point2.y - point1.y) / 4;
        rightBoxX -= (point2.x - point1.x) / 4;
        rightBoxY -= (point2.y - point1.y) / 4;
    }




    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw line from point1 to point2
        g.drawLine(point1.x, point1.y, point2.x, point2.y);

        // Draw kite shape at point2
        int kiteSize = 10; // Size of the kite shape
        int halfKiteSize = kiteSize / 2;

        // Calculate the angle of the line
        double angle = Math.atan2(point2.y - point1.y, point2.x - point1.x);

        // Calculate the coordinates of the kite shape
        int[] xPoints = {
                point2.x,
                point2.x - halfKiteSize,
                point2.x,
                point2.x + halfKiteSize
        };
        int[] yPoints = {
                point2.y,
                point2.y - halfKiteSize,
                point2.y + halfKiteSize,
                point2.y - halfKiteSize
        };

        // Rotate the kite shape
        for (int i = 0; i < xPoints.length; i++) {
            int tempX = xPoints[i] - point2.x;
            int tempY = yPoints[i] - point2.y;
            xPoints[i] = (int) (tempX * Math.cos(angle) - tempY * Math.sin(angle)) + point2.x;
            yPoints[i] = (int) (tempX * Math.sin(angle) + tempY * Math.cos(angle)) + point2.y;
        }

        g.drawPolygon(xPoints, yPoints, 4);

    }
}
