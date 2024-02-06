package org.lancaster.group77.InsertComponents;

import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;
import javax.swing.*;
import java.awt.*;

public class ImageComponent extends DraggableComponent{
    private ImageJLabel picture;
    private Image image;
    private org.lancaster.group77.DisplayComponents.Image imageData;

    public ImageComponent(int x, int y, int width, int height, MouseHandler listener, String location, JLayeredPane frame, CSPPTFile file) {
        super(x, y, width, height, listener, frame, file);
        ImageIcon imageIcon = new ImageIcon(location);
        String fileType = location.substring(location.lastIndexOf(".") + 1);
        image = imageIcon.getImage();
        Image image1 = image.getScaledInstance(width - 14, height - 14, Image.SCALE_SMOOTH);
        imageIcon.setImage(image1);
        picture = new ImageJLabel(imageIcon, this, listener);
        add(picture);
        picture.setBounds(7, 7, width - 14, height - 14);
        imageData = new org.lancaster.group77.DisplayComponents.Image();
        imageData.setX(x);
        imageData.setY(y);
        imageData.setFileType(fileType);
        imageData.convertImageToBase64(image, fileType);

        //TODO get the slide number
        file.getSlides().get(0).addObject(imageData);
    }

    public ImageComponent(int x, int y, int width, int height, MouseHandler listener, String location, JLayeredPane frame, CSPPTFile file, int layer) {
        super(x, y, width, height, listener, frame, file);
        ImageIcon imageIcon = new ImageIcon(location);
        String fileType = location.substring(location.lastIndexOf(".") + 1);
        image = imageIcon.getImage();
        Image image1 = image.getScaledInstance(width - 14, height - 14, Image.SCALE_SMOOTH);
        imageIcon.setImage(image1);
        picture = new ImageJLabel(imageIcon, this, listener);
        add(picture);
        picture.setBounds(7, 7, width - 14, height - 14);
        imageData = new org.lancaster.group77.DisplayComponents.Image();
        imageData.setX(x);
        imageData.setY(y);
        imageData.setLayer(layer);
        imageData.setFileType(fileType);
        imageData.convertImageToBase64(image, fileType);

        //TODO get the slide number
        file.getSlides().get(0).addObject(imageData);
    }

    public void resizeImage(int inputX, int inputY) {
        picture.setSize(inputX - 14, inputY - 14);
        Image image1 = image.getScaledInstance(inputX - 14, inputY - 14, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(image1);
        picture.setIcon(imageIcon);
        imageData.setWidth(inputX);
        imageData.setHeight(inputY);
    }

    public void setLocation1(int x, int y) {
        imageData.setX(x);
        imageData.setY(y);
    }

    public int getLayer() {
        return imageData.getLayer();
    }
}