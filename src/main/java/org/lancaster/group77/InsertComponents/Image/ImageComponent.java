package org.lancaster.group77.InsertComponents.Image;

import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.InsertComponents.DraggableComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageComponent extends DraggableComponent {
    private ImageJLabel picture;
    private Image image;
    private org.lancaster.group77.DisplayComponents.Image imageData;

    public ImageComponent(int x, int y, int width, int height, MouseHandler listener, String location, JLayeredPane frame, CSPPTFile file, int slideNum) {
        super(x, y, width, height, listener, frame, file, "ImageComponent");
        ImageIcon imageIcon = new ImageIcon(location);
        String fileType = location.substring(location.lastIndexOf(".") + 1);
        image = imageIcon.getImage();
        Image image1 = image.getScaledInstance(width - 14, height - 14, Image.SCALE_SMOOTH);
        imageIcon.setImage(image1);
        picture = new ImageJLabel(imageIcon, this, listener);
        add(picture);
        picture.setBounds(7, 7, width - 14, height - 14);
        DraggableComponent.initRightClickMenu(picture);


        //init the image data
        imageData = new org.lancaster.group77.DisplayComponents.Image();
        this.setComponentData(imageData);
        imageData.setX(x/GlobalVariables.FRAME_SIZE_INDEX_X);
        imageData.setY(y/GlobalVariables.FRAME_SIZE_INDEX_Y);
        imageData.setWidth(width/GlobalVariables.FRAME_SIZE_INDEX_X);
        imageData.setHeight(height/GlobalVariables.FRAME_SIZE_INDEX_Y);
        imageData.setFileType(fileType);
        imageData.convertImageToBase64(image, fileType);

        file.getSlides().get(slideNum).addObject(imageData);
    }

    public ImageComponent(org.lancaster.group77.DisplayComponents.Image imageData, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int slideNum) throws IOException {
        super((int) imageData.getX(), (int) imageData.getY(), (int) imageData.getWidth(), (int) imageData.getHeight(), listener, frame, file, "ImageComponent");
        initImageComponent(listener, imageData);

        file.getSlides().get(slideNum).addObject(imageData);
    }

    public ImageComponent(org.lancaster.group77.DisplayComponents.Image imageData, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int slideNum, boolean isOpeningFile) throws IOException {
        super((int) (imageData.getX()* GlobalVariables.FRAME_SIZE_INDEX_X), (int) (imageData.getY()* GlobalVariables.FRAME_SIZE_INDEX_Y), (int) (imageData.getWidth()* GlobalVariables.FRAME_SIZE_INDEX_X), (int) (imageData.getHeight()* GlobalVariables.FRAME_SIZE_INDEX_Y), listener, frame, file, "ImageComponent");

        initImageComponent(listener, imageData);

        if (!isOpeningFile)
            file.getSlides().get(slideNum).addObject(imageData);
    }

    private void initImageComponent(MouseHandler listener, org.lancaster.group77.DisplayComponents.Image imageData) throws IOException {
        byte[] imageBytes = Base64.getDecoder().decode(imageData.getImageBase64());
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));

        ImageIcon imageIcon = new ImageIcon(img);
        image = img;
        Image image1 = image.getScaledInstance((int) (imageData.getWidth()* GlobalVariables.FRAME_SIZE_INDEX_X) - 14, (int) (imageData.getHeight()* GlobalVariables.FRAME_SIZE_INDEX_Y) - 14, Image.SCALE_SMOOTH);
        imageIcon.setImage(image1);
        picture = new ImageJLabel(imageIcon, this, listener);

        add(picture);
        picture.setBounds(7, 7, (int) (imageData.getWidth()* GlobalVariables.FRAME_SIZE_INDEX_X) - 14, (int) (imageData.getHeight()* GlobalVariables.FRAME_SIZE_INDEX_Y) - 14);

        //init the image data
        this.imageData = imageData;
        this.setComponentData(imageData);
    }


    @Override
    public void resizeComponent(int inputX, int inputY, int inputWidth, int inputHeight, int layer) {
        picture.setSize(inputWidth - 14, inputHeight - 14);
        Image image1 = image.getScaledInstance(inputWidth - 14, inputHeight - 14, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(image1);
        picture.setIcon(imageIcon);
        super.resizeComponent(inputX, inputY, inputWidth, inputHeight, layer);
    }

    @Override
    public void resizeComponent(int inputX, int inputY, int inputWidth, int inputHeight) {
        picture.setSize(inputWidth - 14, inputHeight - 14);
        Image image1 = image.getScaledInstance(inputWidth - 14, inputHeight - 14, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(image1);
        picture.setIcon(imageIcon);
        super.resizeComponent(inputX, inputY, inputWidth, inputHeight);
    }

    @Override
    public void setLocation(int x, int y) {
        imageData.setX(x);
        imageData.setY(y);
        super.setLocation(x, y);
    }

    public int getLayer() {
        return imageData.getLayer();
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public org.lancaster.group77.DisplayComponents.Image getImageData() {
        return imageData;
    }

    public void setImageData(org.lancaster.group77.DisplayComponents.Image imageData) {
        this.imageData = imageData;
    }
}