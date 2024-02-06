package org.lancaster.group77.Frame.Buttons.File;

import org.lancaster.group77.FileSystem.SaveAsFileChooser;
import org.lancaster.group77.FileSystem.impl.FileInterfaceImpl;
import org.lancaster.group77.Frame.Bars.FileBar;

import javax.swing.*;
import java.io.FileNotFoundException;

public class SaveAsButton extends BaseFileButton {
    public SaveAsButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputRole, FileBar fileBar){
        super(imageIcon,x, y, width, height, inputRole, fileBar);
    }
    public void saveAsFileFunction() throws FileNotFoundException {
        SaveAsFileChooser saveAsFileChooser = new SaveAsFileChooser();
        String location = saveAsFileChooser.getSaveAsFileLocation();
        getFileBar().getFrame().setFileLocation(location);

        //TODO have a check whether the file is saved successfully

        FileInterfaceImpl fileInterface = new FileInterfaceImpl();
        fileInterface.saveFile(location, getFileBar().getFrame().getFile());
    }
}
