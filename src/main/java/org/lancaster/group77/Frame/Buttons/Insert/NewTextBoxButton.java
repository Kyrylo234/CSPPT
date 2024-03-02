package org.lancaster.group77.Frame.Buttons.Insert;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.TextBox.TextBox;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;

public class NewTextBoxButton extends IconButton {
    private MouseHandler handler;
    private boolean isSlideInitialized = false;

    public NewTextBoxButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputRole, MouseHandler inputHandler) {
        super(imageIcon, x, y, width, height, inputRole);
        handler = inputHandler;
    }

    private void initializeSlide() {
        if (!isSlideInitialized) {
            isSlideInitialized = true;
        }
    }

    public void newTextBoxFunction() {
        if (GlobalVariables.cspptFrame.getCurrentSlideInt() != -1) {
            initializeSlide();
            TextBox tb = new TextBox(100, 100, 150, 100, handler, GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getNewLayer(), GlobalVariables.cspptFrame.getCurrentSlideInt());
            GlobalVariables.handler.resetLastInputted();
            GlobalVariables.cspptFrame.addToFrame(tb);
        }
    }
}
