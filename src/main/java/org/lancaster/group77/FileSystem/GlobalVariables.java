package org.lancaster.group77.FileSystem;

import org.lancaster.group77.DisplayComponents.*;
import org.lancaster.group77.DisplayComponents.Image;
import org.lancaster.group77.DisplayComponents.Shape;
import org.lancaster.group77.Frame.CSPPTFrame;

import java.awt.*;
import java.awt.event.MouseListener;

import org.lancaster.group77.Frame.Presentation.PresentationFrame;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

public class GlobalVariables {
    //TODO FileSystem add more attributes
    public static final Class[] ALL_COMPONENTS = new Class[]{Text.class, Image.class, Shape.class, CodeSection.class, Audio.class, MathChart.class, Table.class, Video.class, Animation.class, SlideData.class, CSBox.class, CSLine.class};
    public static CSPPTFrame cspptFrame;
    public static MouseHandler handler;
    public static int THEME_COLOR = Color.magenta.getRGB();
    public static int DEFAULT_BACKGROUND_COLOR = -14277082;
    public static double FRAME_SIZE_INDEX_X = 1.0;
    public static double FRAME_SIZE_INDEX_Y = 1.0;
    public static double FRAME_SIZE_INDEX = 1.0;
    public static int TOP_LAYER = 0;
    public static PresentationFrame CURRENT_PRESENTATION_FRAME;
    public static boolean IS_PRESENTING = false;
}
