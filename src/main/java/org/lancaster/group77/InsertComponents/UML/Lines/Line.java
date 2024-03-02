package org.lancaster.group77.InsertComponents.UML.Lines;

import javafx.embed.swing.JFXPanel;
import org.lancaster.group77.DisplayComponents.CSLine;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.InsertComponents.DisplayComponent;
import org.lancaster.group77.InsertComponents.DraggableComponent;
import org.lancaster.group77.InsertComponents.UML.ClassDiagrams.ClassDiagram;
import org.lancaster.group77.InsertComponents.UML.LineBorder;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class Line extends BaseLine {

    private Point point1;
    private Point point2;

    private int leftBoxX, leftBoxY, rightBoxX, rightBoxY;


    private ClickableBox clickableBoxRight;
    private ClickableBox clickableBoxLeft;


    private ClassDiagram connectDiagram;
    private ClassDiagram mainDiagram;


    private   OptionSelector optionSelector;


    private boolean flipped;

    private int layer;

    private CSLine csline;
    private String[] options = {"1", "0..1", "0..*", "1..*", "n", "0..n", "1..n"};


    //create a line by drawing it on a panel
    public Line(Point point1, Point point2, ClassDiagram connectDiagram, ClassDiagram mainDiagram, int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame1, String type, CSPPTFile file , int layer, boolean flipped, int slideNumber,String lineType){
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



    //creating line when opening file
    public Line(CSLine line, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int slideNum, boolean isOpeningFile) {
        super( line, listener, frame, file,slideNum, isOpeningFile);
        this.point1 = new Point(line.getPoint_1_x(), line.getPoint_1_y());
        this.point2 = new Point(line.getPoint_2_x(), line.getPoint_2_y());
        clickableBoxLeft =new ClickableBox(leftBoxX, leftBoxY, 30, 30, Color.DARK_GRAY, Color.magenta);
        clickableBoxRight =new ClickableBox(leftBoxX, leftBoxY, 30, 30, Color.DARK_GRAY, Color.magenta);
        clickableBoxLeft.setText(line.getClickable_str_1());
        clickableBoxRight.setText(line.getClickable_str_2());
        repaint();

    }

    //resize lien and clickable box
    public void resizeLine(Point point1, Point point2){
        this.point1 = point1;
        this.point2 = point2;
        recalculate();
        clickableBoxLeft.setRectangle(leftBoxX,leftBoxY);
        clickableBoxRight.setRectangle(rightBoxX,rightBoxY);

        repaint();

    }
    //set text for clickablebox
    public void setLeft(String option){
        clickableBoxLeft.setText(option);
        repaint();
        if(csline != null) {
            csline.setClickable_str_1(option);
        }
    }
    //set text for clickablebox
    public void setRight(String option){
        clickableBoxRight.setText(option);
        repaint();

        if(csline != null) {

            csline.setClickable_str_2(option);
        }
    }

    //recalculate to get the position for clickable box
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

    //get layer line is on
    public int getLayer(){return this.layer;}





    //paint the line

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        Line2D lin = new Line2D.Float(point1.x, point1.y, point2.x, point2.y);
        Line2D lin1 = new Line2D.Float(20,20,85,27);
        g2.setColor(Color.magenta);
        g2.draw(lin);


        clickableBoxRight.draw(g2);
       clickableBoxLeft.draw(g2);
    }
}
