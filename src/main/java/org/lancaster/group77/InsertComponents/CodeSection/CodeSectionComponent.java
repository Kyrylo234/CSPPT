package org.lancaster.group77.InsertComponents.CodeSection;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.InsertComponents.DraggableComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class CodeSectionComponent extends DraggableComponent {
    private JFXPanel jfxPanel;
    private JLayeredPane frame;

    public CodeSectionComponent(int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame, CSPPTFile file) {
        super(x, y, width, height, listener, frame, file,"CodeSectionComponent");
        this.frame = frame;

        jfxPanel = new JFXPanel();
        jfxPanel.setBounds(x, y, width, height);
        jfxPanel.setBackground(new Color(16,16,45));
        MatteBorder border = new MatteBorder(0, 1, 1, 1, Color.WHITE);
        jfxPanel.setBorder(border);
    }

    /**
     * This method is used to run the code in the code section
     *
     * @param htmlCode the code to be run
     */
    public void runCode(String htmlCode,JLayeredPane pane) {
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            WebView webView = new WebView();
            jfxPanel.setScene(new Scene(webView));
            webView.getEngine().loadContent(htmlCode);
            if(!pane.isAncestorOf(jfxPanel)){
                pane.add(jfxPanel);
            }
        });
    }

    public JFXPanel getJfxPanel() {
        return jfxPanel;
    }

    public void setJfxPanel(JFXPanel jfxPanel) {
        this.jfxPanel = jfxPanel;
    }
}
