package org.lancaster.group77.InsertComponents.Image;

import org.lancaster.group77.InsertComponents.Image.ImageComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;

public class ImageJLabel extends JLabel {
    ImageComponent imageComponent;
    ImageJLabel(ImageIcon imageIcon, ImageComponent imageComponent1, MouseHandler listener){
        super(imageIcon);
        addMouseListener(listener);
        addMouseMotionListener(listener);
        imageComponent = imageComponent1;
    }
    public ImageComponent getImageComponent() {
        return imageComponent;
    }
}
