package org.lancaster.group77.Frame.Buttons.File;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.Frame.Bars.FileBar;

import javax.swing.*;

public class NewFileButton extends BaseFileButton {
    public NewFileButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputRole, FileBar fileBar){
        super(imageIcon,x, y, width, height, inputRole, fileBar);
    }

    public void newFileFunction(){
        CSPPTFile cspptFile1 = new CSPPTFile();
        cspptFile1.addEmptySlide();
        wipeFrame();
        getFileBar().getFrame().setFileLocation(null);
        getFileBar().getFrame().setFile(cspptFile1);
        System.out.println("New File");
    }

    public void wipeFrame(){
        getFileBar().getFrame().getPane().removeAll();
        getFileBar().getFrame().getPane().revalidate();
        getFileBar().getFrame().getPane().repaint();
        getFileBar().getFrame().removeAllFromArray();
    }
}
