package org.lancaster.group77.Frame.Buttons.Insert;

import org.lancaster.group77.FileSystem.FileChooser;
import org.lancaster.group77.Frame.Bars.InsertBar;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.ImageComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;

public class InsertImageButton extends IconButton {
    private JLayeredPane frame;
    private MouseHandler handler;

    private InsertBar bar;

    public InsertImageButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputRole, MouseHandler inputhandler, InsertBar bar){
        super(imageIcon,x, y, width, height, inputRole);
        this.bar = bar;
        handler = inputhandler;
    }

    public void insertImageFunction(){
        FileChooser fileChooser = new FileChooser();
        String location = fileChooser.getImageFileLocation();
        if(location != null){
            ImageComponent image = new ImageComponent(100,100,100,100,handler, location,bar.getFrame().getPane(),bar.getFrame().getFile());
            bar.getFrame().getPane().add(image);
            bar.getFrame().getPane().moveToFront(image);
            bar.getFrame().getPane().repaint();
        }
    }

    //remove this
    public void addFrame(JLayeredPane inputFrame){
        frame = inputFrame;
    }
}
