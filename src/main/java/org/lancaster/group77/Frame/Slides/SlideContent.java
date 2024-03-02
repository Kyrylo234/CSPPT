package org.lancaster.group77.Frame.Slides;

import org.checkerframework.checker.units.qual.C;
import org.lancaster.group77.DisplayComponents.Animation;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.InsertComponents.*;
import org.lancaster.group77.InsertComponents.CodeSection.CodeSectionEditor;
import org.lancaster.group77.InsertComponents.Image.ImageComponent;
import org.lancaster.group77.InsertComponents.TextBox.TextBox;
import org.lancaster.group77.InsertComponents.UML.ClassDiagrams.ClassDiagram;
import org.lancaster.group77.InsertComponents.UML.Lines.BaseLine;
import org.lancaster.group77.InsertComponents.Video.VideoComponent;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class SlideContent extends JPanel {
    private int slideNumber;

    //add components to this
    private ArrayList<DisplayComponent> components;

    private boolean presnetationMode = false;
    private Animation animationData;

    public SlideContent(int slideNumber) {
//        System.out.println(new Date() + "SlideContent:SlideContent created" + " slideNumber: " + slideNumber);
        this.slideNumber = slideNumber;
        this.components = new ArrayList<>();
    }

    public void saveComponents(DisplayComponent[] newComponents, JLayeredPane frame) {
//        System.out.println(new Date() + "SlideContent:saveComponents");
        if (!presnetationMode) {
            components.clear();
        }
        components.addAll(Arrays.asList(newComponents));

        frame.removeAll();
        frame.revalidate();
        frame.repaint();
    }

    public void savePresentationComponents(ArrayList<DisplayComponent> components) {
//        System.out.println(new Date() + "SlideContent:savePresentationComponents");
        this.components.addAll(components);

    }


    /**
     * Show the components of the slide
     *
     * @param frame
     */
    public void showFrameComponents(JLayeredPane frame) {
//        System.out.println(new Date() + "SlideContent:showFrameComponents:" + frame.getName());

        reorderComponentsByLayer();

        for (Component component : components) {
            if (!frame.isAncestorOf(component)) {
                frame.add(component);
                if (component instanceof VideoComponent) {
                    ((VideoComponent) component).reAddVideoComponent();
                } else if ((component instanceof CodeSectionEditor) && (frame == GlobalVariables.cspptFrame.getPane())) {
                    ((CodeSectionEditor) component).displayCodeRunButton(GlobalVariables.cspptFrame.getPane(),false);
                }else if(component instanceof ClassDiagram){
                    ((ClassDiagram) component).updateLines();
                }
                // instanceof Baseline maybe
            }
        }

        frame.revalidate();
        frame.repaint();
    }

    public int getSlideNumber() {
        return this.slideNumber;
    }

    public void addComponent(DisplayComponent comp) {
//        System.out.println(new Date() + "SlideContent:addComponenet");
        components.add(comp);
    }

    /**
     * Remove a component from the slide
     *
     * @param comp
     */
    public void removeComponent(DisplayComponent comp) {
//        System.out.println(new Date() + "SlideContent:removeComponenet");
        for (DisplayComponent component : components) {
            if (component.equals(comp)) {
                components.remove(component);
                break;
            }
        }
    }


    public ArrayList<DisplayComponent> getPresentationComponents() {
        return this.components;
    }


    public void addAllComponent(ArrayList<DisplayComponent> components) {
        this.components = components;

    }

    public void visability(boolean mode) {
//        System.out.println(new Date() + "SlideContent:visability");
        for (Component component : components) {
            component.setVisible(false);
        }
    }

    public int getSlideSize() {
        return components.size();
    }

    public int indexOf(DisplayComponent comp) {
        return components.indexOf(comp);
    }

    /**
     * Add a component to the slide
     */
    public void addComponentToThisSlide(DisplayComponent component) {
//        System.out.println(new Date() + "SlideContent:addComponentsToThisSlide");

        boolean added = false;
        //then we need to order them
        if (components.isEmpty()) {
            components.add(component);
        } else {
            for (int o = 0; o < components.size(); o++) {
                if (components.get(o).getComponentData().getLayer() < component.getComponentData().getLayer() ) {
                    components.add(o, component);
                    added = true;
                    break;
                }
            }
            if (!added) {
                components.add(0, component);
            }
        }
    }

    public void setAnimationData(String animation_name, int duration) {
        animationData = new Animation(animation_name, duration);
        GlobalVariables.cspptFrame.getFile().getSlides().get(slideNumber).getAnimations().add(animationData);
    }

    public void setSize1(double size, double size1) {
        for (int i = 0; i < components.size(); i++) {
            components.get(i).resizeComponent((int) (components.get(i).getComponentData().getX() * size), (int) (components.get(i).getComponentData().getY() * size1),(int) (components.get(i).getComponentData().getWidth() * size), (int) (components.get(i).getComponentData().getHeight() * size1));
            if(components.get(i) instanceof ClassDiagram){
                ((ClassDiagram) components.get(i)).updateLines();


            }
        }
    }

    public Animation getAnimationData() {
        return animationData;
    }

    public ArrayList<DisplayComponent> getComponentsArray() {
        return components;
    }

    /**
     * Reorder the components by layer
     * Call it when refresh the frame
     */
    public void reorderComponentsByLayer() {
//        System.out.println(new Date() + "SlideContent:reorderComponentsByLayer");
        components.sort(Comparator.comparingInt((DisplayComponent c) -> c.getComponentData().getLayer()).reversed());

        int highest_value = components.isEmpty() ? 0 : components.get(components.size() - 1).getComponentData().getLayer();
        if (highest_value > GlobalVariables.TOP_LAYER) {
            GlobalVariables.TOP_LAYER = highest_value;
        }
    }

    public void setSlideNumber(int slideNumber) {
        this.slideNumber = slideNumber;
    }
}
