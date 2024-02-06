package org.lancaster.group77.Frame.Buttons.Home;

import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;

public class UnderlineButton extends IconButton {
    private MouseHandler handler;
    public UnderlineButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole, MouseHandler inputHandler) {
        super(imageIcon, x, y, width, height, inputButtonRole);

        handler = inputHandler;
    }

    public void setUnderline(){
        handler.Underline();
    }
}
