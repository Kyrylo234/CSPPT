package org.lancaster.group77.InsertComponents.TextBox;

import org.languagetool.JLanguageTool;
import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.RuleMatch;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class SpellingCheckTextPane extends JTextPane {
    /**
     * Create a new text pane with spelling checking
     */
    public SpellingCheckTextPane(){
        Timer timer = new Timer(1000, e -> checkGrammar(this));
        timer.setRepeats(false);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                timer.restart();

                StyledDocument doc = getStyledDocument();
                int insertPos = getCaretPosition();

                SimpleAttributeSet defaultAttributes = new SimpleAttributeSet();
                StyleConstants.setForeground(defaultAttributes, getForeground());
                StyleConstants.setUnderline(defaultAttributes, false);
                doc.setCharacterAttributes(insertPos - 1, 1, defaultAttributes, true);
            }
        });
    }

    /**
     * Check the grammar of the text in the text pane
     * and underline any errors found
     * Create a new thread to avoid blocking the event dispatch thread
     * @param textPane the text pane to check
     */
    private static void checkGrammar(JTextPane textPane) {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                JLanguageTool langTool = new JLanguageTool(new BritishEnglish());
                String text = textPane.getText();
                List<RuleMatch> matches = langTool.check(text);

                StyledDocument doc = textPane.getStyledDocument();
                StyleContext context = new StyleContext();
                AttributeSet empty = context.getEmptySet();
                SwingUtilities.invokeLater(() -> doc.setCharacterAttributes(0, doc.getLength(), empty, true));

                for (RuleMatch match : matches) {
                    int start = match.getFromPos();
                    int end = match.getToPos();
                    SimpleAttributeSet attributes = new SimpleAttributeSet();
                    StyleConstants.setUnderline(attributes, true);
                    StyleConstants.setForeground(attributes, Color.RED);
                    SwingUtilities.invokeLater(() -> doc.setCharacterAttributes(start, end - start, attributes, false));
                }
                return null;
            }
        }.execute();
    }


}
