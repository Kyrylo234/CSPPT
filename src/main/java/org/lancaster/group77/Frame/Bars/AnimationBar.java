package org.lancaster.group77.Frame.Bars;

import org.lancaster.group77.FileSystem.impl.ActionListenerImplementation;
import org.lancaster.group77.Frame.Buttons.Animations.AnimationButton;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;

public class AnimationBar extends BaseBar {
    private MouseHandler handler;
    private JComboBox<String> durationBox;

    public AnimationBar(JTabbedPane tabbedPane, JPanel AnimationTab, MouseHandler handler, ActionListenerImplementation listener) {
        super(tabbedPane, AnimationTab);
        tabbedPane.addTab("Animations", AnimationTab);
        AnimationTab.setLayout(null);
        this.handler = handler;

        AnimationButton animationButton = new AnimationButton(new ImageIcon("src/main/resources/Icon/Animation Bar/animation.png"), 10, 15, 30, 35, "Animations", handler);
        AnimationTab.add(animationButton);
        AnimationTab.add(createLabel("Animations", 8, 3, 45, 50, 30));

        AnimationTab.add(createLabel("Timings", 8, 145, 65, 50, 30));

        AnimationTab.add(createSeparator(55));

        AnimationTab.add(createLabel("Duration: ", 12, 70, 27, 80, 30));

        String[] durationOptions = {"0.5s", "1s", "1.5s", "2s", "2.5s", "3s", "3.5s", "4s", "4.5s", "5s"};
        durationBox = createDropDown(durationOptions, 140, 33, 100, 15);
        AnimationTab.add(durationBox);

        AnimationTab.add(createSeparator(260));

        animationButton.addActionListener(listener);

    }

    public JComboBox<String> getDurationBox() {
        return durationBox;
    }
}
