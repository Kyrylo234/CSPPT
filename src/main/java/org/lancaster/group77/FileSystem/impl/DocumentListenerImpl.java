package org.lancaster.group77.FileSystem.impl;

import org.lancaster.group77.FileSystem.Tools.GeneralTools;
import org.lancaster.group77.InsertComponents.TextBox;

import javax.swing.event.DocumentListener;

public class DocumentListenerImpl implements DocumentListener {
    private TextBox textBox;
    private Object object;

    public DocumentListenerImpl(TextBox textBox1) {
        textBox = textBox1;
        object = textBox;
    }

    public DocumentListenerImpl(Object object) {
        this.object = object;
    }

    public void changedUpdate(javax.swing.event.DocumentEvent e) {

    }

    public void insertUpdate(javax.swing.event.DocumentEvent e) {
        // text has been added to the textarea
//        textBox.saveText();
        GeneralTools.invokeSaveMethods(object);
    }

    public void removeUpdate(javax.swing.event.DocumentEvent e) {
        // text has been removed from the textarea
//        textBox.saveText();
        GeneralTools.invokeSaveMethods(object);
    }
}
