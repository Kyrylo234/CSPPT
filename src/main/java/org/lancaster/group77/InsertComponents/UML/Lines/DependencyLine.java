package org.lancaster.group77.InsertComponents.UML.Lines;

import org.lancaster.group77.DisplayComponents.CSLine;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.InsertComponents.UML.ClassDiagrams.ClassDiagram;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;

public class DependencyLine extends BaseLine{

    private Point point1;
    private Point point2;


    private int leftBoxX, leftBoxY, rightBoxX, rightBoxY;


    private ClickableBox clickableBoxRight;
    private ClickableBox clickableBoxLeft;


    private ClassDiagram connectDiagram;
    private ClassDiagram mainDiagram;


    private   OptionSelector optionSelector;

    private CSLine csline;

    private  String[] options = {"1", "0..1", "0..*", "1..*", "n", "0..n", "1..n"};

    private boolean flipped;
    //draw dependency line
    public DependencyLine(Point point1, Point point2, ClassDiagram connectDiagram, ClassDiagram mainDiagram, int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame1, String type, CSPPTFile file , int layer, boolean flipped, int slideNumber,String lineType){
        super(point1,point2,connectDiagram, mainDiagram,x,y,width,height,listener,frame1,type,file,layer,flipped,slideNumber,lineType);
        this.flipped =flipped;
        this.connectDiagram = connectDiagram;
        this.point1 = point1;
        this.point2 = point2;
        this.mainDiagram = mainDiagram;



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
    //load dependency line from file
    public DependencyLine(CSLine line, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int slideNum, boolean isOpeningFile) {
        super( line, listener, frame, file,slideNum, isOpeningFile);
        this.point1 = new Point(line.getPoint_1_x(), line.getPoint_1_y());
        this.point2 = new Point(line.getPoint_2_x(), line.getPoint_2_y());

        clickableBoxLeft =new ClickableBox(leftBoxX, leftBoxY, 30, 30, Color.DARK_GRAY, Color.magenta);
        clickableBoxRight =new ClickableBox(leftBoxX, leftBoxY, 30, 30, Color.DARK_GRAY, Color.magenta);
        clickableBoxLeft.setText(line.getClickable_str_1());
        clickableBoxRight.setText(line.getClickable_str_2());
        repaint();


    }

    //resize the line
    public void resizeLine(Point point1, Point point2){
        this.point1 = point1;
        this.point2 = point2;
        recalculate();
        clickableBoxLeft.setRectangle(leftBoxX,leftBoxY);
        clickableBoxRight.setRectangle(rightBoxX,rightBoxY);

        repaint();

    }
    //set the left clickable box
    public void setLeft(String option){
        clickableBoxLeft.setText(option);
        repaint();

    }
    //set the left clickable box
    public void setRight(String option){
        clickableBoxRight.setText(option);
        repaint();
    }

    //recalculate the clickable box location
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


    //draw line
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        float[] dashPattern = {5, 5};
        Stroke dashedStroke = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dashPattern, 0);
        g2.setStroke(dashedStroke);

        g2.setColor(Color.magenta);
        g2.drawLine(point1.x, point1.y, point2.x, point2.y);
        g2.setStroke(new BasicStroke(1));

        double angle = Math.atan2(point2.y - point1.y, point2.x - point1.x);

        int arrowLength = 10;
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];
        xPoints[0] = point2.x;
        yPoints[0] = point2.y;
        xPoints[1] = (int) (point2.x - arrowLength * Math.cos(angle - Math.PI / 6));
        yPoints[1] = (int) (point2.y - arrowLength * Math.sin(angle - Math.PI / 6));
        xPoints[2] = (int) (point2.x - arrowLength * Math.cos(angle + Math.PI / 6));
        yPoints[2] = (int) (point2.y - arrowLength * Math.sin(angle + Math.PI / 6));

        g2.setColor(Color.white);
        g2.fillPolygon(xPoints, yPoints, 3);
        clickableBoxRight.draw(g2);
        clickableBoxLeft.draw(g2);
    }


}
