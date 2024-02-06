package org.lancaster.group77.Frame.Buttons.File;

import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.Frame.Bars.FileBar;
import org.lancaster.group77.Frame.Buttons.IconButton;

import javax.swing.*;

public class BaseFileButton extends IconButton {
    private FileBar fileBar;
    public BaseFileButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputRole, FileBar fileBar1){
        super(imageIcon,x, y, width, height, inputRole);
        fileBar = fileBar1;
    }


    public FileBar getFileBar(){
        return fileBar;
    }
}
