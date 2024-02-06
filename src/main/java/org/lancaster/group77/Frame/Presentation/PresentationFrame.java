package org.lancaster.group77.Frame.Presentation;

import org.lancaster.group77.Frame.CSPPTFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class PresentationFrame extends JFrame {

    /**
     * Constructor for PresentationFrame
     * Create a full screen window
     */
    public PresentationFrame(CSPPTFrame cspptFrame) {
        GraphicsDevice screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (screen.isFullScreenSupported()) {
            setUndecorated(true);
            screen.setFullScreenWindow(this);
        } else {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setUndecorated(true);
        }

        initKeyboardListener();

        //TODO get all components from the slide and add them to the frame

        for(int i=0;i<cspptFrame.getPane().getComponentCount();i++){
            add(cspptFrame.getPane().getComponent(i));
        }

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    /**
     * Initialize the keyboard listener
     * Press ESC to exit the presentation
     */
    private void initKeyboardListener() {
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke("ESCAPE");
        Action escapeAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        };
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);
    }
}
