package org.lancaster.group77.Frame.Buttons.Home;

import org.lancaster.group77.FileSystem.ClipboardInterface;
import org.lancaster.group77.FileSystem.impl.ClipboardImpl;
import org.lancaster.group77.Frame.Buttons.IconButton;

import javax.swing.*;
import java.util.List;

public class PasteButton extends IconButton {
    public PasteButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole) {
        super(imageIcon, x, y, width, height, inputButtonRole);
    }

    public void paste(){
        System.out.println("paste");

        ClipboardInterface clipboard = new ClipboardImpl();
        List<Object> objectList = clipboard.paste();
        //TODO print object list to slide
    }
}
