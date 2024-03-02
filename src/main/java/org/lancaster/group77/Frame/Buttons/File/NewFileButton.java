package org.lancaster.group77.Frame.Buttons.File;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.Frame.Buttons.IconButton;

import javax.swing.*;

public class NewFileButton extends IconButton {
    public NewFileButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputRole){
        super(imageIcon,x, y, width, height, inputRole);
    }

    public static void newFileFunction(){
        GlobalVariables.cspptFrame.getSlideManager().createNewFile();
        GlobalVariables.cspptFrame.getSlideManager().wipeFrame();
        GlobalVariables.cspptFrame.getPane().setVisible(false);
    }


}
