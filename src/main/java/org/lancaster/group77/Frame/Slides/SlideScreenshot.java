package org.lancaster.group77.Frame.Slides;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class SlideScreenshot {

    public static BufferedImage ScreenShot(JLayeredPane jPanel){

        Rectangle panelBounds = jPanel.getBounds();
        BufferedImage screenshot  = new BufferedImage(panelBounds.width,panelBounds.height, BufferedImage.TYPE_INT_ARGB);
        try{
            Robot robot = new Robot();
            screenshot = robot.createScreenCapture(panelBounds);
        }catch (AWTException e){
            e.printStackTrace();
        }
        return screenshot;
    }
}
