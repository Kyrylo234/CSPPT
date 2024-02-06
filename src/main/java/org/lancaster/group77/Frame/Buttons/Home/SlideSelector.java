package org.lancaster.group77.Frame.Buttons.Home;

import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.Frame.Slides.SlideManager;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;

public class SlideSelector extends IconButton {
    private  MouseHandler handler;
    private SlideManager slideManager;
    public SlideSelector(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole, MouseHandler inputHandler, SlideManager slideManager){
        super(imageIcon, x, y, width, height, inputButtonRole);
        this.handler = inputHandler;
        this.slideManager = slideManager;

    }
}
