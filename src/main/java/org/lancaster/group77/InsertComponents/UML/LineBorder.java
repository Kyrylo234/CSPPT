package org.lancaster.group77.InsertComponents.UML;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;

public class LineBorder extends JLayeredPane {
    private JFXPanel panel;
    public LineBorder(JFXPanel panel ){
        this.panel = panel;
    }


    public JFXPanel getPanel(){return panel;}
}
