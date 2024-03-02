package org.lancaster.group77.Frame.Buttons.File;

import com.sun.jna.platform.win32.Ntifs;
import javafx.stage.Popup;
import org.lancaster.group77.FileSystem.FileChooser;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.SaveAsFileChooser;
import org.lancaster.group77.FileSystem.Tools.GeneralTools;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.Frame.Dialogs.PopupDialog;
import org.lancaster.group77.Frame.Slides.SlideContent;
import org.lancaster.group77.InsertComponents.Image.ImageComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ExportAsPDFButton extends IconButton {
    public ExportAsPDFButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputRole) {
        super(imageIcon, x, y, width, height, inputRole);
    }

    public void exportAsPDFFunction() {
        SaveAsFileChooser saveAsFileChooser = new SaveAsFileChooser();
        String location = saveAsFileChooser.getSaveAsFileLocationPDF();
        if (location != null) {
            ArrayList<JLayeredPane> jLayeredPanes = new ArrayList<>();

            for (SlideContent slideContent : GlobalVariables.cspptFrame.getSlideManager().getSlideContents()) {
                JLayeredPane pane = new JLayeredPane();
                pane.setLayout(null);
                pane.setBounds(0, 0, GlobalVariables.cspptFrame.getPane().getWidth(), GlobalVariables.cspptFrame.getPane().getHeight());
                slideContent.showFrameComponents(pane);
                jLayeredPanes.add(pane);
            }

            GeneralTools.saveAsPDF(jLayeredPanes, location);

            GlobalVariables.cspptFrame.getSlideManager().refreshSlideIcon();

            new PopupDialog("PDF successfully created", "Success");
        }
    }
}
