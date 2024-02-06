package org.lancaster.group77.InsertComponents;

import javax.swing.*;

public class ExtendedJtextArea extends JTextPane {
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
