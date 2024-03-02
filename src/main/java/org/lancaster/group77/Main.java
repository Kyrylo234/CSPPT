package org.lancaster.group77;


import org.lancaster.group77.Exceptions.FileDoesNotExistException;
import org.lancaster.group77.Frame.CSPPTFrame;


import javax.swing.*;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileDoesNotExistException, FileNotFoundException {
        SwingUtilities.invokeLater(() -> {
            CSPPTFrame cspptFrame = new CSPPTFrame();
            cspptFrame.setVisible(true);
            //add first slide
            cspptFrame.getSlideManager().addSlide();
        });
    }
}