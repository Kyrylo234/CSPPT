package org.lancaster.group77.Frame.Slides;

import org.lancaster.group77.Frame.CSPPTFrame;

import java.awt.*;
import java.awt.image.BufferedImage;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;
public class SlideScreenshot {
    private JLayeredPane jPane;

    public BufferedImage ScreenShot(CSPPTFrame cspptFrame) {
        jPane = cspptFrame.getPane();
        Rectangle panelBounds = jPane.getBounds();
        BufferedImage screenshot = new BufferedImage(panelBounds.width, panelBounds.height, BufferedImage.TYPE_INT_ARGB);
        try {
            Robot robot = new Robot();
            screenshot = robot.createScreenCapture(panelBounds);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return screenshot;
    }
}
