package org.lancaster.group77.Frame.Buttons.Home;

import org.lancaster.group77.DisplayComponents.*;
import org.lancaster.group77.FileSystem.ClipboardInterface;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.Manager.ClipboardManager;
import org.lancaster.group77.FileSystem.Tools.GeneralTools;
import org.lancaster.group77.FileSystem.impl.ClipboardImpl;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.Frame.Slides.SlideManager;
import org.lancaster.group77.InsertComponents.DisplayComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class PasteButton extends IconButton {
    public PasteButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole) {
        super(imageIcon, x, y, width, height, inputButtonRole);
    }

    public static void paste() throws IOException {
        ClipboardInterface clipboard = new ClipboardImpl();
        List<Object> objectList = clipboard.paste();

        if (ClipboardManager.copyType.equals("slide")) {
            // Paste the slide
            SlideManager slideManager = GlobalVariables.cspptFrame.getSlideManager();
            slideManager.addSlideWithoutRefresh();
            for (int i = 0; i < objectList.size(); i++) {
                DisplayComponentBase displayComponentBase = (DisplayComponentBase) objectList.get(i);
                pasteComponent(displayComponentBase, displayComponentBase.getX(), displayComponentBase.getY());
            }

        } else if (objectList != null && !objectList.isEmpty() && ClipboardManager.copyType.equals("component")) {
            // Paste the object
            DisplayComponentBase displayComponentBase = GeneralTools.deepCopy((DisplayComponentBase) objectList.get(0));
            pasteComponent(displayComponentBase, displayComponentBase.getX() + 10 / GlobalVariables.FRAME_SIZE_INDEX_X, displayComponentBase.getY() + 10 / GlobalVariables.FRAME_SIZE_INDEX_Y);
        }
    }

    public static void pasteWithGivenXY(double x, double y) throws IOException {
        ClipboardInterface clipboard = new ClipboardImpl();
        List<Object> objectList = clipboard.paste();

        if (objectList == null || objectList.isEmpty()) {
            return;
        }
        DisplayComponentBase displayComponentBase = GeneralTools.deepCopy((DisplayComponentBase) objectList.get(0));
        pasteComponent(displayComponentBase, x, y);
    }

    private static void pasteComponent(DisplayComponentBase displayComponentBase, double x, double y) throws IOException {
        displayComponentBase.setX(x);
        displayComponentBase.setY(y);
        displayComponentBase.setLayer(GlobalVariables.cspptFrame.getNewLayer());
        DisplayComponent component = SlideManager.createComponent(displayComponentBase);
        GlobalVariables.cspptFrame.addToFrame(component);
        GlobalVariables.cspptFrame.getFile().getSlides().get(GlobalVariables.cspptFrame.getCurrentSlideInt()).addObject(displayComponentBase);
        ClipboardManager.copyTempFile(GeneralTools.deepCopy(GlobalVariables.cspptFrame.getFile()));
    }
}
