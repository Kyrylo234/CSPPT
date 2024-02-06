package org.lancaster.group77.Frame.Buttons.Home;

import org.lancaster.group77.Frame.Bars.HomeBar;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.Frame.Slides.SlideManager;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;

public class NewSlide extends IconButton  {
    private final MouseHandler handler;
    private HomeBar bar;
    private final SlideManager MainSlideManager;
    public NewSlide (ImageIcon imageIcon, int x, int y, int width, int height, String inputRole, MouseHandler inputHandler, SlideManager MainSlideManager, HomeBar bar){
        super(imageIcon,x, y, width, height, inputRole);
        this.MainSlideManager =MainSlideManager;
        this.handler = inputHandler;
        this.bar = bar;
    }

    public void addNewSlide(){
        if (MainSlideManager != null) {
            MainSlideManager.addSlide();
            bar.getFrame().newSlide();
        } else {
            System.out.println("Slide Manager is null");
        }
    }


}
