package org.lancaster.group77.Frame.Buttons.Insert;

import org.lancaster.group77.FileSystem.FileChooser;
import org.lancaster.group77.Frame.Bars.InsertBar;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.AudioComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;


import javax.swing.*;
import java.io.File;

public class InsertAudioButton extends IconButton {

    MouseHandler handler;
    private InsertBar bar;
    JLayeredPane frame;
    public InsertAudioButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole, MouseHandler inputHandler, InsertBar bar) {
        super(imageIcon, x, y, width, height, inputButtonRole);
        this.bar = bar;
        this.handler = inputHandler;
    }

    public void InsertSound(){
        FileChooser fileChooser = new FileChooser();
        String location = fileChooser.getFileLocation();

        if (location != null){
            AudioComponent audio = new AudioComponent(50,50,100,100, handler,location);
            add(audio);
            frame.repaint();


        }
    }

}
