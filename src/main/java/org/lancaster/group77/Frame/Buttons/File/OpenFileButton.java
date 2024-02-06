package org.lancaster.group77.Frame.Buttons.File;

import org.lancaster.group77.FileSystem.FileChooser;
import org.lancaster.group77.FileSystem.impl.FileInterfaceImpl;
import org.lancaster.group77.Frame.Bars.FileBar;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.Frame.CSPPTFrame;

import javax.swing.*;
import java.io.FileNotFoundException;

public class OpenFileButton extends BaseFileButton {
    public OpenFileButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputRole, FileBar fileBar){
        super(imageIcon,x, y, width, height, inputRole, fileBar);
    }
    public void openFileFunction(){
        FileChooser fileChooser = new FileChooser();
        String location = fileChooser.getFileLocation();
        if(location != getFileBar().getFrame().getFileLocation()){
            FileInterfaceImpl fileInterface = new FileInterfaceImpl();
            try{
                CSPPTFile file1 = fileInterface.openFile(location);
                getFileBar().getFrame().setFileLocation(location);
                getFileBar().getFrame().setFile(file1);
                getFileBar().getFrame().removeAllFromArray();
                getFileBar().getFrame().populateFrame();
            } catch(FileNotFoundException e){
                System.err.println("OPEN ERROR: " + e.getMessage());
            }
        }
    }
}
