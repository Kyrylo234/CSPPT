package org.lancaster.group77.Listener;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.Frame.CSPPTFrame;
import org.lancaster.group77.Frame.Slides.SlideManager;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CSPPTFrameAdapter extends ComponentAdapter {

    private CSPPTFrame frame;


    public CSPPTFrameAdapter(CSPPTFrame frame) {
        this.frame = frame;
    }

    /**
     * Invoked when the window's size changes.
     * @param e the event to be processed
     */
    public void componentResized(ComponentEvent e) {
        frame.resizeScreen();
        frame.updateCommandLinePosition();
    }
}
