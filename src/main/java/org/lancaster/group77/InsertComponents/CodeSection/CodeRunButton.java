package org.lancaster.group77.InsertComponents.CodeSection;

import org.lancaster.group77.Frame.Bars.InsertBar;
import org.lancaster.group77.Frame.Buttons.Insert.InsertCodeSectionButton;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CodeRunButton extends JButton {
    private InsertBar bar;
    private CodeSectionComponent component;
    private int count = 0;

    public CodeRunButton(int x, int y, int width, int height, MouseHandler listener, InsertBar bar, CodeSectionEditor codeSectionEditor) {
        super();
        this.bar = bar;

        setBounds(codeSectionEditor.getX()-20, codeSectionEditor.getY(), width, height);
        setOpaque(false);
        setBorder(null);
        setContentAreaFilled(false);
        Image img = new ImageIcon("src/main/resources/Icon/Code Section/run.png").getImage();
        Image scaledImg = img.getScaledInstance(width - 4, height - 4, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        setIcon(scaledIcon);

        component = new CodeSectionComponent(codeSectionEditor.getX(), codeSectionEditor.getY() + codeSectionEditor.getHeight(), 200, 100, listener, bar.getFrame().getPane(), bar.getFrame().getFile());
        codeSectionEditor.setCodeSectionComponent(component);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    component.runCode(generateHtmlWithJs(codeSectionEditor.getjTextPane().getText()), count);
                    if (count == 0) {
                        bar.getFrame().getPane().add(component);
                        bar.getFrame().getPane().moveToFront(component);
                    }
                    bar.getFrame().getPane().repaint();
                    count++;
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
    public static String generateHtmlWithJs(String jsCode) throws IOException {
        String rewriteMethod = "var oldConsoleLog = console.log;\n" +
                "console.log = function (message) {\n" +
                "    oldConsoleLog(message);\n" +
                "    var resultDiv = document.getElementById('result');\n" +
                "    if (resultDiv) resultDiv.innerHTML += message + '<br>';\n" +
                "};\n";

        String htmlContent = "<!DOCTYPE html>\n"
                + "<html>\n<head>\n<title>Test JS Execution</title>\n</head>\n<body>\n"
                + "<div id='result'></div>\n"
                + "<script>\n" + rewriteMethod + jsCode + "\n</script>\n"
                + "</body>\n</html>";

        return htmlContent;
    }
}
