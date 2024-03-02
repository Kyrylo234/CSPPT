package org.lancaster.group77.InsertComponents.CodeSection;

import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.lancaster.group77.FileSystem.GlobalVariables;

import javax.swing.text.BadLocationException;
import java.awt.*;

public class CodeArea extends RSyntaxTextArea {
    private CodeSectionEditor codeSectionEditor;
    private Object lastHighlight;
    public CodeArea(CodeSectionEditor codeSectionEditor) {
        this.codeSectionEditor = codeSectionEditor;

        this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
        this.setCodeFoldingEnabled(true);
        this.setAntiAliasingEnabled(true);

        // change theme style
        try {
            Theme darkTheme = Theme.load(CodeArea.class.getResourceAsStream(
                    "/org/fife/ui/rsyntaxtextarea/themes/monokai.xml"));
            darkTheme.apply(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //change theme color
        SyntaxScheme scheme = this.getSyntaxScheme();

        Color themeColor = new Color(GlobalVariables.THEME_COLOR);
        scheme.getStyle(Token.RESERVED_WORD).foreground = themeColor;
        scheme.getStyle(Token.DATA_TYPE).foreground = themeColor;
        scheme.getStyle(Token.FUNCTION).foreground = themeColor;
        scheme.getStyle(Token.VARIABLE).foreground = themeColor;
        this.setSyntaxScheme(scheme);
    }

    public CodeSectionEditor getCodeSectionEditor() {
        return codeSectionEditor;
    }

    public void highlightLine(int lineNumber, Color color) {
        try {
            if (lastHighlight != null) {
                removeLineHighlight(lastHighlight);
            }
            lastHighlight = addLineHighlight(lineNumber - 1, color);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void removeHighlight() {
        if (lastHighlight != null) {
            removeLineHighlight(lastHighlight);
            lastHighlight = null;
        }
    }

}
