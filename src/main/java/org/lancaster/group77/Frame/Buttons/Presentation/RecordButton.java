package org.lancaster.group77.Frame.Buttons.Presentation;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.Frame.CSPPTFrame;
import org.lancaster.group77.InsertComponents.ScreenRecordComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;

public class RecordButton extends IconButton {
    public RecordButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputButtonRole, MouseHandler inputHandler) {
        super(imageIcon, x, y, width, height, inputButtonRole);
    }

    public void setRecord(){
        ScreenRecordComponent screenRecordComponent = new ScreenRecordComponent();
        FromBeginningButton.fromBeginningFunction();
        screenRecordComponent.setPresentationFrame(GlobalVariables.CURRENT_PRESENTATION_FRAME);
        GlobalVariables.CURRENT_PRESENTATION_FRAME.setScreenRecordComponent(screenRecordComponent);
        SwingUtilities.invokeLater(screenRecordComponent::buttonAction);
    }
}
