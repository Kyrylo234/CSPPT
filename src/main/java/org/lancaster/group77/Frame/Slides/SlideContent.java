package org.lancaster.group77.Frame.Slides;

import org.lancaster.group77.InsertComponents.DraggableComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class SlideContent extends JPanel {

    private int slideNumber;

    //add components to this
    private ArrayList<Component> components;

    public SlideContent(int slideNumber) {
        this.slideNumber = slideNumber;
        this.components = new ArrayList<>();
    }

    public void saveComponents(Component[] newComponents, JLayeredPane frame) {
        components.clear();
        Collections.addAll(components, newComponents);

        frame.removeAll();
        frame.revalidate();
        frame.repaint();
    }



    public void showFrameComponents(JLayeredPane frame) {

        for (Component component : components) {
            frame.add(component);
        }

        frame.revalidate();
        frame.repaint();
    }

    public int getSlideNumber() {
        return this.slideNumber;
    }

    public void addComponenet(DraggableComponent comp){
        components.add(comp);
    }
}
