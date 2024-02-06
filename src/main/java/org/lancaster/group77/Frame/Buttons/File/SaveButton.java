package org.lancaster.group77.Frame.Buttons.File;


import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.FileInterface;
import org.lancaster.group77.FileSystem.impl.FileInterfaceImpl;
import org.lancaster.group77.Frame.Bars.FileBar;
import org.lancaster.group77.Frame.Buttons.IconButton;

import javax.swing.*;
import java.io.FileNotFoundException;

public class SaveButton extends BaseFileButton {
    public SaveButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputRole, FileBar fileBar){
        super(imageIcon,x, y, width, height, inputRole, fileBar);
    }

    public void saveFunction() throws FileNotFoundException {
        if(getFileBar().getFrame().getFileLocation() == null){
            getFileBar().getSaveAsButton().saveAsFileFunction();
            JOptionPane alertOfSucess = new JOptionPane("File successfully created");
            JDialog dialog = alertOfSucess.createDialog("Success");
            dialog.setVisible(true);
        }else{
            FileInterface fileInterface = new FileInterfaceImpl();
            fileInterface.saveFile(getFileBar().getFrame().getFileLocation(),getFileBar().getFrame().getFile());
            JOptionPane alertOfSucess = new JOptionPane("File successfully saved");
            JDialog dialog = alertOfSucess.createDialog("Success");
            dialog.setVisible(true);
        }



        //TODO create a cspptfile instance
        //TODO save to the BaseFileButton.setCurrentFile

    }
}
