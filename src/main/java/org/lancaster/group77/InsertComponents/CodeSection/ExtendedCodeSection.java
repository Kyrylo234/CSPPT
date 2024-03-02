package org.lancaster.group77.InsertComponents.CodeSection;

import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;

public class ExtendedCodeSection extends RTextScrollPane {
    private CodeSectionEditor codeSectionEditor;
    private CodeRunButton codeRunButton;
    private CodeSectionComponent codeSectionComponent;
    private CodeRunLBLButton codeRunLBLButton;

    public ExtendedCodeSection(CodeSectionEditor codeSectionEditor, CodeArea codeArea) {
        super(codeArea);
        this.codeSectionEditor = codeSectionEditor;
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    }

    public CodeSectionEditor getCodeSectionEditor() {
        return codeSectionEditor;
    }

    public void setCodeSectionEditor(CodeSectionEditor codeSectionEditor) {
        this.codeSectionEditor = codeSectionEditor;
    }

    public CodeRunButton getCodeRunButton() {
        return codeRunButton;
    }

    public void setCodeRunButton(CodeRunButton codeRunButton) {
        this.codeRunButton = codeRunButton;
    }


    public CodeSectionComponent getCodeSectionComponent() {
        return codeSectionComponent;
    }

    public void setCodeSectionComponent(CodeSectionComponent codeSectionComponent) {
        this.codeSectionComponent = codeSectionComponent;
    }

    public CodeRunLBLButton getCodeRunLBLButton() {
        return codeRunLBLButton;
    }

    public void setCodeRunLBLButton(CodeRunLBLButton codeRunLBLButton) {
        this.codeRunLBLButton = codeRunLBLButton;
    }
}
