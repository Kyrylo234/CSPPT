package org.lancaster.group77.Frame.Buttons.File;

import org.lancaster.group77.FileSystem.FileChooser;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.impl.FileInterfaceImpl;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.Frame.Buttons.IconButton;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class OpenFileButton extends IconButton {
    public OpenFileButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputRole) {
        super(imageIcon, x, y, width, height, inputRole);
    }

    public static void openFileFunction() {
        FileChooser fileChooser = new FileChooser();
        String location = fileChooser.getFileLocation();
        if (fileChooser.isFileSelected() && location != null) {
            FileInterfaceImpl fileInterface = new FileInterfaceImpl();
            try {
                GlobalVariables.cspptFrame.getSlideManager().createNewFile();
                GlobalVariables.cspptFrame.getSlideManager().wipeFrame();

                CSPPTFile file1 = fileInterface.openFile(location);

                GlobalVariables.cspptFrame.setFileLocation(location);
                GlobalVariables.cspptFrame.setFile(file1);
                GlobalVariables.cspptFrame.removeAllFromArray();
                GlobalVariables.cspptFrame.populateFrame();
                GlobalVariables.cspptFrame.getSlideManager().refreshSlideIcon();

            } catch (FileNotFoundException e) {
                System.err.println("OPEN ERROR: " + e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
