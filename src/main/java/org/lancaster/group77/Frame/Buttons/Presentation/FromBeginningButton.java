package org.lancaster.group77.Frame.Buttons.Presentation;

import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.Frame.CSPPTFrame;
import org.lancaster.group77.Frame.Presentation.PresentationFrame;

import javax.swing.*;

public class FromBeginningButton extends IconButton{

    CSPPTFrame cspptFrame;
    public FromBeginningButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole, CSPPTFrame cspptFrame) {
        super(imageIcon, x, y, width, height, inputButtonRole);

        this.cspptFrame = cspptFrame;
    }

    public void fromBeginningFunction(){
        System.out.println("from beginning");

        PresentationFrame presentationFrame = new PresentationFrame(cspptFrame);

        presentationFrame.setVisible(true);
    }
}
