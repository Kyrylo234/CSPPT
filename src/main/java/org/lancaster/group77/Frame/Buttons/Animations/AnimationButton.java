package org.lancaster.group77.Frame.Buttons.Animations;

import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.Frame.CSPPTFrame;
import org.lancaster.group77.Frame.Presentation.PresentationFrame;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationButton extends IconButton {
    private MouseHandler handler;
    private JDialog dialog;


    public AnimationButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole, MouseHandler handler) {
        super(imageIcon, x, y, width, height, inputButtonRole);
        this.handler = handler;
    }

    public boolean setAnimation() {
        if (dialog != null) {
            dialog.dispose();
        }
        dialog = new JDialog();
        dialog.setSize(300, 150);
        dialog.setLayout(new GridLayout(3, 1));
        dialog.setIconImage(new ImageIcon("src/main/resources/Icon/csppt_logo.png").getImage());
        dialog.setTitle("Choose Animation");
        String duration_str = (String) GlobalVariables.cspptFrame.getMainAnimationBar().getDurationBox().getSelectedItem();
        int duration = (int) (Double.parseDouble(duration_str.substring(0, duration_str.length() - 1)) * 1000);
        JButton cubeButton = new JButton("Cube");
        cubeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAnimationData("cube", duration);
            }
        });

        JButton fadeButton = new JButton("Fade");
        fadeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAnimationData("fade", duration);
            }
        });

        JButton irisButton = new JButton("Iris");
        irisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAnimationData("iris", duration);
            }
        });

        JButton radialButton = new JButton("Radial");
        radialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAnimationData("radial", duration);
            }
        });

        dialog.add(cubeButton);
        dialog.add(radialButton);
        dialog.add(irisButton);
        dialog.add(fadeButton);

        dialog.setVisible(true);

        return true;

    }

    private void setAnimationData(String animation_name, int duration) {
        CSPPTFrame frame = GlobalVariables.cspptFrame;
        if (!frame.getFile().getSlides().get(frame.getCurrentSlideInt()).getAnimations().isEmpty()) {
            frame.getFile().getSlides().get(frame.getCurrentSlideInt()).getAnimations().remove(0);
        }
        GlobalVariables.cspptFrame.getSlideManager().getSingleSlideContent(GlobalVariables.cspptFrame.getCurrentSlideInt()).setAnimationData(animation_name, duration);
        dialog.dispose();
    }


}
