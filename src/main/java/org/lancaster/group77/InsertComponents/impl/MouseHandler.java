package org.lancaster.group77.InsertComponents.impl;


import org.lancaster.group77.Frame.CSPPTFrame;
import org.lancaster.group77.Frame.Slides.SlideManager;
import org.lancaster.group77.InsertComponents.*;
import org.lancaster.group77.InsertComponents.Shapes.*;
import org.lancaster.group77.InsertComponents.CodeSection.CodeSectionEditor;
import org.lancaster.group77.InsertComponents.CodeSection.ExtendedCodeSection;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

public class MouseHandler implements MouseListener, MouseMotionListener{
    //Mouse location when pressed
    private int newX;
    private int newY;
    //Recorded length and width when pressed
    private int initialWidth;
    private int initialHeight;

    private boolean rightOfComponent = false;
    private boolean bottomOfComponenet = false;

    //This is used for editing the variables if the component (increasing font size etc)
    //And is used to check if has been pressed before being strecthed
    private DraggableComponent lastClicked = null;

    //This is used to stretch the components
    private DraggableComponent lastRightClicked = null;

    //Used for when the component needs to be moved when resizing
    private int initalXOfComponent;
    private int initalYOfComponent;
    //Used for editing components
    private TextBox lastLeftClicked = null;
    private SlideManager MainSlideManager;
    private CSPPTFrame cspptFrame;  // Reference to the CSPPTFrame instance

    private int edgeVariant = 10;

    private int postition = 0;

    public MouseHandler(CSPPTFrame frame){
        cspptFrame = frame;
    }

