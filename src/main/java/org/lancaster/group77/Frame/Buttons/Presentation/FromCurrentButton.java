package org.lancaster.group77.Frame.Buttons.Presentation;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.Frame.Presentation.PresentationFrame;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;

public class FromCurrentButton extends IconButton {
    private MouseHandler handler;

    public FromCurrentButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole, MouseHandler handler) {
        super(imageIcon, x, y, width, height, inputButtonRole);
        this.handler = handler;
    }

    public static void fromCurrentFunction() {
        GlobalVariables.CURRENT_PRESENTATION_FRAME = FromBeginningButton.startPresentation(GlobalVariables.cspptFrame.getCurrentSlideInt());
    }
}
