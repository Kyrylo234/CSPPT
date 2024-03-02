package org.lancaster.group77.Frame.Buttons.Home;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.UML.ClassDiagrams.ClassDiagram;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;

public class CSChartButton extends IconButton {

    int boxcounter =1;

    private MouseHandler handler;
    public CSChartButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole, MouseHandler handler) {
        super(imageIcon, x, y, width, height, inputButtonRole);
        this.handler = handler;


    }

    public void createClass(){
        ClassDiagram classDiagram = new ClassDiagram(100, 100, 100, 100, handler, GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getNewLayer(),GlobalVariables.cspptFrame.getCurrentSlideInt(), boxcounter);
        GlobalVariables.cspptFrame.addToFrame(classDiagram);
        boxcounter++;


    }


}