    // handle event when mouse released immediately after press
    @Override
    public void mouseClicked(MouseEvent event) {

        //Stores the right click
        if(SwingUtilities.isLeftMouseButton(event)) {
            MainSlideManager.updateSlideIcon();

            //Stores normal click
            if (event.getComponent() instanceof DraggableComponent) {
                if (lastClicked != null) {
                    lastClicked.setInvisible();
                }
                lastClicked = (DraggableComponent) event.getComponent();
                lastClicked.setVisible();
            } else if (event.getComponent() instanceof ExtendedJtextArea) {
                if (lastClicked != null) {
                    lastClicked.setInvisible();
                }
                lastClicked = ((ExtendedJtextArea) event.getComponent()).getTextBox();
                cspptFrame.toFront(cspptFrame.getPostition(lastClicked));
                lastClicked.setVisible();
            } else if (event.getComponent() instanceof ImageJLabel) {
                if (lastClicked != null) {
                    lastClicked.setInvisible();
                }
                lastClicked = (DraggableComponent) ((ImageJLabel) event.getComponent()).getImageComponent();
                lastClicked.setVisible();
            }else if(event.getComponent() instanceof ExtendedCodeSection){
                if(lastClicked != null){
                    lastClicked.setInvisible();
                }
                lastClicked = (DraggableComponent) ((ExtendedCodeSection) event.getComponent()).getCodeSectionEditor();
                lastClicked.setVisible();
            }else if(event.getComponent() instanceof JList<?> clicked){
                int selectedSlide = clicked.getSelectedIndex();
                if (selectedSlide >= -1) {
                    MainSlideManager.setSlide(selectedSlide);
                }
            } else if (event.getComponent() instanceof ShapeBorder) {
                if (lastClicked != null) {
                    lastClicked.setInvisible();
                }
                lastClicked = (DraggableComponent) ((ShapeBorder) event.getComponent()).getPanel();

                lastClicked.setVisible();

            } else{
                MainSlideManager.updateSlideIcon();

                //If for example background pane pressed then resets to null
                if(lastClicked != null){
                    lastClicked.setInvisible();
                }
                lastClicked = null;
            }
        }
    }
    // handle event when mouse pressed
    @Override
    public void mousePressed(MouseEvent event) {
        //If component is meant to be draggable...

        if(event.getComponent() instanceof DraggableComponent){

            if(lastClicked != null && lastClicked == event.getComponent()){
                lastClicked.setVisible();
                newX = event.getXOnScreen();
                newY = event.getYOnScreen();
                initialWidth = event.getComponent().getWidth();
                initialHeight = event.getComponent().getHeight();
                initalXOfComponent = event.getComponent().getX();
                initalYOfComponent = event.getComponent().getY();
                //if smaller than width/2 means in left part of shape
                int mouseXAwayFromTopLeft = event.getX();
                int mouseYAwayFromTopLeft = event.getY();
                if(mouseXAwayFromTopLeft>(event.getComponent().getWidth()/2)-5 && mouseXAwayFromTopLeft<(event.getComponent().getWidth()/2)+5){
                    postition = -1;
                }else if(mouseXAwayFromTopLeft > event.getComponent().getWidth()/2){
                    rightOfComponent = true;
                    if(event.getX() > event.getComponent().getWidth()-10){
                        postition=1;
                    }
                }else{
                    rightOfComponent = false;
                    if(event.getX() < 10){
                        postition=2;
                    }
                }
                if(mouseYAwayFromTopLeft>(event.getComponent().getHeight()/2)-5 && mouseYAwayFromTopLeft<(event.getComponent().getHeight()/2)+5) {
                    if(rightOfComponent){
                        postition=-3;
                    }else{
                        postition=-4;
                    }
                }
                if(mouseYAwayFromTopLeft > event.getComponent().getHeight()/2){
                    bottomOfComponenet = true;
                    if(postition==-1){
                        postition=-2;
                    }
                    if(event.getY() > event.getComponent().getHeight()-10){
                        if(postition==1){
                            postition=5;
                        }else if(postition==2){
                            postition=6;
                        }
                    }
                }else {
                    bottomOfComponenet = false;
                    if(event.getY() < 10){
                        if(postition==1){
                            postition=7;
                        }else if(postition==2){
                            postition=8;
                        }
                    }
                }

            } else{
                newX = event.getXOnScreen()  - event.getComponent().getX();
                newY = event.getYOnScreen()  - event.getComponent().getY();
            }

        } else if(event.getComponent() instanceof ExtendedJtextArea){

            if(lastClicked!=null){
                lastClicked.setInvisible();
            }
            lastClicked = ((ExtendedJtextArea) event.getComponent()).getTextBox();
            cspptFrame.toFront(cspptFrame.getPostition(lastClicked));
            lastClicked.setVisible();
            newX = event.getXOnScreen()  - lastClicked.getX();
            newY = event.getYOnScreen()  - lastClicked.getY();
        } else if(event.getComponent() instanceof ImageJLabel){

            if(lastClicked!=null){
                lastClicked.setInvisible();
            }
            lastClicked = (DraggableComponent) ((ImageJLabel) event.getComponent()).getImageComponent();
            lastClicked.setVisible();
            newX = event.getXOnScreen()  - lastClicked.getX();
            newY = event.getYOnScreen()  - lastClicked.getY();
        }else if(event.getComponent() instanceof ShapeBorder){
            if(lastClicked!=null){
                lastClicked.setInvisible();
            }
            lastClicked = (DraggableComponent) ((ShapeBorder) event.getComponent()).getPanel();
            lastClicked.setVisible();
            newX = event.getXOnScreen()  - lastClicked.getX();
            newY = event.getYOnScreen()  - lastClicked.getY();
        }else if(event.getComponent() instanceof ExtendedCodeSection){
            if(lastClicked!=null){
                lastClicked.setInvisible();
            }
            lastClicked = ((ExtendedCodeSection) event.getComponent()).getCodeSectionEditor();
            lastClicked.setVisible();
            newX = event.getXOnScreen()  - lastClicked.getX();
            newY = event.getYOnScreen()  - lastClicked.getY();
        } else {
            //If for example background pane pressed then resets to null
            if(lastClicked != null){
                lastClicked.setInvisible();
            }
            lastClicked = null;
        }
    }
    // handle event when mouse released
    @Override
    public void mouseReleased(MouseEvent event) {
        MainSlideManager.updateSlideIcon();
        postition=0;

        if(event.getComponent() instanceof ExtendedCodeSection extendedCodeSection){
            DraggableComponent draggableComponent = ((ExtendedCodeSection) event.getComponent()).getCodeSectionEditor();

            extendedCodeSection.getCodeRunButton().setLocation(draggableComponent.getX()-20,draggableComponent.getY());
            extendedCodeSection.getCodeSectionComponent().getJfxPanel().setLocation(draggableComponent.getX(),draggableComponent.getY()+draggableComponent.getHeight());
        }

    }
    // handle event when mouse enters area
    @Override
    public void mouseEntered(MouseEvent event) {

    }
    // handle event when mouse exits area
    @Override
    public void mouseExited(MouseEvent event) {

    }

