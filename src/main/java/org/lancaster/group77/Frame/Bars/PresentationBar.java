package org.lancaster.group77.Frame.Bars;

import org.bytedeco.libfreenect._freenect_context;
import org.lancaster.group77.FileSystem.impl.ActionListenerImplementation;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.Frame.Buttons.Presentation.FromBeginningButton;
import org.lancaster.group77.Frame.Buttons.Presentation.FromCurrentButton;
import org.lancaster.group77.Frame.Buttons.Presentation.RecordButton;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;

public class PresentationBar extends BaseBar {

    public PresentationBar(JTabbedPane tabbedPane, JPanel PresentationTab, ActionListenerImplementation listener, MouseHandler handler){
        super(tabbedPane,PresentationTab);

        tabbedPane.addTab("Presentation", PresentationTab);
        PresentationTab.setLayout(null);

        FromBeginningButton fromBeginningButton = new FromBeginningButton(new ImageIcon("src/main/resources/Icon/Presentation Bar/from_beginning.png"),10,15,30,35,"From_Beginning",handler );
        FromCurrentButton fromCurrentButton = new FromCurrentButton(new ImageIcon("src/main/resources/Icon/Presentation Bar/from_current_slide.png"),60,15,30,35,"From_Current",handler );
        PresentationTab.add(fromBeginningButton);
        PresentationTab.add(fromCurrentButton);
        RecordButton recordButton = new RecordButton(new ImageIcon("src/main/resources/Icon/Presentation Bar/record.png"),110,15,30,35,"Record", handler);
        PresentationTab.add(recordButton);
//        PresentationTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Presentation Bar/record.png"),110,15,30,35,"Record"));

        PresentationTab.add(createLabel("From",8,10,40,50,30));
        PresentationTab.add(createLabel("From",8,60,40,50,30));
        PresentationTab.add(createLabel("Record",8,110,40,50,30));
        PresentationTab.add(createLabel("Beginning",8,10,50,50,30));
        PresentationTab.add(createLabel("Current",8,60,50,50,30));

        fromBeginningButton.addActionListener(listener);
        fromCurrentButton.addActionListener(listener);
        recordButton.addActionListener(listener);
    }

}
