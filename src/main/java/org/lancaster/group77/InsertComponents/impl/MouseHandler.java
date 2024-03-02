package org.lancaster.group77.InsertComponents.impl;


import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.Manager.ClipboardManager;
import org.lancaster.group77.FileSystem.Tools.GeneralTools;
import org.lancaster.group77.Frame.CSPPTFrame;
import org.lancaster.group77.Frame.Slides.SlideManager;
import org.lancaster.group77.InsertComponents.*;
import org.lancaster.group77.InsertComponents.CodeSection.CodeArea;
import org.lancaster.group77.InsertComponents.CodeSection.CodeSectionEditor;
import org.lancaster.group77.InsertComponents.Image.ImageComponent;
import org.lancaster.group77.InsertComponents.Image.ImageJLabel;
import org.lancaster.group77.InsertComponents.MathChart.ChartComponent;
import org.lancaster.group77.InsertComponents.MathChart.ExtendedChartPanel;
import org.lancaster.group77.InsertComponents.Shapes.*;
import org.lancaster.group77.InsertComponents.CodeSection.ExtendedCodeSection;
import org.lancaster.group77.InsertComponents.UML.ClassDiagrams.ClassDiagram;
import org.lancaster.group77.InsertComponents.UML.Lines.BaseLine;
import org.lancaster.group77.InsertComponents.UML.UMLBorder;

import org.lancaster.group77.InsertComponents.Table.ExtendedJTable;
import org.lancaster.group77.InsertComponents.Table.TableComponent;
import org.lancaster.group77.InsertComponents.TextBox.ExtendedJtextArea;
import org.lancaster.group77.InsertComponents.TextBox.TextBox;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

public class MouseHandler implements MouseListener, MouseMotionListener {
    //Mouse location when pressed
    private int newX;
    private int newY;
    //Recorded length and width when pressed
    private int initialWidth;
    private int initialHeight;
    private boolean handlerEnabled = true;


    private boolean rightOfComponent = false;
    private boolean bottomOfComponenet = false;

    //This is used for editing the variables if the component (increasing font size etc)
    //And is used to check if has been pressed before being strecthed
    private DisplayComponent lastClicked = null;
    private int lastClickSlide = -1;

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
    private int selectedSlide = -1;

    public MouseHandler(CSPPTFrame frame) {
        cspptFrame = frame;
    }

