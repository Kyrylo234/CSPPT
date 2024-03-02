package org.lancaster.group77.InsertComponents.CodeSection;

import org.fife.ui.rtextarea.RTextScrollPane;
import org.lancaster.group77.DisplayComponents.CodeSection;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.impl.DocumentListenerImpl;
import org.lancaster.group77.InsertComponents.DraggableComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class CodeSectionEditor extends DraggableComponent {
    private ExtendedCodeSection extendedCodeSection;
    private CodeSection codeSection;
    private CodeRunButton codeRunButton;
    private CodeRunLBLButton codeRunLBLButton;
    private CodeEditButton codeEditButton;
    private CodeSectionComponent codeSectionComponent;
    private MouseHandler listener;
    private CodeArea codeArea;
    private JPanel topPane;
    private JTextArea titleArea;
    private JLayeredPane cspptframe;

    public CodeSectionEditor(int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int layer, int slideNum) {
        super(x, y, width, height, listener, frame, file, "CodeSectionEditor");
        this.cspptframe = frame;

        codeSection = new CodeSection();
        codeSection.setLayer(layer);
        codeSection.setX(x / GlobalVariables.FRAME_SIZE_INDEX_X);
        codeSection.setY(y / GlobalVariables.FRAME_SIZE_INDEX_Y);
        codeSection.setWidth(width / GlobalVariables.FRAME_SIZE_INDEX_X);
        codeSection.setHeight(height / GlobalVariables.FRAME_SIZE_INDEX_Y);
        this.setComponentData(codeSection);
        file.getSlides().get(slideNum).addObject(codeSection);

        initCodeSectionEditor(listener, x, y, width, height);
    }

    public CodeSectionEditor(CodeSection codeSection, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int slideNum, boolean isOpeningFile) {
        super((int) (codeSection.getX() * GlobalVariables.FRAME_SIZE_INDEX_X), (int) (codeSection.getY() * GlobalVariables.FRAME_SIZE_INDEX_Y), (int) (codeSection.getWidth() * GlobalVariables.FRAME_SIZE_INDEX_X), (int) (codeSection.getHeight() * GlobalVariables.FRAME_SIZE_INDEX_Y), listener, frame, file, "CodeSectionEditor");
        this.codeSection = codeSection;
        this.setComponentData(codeSection);
        if (!isOpeningFile)
            file.getSlides().get(slideNum).addObject(codeSection);

        initCodeSectionEditor(listener, (int) (codeSection.getWidth() * GlobalVariables.FRAME_SIZE_INDEX_X), (int) (codeSection.getHeight() * GlobalVariables.FRAME_SIZE_INDEX_Y), (int) (codeSection.getWidth() * GlobalVariables.FRAME_SIZE_INDEX_X), (int) (codeSection.getHeight() * GlobalVariables.FRAME_SIZE_INDEX_Y));
        codeArea.setText(codeSection.getCode());
    }

    private void initCodeSectionEditor(MouseHandler listener, int x, int y, int width, int height) {
        this.listener = listener;
        this.cspptframe = GlobalVariables.cspptFrame.getPane();
        initTopPane(width);
        codeArea = new CodeArea(this);
        codeArea.setText("console.log(\"Hello CSPPT!\");\n\n\n");
        DraggableComponent.initRightClickMenu(codeArea);
        extendedCodeSection = new ExtendedCodeSection(this, codeArea);
        MatteBorder border = new MatteBorder(1, 1, 1, 1, Color.WHITE);
        extendedCodeSection.setBorder(border);
        add(extendedCodeSection);
        extendedCodeSection.addMouseListener(listener);
        extendedCodeSection.addMouseMotionListener(listener);
        DraggableComponent.initRightClickMenu(extendedCodeSection);
        codeArea.addMouseListener(listener);
        codeArea.addMouseMotionListener(listener);
        extendedCodeSection.setBounds(7, 7 + 20, width - 14, height - 14 - 20);
        codeArea.getDocument().addDocumentListener(new DocumentListenerImpl(this));
        displayCodeRunButton(GlobalVariables.cspptFrame.getPane(), false);
    }

    private void initTopPane(int width) {
        topPane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label = new JLabel(" JavaScript");
        label.setFont(new Font("Arial", Font.BOLD, 11));
        label.setForeground(Color.WHITE);
        label.setBackground(Color.BLACK);
        label.setOpaque(true);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        topPane.add(label, gbc);

        titleArea = new JTextArea();
        titleArea.setBackground(Color.BLACK);
        titleArea.setForeground(new Color(GlobalVariables.THEME_COLOR));
        titleArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        setBorder(null);
        titleArea.setText("Input Code Name Here");
        titleArea.setCaretColor(Color.WHITE);
        gbc.gridx = 1;
        gbc.weightx = 0.5;

        topPane.add(titleArea, gbc);
        topPane.setBounds(7, 7, width - 14, 20);
        MatteBorder border = new MatteBorder(1, 1, 0, 1, Color.WHITE);
        topPane.setBorder(border);
        add(topPane);
    }

    public void displayCodeRunButton(JLayeredPane pane, boolean isPresentation) {
        if (codeRunButton == null) {
            codeRunButton = new CodeRunButton((int) codeSection.getX() + (int) codeSection.getWidth() - 20, (int) codeSection.getY() - 20, 20, 20, listener, this);
            codeRunLBLButton = new CodeRunLBLButton((int) codeSection.getX() + (int) codeSection.getWidth() - 20, (int) codeSection.getY() - 20 + 30, 20, 20, listener, this);
            codeEditButton = new CodeEditButton((int) codeSection.getX() + (int) codeSection.getWidth() - 20, (int) codeSection.getY() - 20 + 60, 20, 20, listener, this);
            setCodeRunButton(codeRunButton);
            setCodeRunLBLButton(codeRunLBLButton);
            pane.add(codeRunButton);
            pane.add(codeRunLBLButton);
            pane.add(codeEditButton);
        } else {
            boolean isPresent = false;
            for (Component component : pane.getComponents()) {
                if (component.equals(codeRunButton)) {
                    isPresent = true;
                    break;
                } else if (component.equals(codeRunLBLButton)) {
                    isPresent = true;
                    break;
                }
            }

            if (!isPresent) {
                pane.add(codeRunButton);
                pane.add(codeRunLBLButton);
                pane.add(codeEditButton);
            }
        }
        codeRunButton.setCodeRunPanel(pane);
        codeRunLBLButton.setCodeRunPanel(pane);
        codeEditButton.setCodeRunPanel(pane);
        codeRunButton.setVisible(true);
        codeRunLBLButton.setVisible(true);

        if (isPresentation)
            codeEditButton.setVisible(true);
        else
            codeEditButton.setVisible(false);
    }

    public void updateButtonPosition(int x, int y, int width, int height) {
        codeRunButton.setLocation(x - 20, y);
        codeRunLBLButton.setLocation(x - 20, y + 30);
        codeEditButton.setLocation(x - 20, y + 60);
        codeSectionComponent.getJfxPanel().setLocation(x + 7, y + height - 7);
    }

    public void removeButtonAndComponent() {
        try {
            GlobalVariables.cspptFrame.getPane().remove(codeRunButton);
            GlobalVariables.cspptFrame.getPane().remove(codeRunLBLButton);
            GlobalVariables.cspptFrame.getPane().remove(codeSectionComponent.getJfxPanel());
        } catch (Exception e) {
        }
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

    public CodeRunLBLButton getCodeRunLBLButton() {
        return codeRunLBLButton;
    }

    public CodeSectionComponent getCodeSectionComponent() {
        return codeSectionComponent;
    }

    public void setCodeSectionComponent(CodeSectionComponent codeSectionComponent) {
        this.codeSectionComponent = codeSectionComponent;
        extendedCodeSection.setCodeSectionComponent(codeSectionComponent);
    }

    public void setCodeRunButton(CodeRunButton codeRunButton) {
        this.codeRunButton = codeRunButton;
        extendedCodeSection.setCodeRunButton(codeRunButton);
    }

    public void setCodeRunLBLButton(CodeRunLBLButton codeRunLBLButton) {
        this.codeRunLBLButton = codeRunLBLButton;
        extendedCodeSection.setCodeRunLBLButton(codeRunLBLButton);
    }

    public void save() {
        codeSection.setCode(codeArea.getText());
    }

    public void setLocation1(int x, int y) {
        codeSection.setX(x);
        codeSection.setY(y);
    }

    @Override
    public void resizeComponent(int inputX, int inputY, int inputWidth, int inputHeight, int layer) {
        extendedCodeSection.setSize(inputWidth - 14, inputHeight - 14 - 20);
        topPane.setSize(inputWidth - 14, 20);
        codeSectionComponent.getJfxPanel().setBounds(inputX + 7, inputY + inputHeight - 7, inputWidth - 14, 80);
        codeRunButton.setLocation(inputX - 20, inputY);
        codeRunLBLButton.setLocation(inputX - 20, inputY + 30);
        super.resizeComponent(inputX, inputY, inputWidth, inputHeight, layer);
    }

    @Override
    public void resizeComponent(int inputX, int inputY, int inputWidth, int inputHeight) {
        extendedCodeSection.setSize(inputWidth - 14, inputHeight - 14 - 20);
        topPane.setSize(inputWidth - 14, 20);
        codeSectionComponent.getJfxPanel().setBounds(inputX + 7, inputY + inputHeight - 7, inputWidth - 14, 80);
        codeRunButton.setLocation(inputX - 20, inputY);
        codeRunLBLButton.setLocation(inputX - 20, inputY + 30);
        super.resizeComponent(inputX, inputY, inputWidth, inputHeight);
    }

    public ExtendedCodeSection getExtendedCodeSection() {
        return extendedCodeSection;
    }

    public CodeArea getCodeArea() {
        return codeArea;
    }

    public void disableInput() {
        codeArea.setEditable(false);
        titleArea.setEditable(false);
    }

    public void enableInput() {
        codeArea.setEditable(true);
        titleArea.setEditable(true);
    }
}
