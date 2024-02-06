package org.lancaster.group77.InsertComponents.CodeSection;

import org.lancaster.group77.DisplayComponents.CodeSection;
import org.lancaster.group77.DisplayComponents.Text;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.impl.DocumentListenerImpl;
import org.lancaster.group77.InsertComponents.DraggableComponent;
import org.lancaster.group77.InsertComponents.ExtendedJtextArea;
import org.lancaster.group77.InsertComponents.TextBox;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;

public class CodeSectionEditor extends DraggableComponent {
    private ExtendedCodeSection jTextPane;
    private CodeSection codeSection;
    private CodeRunButton codeRunButton;
    private CodeSectionComponent codeSectionComponent;

    public CodeSectionEditor(int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int layer) {
        super(x, y, width, height, listener, frame, file);
        jTextPane = new ExtendedCodeSection(this);
        add(jTextPane);
        jTextPane.addMouseListener(listener);
        jTextPane.addMouseMotionListener(listener);
        jTextPane.setBounds(7, 7, width - 14, height - 14);
        jTextPane.getDocument().addDocumentListener(new DocumentListenerImpl(this));
        codeSection = new CodeSection();
        codeSection.setLayer(layer);
        codeSection.setX(x);
        codeSection.setY(y);
        //TODO get the slide number
        file.getSlides().get(0).addObject(codeSection);
    }

    public CodeSection getCodeSection() {
        return codeSection;
    }

    public void setCodeSection(CodeSection codeSection) {
        this.codeSection = codeSection;
    }

    public CodeRunButton getCodeRunButton() {
        return codeRunButton;
    }

    public CodeSectionComponent getCodeSectionComponent() {
        return codeSectionComponent;
    }

    public void setCodeSectionComponent(CodeSectionComponent codeSectionComponent) {
        this.codeSectionComponent = codeSectionComponent;
        jTextPane.setCodeSectionComponent(codeSectionComponent);
    }

    public void setCodeRunButton(CodeRunButton codeRunButton) {
        this.codeRunButton = codeRunButton;
        jTextPane.setCodeRunButton(codeRunButton);
    }

    public void save() {
        codeSection.setCode(jTextPane.getText());
    }

    public void setLocation1(int x, int y) {
        codeSection.setX(x);
        codeSection.setY(y);
    }

    public void resizePane(int inputX, int inputY) {
        jTextPane.setSize(inputX - 14, inputY - 14);
        codeSection.setWidth(inputX - 14);
        codeSection.setHeight(inputY - 14);
    }

    public ExtendedCodeSection getjTextPane() {
        return jTextPane;
    }

    public void setjTextPane(ExtendedCodeSection jTextPane) {
        this.jTextPane = jTextPane;
    }
}
