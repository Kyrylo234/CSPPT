package org.lancaster.group77.Frame.Buttons.Home;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.Frame.Slides.SlideManager;

import javax.swing.*;

public class NextSlide extends IconButton  {
    private final SlideManager MainSlideManager;
    public NextSlide (ImageIcon imageIcon, int x, int y, int width, int height, String inputRole, SlideManager MainSlideManager){
        super(imageIcon,x, y, width, height, inputRole);
        this.MainSlideManager =MainSlideManager;
    }

    public void nextSlide(){
        if (MainSlideManager != null){
            MainSlideManager.nextSlide();
            GlobalVariables.cspptFrame.nextSlide();
        }
    }


}

