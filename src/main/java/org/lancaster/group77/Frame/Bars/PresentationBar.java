package org.lancaster.group77.Frame.Bars;

import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.impl.ActionListenerImplmenetation;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.Frame.Buttons.Presentation.FromBeginningButton;
import org.lancaster.group77.Frame.CSPPTFrame;

import javax.swing.*;

public class PresentationBar extends BaseBar {

    CSPPTFrame cspptFrame;

    public PresentationBar(JTabbedPane tabbedPane, JPanel PresentationTab, ActionListenerImplmenetation listener, CSPPTFrame cspptFrame){
        super(tabbedPane,PresentationTab);

        this.cspptFrame = cspptFrame;

        tabbedPane.addTab("Presentation", PresentationTab);
        PresentationTab.setLayout(null);

        FromBeginningButton fromBeginningButton = new FromBeginningButton(new ImageIcon("src/main/resources/Icon/Presentation Bar/from_beginning.png"),10,15,30,35,"From_Beginning",cspptFrame);
        PresentationTab.add(fromBeginningButton);
        PresentationTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Presentation Bar/from_current_slide.png"),60,15,30,35,"From_Current"));
        PresentationTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Presentation Bar/record.png"),110,15,30,35,"Record"));

        PresentationTab.add(createLabel("From",8,10,40,50,30));
        PresentationTab.add(createLabel("From",8,60,40,50,30));
        PresentationTab.add(createLabel("Record",8,110,40,50,30));
        PresentationTab.add(createLabel("Beginning",8,10,50,50,30));
        PresentationTab.add(createLabel("Current",8,60,50,50,30));

        fromBeginningButton.addActionListener(listener);
    }

}
