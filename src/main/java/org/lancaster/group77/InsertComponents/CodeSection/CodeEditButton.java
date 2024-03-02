package org.lancaster.group77.InsertComponents.CodeSection;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CodeEditButton extends JButton {
    private JLayeredPane codeRunPanel;

    public CodeEditButton(int x, int y, int width, int height, MouseHandler listener, CodeSectionEditor codeSectionEditor) {
        super();

        setBounds(codeSectionEditor.getX() - 20, codeSectionEditor.getY(), width, height);
        setOpaque(false);
        setBorder(null);
        setContentAreaFilled(false);
        Image img = new ImageIcon("src/main/resources/Icon/Code Section/edit.png").getImage();
        Image scaledImg = img.getScaledInstance(width - 4, height - 4, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        setIcon(scaledIcon);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(codeSectionEditor.getCodeArea().isEditable()){
                    codeSectionEditor.getCodeArea().setEditable(false);
                }else{
                    codeSectionEditor.getCodeArea().setEditable(true);
                }
            }
        });
    }

    public JLayeredPane getCodeRunPanel() {
        return codeRunPanel;
    }

    public void setCodeRunPanel(JLayeredPane codeRunPanel) {
        this.codeRunPanel = codeRunPanel;
    }
}
