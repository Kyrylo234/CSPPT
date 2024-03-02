package org.lancaster.group77.Frame.Buttons.Home;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.Frame.Buttons.IconButton;

import javax.swing.*;

public class CommandLineButton extends IconButton {
    public CommandLineButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole) {
        super(imageIcon, x, y, width, height, inputButtonRole);
    }

    public static void openCommandLine() {
        if (!GlobalVariables.cspptFrame.getPanel1().isAncestorOf(GlobalVariables.cspptFrame.getCommandLineComponent())) {
            GlobalVariables.cspptFrame.getPanel1().add(GlobalVariables.cspptFrame.getCommandLineComponent());
            GlobalVariables.cspptFrame.updateCommandLinePosition();
            GlobalVariables.cspptFrame.getCommandLineComponent().setVisible(true);
            GlobalVariables.cspptFrame.getCommandLineComponent().getCommandArea().requestFocus();
        } else
            openCommandLineFunction();
    }

    public static void openCommandLineFunction() {
        GlobalVariables.cspptFrame.getCommandLineComponent().setVisibility();
        GlobalVariables.cspptFrame.getCommandLineComponent().getCommandArea().requestFocus();
    }
}
