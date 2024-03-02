package org.lancaster.group77.Frame.Buttons.Insert;

import org.lancaster.group77.FileSystem.FileChooser;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.Audio.AudioComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class InsertAudioButton extends IconButton {

    private MouseHandler handler;
    File file;

    public InsertAudioButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole, MouseHandler inputHandler) {
        super(imageIcon, x, y, width, height, inputButtonRole);
        this.handler = inputHandler;
    }

    public void InsertSound() throws IOException {
        if (GlobalVariables.cspptFrame.getCurrentSlideInt() != -1) {
            FileChooser fileChooser = new FileChooser();
            String location = fileChooser.getFileLocationForAnyType("Audio", "wav", "mp3");

            if (location != null) {
                AudioComponent audio = new AudioComponent(50, 50, 100, 50, handler, location, GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getCurrentSlideInt());
                GlobalVariables.handler.resetLastInputted();
                JPanel audioPanel = new JPanel();
                audioPanel.setLayout(new BorderLayout());
                audioPanel.setBounds(100, 100, 200, 200);
                audioPanel.add(audio, BorderLayout.CENTER);
                audioPanel.setVisible(true);
                GlobalVariables.cspptFrame.addToFrame(audio);
            }
        }
    }

}
