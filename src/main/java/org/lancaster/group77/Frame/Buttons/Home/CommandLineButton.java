package org.lancaster.group77.Frame.Buttons.Home;

import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.Frame.CSPPTFrame;

import javax.swing.*;

public class CommandLineButton extends IconButton {
    CSPPTFrame frame;
    public CommandLineButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole, CSPPTFrame frame) {
        super(imageIcon, x, y, width, height, inputButtonRole);
        this.frame = frame;
    }

    public void openCommandLine() {
        frame.getCommandLineComponent().setVisibility();
        frame.getCommandLineComponent().getCommandArea().requestFocus();
    }
}
