package org.lancaster.group77.Frame.RightClickMenus;

import org.lancaster.group77.Frame.Buttons.Home.CommandLineButton;
import org.lancaster.group77.Frame.Presentation.PresentationFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PresentationRightClickMenu extends BaseRightClickMenu {
    private JMenuItem Next;
    private JMenuItem Previous;
    private JMenuItem InsertText;
    private JMenuItem MarkPen;
    private JMenuItem LaserPointer;
    private JMenuItem CommandLine;
    private JMenuItem EndShow;
    private PresentationFrame presentationFrame;

    public PresentationRightClickMenu(PresentationFrame presentationFrame) {
        super();
        this.presentationFrame = presentationFrame;

        Next = new JMenuItem("Next");
        Previous = new JMenuItem("Previous");
        InsertText = new JMenuItem("Insert Text");
        MarkPen = new JMenuItem("Mark Pen");
        LaserPointer = new JMenuItem("Laser Pointer");
        CommandLine = new JMenuItem("Command Line");
        EndShow = new JMenuItem("End Show");

        add(Next);
        add(Previous);
        add(InsertText);
        add(MarkPen);
        add(LaserPointer);
        add(CommandLine);
        add(EndShow);

        Next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presentationFrame.goNextSlide();
            }
        });

        Previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presentationFrame.goLastSlide();
            }
        });

        InsertText.setAction(new AbstractAction("Insert Text") {
            @Override
            public void actionPerformed(ActionEvent e) {
                presentationFrame.insertText(getX1(), getY1());

            }
        });

        MarkPen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presentationFrame.turnPen();
            }
        });

        LaserPointer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presentationFrame.turnLaser();
            }
        });

        CommandLine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommandLineButton.openCommandLineFunction();
            }
        });

        EndShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presentationFrame.endPresentation();
            }
        });
    }
}
