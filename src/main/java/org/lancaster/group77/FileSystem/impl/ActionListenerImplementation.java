package org.lancaster.group77.FileSystem.impl;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.Manager.ClipboardManager;
import org.lancaster.group77.FileSystem.Tools.GeneralTools;
import org.lancaster.group77.Frame.Buttons.*;
import org.lancaster.group77.Frame.Buttons.Animations.AnimationButton;
import org.lancaster.group77.Frame.Buttons.File.*;
import org.lancaster.group77.Frame.Buttons.Home.*;
import org.lancaster.group77.Frame.Buttons.Insert.*;
import org.lancaster.group77.Frame.Buttons.Presentation.FromBeginningButton;
import org.lancaster.group77.Frame.Buttons.Presentation.FromCurrentButton;
import org.lancaster.group77.Frame.Buttons.Presentation.RecordButton;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ActionListenerImplementation implements ActionListener {
    private MouseHandler mouseHandler;
    private int hit = 0;


    //Action listeners for the
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean canCopyFile = true;
        if (e.getSource() instanceof BaseButton) {
            BaseButton clickedButton = (BaseButton) e.getSource();
            switch (clickedButton.getButtonRole()) {
                case "NewFile":
                    NewFileButton.newFileFunction();
                    break;
                case "OpenFile":
                    try {
                        OpenFileButton.openFileFunction();
                    } catch (Exception exception) {
                        throw new RuntimeException(exception);
                    }
                    break;
                case "SaveAs":
                    try {
                        SaveAsButton clickedButton1 = (SaveAsButton) e.getSource();
                        clickedButton1.saveAsFileFunction();
                    } catch (Exception exception) {
                        System.err.println("Error Opening file: " + exception.getMessage());
                    }
                    break;
                case "Save":
                    SaveButton clickedButton1 = (SaveButton) e.getSource();
                    try {
                        clickedButton1.saveFunction();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case "ExportAsPDF":
                    ExportAsPDFButton clickedButton2 = (ExportAsPDFButton) e.getSource();
                    clickedButton2.exportAsPDFFunction();
                    break;

                case "TextBox":
                    NewTextBoxButton clickedButton3 = (NewTextBoxButton) e.getSource();
                    clickedButton3.newTextBoxFunction();
                    break;

                case "IncreaseFontSize":
                    IncreaseFontSizeButton increaseFontSizeButton = (IncreaseFontSizeButton) e.getSource();
                    increaseFontSizeButton.increaseFontSizeFunction();
                    break;

                case "DecreaseFontSize":
                    DecreaseFontSizeButton decreaseFontSizeButton = (DecreaseFontSizeButton) e.getSource();
                    decreaseFontSizeButton.DecreaseFontSizeFunction();
                    break;


                case "Bold":
                    BoldButton boldButton = (BoldButton) e.getSource();
                    boldButton.BoldButtonFunction();
                    break;

                case "Italic":
                    ItalicButton italicButton = (ItalicButton) e.getSource();
                    italicButton.italicButtonFunction();
                    break;

                case "Bullets":
                    BulletPointButton bulletPointButton = (BulletPointButton) e.getSource();
                    bulletPointButton.setBulletpoints();
                    break;

                case "FontColor":
                    ColorButton colorbutton = (ColorButton) e.getSource();
                    colorbutton.ChangeColorFunction();
                    break;


                case "FormatBackground":
                    FormatBackgroundButton formatBackgroundButton = (FormatBackgroundButton) e.getSource();
                    formatBackgroundButton.formatBackgroundFunction();
                    break;

                case "Pictures":
                    InsertImageButton insertImageButton = (InsertImageButton) e.getSource();
                    insertImageButton.insertImageFunction();
                    break;

                case "Video":
                    NewVideoButton newVideoButton = (NewVideoButton) e.getSource();
                    try {
                        newVideoButton.insertVideoFunction();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;

                case "Audio":
                    InsertAudioButton insertAudioButton = (InsertAudioButton) e.getSource();
                    try {
                        insertAudioButton.InsertSound();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;

                case "NewSlide":
                    NewSlide newSlide = (NewSlide) e.getSource();
                    newSlide.addNewSlide();
                    break;

                case "Record":
                    RecordButton recordButton = (RecordButton) e.getSource();
                    recordButton.setRecord();
                    break;

                case "NextSlide":
                    NextSlide nextSlide = (NextSlide) e.getSource();
                    nextSlide.nextSlide();
                    break;

                case "Chart":
                    InsertMathChartButton insertMathChartButton = (InsertMathChartButton) e.getSource();
                    insertMathChartButton.createDataset();
                    break;

                case "Table":
                    InsertTableButton insertTableButton = (InsertTableButton) e.getSource();
                    insertTableButton.makeTable();
                    break;

                case "Animations":
                    AnimationButton animationButton = (AnimationButton) e.getSource();
                    animationButton.setAnimation();
                    break;

                case "Code":
                    InsertCodeSectionButton btn = (InsertCodeSectionButton) e.getSource();
                    btn.newCodeSectionFunction();
                    break;


                case "Shapes":
                    InsertShapeButton insertShapeButton = (InsertShapeButton) e.getSource();
                    insertShapeButton.InsertShapeFunction();
                    break;

                case "Underline":
                    UnderlineButton underlineButton = (UnderlineButton) e.getSource();
                    underlineButton.setUnderline();
                    break;

                case "StrikeThrough":
                    StrikethroughButton strikethroughButton = (StrikethroughButton) e.getSource();
                    strikethroughButton.setStrikethrough();
                    break;

                case "AlignCenter":
                    AlignCenterButton alignCenterButton = (AlignCenterButton) e.getSource();
                    alignCenterButton.setCenterAlignment();
                    break;

                case "AlignLeft":
                    AlignLeftButton alignLeftButton = (AlignLeftButton) e.getSource();
                    alignLeftButton.setLeftAlignment();
                    break;

                case "AlignRight":
                    AlignRightButton alignRightButton = (AlignRightButton) e.getSource();
                    alignRightButton.setRightAlignment();
                    break;

                case "LineSpacing":
                    LineSpacingButton lineSpacingButton = (LineSpacingButton) e.getSource();
                    lineSpacingButton.setLineSpacing();
                    break;

                case "CommandLine":
                    CommandLineButton commandLineButton = (CommandLineButton) e.getSource();
                    commandLineButton.openCommandLine();
                    break;

                case "From_Beginning":
                    FromBeginningButton fromBeginningButton = (FromBeginningButton) e.getSource();
                    fromBeginningButton.fromBeginningFunction();
                    break;

                case "From_Current":
                    FromCurrentButton fromCurrentButton = (FromCurrentButton) e.getSource();
                    fromCurrentButton.fromCurrentFunction();
                    break;

                case "Copy":
                    CopyButton copyButton = (CopyButton) e.getSource();
                    copyButton.copy();
                    break;
                case "Cut":
                    CutButton cutButton = (CutButton) e.getSource();
                    cutButton.cut();
                    break;
                case "Paste":
                    PasteButton pasteButton = (PasteButton) e.getSource();
                    try {
                        pasteButton.paste();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case "Undo":
                    canCopyFile = false;
                    hit++;
                    try {
                        ClipboardManager.undo(GeneralTools.deepCopy(GlobalVariables.cspptFrame.getFile()));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case "Redo":
                    canCopyFile = false;
                    try {
                        ClipboardManager.redo();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case "CSChart":
                    CSChartButton CSchartButton = (CSChartButton) e.getSource();
                    CSchartButton.createClass();

            }
        }

        if (e.getSource() instanceof JComboBox<?> jComboBox) {
            if (jComboBox.getName().equals("font size")) {
                JComboBox<?> jComboBoxSize = (JComboBox<?>) e.getSource();
                Integer size = (Integer) jComboBoxSize.getSelectedItem();
                mouseHandler.changeFontSize(size);
            } else if (jComboBox.getName().equals("font")) {
                mouseHandler.changeFont(jComboBox.getSelectedItem().toString());
            }
        }

        if (canCopyFile) {
            ClipboardManager.copyTempFile(GeneralTools.deepCopy(GlobalVariables.cspptFrame.getFile()));
            hit = 0;
        }
    }

    public void addMouseHandler(MouseHandler handler) {
        mouseHandler = handler;
    }
}
