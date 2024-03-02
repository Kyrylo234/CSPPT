package org.lancaster.group77.InsertComponents.UML.Lines;

import org.lancaster.group77.DisplayComponents.CSLine;
import org.lancaster.group77.DisplayComponents.Shape;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.InsertComponents.DisplayComponent;
import org.lancaster.group77.InsertComponents.UML.ClassDiagrams.ClassDiagram;
import org.lancaster.group77.InsertComponents.UML.LineBorder;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class BaseLine extends DisplayComponent {
    private Point point1;
    private Point point2;
    private boolean firstMenuShown = false;

    private JLayeredPane panel;
    private int leftBoxX, leftBoxY, rightBoxX, rightBoxY;


    private ClickableBox clickableBoxRight;
    private ClickableBox clickableBoxLeft;


    private ClassDiagram connectDiagram;
    private ClassDiagram mainDiagram;
    private JPopupMenu popupMenu;


    private boolean firstTime = true;
    private boolean flipped;

    private CSLine csline;

    private int layer;
    //create baseline
    public BaseLine(Point point1, Point point2, ClassDiagram connectDiagram, ClassDiagram mainDiagram, int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame1, String type, CSPPTFile file , int layer, boolean  flipped, int slideNumber,String lineType) {


        super(x, y, width, height, listener, frame1, file, "line");
        panel = new LineBorder(this);
        panel.setBorder(null);
        this.flipped = flipped;
        this.connectDiagram = connectDiagram;
        this.point1 = point1;
        this.point2 = point2;
        this.mainDiagram = mainDiagram;
        this.layer = layer;


        this.setOpaque(false);
        panel.setOpaque(false);
        add(panel);
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
        panel.setOpaque(false);
        panel.setLayout(null);
        String boxNumber1 = String.valueOf(mainDiagram.getBoxNumber());
        String boxNumber2 = String.valueOf(connectDiagram.getBoxNumber());
        initLine(point1, point2,clickableBoxLeft.getText(),clickableBoxRight.getText(),boxNumber1,boxNumber2, lineType,clickableBoxLeft.getText(),clickableBoxRight.getText());

        file.getSlides().get(slideNumber).addObject(csline);
    }

    //set the lines information so we can save it

    private  void initLine(Point point1 , Point point2, String text1, String text2, String BoxNumber1, String BoxNumber2,String lineType,String clickableBox1Text, String clickableBox2Text){
            csline = new CSLine();
            csline.setPoint_1_x(point1.x);
            csline.setPoint_1_y(point1.y);
            csline.setPoint_2_x(point2.x);
            csline.setPoint_2_y(point2.y);
            csline.setBox_name_1(BoxNumber1);
            csline.setBox_name_2(BoxNumber2);
            csline.setLine_type(lineType);
            csline.setClickable_str_1(clickableBox1Text);
            csline.setClickable_str_2(clickableBox2Text);
            this.setComponentData(csline);




    }
    //when a line is opened from file we use this to set it up for the presentation software
    //we add the lines to the corresponding class diagrams

    public BaseLine(CSLine line, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int slideNum, boolean isOpeningFile) {
        super((int) (line.getX()), (int) (line.getY() ), (int) (line.getWidth() ), (int) (line.getHeight() ), listener, frame, file, "CS_line");
        csline = line;
        this.setComponentData(csline);
        this.setBounds(0,0,1920,1080);
        point1 = new Point(csline.getPoint_1_x(),csline.getPoint_1_y());
        point2 = new Point(csline.getPoint_2_x(),csline.getPoint_2_y());
        for(DisplayComponent component: GlobalVariables.handler.getSlideManager().getSlideContent(GlobalVariables.handler.getSlideManager().getCurrentSlideInt())){
            if(component instanceof ClassDiagram){
                ClassDiagram classDiagram = (ClassDiagram) component;
                int boxNum = classDiagram.getBoxNum();
                int csBoxNum1 = Integer.parseInt(csline.getBox_name_1());
                int csBoxNum2 = Integer.parseInt(csline.getBox_name_2());

                if(csBoxNum1 == boxNum){
                    this.mainDiagram = (ClassDiagram) component;
                    mainDiagram.setLine(this);

                } else if (csBoxNum2 == boxNum) {
                    this.connectDiagram = (ClassDiagram) component;
                    connectDiagram.setLine(this);

                }
            }
        }

        this.layer = 5;

        clickableBoxLeft =new ClickableBox(leftBoxX, leftBoxY, 30, 30, Color.DARK_GRAY, Color.magenta);
        clickableBoxRight =new ClickableBox(leftBoxX, leftBoxY, 30, 30, Color.DARK_GRAY, Color.magenta);


        clickableBoxLeft.setText(csline.getClickable_str_1());
        clickableBoxRight.setText(csline.getClickable_str_2());
        // Line baseLine = new Line(point1, point2,mainDiagram,connectDiagram,0,0,1920,1080,listener,frame,"line",file,1,false,slideNum,csline.getClickable_str_1(),csline.getClickable_str_2());
        GlobalVariables.cspptFrame.addToFrame(this);
        repaint();
    }
    //get connectDiagram
    public ClassDiagram getConnection(){
        return connectDiagram;
    }
    //get main Diagram
    public ClassDiagram getMain(){
        return mainDiagram;
    }

    //set connecting diagram
    public ClassDiagram setConnection(){
        return connectDiagram;
    }
    //set main diagram
    public ClassDiagram setMain(){
        return mainDiagram;
    }
    //get layer
    public int getLayer(){return this.layer;}

    //resize the line
    public void resizeLine(Point point1, Point point2){
        this.point1 = point1;
        this.point2 = point2;
        recalculate();
        repaint();

        clickableBoxLeft.setRectangle(leftBoxX,leftBoxY);
        clickableBoxRight.setRectangle(rightBoxX,rightBoxY);


        repaint();


    }

    //set left clickableBox
    public void setLeft(String option){
        this.clickableBoxLeft.setText(option);
        repaint();
        csline.setClickable_str_2(option);

    }

    //set right clickableBox
    public void setRight(String option){
        this.clickableBoxRight.setText(option);

        repaint();
        csline.setClickable_str_2(option);
    }

    //set clickable box text to files info and set the text for clickable boxes
    public void setlines(String left , String right){
        csline.setClickable_str_1(left);
        csline.setClickable_str_2(right);
        setRight(right);
        setLeft(left);
    }


    //recalculate clickable box position
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

    //Always return false to make the component unclickable
    @Override
    public boolean contains(int x, int y) {
        return false;
    }

    public ClassDiagram getConnectDiagram(){return connectDiagram;}
    public void setClear(){this.setVisible(false);}


}
