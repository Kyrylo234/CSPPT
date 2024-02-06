package org.lancaster.group77.Frame.Buttons.Insert;

import org.lancaster.group77.DisplayComponents.Slide;
import org.lancaster.group77.Frame.Bars.InsertBar;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.Shapes.*;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertShapeButton extends IconButton {
    MouseHandler handler;

    private boolean isSlideInitialized;
    private InsertBar bar;

    private Slide slide;
    public InsertShapeButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole, MouseHandler inputHandler,InsertBar bar) {
        super(imageIcon, x, y, width, height, inputButtonRole);
        this.bar=bar;
        handler = inputHandler;

    }

    public void selectShape(){
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openOptions();
            }
        });
    }

    public void openOptions(){
        ShapeOptions options = new ShapeOptions(handler,bar.getFrame().getPane(), bar.getFrame());
        options.setLocationRelativeTo(this);
        options.setVisible(true);
    }

    private void initializeSlide() {
        if (!isSlideInitialized) {
            //slide = handler.getcurrentSlide();
            System.out.println("current slide " + slide);
            isSlideInitialized = true;
        }
    }

    public void InsertShapeFunction(){
        initializeSlide();
        selectShape();

    }

}