    // handle event when mouse released immediately after press
    @Override
    public void mouseClicked(MouseEvent event) {
        if (!handlerEnabled) return;
        //Stores the right click
        if (event.getComponent() instanceof JList<?>) {
            lastClickSlide = ((JList<?>) event.getComponent()).getSelectedIndex();
            lastClicked = null;
            return;
        }
        if (SwingUtilities.isLeftMouseButton(event)) {
            lastClickSlide = -1;
            //Stores normal click
            if (event.getComponent() instanceof ExtendedJtextArea) {
                if (lastClicked != null) {
                    lastClicked.setInvisible();
                }
                lastClicked = ((ExtendedJtextArea) event.getComponent()).getTextBox();
                lastClicked.setVisible();
            } else if (event.getComponent() instanceof ImageJLabel) {
                if (lastClicked != null) {
                    lastClicked.setInvisible();
                }
                lastClicked = (DraggableComponent) ((ImageJLabel) event.getComponent()).getImageComponent();
                lastClicked.setVisible();
            } else if (event.getComponent() instanceof ExtendedCodeSection) {
                if (lastClicked != null) {
                    lastClicked.setInvisible();
                }
                lastClicked = (DraggableComponent) ((ExtendedCodeSection) event.getComponent()).getCodeSectionEditor();
                lastClicked.setVisible();
            } else if (event.getComponent() instanceof CodeArea) {
                if (lastClicked != null) {
                    lastClicked.setInvisible();
                }
                lastClicked = (DraggableComponent) ((CodeArea) event.getComponent()).getCodeSectionEditor();
                lastClicked.setVisible();
            } else if (event.getComponent() instanceof JList<?> clicked) {
                selectedSlide = clicked.getSelectedIndex();
                lastClicked = null;
            } else if (event.getComponent() instanceof ShapeBorder) {
                if (lastClicked != null) {
                    lastClicked.setInvisible();
                }
                lastClicked = (DraggableComponent) ((ShapeBorder) event.getComponent()).getPanel();
                lastClicked.setVisible();
            } else if ((event.getComponent() instanceof ExtendedJTable extendedJTable)) {
                if (lastClicked != null) {
                    lastClicked.setInvisible();
                }
                TableComponent tableComponent = extendedJTable.getTableComponent();
                lastClicked = tableComponent;
                lastClicked.setVisible();
                tableComponent.displayFinishButton();
            } else if (event.getComponent() instanceof TableComponent tableComponent) {
                if (lastClicked != null) {
                    lastClicked.setInvisible();
                }
                lastClicked = tableComponent;
                lastClicked.setVisible();

                tableComponent.displayFinishButton();
            } else if ((event.getComponent() instanceof ExtendedChartPanel extendedChartPanel)) {
                if (lastClicked != null) {
                    lastClicked.setInvisible();
                }
                lastClicked = extendedChartPanel.getChartComponent();
                lastClicked.setVisible();
            } else if (event.getComponent() instanceof DraggableComponent) {
                if (lastClicked != null) {
                    lastClicked.setInvisible();
                }
                lastClicked = (DraggableComponent) event.getComponent();
                lastClicked.setVisible();
            }else if (event.getComponent() instanceof ClassDiagram){
                if(lastClicked !=  null){
                    lastClicked.setInvisible();
                }

                lastClicked =(ClassDiagram) event.getComponent();
                lastClicked.setVisible();
        }else if(event.getComponent() instanceof BaseLine){
                if(lastClicked !=  null){
                    lastClicked.setInvisible();
                }

                lastClicked =(BaseLine) event.getComponent();
                lastClicked.setVisible();
            } else {
                MainSlideManager.updateSlideIcon();

                //If for example background pane pressed then resets to null
                if (lastClicked != null) {
                    lastClicked.setInvisible();
                }
                lastClicked = null;
                lastClickSlide = -1;
            }
        }
    }

