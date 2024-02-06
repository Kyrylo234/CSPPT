package org.lancaster.group77.Frame.Buttons.Home;

import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;

public class ItalicButton extends IconButton{
    private MouseHandler handler;

    public ItalicButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputRole, MouseHandler inputHandler){
        super(imageIcon,x, y, width, height, inputRole);
        handler = inputHandler;
    }

    public void italicButtonFunction(){
        handler.setItalic();
    }

}
