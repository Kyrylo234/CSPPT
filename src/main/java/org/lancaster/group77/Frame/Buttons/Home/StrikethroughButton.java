package org.lancaster.group77.Frame.Buttons.Home;

import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class StrikethroughButton extends IconButton {
    private MouseHandler handler;
    public StrikethroughButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole, MouseHandler inputHandler) {
        super(imageIcon, x, y, width, height, inputButtonRole);
        handler = inputHandler;
    }

    public void setStrikethrough(){
        handler.Strikethrough();
    }
}
