package org.lancaster.group77.Frame.Bars;

import org.lancaster.group77.Frame.Buttons.IconButton;

import javax.swing.*;

public class AnimationBar extends BaseBar{

    public AnimationBar(JTabbedPane tabbedPane, JPanel AnimationTab){
        super(tabbedPane,AnimationTab);
        tabbedPane.addTab("Animations", AnimationTab);
        AnimationTab.setLayout(null);

        AnimationTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Animation Bar/animation.png"),10,15,30,35,"Shapes"));
        AnimationTab.add(createLabel("Animations",8,3,45,50,30));
        AnimationTab.add(createLabel("Timings",8,145,65,50,30));

        AnimationTab.add(createSeparator(55));

        AnimationTab.add(createLabel("Start: ",12,70,7,80,30));
        AnimationTab.add(createLabel("Duration: ",12,70,27,80,30));
        AnimationTab.add(createLabel("Delay: ",12,70,47,80,30));

        String[] startOptions = {"On Click", "With Previous", "After Previous"};
        AnimationTab.add(createDropDown(startOptions,140,13,100,15));

        Integer[] durationOptions = {1,2,3,4,5};
        AnimationTab.add(createDropDown(durationOptions,140,33,100,15));

        Integer[] delayOptions = {0,1,2,3,4,5};
        AnimationTab.add(createDropDown(delayOptions,140,53,100,15));

        AnimationTab.add(createSeparator(260));

    }

}
