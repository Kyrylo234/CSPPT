package org.lancaster.group77.Frame.Buttons.Home;

import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;

public class BulletPointButton extends IconButton {
    MouseHandler handler;
    public BulletPointButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole, MouseHandler inputhandler) {
        super(imageIcon, x, y, width, height, inputButtonRole);
        this.handler = inputhandler;
    }

    public void setBulletpoints(){
        handler.Bullet();
    }
}
