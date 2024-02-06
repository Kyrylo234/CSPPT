package org.lancaster.group77.Frame.Buttons.Insert;

import org.lancaster.group77.Frame.Bars.InsertBar;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.CodeSection.CodeRunButton;
import org.lancaster.group77.InsertComponents.CodeSection.CodeSectionEditor;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class InsertCodeSectionButton extends IconButton {
    private MouseHandler handler;
    private boolean isSlideInitialized = false;
    private InsertBar bar;

    public InsertCodeSectionButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole, MouseHandler inputHandler, InsertBar bar) {
        super(imageIcon, x, y, width, height, inputButtonRole);
        this.bar = bar;
        handler = inputHandler;
    }

    public void newCodeSectionFunction() {
        CodeSectionEditor codeSectionEditor = new CodeSectionEditor(100, 100, 200, 100, handler, bar.getFrame().getPane(), bar.getFrame().getFile(), bar.getFrame().getSizeOfArray());
        bar.getFrame().getPane().add(codeSectionEditor);
        bar.getFrame().getPane().moveToFront(codeSectionEditor);

        //add a button let code run
        CodeRunButton runButton = new CodeRunButton(80, 80, 20, 20, handler, bar, codeSectionEditor);
        codeSectionEditor.setCodeRunButton(runButton);
        bar.getFrame().getPane().add(runButton);
        bar.getFrame().getPane().moveToFront(runButton);
        bar.getFrame().getPane().repaint();
    }
}
