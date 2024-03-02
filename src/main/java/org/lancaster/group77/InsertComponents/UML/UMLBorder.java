package org.lancaster.group77.InsertComponents.UML;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;

public class UMLBorder extends TextArea {

    private JFXPanel frame;
    public UMLBorder (JFXPanel frame){
        this.frame =frame;
    }

    public JFXPanel getPanel(){return frame;}
}
