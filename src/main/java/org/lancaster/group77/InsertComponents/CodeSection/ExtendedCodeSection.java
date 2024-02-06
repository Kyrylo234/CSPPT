package org.lancaster.group77.InsertComponents.CodeSection;

import org.lancaster.group77.InsertComponents.TextBox;

import javax.swing.*;

public class ExtendedCodeSection extends JTextPane {
    private CodeSectionEditor codeSectionEditor;
    private CodeRunButton codeRunButton;
    private CodeSectionComponent codeSectionComponent;
    public ExtendedCodeSection(CodeSectionEditor codeSectionEditor){
        this.codeSectionEditor = codeSectionEditor;
    }
    public CodeSectionEditor getCodeSectionEditor(){
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
}
