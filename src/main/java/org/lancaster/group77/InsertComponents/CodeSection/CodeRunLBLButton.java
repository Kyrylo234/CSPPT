package org.lancaster.group77.InsertComponents.CodeSection;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CodeRunLBLButton extends JButton {
    private CodeSectionComponent component;
    private JLayeredPane codeRunPanel;
    private int line = 0;

    public CodeRunLBLButton(int x, int y, int width, int height, MouseHandler listener, CodeSectionEditor codeSectionEditor) {
        super();

        setBounds(codeSectionEditor.getX() - 20, codeSectionEditor.getY() + 30, width, height);
        setOpaque(false);
        setBorder(null);
        setContentAreaFilled(false);
        Image img = new ImageIcon("src/main/resources/Icon/Code Section/run_line_by_line.png").getImage();
        Image scaledImg = img.getScaledInstance(width - 4, height - 4, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        setIcon(scaledIcon);

        component = codeSectionEditor.getCodeSectionComponent();

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                line++;
                if (line > countLinesOfCode(codeSectionEditor.getCodeArea().getText())) {
                    line = 1;
                }
                component.runCode(generateHtmlWithJs(codeSectionEditor.getCodeArea().getText(), line), codeRunPanel);
                codeRunPanel.repaint();
                codeSectionEditor.getCodeArea().highlightLine(line, Color.BLUE);
            }
        });
    }

    /**
     * Generate a html file with js code
     * This is line by line version
     *
     * @param jsCode
     * @param line
     */
    public static String generateHtmlWithJs(String jsCode, int line) {
        String analyzeCode = "function analyzeCode(code) {\n" +
                "    if (!code.trim()) return 'Empty line';\n" +
                "    if (/console\\.log\\s*\\(/.test(code)) return 'console.log';\n" +
                "    if (/\\b(var|let|const)\\s+/.test(code)) return 'Variable declaration or assignment';\n" +
                "    if (/function\\s+(\\w*)\\s*\\(/.test(code)) return 'Function declaration';\n" +
                "    if (/\\w+\\s*\\(.*\\)/.test(code)) return 'Function call';\n" +
                "    return 'Executing some code';\n" +
                "}\n";

        String rewriteMethod = "var jsCodeLines = `" + jsCode + "`.split('\\n');\n" +
                "jsCodeLines.forEach(function(line, index) {\n" +
                "    var oldConsoleLog = console.log;\n" +
                "    console.log = function (message) {\n" +
                "        oldConsoleLog(message);\n" +
                "        var resultDiv = document.getElementById('result');\n" +
                "        resultDiv.innerHTML += '<b>Line ' + (index + 1) + ' Output:</b> ' + message + '<br>';\n" +
                "    };\n" +
                "    var analysis = analyzeCode(line);\n" +
                "    var resultDiv = document.getElementById('result');\n" +
                "    if (index + 1 === " + line + ") {\n" +
                "        if (analysis !== 'console.log' && resultDiv) {\n" +
                "            resultDiv.innerHTML += '<b>Line ' + (index + 1) + ':</b> ' + analysis + '<br>';\n" +
                "        }\n" +
                "        eval(line);\n" +
                "    }\n" +
                "    console.log = oldConsoleLog;\n" +
                "});\n";

        String htmlContent = "<!DOCTYPE html>\n" +
                "<html>\n<head>\n<title>Test JS Execution</title>\n</head>\n" +
                "<body style='background-color: #10102D;color: #FFFFFF;margin: 5px; padding: 0;'>\n" +
                "<div id='result' style='white-space: pre-wrap;'></div>\n" +
                "<div style='position: fixed; bottom: 0; left: 2px; font-size: 10px; color:#b7b7b7'>Results</div>\n" +
                "<script>\n" + analyzeCode + rewriteMethod + "\n</script>\n" +
                "</body>\n</html>";

        return htmlContent;
    }

    public static int countLinesOfCode(String jsCode) {
        if (jsCode == null || jsCode.isEmpty()) {
            return 0;
        }

        int lines = 1;
        for (int i = 0; i < jsCode.length(); i++) {
            if (jsCode.charAt(i) == '\n') {
                lines++;
            }
        }

        return lines;
    }

    public JLayeredPane getCodeRunPanel() {
        return codeRunPanel;
    }

    public void setCodeRunPanel(JLayeredPane codeRunPanel) {
        this.codeRunPanel = codeRunPanel;
    }
}
