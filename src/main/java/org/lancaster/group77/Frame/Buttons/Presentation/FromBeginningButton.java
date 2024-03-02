package org.lancaster.group77.Frame.Buttons.Presentation;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.Frame.CSPPTFrame;
import org.lancaster.group77.Frame.Presentation.PresentationFrame;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;

public class FromBeginningButton extends IconButton {

    public FromBeginningButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole, MouseHandler handler) {
        super(imageIcon, x, y, width, height, inputButtonRole);
    }

    public static void fromBeginningFunction() {
        GlobalVariables.CURRENT_PRESENTATION_FRAME = FromBeginningButton.startPresentation(0);
    }

    /**
     * This method starts the presentation from the beginning
     * Start from the slideNum
     *
     * @param slideNum
     * @return
     */
    public static PresentationFrame startPresentation(int slideNum) {
        PresentationFrame presentationFrame = new PresentationFrame(GlobalVariables.cspptFrame, GlobalVariables.handler);

        presentationFrame.goThatSlideWithNoAnimation(slideNum);
        presentationFrame.showPresentation();

        return presentationFrame;
    }
}
