package org.lancaster.group77.Frame.Buttons.Insert;

import org.lancaster.group77.FileSystem.FileChooser;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.Image.ImageComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;

public class InsertImageButton extends IconButton {
    private MouseHandler handler;

    public InsertImageButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputRole, MouseHandler inputhandler){
        super(imageIcon,x, y, width, height, inputRole);
        handler = inputhandler;
    }

    public void insertImageFunction(){
        if(GlobalVariables.cspptFrame.getCurrentSlideInt() != -1){
            FileChooser fileChooser = new FileChooser();
            String location = fileChooser.getImageFileLocation();
            if(location != null){
                ImageComponent image = new ImageComponent(100,100,100,100,handler, location,GlobalVariables.cspptFrame.getPane(),GlobalVariables.cspptFrame.getFile(),GlobalVariables.cspptFrame.getCurrentSlide());
                GlobalVariables.handler.resetLastInputted();
                GlobalVariables.cspptFrame.addToFrame(image);
            }
        }
    }
}
