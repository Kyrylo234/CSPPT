package org.lancaster.group77.Frame.Buttons.File;

import org.lancaster.group77.DisplayComponents.Slide;
import org.lancaster.group77.FileSystem.FileInterface;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.impl.FileInterfaceImpl;
import org.lancaster.group77.Frame.Bars.FileBar;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.Frame.Dialogs.PopupDialog;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SaveButton extends IconButton {
    FileBar fileBar;

    public SaveButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputRole, FileBar fileBar) {
        super(imageIcon, x, y, width, height, inputRole);
        this.fileBar = fileBar;
    }

    public static void saveFunction() throws FileNotFoundException {
        if (GlobalVariables.cspptFrame.getFileLocation() == null) {
            SaveAsButton.saveAsFileFunction();
        } else {
            FileInterface fileInterface = new FileInterfaceImpl();
            fileInterface.saveFile(GlobalVariables.cspptFrame.getFileLocation(), GlobalVariables.cspptFrame.getFile());
            new PopupDialog("File successfully saved", "Success");
        }

    }
}
