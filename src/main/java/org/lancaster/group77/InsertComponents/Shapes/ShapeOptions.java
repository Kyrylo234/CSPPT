package org.lancaster.group77.InsertComponents.Shapes;

import org.lancaster.group77.Frame.Bars.InsertBar;
import org.lancaster.group77.Frame.CSPPTFrame;
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
    private MouseHandler mouseHandler;
    private JLayeredPane frame;
    private CSPPTFrame cspptFrame;

    public ShapeOptions(MouseHandler mouseHandler, JLayeredPane frame, CSPPTFrame cspptFrame){
        setSize(300, 150);
        setLayout(new GridLayout(3, 1));
        JButton squareButton = new JButton("Square");
        this.frame = frame;
        this.mouseHandler = mouseHandler;
        this.cspptFrame = cspptFrame;

        squareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                square = new Square(50,50,100,100,mouseHandler,frame, cspptFrame.getFile(), cspptFrame.getSizeOfArray());
                frame.add(square);
                frame.moveToFront(square);
                frame.repaint();
                dispose(); // Close the dialog after adding the shape
            }
        });

        JButton triangleButton = new JButton("Triangle");
        triangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                traingle = new Triangle(100,100,100,100,mouseHandler,frame, cspptFrame.getFile(), cspptFrame.getSizeOfArray());
                frame.add(traingle);
                frame.moveToFront(traingle);
                frame.repaint();
                dispose();
            }
        });

        JButton pentagonButton = new JButton("Pentagon");
        pentagonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pentagon =new Pentagon (100,100,100,100,mouseHandler,frame, cspptFrame.getFile(), cspptFrame.getSizeOfArray());
                frame.add(pentagon);
                frame.moveToFront(pentagon);
                frame.repaint();

                dispose();
            }
        });
        JButton hexagonButton = new JButton("Hexagon");
        hexagonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                hexagon = new Hexagon(100,200,100,100,mouseHandler,frame, cspptFrame.getFile(),cspptFrame.getSizeOfArray());
                frame.add(hexagon);
                frame.moveToFront(hexagon);
                frame.repaint();
                dispose();


            }
        });
        JButton septagonButton = new JButton("Heptagon");
        septagonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                septagon = new Septagon(100,200,100,100,mouseHandler,frame, cspptFrame.getFile(), cspptFrame.getSizeOfArray());
                frame.add(septagon);
                frame.moveToFront(septagon);
                frame.repaint();
                dispose();


            }
        });
        JButton circleButton = new JButton("circle");
        circleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                circle = new Circle(100,200,100,100,mouseHandler,frame, cspptFrame.getFile(), cspptFrame.getSizeOfArray());
                frame.add(circle);
                frame.moveToFront(circle);
                frame.repaint();
                dispose();


            }
        });
        JButton octagonButton = new JButton("Octagon");
        octagonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                octagon = new Octagon(100,200,100,100,mouseHandler,frame, cspptFrame.getFile(), cspptFrame.getSizeOfArray());
                frame.add(octagon);
                frame.moveToFront(octagon);
                frame.repaint();
                dispose();


            }
        });
        JButton starButton = new JButton("Star");
        starButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                star = new Star(100,200,100,100,mouseHandler,frame, cspptFrame.getFile(), cspptFrame.getSizeOfArray());
                frame.add(star);
                frame.moveToFront(star);
                frame.repaint();
                dispose();


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
