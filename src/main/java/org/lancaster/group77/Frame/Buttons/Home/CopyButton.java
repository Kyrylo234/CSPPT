package org.lancaster.group77.Frame.Buttons.Home;

import org.lancaster.group77.DisplayComponents.DisplayComponentBase;
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
import java.util.ArrayList;

public class CopyButton extends IconButton {
    private MouseHandler handler;

    public CopyButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole, MouseHandler handler) {
        super(imageIcon, x, y, width, height, inputButtonRole);
        this.handler = handler;
    }

    public static void copy() {
        ClipboardInterface clipboardInterface = new ClipboardImpl();
        MouseHandler handler = GlobalVariables.cspptFrame.getHandler();

        DisplayComponent displayComponent = handler.getLastClicked();
        if (handler.getLastClickSlide() != -1) {
            // Copy the slide number
            clipboardInterface.copySlideNumber(handler.getLastClickSlide());
            ArrayList<Object> selectedObjects = new ArrayList<>();
            SlideManager slideManager = GlobalVariables.cspptFrame.getSlideManager();
            ArrayList<DisplayComponent> components = slideManager.getSelectedSlide(handler.getLastClickSlide()).getComponentsArray();
            for (DisplayComponent componentOrigin : components) {
                DisplayComponentBase selectedObject = GeneralTools.deepCopy(componentOrigin.getComponentData());
                selectedObjects.add(selectedObject);
            }
            clipboardInterface.copy(selectedObjects);
            ClipboardManager.copyType = "slide";
        } else if (displayComponent != null) {
            ArrayList<Object> selectedObjects = new ArrayList<>();
            DisplayComponentBase selectedObject = GeneralTools.deepCopy(displayComponent.getComponentData());
            selectedObjects.add(selectedObject);
            clipboardInterface.copy(selectedObjects);
            ClipboardManager.copyType = "component";
        }
    }
}
