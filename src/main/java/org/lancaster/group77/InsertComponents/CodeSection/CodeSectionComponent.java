package org.lancaster.group77.InsertComponents.CodeSection;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import org.lancaster.group77.DisplayComponents.CodeSection;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.impl.DocumentListenerImpl;
import org.lancaster.group77.InsertComponents.DraggableComponent;
import org.lancaster.group77.InsertComponents.ExtendedJtextArea;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;

public class CodeSectionComponent extends DraggableComponent {
    private JFXPanel jfxPanel;
    private JLayeredPane frame;

    public CodeSectionComponent(int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame, CSPPTFile file) {
        super(x, y, width, height, listener, frame, file);
        this.frame = frame;

        jfxPanel = new JFXPanel();
        jfxPanel.setBounds(x, y, width, height);
    }

    /**
     * This method is used to run the code in the code section
     *
     * @param htmlCode the code to be run
     */
    public void runCode(String htmlCode, int count) {
        Platform.runLater(() -> {
            WebView webView = new WebView();
            jfxPanel.setScene(new Scene(webView));
            webView.getEngine().loadContent(htmlCode);
            if (count == 0) {
                frame.add(jfxPanel);
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