    // handle event when mouse pressed
    @Override
    public void mousePressed(MouseEvent event) {
        if (!handlerEnabled) return;
        //If component is meant to be draggable...

        if (event.getComponent() instanceof JList<?> clicked) {
            lastClickSlide = ((JList<?>) event.getComponent()).getSelectedIndex();
            lastClicked = null;
            selectedSlide = clicked.getSelectedIndex();
            return;
        }

        lastClickSlide = -1;
        if (event.getComponent() instanceof DraggableComponent draggableComponent) {
            if (lastClicked != null && lastClicked == event.getComponent()) {
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
                if (mouseXAwayFromTopLeft > (event.getComponent().getWidth() / 2) - 5 && mouseXAwayFromTopLeft < (event.getComponent().getWidth() / 2) + 5) {
                    postition = -1;
                } else if (mouseXAwayFromTopLeft > event.getComponent().getWidth() / 2) {
                    rightOfComponent = true;
                    if (event.getX() > event.getComponent().getWidth() - 10) {
                        postition = 1;
                    }
                } else {
                    rightOfComponent = false;
                    if (event.getX() < 10) {
                        postition = 2;
                    }
                }
                if (mouseYAwayFromTopLeft > (event.getComponent().getHeight() / 2) - 5 && mouseYAwayFromTopLeft < (event.getComponent().getHeight() / 2) + 5) {
                    if (rightOfComponent) {
                        postition = -3;
                    } else {
                        postition = -4;
                    }
                }
                if (mouseYAwayFromTopLeft > event.getComponent().getHeight() / 2) {
                    bottomOfComponenet = true;
                    if (postition == -1) {
                        postition = -2;
                    }
                    if (event.getY() > event.getComponent().getHeight() - 10) {
                        if (postition == 1) {
                            postition = 5;
                        } else if (postition == 2) {
                            postition = 6;
                        }
                    }
                } else {
                    bottomOfComponenet = false;
                    if (event.getY() < 10) {
                        if (postition == 1) {
                            postition = 7;
                        } else if (postition == 2) {
                            postition = 8;
                        }
                    }
                }

            } else {
                newX = event.getXOnScreen() - event.getComponent().getX();
                newY = event.getYOnScreen() - event.getComponent().getY();
            }

            if (draggableComponent instanceof TableComponent tableComponent) {
                tableComponent.displayFinishButton();
            }
        } else if (event.getComponent() instanceof ExtendedJtextArea) {

            if (lastClicked != null) {
                lastClicked.setInvisible();
            }
            lastClicked = ((ExtendedJtextArea) event.getComponent()).getTextBox();
            lastClicked.setVisible();
            newX = event.getXOnScreen() - lastClicked.getX();
            newY = event.getYOnScreen() - lastClicked.getY();
        } else if (event.getComponent() instanceof ImageJLabel) {

            if (lastClicked != null) {
                lastClicked.setInvisible();
            }
            lastClicked = (DraggableComponent) ((ImageJLabel) event.getComponent()).getImageComponent();
            lastClicked.setVisible();
            newX = event.getXOnScreen()  - lastClicked.getX();
            newY = event.getYOnScreen()  - lastClicked.getY();

        } else if (event.getComponent() instanceof ShapeBorder) {
            if (lastClicked != null) {
                lastClicked.setInvisible();
            }
            lastClicked = (DraggableComponent) ((ShapeBorder) event.getComponent()).getPanel();
            lastClicked.setVisible();
            newX = event.getXOnScreen() - lastClicked.getX();
            newY = event.getYOnScreen() - lastClicked.getY();
        } else if (event.getComponent() instanceof UMLBorder) {
            if (lastClicked != null) {
                lastClicked.setInvisible();
            }
            lastClicked = (DraggableComponent) ((UMLBorder) event.getComponent()).getPanel();
            lastClicked.setVisible();
            newX = event.getXOnScreen() - lastClicked.getX();
            newY = event.getYOnScreen() - lastClicked.getY();

        } else if (event.getComponent() instanceof ExtendedJTable) {
            if (lastClicked != null) {
                lastClicked.setInvisible();
            }
            TableComponent tableComponent = ((ExtendedJTable) event.getComponent()).getTableComponent();
            lastClicked = tableComponent;
            lastClicked.setVisible();
            newX = event.getXOnScreen() - lastClicked.getX();
            newY = event.getYOnScreen() - lastClicked.getY();
            tableComponent.displayFinishButton();
        } else if (event.getComponent() instanceof ExtendedCodeSection) {
            if (lastClicked != null) {
                lastClicked.setInvisible();
            }
            lastClicked = ((ExtendedCodeSection) event.getComponent()).getCodeSectionEditor();
            lastClicked.setVisible();
            newX = event.getXOnScreen() - lastClicked.getX();
            newY = event.getYOnScreen() - lastClicked.getY();
        } else if (event.getComponent() instanceof CodeArea) {
            if (lastClicked != null) {
                lastClicked.setInvisible();
            }
            lastClicked = ((CodeArea) event.getComponent()).getCodeSectionEditor();
            lastClicked.setVisible();
            newX = event.getXOnScreen() - lastClicked.getX();
            newY = event.getYOnScreen() - lastClicked.getY();
        } else if (event.getComponent() instanceof ExtendedChartPanel) {
            if (lastClicked != null) {
                lastClicked.setInvisible();
            }
            lastClicked = ((ExtendedChartPanel) event.getComponent()).getChartComponent();
            lastClicked.setVisible();
            newX = event.getXOnScreen() - lastClicked.getX();
            newY = event.getYOnScreen() - lastClicked.getY();
        } else {
            //If for example background pane pressed then resets to null
            if (lastClicked != null) {
                lastClicked.setInvisible();
            }
            lastClicked = null;
        }
    }

