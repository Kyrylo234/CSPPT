package org.lancaster.group77.Frame.Buttons.Home;

import org.lancaster.group77.Frame.Buttons.IconButton;

import javax.swing.*;

public class CopyButton extends IconButton {
    public CopyButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole) {
        super(imageIcon, x, y, width, height, inputButtonRole);
    }

    public void copy(){
        System.out.println("copy");
        //TODO get selected object

        //TODO get selected slides
    }
}
