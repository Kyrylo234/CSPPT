package org.lancaster.group77.InsertComponents.UML.ClassDiagrams;

import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.InsertComponents.TextBox.TextBox;
import org.lancaster.group77.InsertComponents.UML.UMLBorder;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;

public class Class extends TextBox  {

    private UMLBorder border;

    private JSeparator firstSeparator;
    private JSeparator secondSeparator;
    public Class(int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int layer, int slideNumber){
        super(x,y,width,height, listener, frame,file, layer, slideNumber);

        border = new UMLBorder(this);
        add(border);
        border.addMouseListener(listener);
        border.addMouseMotionListener(listener);
        border.setBounds(15, 15, width - 30, height - 30);
        firstSeparator = createSeparator(this.getY()-10);
        secondSeparator = createSeparator(this.getY()-30);
    }





    public JSeparator createSeparator(int y ){
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setBounds(0,y,5,this.getWidth());  // Adjust the bounds as needed
        separator.setForeground(Color.BLACK);
        return  separator;
    }


    public void resizeClass(int inputX, int inputY) {
        this.setSize(inputX-14, inputY-14);
        repaint();

    }
    public void setClassLocation(int x, int y){
        this.setLocation(x,y);
    }
    public void resizePanel(int inputX, int inputY) {
        border.setSize(inputX - 14, inputY - 14);
        repaint();
    }



}
