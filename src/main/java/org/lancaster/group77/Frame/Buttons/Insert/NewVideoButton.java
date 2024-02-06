package org.lancaster.group77.Frame.Buttons.Insert;

import org.lancaster.group77.FileSystem.FileChooser;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.VideoComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;
//import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;

public class NewVideoButton extends IconButton {
    private MouseHandler handler;
    private JLayeredPane frame;
    public NewVideoButton(ImageIcon image, int x, int y, int width, int height, String inputRole, MouseHandler inputHandler){
        super(image,x,y,width,height,inputRole);
        handler = inputHandler;
    }

    public void insertVideoFunction(){
        FileChooser fileChooser = new FileChooser();
        String location = fileChooser.getFileLocation();
        if(location != null){
            VideoComponent video = new VideoComponent(100,100,300,300,handler,location);
            //frame.add(video);
            frame.repaint();
        }
    }

    public void addFrame(JLayeredPane inputFrame){
        frame = inputFrame;
    }
}
