package org.lancaster.group77.Frame.Buttons.Animations;

import org.lancaster.group77.Frame.Presentation.PresentationFrame;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationOptions extends JDialog {
    PresentationFrame pframe;
    int slideno;

    public AnimationOptions(MouseHandler handler, JLayeredPane frame){
        setSize(300, 150);
        setLayout(new GridLayout(3, 1));

        JButton cubeButton = new JButton("Cube");
        cubeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pframe.setAnimation(slideno);

            }
        });
    }
}
