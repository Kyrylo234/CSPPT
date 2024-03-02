package org.lancaster.group77.InsertComponents.Shapes;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.Manager.ClipboardManager;
import org.lancaster.group77.FileSystem.Tools.GeneralTools;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeOptions extends JDialog {
    private Square square;
    private Triangle traingle;
    private Pentagon pentagon;
    private Hexagon hexagon;
    private Circle circle;
    private Septagon septagon;
    private Octagon octagon;
    private Star star;


    public ShapeOptions(MouseHandler mouseHandler, JLayeredPane frame){
        setSize(400, 200);
        setLayout(new GridLayout(3, 1));
        JButton squareButton = new JButton("Square");

        squareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlobalVariables.handler.resetLastInputted();
                square = new Square(50,50,100,100,mouseHandler,frame, GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getNewLayer());
                GlobalVariables.cspptFrame.addToFrame(square);
                GlobalVariables.handler.setLastClicked(square);
                dispose(); // Close the dialog after adding the shape
                ClipboardManager.copyTempFile(GeneralTools.deepCopy(GlobalVariables.cspptFrame.getFile()));
            }
        });

        JButton triangleButton = new JButton("Triangle");
        triangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlobalVariables.handler.resetLastInputted();
                traingle = new Triangle(100,100,100,100,mouseHandler,frame, GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getNewLayer());
                GlobalVariables.cspptFrame.addToFrame(traingle);
                GlobalVariables.handler.setLastClicked(traingle);
                dispose();
                ClipboardManager.copyTempFile(GeneralTools.deepCopy(GlobalVariables.cspptFrame.getFile()));

            }
        });

        JButton pentagonButton = new JButton("Pentagon");
        pentagonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlobalVariables.handler.resetLastInputted();
                pentagon =new Pentagon (100,100,100,100,mouseHandler,frame, GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getNewLayer());
                GlobalVariables.cspptFrame.addToFrame(pentagon);
                GlobalVariables.handler.setLastClicked(pentagon);
                dispose();
                ClipboardManager.copyTempFile(GeneralTools.deepCopy(GlobalVariables.cspptFrame.getFile()));

            }
        });
        JButton hexagonButton = new JButton("Hexagon");
        hexagonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlobalVariables.handler.resetLastInputted();
                hexagon = new Hexagon(100,200,100,100,mouseHandler,frame, GlobalVariables.cspptFrame.getFile(),GlobalVariables.cspptFrame.getNewLayer());
                GlobalVariables.cspptFrame.addToFrame(hexagon);
                GlobalVariables.handler.setLastClicked(hexagon);
                dispose();
                ClipboardManager.copyTempFile(GeneralTools.deepCopy(GlobalVariables.cspptFrame.getFile()));



            }
        });
        JButton septagonButton = new JButton("Heptagon");
        septagonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlobalVariables.handler.resetLastInputted();
                septagon = new Septagon(100,200,100,100,mouseHandler,frame, GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getNewLayer());
                GlobalVariables.cspptFrame.addToFrame(septagon);
                GlobalVariables.handler.setLastClicked(septagon);
                dispose();
                ClipboardManager.copyTempFile(GeneralTools.deepCopy(GlobalVariables.cspptFrame.getFile()));



            }
        });
        JButton circleButton = new JButton("Circle");
        circleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlobalVariables.handler.resetLastInputted();
                circle = new Circle(100,200,100,100,mouseHandler,frame, GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getNewLayer());
                GlobalVariables.cspptFrame.addToFrame(circle);
                GlobalVariables.handler.setLastClicked(circle);
                dispose();
                ClipboardManager.copyTempFile(GeneralTools.deepCopy(GlobalVariables.cspptFrame.getFile()));



            }
        });
        JButton octagonButton = new JButton("Octagon");
        octagonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlobalVariables.handler.resetLastInputted();
                octagon = new Octagon(100,200,100,100,mouseHandler,frame, GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getNewLayer());
                GlobalVariables.cspptFrame.addToFrame(octagon);
                GlobalVariables.handler.setLastClicked(octagon);
                dispose();
                ClipboardManager.copyTempFile(GeneralTools.deepCopy(GlobalVariables.cspptFrame.getFile()));
            }
        });
        JButton starButton = new JButton("Star");
        starButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlobalVariables.handler.resetLastInputted();
                star = new Star(100,200,100,100,mouseHandler,frame, GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getNewLayer());
                GlobalVariables.cspptFrame.addToFrame(star);
                GlobalVariables.handler.setLastClicked(star);
                dispose();
                ClipboardManager.copyTempFile(GeneralTools.deepCopy(GlobalVariables.cspptFrame.getFile()));
            }
        });
        add(squareButton);
        add(triangleButton);
        add(pentagonButton);
        add(hexagonButton);
        add(circleButton);
        add(septagonButton);
        add(octagonButton);
        add(starButton);

    }

}
