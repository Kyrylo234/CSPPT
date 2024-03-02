package org.lancaster.group77.Frame.Buttons.Insert;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.CodeSection.CodeSectionEditor;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;

public class InsertCodeSectionButton extends IconButton {
    private MouseHandler handler;

    public InsertCodeSectionButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole, MouseHandler inputHandler) {
        super(imageIcon, x, y, width, height, inputButtonRole);
        handler = inputHandler;
    }

    public void newCodeSectionFunction() {
        if(GlobalVariables.cspptFrame.getCurrentSlideInt() != -1){
            CodeSectionEditor codeSectionEditor = new CodeSectionEditor(50, 20, 350, 200, handler, GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getNewLayer(), GlobalVariables.cspptFrame.getCurrentSlideInt());
            GlobalVariables.handler.resetLastInputted();
            GlobalVariables.cspptFrame.addToFrame(codeSectionEditor);
        }
    }
}
