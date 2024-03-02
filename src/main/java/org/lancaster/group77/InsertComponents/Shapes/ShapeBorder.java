package org.lancaster.group77.InsertComponents.Shapes;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;

public class ShapeBorder extends JPanel {
    private JFXPanel panel;
    public ShapeBorder(JFXPanel panel ){
       this.panel = panel;
    }


    public JFXPanel getPanel(){return panel;}

}
