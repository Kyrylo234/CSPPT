package org.lancaster.group77.Frame.Buttons.Insert;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.Shapes.*;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertShapeButton extends IconButton {
    private MouseHandler handler;
    private ShapeOptions options;

    private boolean isSlideInitialized;
    public InsertShapeButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole, MouseHandler inputHandler) {
        super(imageIcon, x, y, width, height, inputButtonRole);
        handler = inputHandler;
    }

    public void openOptions(){
        if(options != null){
            options.dispose();
        }
        options = new ShapeOptions(handler, GlobalVariables.cspptFrame.getPane());
        options.setTitle("Insert Shape");
        options.setLocationRelativeTo(this);
        options.setIconImage(new ImageIcon("src/main/resources/Icon/csppt_logo.png").getImage());
        options.setVisible(true);
    }

    private void initializeSlide() {
        if (!isSlideInitialized) {
            isSlideInitialized = true;
        }
    }

    public void InsertShapeFunction(){
        if(GlobalVariables.cspptFrame.getCurrentSlideInt() != -1){
            initializeSlide();
            openOptions();
        }
    }

}
