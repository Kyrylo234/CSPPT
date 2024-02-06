package org.lancaster.group77.Frame.Buttons.Home;

import org.lancaster.group77.Frame.Buttons.IconButton;

import javax.swing.*;

public class CutButton extends IconButton {
    public CutButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole) {
        super(imageIcon, x, y, width, height, inputButtonRole);
    }

    public void cut(){
        System.out.println("cut");

        //TODO get selected object

        //TODO get selected slides
    }
}
