package org.lancaster.group77.Frame.RightClickMenus;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.Frame.Buttons.Home.CopyButton;
import org.lancaster.group77.Frame.Buttons.Home.CutButton;
import org.lancaster.group77.Frame.Buttons.Home.PasteButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SlideListRightClickMenu extends BaseRightClickMenu {
    private JMenuItem cut;
    private JMenuItem copy;
    private JMenuItem paste;
    private JMenuItem delete;
    private JMenuItem newSlide;


    public SlideListRightClickMenu() {
        super();

        newSlide = new JMenuItem("New Slide");
        delete = new JMenuItem("Delete");
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");

        add(newSlide);
        add(delete);
        add(cut);
        add(copy);
        add(paste);

        newSlide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlobalVariables.cspptFrame.getSlideManager().addSlide();
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int deleting_slide = GlobalVariables.cspptFrame.getHandler().getSelectedSlide();
                GlobalVariables.cspptFrame.removeSlide(deleting_slide);
            }
        });
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
                    PasteButton.paste();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }


}
