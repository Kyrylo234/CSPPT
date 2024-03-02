package org.lancaster.group77.InsertComponents.TextBox;

import org.lancaster.group77.InsertComponents.TextBox.TextBox;

import javax.swing.*;

public class ExtendedJtextArea extends SpellingCheckTextPane {
    private TextBox textBox;
    public ExtendedJtextArea(TextBox textBox1){
        textBox = textBox1;
    }
    public ExtendedJtextArea(TextBox textBox1, String text){
        textBox = textBox1;
        setText(text);
    }

    public TextBox getTextBox(){
        return textBox;
    }
}
