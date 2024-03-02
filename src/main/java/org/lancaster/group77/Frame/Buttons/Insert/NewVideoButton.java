package org.lancaster.group77.Frame.Buttons.Insert;

import org.lancaster.group77.FileSystem.FileChooser;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.Frame.Bars.InsertBar;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.Video.VideoComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;
//import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.io.IOException;

public class NewVideoButton extends IconButton {
    private MouseHandler handler;

    public NewVideoButton(ImageIcon image, int x, int y, int width, int height, String inputRole, MouseHandler inputHandler, InsertBar bar) {
        super(image, x, y, width, height, inputRole);
        handler = inputHandler;
    }

    public void insertVideoFunction() throws IOException {
        FileChooser fileChooser = new FileChooser();
        String location = fileChooser.getFileLocationForAnyType("Video", "mp4");
        if (location != null) {
            VideoComponent video = new VideoComponent(10, 10, 300, 150, handler, location, GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getNewLayer(), GlobalVariables.cspptFrame.getCurrentSlide());
            GlobalVariables.handler.resetLastInputted();
            GlobalVariables.cspptFrame.addToFrame(video);

        } else {
            System.out.println("No File Selected");
        }
    }

}