    // MouseMotionListener event handlers

    // handle event when user drags mouse with button pressed
    @Override
    public void mouseDragged(MouseEvent event) {

        //If it has been right clicked
        //Stretching function:
        if(event.getComponent() instanceof DraggableComponent){
            if(lastClicked == event.getComponent()){

                boolean locationChanged = false;
                int newXCoordinate = event.getComponent().getX();
                int newYCoordinate = event.getComponent().getY();

                //NewX = the oldXLocation of mouse (When first pressed)
                //NewY = the oldYLocation of mouse (When first pressed)
                int newWidth = initialWidth;
                int newHeight = initialHeight;
                boolean dontChange = false;
                switch (postition){
                    case 7:
                        newWidth = event.getXOnScreen() - newX + initialWidth;
                        newHeight = event.getXOnScreen() - newX + initialHeight;
                        if(newWidth<15 || newHeight <15){
                            dontChange = true;
                        }else{
                            locationChanged = true;
                            newYCoordinate = initalYOfComponent - (event.getXOnScreen() - newX);
                            newXCoordinate = initalXOfComponent;
                        }
                        break;
                    case 5:
                        newWidth = event.getXOnScreen() - newX + initialWidth;
                        newHeight = event.getXOnScreen() - newX + initialHeight;
                        if(newWidth<15 || newHeight <15){
                            dontChange = true;
                        }
                        break;
                    case 6:
                        newWidth = newX - event.getXOnScreen() + initialWidth;
                        newHeight = newX - event.getXOnScreen() + initialHeight;
                        if(newWidth<15 || newHeight <15){
                            dontChange = true;
                        }else{
                            locationChanged = true;
                            newXCoordinate = initalXOfComponent + (event.getXOnScreen() - newX);
                        }
                        break;
                    case 8:
                        newWidth = newX - event.getXOnScreen() + initialWidth;
                        newHeight = newX - event.getXOnScreen() + initialHeight;
                        if(newWidth<15 || newHeight <15){
                            dontChange = true;
                        }else{
                            locationChanged = true;
                            newYCoordinate = initalYOfComponent + (event.getXOnScreen() - newX);
                            newXCoordinate = initalXOfComponent + (event.getXOnScreen() - newX);
                        }
                        break;
                    case -1:
                        newHeight = newY - event.getYOnScreen() + initialHeight;
                        if(newHeight <15){
                            dontChange = true;
                        }else{
                            locationChanged = true;
                            newYCoordinate = initalYOfComponent + (event.getYOnScreen() - newY);
                        }
                        break;
                    case -2:
                        newHeight = event.getYOnScreen() - newY + initialHeight;
                        if(newHeight <15){
                            locationChanged = true;
                            dontChange = true;
                        }
                        break;
                    case -3:
                        newWidth = event.getXOnScreen() - newX + initialWidth;
                        if(newWidth <15){
                            dontChange = true;
                        }
                        break;
                    case -4:
                        newWidth = initialWidth - (event.getXOnScreen() - newX);
                        if(newWidth <15){
                            dontChange = true;
                        }else{
                            locationChanged = true;
                            newXCoordinate = initalXOfComponent + (event.getXOnScreen() - newX);
                        }
                        break;
                    default:
                        if(rightOfComponent){
                            //If stretching X towards the bottom right of the screen
                            newWidth = event.getXOnScreen() - newX + initialWidth;
                            if(newWidth<15){
                                newWidth=15;
                            }
                        }else{
                            //If stretching towards to left of screen
                            //In a random point
                            newWidth = newX - event.getXOnScreen() + initialWidth;
                            if(newWidth<15){
                                newWidth=15;
                            }else{
                                locationChanged = true;
                                newXCoordinate = initalXOfComponent + (event.getXOnScreen() - newX);
                            }
                        }
                        if(bottomOfComponenet){
                            //If stretching X towards the bottom left of the screen
                            if(postition!= 5 && postition !=6){
                                newHeight = event.getYOnScreen() - newY + initialHeight;
                                if(newHeight<15){
                                    newHeight=15;
                                }
                            }
                        }else{
                            //If stretching towards to left of screen
                            if(postition != 7 && postition !=8){
                                newHeight = newY - event.getYOnScreen() + initialHeight;
                                if(newHeight<15){
                                    newHeight=15;
                                }else{
                                    locationChanged = true;
                                    newYCoordinate = initalYOfComponent + (event.getYOnScreen() - newY);
                                }
                            }
                        }
                        break;
                }

                if(locationChanged){
                    event.getComponent().setLocation(newXCoordinate,newYCoordinate);
                }
                if(!dontChange){
                    event.getComponent().setSize(newWidth,newHeight);
                }
                if(event.getComponent() instanceof TextBox){
                    ((TextBox) event.getComponent()).resizeTextBox(newWidth,newHeight);
                }else if(event.getComponent() instanceof ImageComponent){
                    ((ImageComponent) event.getComponent()).resizeImage(newWidth,newHeight);
                }else if(event.getComponent() instanceof Pentagon){
                    ((Pentagon) event.getComponent()).resizePentagon(newWidth,newHeight);
                    ((Pentagon) event.getComponent()).resizePanel(newWidth-14,newHeight-14);

                } else if (event.getComponent() instanceof Hexagon) {
                    ((Hexagon) event.getComponent()).resizeHexagon(newWidth,newHeight);
                    ((Hexagon) event.getComponent()).resizePanel(newWidth-14,newHeight-14);

                }else if(event.getComponent() instanceof Square){
                    ((Square) event.getComponent()).resizeSquare(newWidth,newHeight);
                    ((Square) event.getComponent()).resizePanel(newWidth-14,newHeight-14);
                } else if (event.getComponent() instanceof  Triangle) {
                    ((Triangle) event.getComponent()).resizeTriangle(newWidth,newHeight);
                    ((Triangle) event.getComponent()).resizePanel(newWidth-14,newHeight-14);
                } else if (event.getComponent() instanceof  Circle) {
                    ((Circle) event.getComponent()).resizeCircle(newWidth,newHeight);
                    ((Circle) event.getComponent()).resizePanel(newWidth-14,newHeight-14);

                }else if (event.getComponent() instanceof  Septagon) {
                    ((Septagon) event.getComponent()).resizeSeptagon(newWidth,newHeight);
                    ((Septagon) event.getComponent()).resizePanel(newWidth-14,newHeight-14);

                }else if (event.getComponent() instanceof  Octagon) {
                    ((Octagon) event.getComponent()).resizeOctagon(newWidth,newHeight);
                    ((Octagon) event.getComponent()).resizePanel(newWidth-14,newHeight-14);

                }else if (event.getComponent() instanceof  Star) {
                    ((Star) event.getComponent()).resizeStar(newWidth,newHeight);
                    ((Star) event.getComponent()).resizePanel(newWidth-14,newHeight-14);

                }else if(event.getComponent() instanceof CodeSectionEditor){
                    ((CodeSectionEditor) event.getComponent()).resizePane(newWidth,newHeight);
                }


            }
        }else{

            //Dragging function
            if(event.getComponent() instanceof DraggableComponent){
                int newX1 = event.getXOnScreen() - newX;
                int newY1 = event.getYOnScreen() - newY;
                event.getComponent().setLocation(newX1,newY1);
            } else if (event.getComponent() instanceof ExtendedJtextArea){
                DraggableComponent draggableComponent = ((ExtendedJtextArea) event.getComponent()).getTextBox();

                int newX1 = event.getXOnScreen() - newX;
                int newY1 = event.getYOnScreen() - newY;
                draggableComponent.setLocation(newX1,newY1);
                TextBox textBox = (TextBox)draggableComponent;
                textBox.setLocation1(newX1,newY1);
            }else if(event.getComponent() instanceof ImageJLabel){
                DraggableComponent draggableComponent = (DraggableComponent) ((ImageJLabel) event.getComponent()).getImageComponent();
                int newX1 = event.getXOnScreen() - newX;
                int newY1 = event.getYOnScreen() - newY;
                draggableComponent.setLocation(newX1,newY1);
            } else if (event.getComponent() instanceof  ShapeBorder) {
                DraggableComponent draggableComponent = (DraggableComponent) ((ShapeBorder) event.getComponent()).getPanel();
                int newX1 = event.getXOnScreen() - newX;
                int newY1 = event.getYOnScreen() - newY;
                draggableComponent.setLocation(newX1,newY1);
                if(((ShapeBorder) event.getComponent()).getPanel() instanceof Circle){
                    Circle circle = (Circle) ((ShapeBorder) event.getComponent()).getPanel();
                    circle.setLocation1(newX1,newY1);
                } else if (((ShapeBorder) event.getComponent()).getPanel() instanceof Hexagon) {
                    Hexagon hexagon = (Hexagon) ((ShapeBorder) event.getComponent()).getPanel();
                    hexagon.setLocation1(newX1,newY1);
                }else if (((ShapeBorder) event.getComponent()).getPanel() instanceof Octagon) {
                    Octagon octagon = (Octagon) ((ShapeBorder) event.getComponent()).getPanel();
                    octagon.setLocation1(newX1,newY1);
                }else if (((ShapeBorder) event.getComponent()).getPanel() instanceof Pentagon) {
                    Pentagon pentagon = (Pentagon) ((ShapeBorder) event.getComponent()).getPanel();
                    pentagon.setLocation1(newX1,newY1);
                }else if (((ShapeBorder) event.getComponent()).getPanel() instanceof Septagon) {
                    Septagon septagon = (Septagon) ((ShapeBorder) event.getComponent()).getPanel();
                    septagon.setLocation1(newX1,newY1);
                }else if (((ShapeBorder) event.getComponent()).getPanel() instanceof Square) {
                    Square square = (Square) ((ShapeBorder) event.getComponent()).getPanel();
                    square.setLocation1(newX1,newY1);
                }else if (((ShapeBorder) event.getComponent()).getPanel() instanceof Star) {
                    Star star = (Star) ((ShapeBorder) event.getComponent()).getPanel();
                    star.setLocation1(newX1,newY1);
                }else if (((ShapeBorder) event.getComponent()).getPanel() instanceof Triangle) {
                    Triangle triangle = (Triangle) ((ShapeBorder) event.getComponent()).getPanel();
                    triangle.setLocation1(newX1,newY1);
                }


            }else if (event.getComponent() instanceof ExtendedCodeSection){
                DraggableComponent draggableComponent = ((ExtendedCodeSection) event.getComponent()).getCodeSectionEditor();
                int newX1 = event.getXOnScreen() - newX;
                int newY1 = event.getYOnScreen() - newY;
                draggableComponent.setLocation(newX1,newY1);
                CodeSectionEditor codeSectionEditor = (CodeSectionEditor)draggableComponent;
                codeSectionEditor.setLocation1(newX1,newY1);
            }
        }
    }



