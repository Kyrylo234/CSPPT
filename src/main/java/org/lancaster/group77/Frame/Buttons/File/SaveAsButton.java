package org.lancaster.group77.Frame.Buttons.File;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.SaveAsFileChooser;
import org.lancaster.group77.FileSystem.impl.FileInterfaceImpl;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.Frame.Dialogs.PopupDialog;

import javax.swing.*;
import java.io.FileNotFoundException;

public class SaveAsButton extends IconButton {
    public SaveAsButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputRole) {
        super(imageIcon, x, y, width, height, inputRole);
    }

    public static void saveAsFileFunction() {
        SaveAsFileChooser saveAsFileChooser = new SaveAsFileChooser();
        String location = saveAsFileChooser.getSaveAsFileLocation();
        GlobalVariables.cspptFrame.setFileLocation(location);

        if (location != null && !location.isEmpty()) {
            FileInterfaceImpl fileInterface = new FileInterfaceImpl();
            fileInterface.saveFile(location, GlobalVariables.cspptFrame.getFile());
            new PopupDialog("File successfully saved", "Success");
        }
    }
}