    // handle event when mouse released
    @Override
    public void mouseReleased(MouseEvent event) {
        //Store a copy of last change
        ClipboardManager.copyTempFile(GeneralTools.deepCopy(GlobalVariables.cspptFrame.getFile()));

        postition = 0;

        if (event.getComponent() instanceof ExtendedCodeSection extendedCodeSection) {
            DraggableComponent draggableComponent = ((ExtendedCodeSection) event.getComponent()).getCodeSectionEditor();
            extendedCodeSection.getCodeSectionEditor().updateButtonPosition(draggableComponent.getX(), draggableComponent.getY(), draggableComponent.getWidth(), draggableComponent.getHeight());
        } else if (event.getComponent() instanceof CodeArea codeArea) {
            CodeSectionEditor draggableComponent = ((CodeArea) event.getComponent()).getCodeSectionEditor();
            codeArea.getCodeSectionEditor().updateButtonPosition(draggableComponent.getX(), draggableComponent.getY(), draggableComponent.getWidth(), draggableComponent.getHeight());
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
        if (!handlerEnabled) return;
        //If it has been right clicked
        //Stretching function:

        if (event.getComponent() instanceof DraggableComponent draggableComponent) {
            if (lastClicked == event.getComponent()) {
                boolean locationChanged = false;
                int newXCoordinate = event.getComponent().getX();
                int newYCoordinate = event.getComponent().getY();

                //NewX = the oldXLocation of mouse (When first pressed)
                //NewY = the oldYLocation of mouse (When first pressed)
                int newWidth = initialWidth;
                int newHeight = initialHeight;
                boolean dontChange = false;
                switch (postition) {
                    case 7:
                        newWidth = event.getXOnScreen() - newX + initialWidth;
                        newHeight = event.getXOnScreen() - newX + initialHeight;
                        if (newWidth < 15 || newHeight < 15) {
                            dontChange = true;
                        } else {
                            locationChanged = true;
                            newYCoordinate = initalYOfComponent - (event.getXOnScreen() - newX);
                            newXCoordinate = initalXOfComponent;
                        }
                        break;
                    case 5:
                        newWidth = event.getXOnScreen() - newX + initialWidth;
                        newHeight = event.getXOnScreen() - newX + initialHeight;
                        if (newWidth < 15 || newHeight < 15) {
                            dontChange = true;
                        }
                        break;
                    case 6:
                        newWidth = newX - event.getXOnScreen() + initialWidth;
                        newHeight = newX - event.getXOnScreen() + initialHeight;
                        if (newWidth < 15 || newHeight < 15) {
                            dontChange = true;
                        } else {
                            locationChanged = true;
                            newXCoordinate = initalXOfComponent + (event.getXOnScreen() - newX);
                        }
                        break;
                    case 8:
                        newWidth = newX - event.getXOnScreen() + initialWidth;
                        newHeight = newX - event.getXOnScreen() + initialHeight;
                        if (newWidth < 15 || newHeight < 15) {
                            dontChange = true;
                        } else {
                            locationChanged = true;
                            newYCoordinate = initalYOfComponent + (event.getXOnScreen() - newX);
                            newXCoordinate = initalXOfComponent + (event.getXOnScreen() - newX);
                        }
                        break;
                    case -1:
                        newHeight = newY - event.getYOnScreen() + initialHeight;
                        if (newHeight < 15) {
                            dontChange = true;
                        } else {
                            locationChanged = true;
                            newYCoordinate = initalYOfComponent + (event.getYOnScreen() - newY);
                        }
                        break;
                    case -2:
                        newHeight = event.getYOnScreen() - newY + initialHeight;
                        if (newHeight < 15) {
                            locationChanged = true;
                            dontChange = true;
                        }
                        break;
                    case -3:
                        newWidth = event.getXOnScreen() - newX + initialWidth;
                        if (newWidth < 15) {
                            dontChange = true;
                        }
                        break;
                    case -4:
                        newWidth = initialWidth - (event.getXOnScreen() - newX);
                        if (newWidth < 15) {
                            dontChange = true;
                        } else {
                            locationChanged = true;
                            newXCoordinate = initalXOfComponent + (event.getXOnScreen() - newX);
                        }
                        break;
                    default:
                        if (rightOfComponent) {
                            //If stretching X towards the bottom right of the screen
                            newWidth = event.getXOnScreen() - newX + initialWidth;
                            if (newWidth < 15) {
                                newWidth = 15;
                            }
                        } else {
                            //If stretching towards to left of screen
                            //In a random point
                            newWidth = newX - event.getXOnScreen() + initialWidth;
                            if (newWidth < 15) {
                                newWidth = 15;
                            } else {
                                locationChanged = true;
                                newXCoordinate = initalXOfComponent + (event.getXOnScreen() - newX);
                            }
                        }
                        if (bottomOfComponenet) {
                            //If stretching X towards the bottom left of the screen
                            if (postition != 5 && postition != 6) {
                                newHeight = event.getYOnScreen() - newY + initialHeight;
                                if (newHeight < 15) {
                                    newHeight = 15;
                                }
                            }
                        } else {
                            //If stretching towards to left of screen
                            if (postition != 7 && postition != 8) {
                                newHeight = newY - event.getYOnScreen() + initialHeight;
                                if (newHeight < 15) {
                                    newHeight = 15;
                                } else {
                                    locationChanged = true;
                                    newYCoordinate = initalYOfComponent + (event.getYOnScreen() - newY);
                                }
                            }
                        }
                        break;
                }

                if (locationChanged) {
                    draggableComponent.setLocation1(newXCoordinate, newYCoordinate, cspptFrame.getNewLayer());
                }
                if (!dontChange) {

                    draggableComponent.resizeComponent(newXCoordinate, newYCoordinate, newWidth, newHeight, cspptFrame.getNewLayer());
                }
            } else {
                int newX1 = event.getXOnScreen() - newX;
                int newY1 = event.getYOnScreen() - newY;
                draggableComponent.setLocation1(newX1, newY1, cspptFrame.getNewLayer());
            }
        } else {
            //Dragging function
            int newX1 = event.getXOnScreen() - newX;
            int newY1 = event.getYOnScreen() - newY;
            if (event.getComponent() instanceof ExtendedJtextArea) {
                DraggableComponent draggableComponent = ((ExtendedJtextArea) event.getComponent()).getTextBox();
                draggableComponent.setLocation1(newX1, newY1, cspptFrame.getNewLayer());
            } else if (event.getComponent() instanceof ImageJLabel) {
                ImageComponent draggableComponent = ((ImageJLabel) event.getComponent()).getImageComponent();
                draggableComponent.setLocation1(newX1, newY1, cspptFrame.getNewLayer());
            } else if (event.getComponent() instanceof ShapeBorder) {
                DraggableComponent draggableComponent = (DraggableComponent) ((ShapeBorder) event.getComponent()).getPanel();
                draggableComponent.setLocation1(newX1, newY1, cspptFrame.getNewLayer());
            } else if (event.getComponent() instanceof ExtendedCodeSection) {
                DraggableComponent draggableComponent = ((ExtendedCodeSection) event.getComponent()).getCodeSectionEditor();
                draggableComponent.setLocation1(newX1, newY1, cspptFrame.getNewLayer());
            } else if (event.getComponent() instanceof CodeArea) {
                DraggableComponent draggableComponent = ((CodeArea) event.getComponent()).getCodeSectionEditor();
                draggableComponent.setLocation1(newX1, newY1, cspptFrame.getNewLayer());
            } else if (event.getComponent() instanceof ExtendedJTable) {
                DraggableComponent draggableComponent = ((ExtendedJTable) event.getComponent()).getTableComponent();
                draggableComponent.setLocation1(newX1, newY1, cspptFrame.getNewLayer());
            } else if (event.getComponent() instanceof ExtendedChartPanel) {
                DraggableComponent draggableComponent = ((ExtendedChartPanel) event.getComponent()).getChartComponent();
                draggableComponent.setLocation1(newX1, newY1, cspptFrame.getNewLayer());
            } else if (event.getComponent() instanceof DraggableComponent draggableComponent) {
                draggableComponent.setLocation1(newX1, newY1, cspptFrame.getNewLayer());
            }
        }


    }


    // handle event when user moves mouse
    @Override
    public void mouseMoved(MouseEvent event) {

    }

    //Is called when the increase font size button is called
    public void increaseFontSize() {
        if (lastClicked instanceof TextBox) {
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).increaseFontSize();
        }
    }

