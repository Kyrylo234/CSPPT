package org.lancaster.group77.Frame.Buttons.Home;

import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;

public class BoldButton extends IconButton {
    private MouseHandler handler;
    public BoldButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputRole, MouseHandler inputHandler){
        super(imageIcon,x, y, width, height, inputRole);
        handler = inputHandler;
    }

    public void BoldButtonFunction(){
        handler.setBold();

    }
}