    // handle event when user moves mouse
    @Override
    public void mouseMoved(MouseEvent event) {

    }
    //Is called when the increase font size button is called
    public void increaseFontSize(){
        if(lastClicked instanceof TextBox){
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).increaseFontSize();
        }
    }
    public void decreaseFontSize(){
        if(lastClicked instanceof TextBox){
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).decreaseFontSize();
        }
    }

    public void changeFont(String fontName){
        if(lastClicked instanceof TextBox){
            MainSlideManager.updateSlideIcon();
            ((TextBox) lastClicked).changeFont(fontName);
        }
    }

    public void changeFontSize(Integer fontSize){
        System.out.println(fontSize);
        if(lastClicked instanceof TextBox){
            MainSlideManager.updateSlideIcon();
            ((TextBox) lastClicked).changeFontSize(fontSize);
        }
    }

    public void setBold(){
        if(lastClicked instanceof TextBox){
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).boldFunction();
        }
    }
    public void setItalic(){
        if(lastClicked instanceof TextBox){
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).italicFunction();
        }
    }
    public void setFontColor(){
        if (lastClicked instanceof TextBox){
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).changeFontColour();
        }
    }

    public void setBackgroundColor(){
        if(lastClicked==null){
            cspptFrame.changeBackgroundColour();
        }else{
            if (lastClicked instanceof TextBox){
                MainSlideManager.updateSlideIcon();
                ((TextBox) lastClicked).changeBackgroundColour();
            }else if(lastClicked instanceof Circle){
                MainSlideManager.updateSlideIcon();
                ((Circle) lastClicked).changeBackgroundColour();
            }else if(lastClicked instanceof Hexagon){
                MainSlideManager.updateSlideIcon();
                ((Hexagon) lastClicked).changeBackgroundColour();
            }else if(lastClicked instanceof Octagon){
                MainSlideManager.updateSlideIcon();
                ((Octagon) lastClicked).changeBackgroundColour();
            }else if(lastClicked instanceof Pentagon){
                MainSlideManager.updateSlideIcon();
                ((Octagon) lastClicked).changeBackgroundColour();
            }else if(lastClicked instanceof Septagon){
                MainSlideManager.updateSlideIcon();
                ((Septagon) lastClicked).changeBackgroundColour();
            }else if(lastClicked instanceof Square){
                MainSlideManager.updateSlideIcon();
                ((Square) lastClicked).changeBackgroundColour();
            }else if(lastClicked instanceof Star){
                MainSlideManager.updateSlideIcon();
                ((Star) lastClicked).changeBackgroundColour();
            }else if(lastClicked instanceof Triangle){
                MainSlideManager.updateSlideIcon();
                ((Triangle) lastClicked).changeBackgroundColour();
            }
        }
    }
    public void RightAlign(){
        if (lastClicked instanceof TextBox){
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).alignRightFunction();
        }
    }
    public void LeftAlign(){
        if (lastClicked instanceof TextBox){
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).alignLeftFunction();
        }
    }
    public void CenterAlign(){
        if (lastClicked instanceof TextBox){
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).alignCenterFunction();
        }
    }
    public void Underline(){
        if (lastClicked instanceof  TextBox){
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).underlineFunction();
        }
    }
    public void Strikethrough() {
        if (lastClicked instanceof TextBox){
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).strikethroughFunction();
        }
    }

    public void LineSpacing(){
        if (lastClicked instanceof TextBox){
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).lineSpacingFunction();
        }

    }
    public void setMainSlideManager(SlideManager slideManager){
        this.MainSlideManager =slideManager;
    }

    public SlideManager getSlideManager(){

        return this.MainSlideManager;
    }

}