    public void decreaseFontSize() {
        if (lastClicked instanceof TextBox) {
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).decreaseFontSize();
        }
    }

    public void changeFont(String fontName) {
        if (lastClicked instanceof TextBox) {
            MainSlideManager.updateSlideIcon();
            ((TextBox) lastClicked).changeFont(fontName);
        }
    }

    public void changeFontSize(Integer fontSize) {
        if (lastClicked instanceof TextBox) {
            MainSlideManager.updateSlideIcon();
            ((TextBox) lastClicked).changeFontSize(fontSize);
        }
    }

    public void setBold() {
        if (lastClicked instanceof TextBox) {
            MainSlideManager.updateSlideIcon();
            TextBox textBox = (TextBox) lastClicked;
            textBox.boldFunction();
        }
    }

    public void setItalic() {
        if (lastClicked instanceof TextBox) {
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).italicFunction();
        }
    }

    public void setFontColor() {
        if (lastClicked instanceof TextBox) {
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).changeFontColour();
        }
    }

    public void setBackgroundColor() {
        //TODO add more components
        if (lastClicked == null) {
            cspptFrame.changeBackgroundColour();
        } else {
            if (lastClicked instanceof TextBox) {
                MainSlideManager.updateSlideIcon();
                ((TextBox) lastClicked).changeBackgroundColour();
            } else if (lastClicked instanceof BaseShape) {
                MainSlideManager.updateSlideIcon();
                ((BaseShape) lastClicked).changeBackgroundColour();
            }
        }
    }

    public void RightAlign() {
        if (lastClicked instanceof TextBox) {
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).alignRightFunction();
        }
    }

    public void LeftAlign() {
        if (lastClicked instanceof TextBox) {
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).alignLeftFunction();
        }
    }

    public void CenterAlign() {
        if (lastClicked instanceof TextBox) {
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).alignCenterFunction();
        }
    }

    public void Underline() {
        if (lastClicked instanceof TextBox) {
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).underlineFunction();
        }
    }

    public void Bullet() {
        if (lastClicked instanceof TextBox) {
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).bulletpointFunction();
        }
    }

    public void Strikethrough() {
        if (lastClicked instanceof TextBox) {
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).strikethroughFunction();
        }
    }

    public void LineSpacing() {
        if (lastClicked instanceof TextBox) {
            MainSlideManager.updateSlideIcon();

            ((TextBox) lastClicked).lineSpacingFunction();
        }

    }

    public void setMainSlideManager(SlideManager slideManager) {
        this.MainSlideManager = slideManager;
    }

    public SlideManager getSlideManager() {

        return this.MainSlideManager;
    }

    public DisplayComponent getLastClicked() {
        return lastClicked;
    }

    public int getLastClickSlide() {
        return lastClickSlide;
    }

    public void enableHandler() {
        handlerEnabled = true;
    }

    public void disableHandler() {
        handlerEnabled = false;
    }

    public int getSelectedSlide() {
        return selectedSlide;
    }

    public void setLastClicked(DisplayComponent comp) {
        lastClicked = comp;
    }

    public void resetLastInputted() {
        if (lastClicked != null) {
            lastClicked.setInvisible();
            lastClicked = null;
        }
    }
}
