package org.lancaster.group77.Frame.RightClickMenus;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.Frame.Buttons.Home.CommandLineButton;
import org.lancaster.group77.Frame.Buttons.Home.CopyButton;
import org.lancaster.group77.Frame.Buttons.Home.CutButton;
import org.lancaster.group77.Frame.Buttons.Home.PasteButton;
import org.lancaster.group77.Frame.Presentation.PresentationFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SlideEditionRightClickMenu extends BaseRightClickMenu {
    private JMenuItem cut;
    private JMenuItem copy;
    private JMenuItem paste;

    public SlideEditionRightClickMenu() {
        super();

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");

        add(cut);
        add(copy);
        add(paste);

        cut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CutButton.cut();
            }
        });

        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CopyButton.copy();
            }
        });

        paste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PasteButton.pasteWithGivenXY(getX1() / GlobalVariables.FRAME_SIZE_INDEX_X, getY1() / GlobalVariables.FRAME_SIZE_INDEX_Y);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
