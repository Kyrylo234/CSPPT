package org.lancaster.group77.InsertComponents.CodeSection;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CodeRunButton extends JButton {
    private CodeSectionComponent component;
    private JLayeredPane codeRunPanel;

    public CodeRunButton(int x, int y, int width, int height, MouseHandler listener, CodeSectionEditor codeSectionEditor) {
        super();

        setBounds(codeSectionEditor.getX() - 20, codeSectionEditor.getY(), width, height);
        setOpaque(false);
        setBorder(null);
        setContentAreaFilled(false);
        Image img = new ImageIcon("src/main/resources/Icon/Code Section/run.png").getImage();
        Image scaledImg = img.getScaledInstance(width - 4, height - 4, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        setIcon(scaledIcon);

        component = new CodeSectionComponent(codeSectionEditor.getX() + 7, codeSectionEditor.getY() + codeSectionEditor.getHeight() - 7, codeSectionEditor.getWidth() - 14, 80, listener, GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile());
        codeSectionEditor.setCodeSectionComponent(component);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    component.runCode(generateHtmlWithJs(codeSectionEditor.getCodeArea().getText()),codeRunPanel);
                    codeRunPanel.repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     * Generate a html file with js code
     *
     * @param jsCode
     * @throws IOException
     */
    public String generateHtmlWithJs(String jsCode) throws IOException {
        String rewriteMethod = "var oldConsoleLog = console.log;\n" +
                "console.log = function (message) {\n" +
                "    oldConsoleLog(message);\n" +
                "    var resultDiv = document.getElementById('result');\n" +
                "    if (resultDiv) resultDiv.innerHTML += message + '<br>';\n" +
                "};\n";

        String htmlContent = "<!DOCTYPE html>\n"
                + "<html>\n<head>\n<title>Test JS Execution</title>\n</head>\n"
                + "<body style='background-color: #10102D;color: #FFFFFF;margin: 5; padding: 0;'>\n"
                + "<div id='result'>"
                + ">>"
                + "</div>\n"
                + "<div style='position: fixed; bottom: 0; left: 2; font-size: 10px; color:#b7b7b7'>Results</div>\n"
                + "<script>\n" + rewriteMethod + jsCode + "\n</script>\n"
                + "</body>\n</html>";

        return htmlContent;
    }

    public JLayeredPane getCodeRunPanel() {
        return codeRunPanel;
    }

    public void setCodeRunPanel(JLayeredPane codeRunPanel) {
        this.codeRunPanel = codeRunPanel;
    }
}
