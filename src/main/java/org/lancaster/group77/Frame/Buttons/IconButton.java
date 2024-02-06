package org.lancaster.group77.Frame.Buttons;

import javax.swing.*;
import java.awt.*;

public class IconButton extends BaseButton {
    public IconButton(ImageIcon imageIcon,int x, int y, int width, int height, String inputButtonRole){
        super(x,y,width,height, inputButtonRole);
        setBackground(Color.LIGHT_GRAY);
        Image img = imageIcon.getImage();
        Image scaledImg = img.getScaledInstance(width-4, height-4, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        this.setIcon(scaledIcon);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setBorderPainted(false);
    }
}