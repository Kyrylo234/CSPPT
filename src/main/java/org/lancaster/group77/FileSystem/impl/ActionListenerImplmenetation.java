package org.lancaster.group77.FileSystem.impl;

import org.lancaster.group77.Frame.Buttons.*;
import org.lancaster.group77.Frame.Buttons.File.*;
import org.lancaster.group77.Frame.Buttons.Home.*;
import org.lancaster.group77.Frame.Buttons.Insert.*;
import org.lancaster.group77.Frame.Buttons.Presentation.FromBeginningButton;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;



public class ActionListenerImplmenetation implements ActionListener {
    private MouseHandler mouseHandler;
//    JComboBox<?> jComboBoxSize;
//    JComboBox<?> jComboBox;
    //Action listeners for the
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof BaseButton){
            BaseButton clickedButton = (BaseButton) e.getSource();
            switch (clickedButton.getButtonRole()) {
                case "NewFile":
                    NewFileButton newFileButton = (NewFileButton) e.getSource();
                    newFileButton.newFileFunction();
                    break;
                case "OpenFile":
                    try {
                        OpenFileButton clickedButton1 = (OpenFileButton) e.getSource();
                        clickedButton1.openFileFunction();
                    } catch (Exception exception) {
                        System.err.println("Error Opening file: " + exception.getMessage());
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
                    System.out.println(clickedButton.getButtonRole());
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
                newVideoButton.insertVideoFunction();
                break;

            case "Audio":
                InsertAudioButton insertAudioButton = (InsertAudioButton) e.getSource();
                insertAudioButton.InsertSound();
                break;

                case "NewSlide":
                    NewSlide newSlide = (NewSlide) e.getSource();
                    newSlide.addNewSlide();
                    break;

                case "Chart":
                    InsertMathChartButton insertMathChartButton = (InsertMathChartButton) e.getSource();
                    insertMathChartButton.createDataset();
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

            }
        }


//
//        if(e.getSource() instanceof JComboBox<?>){
//            JComboBox<?> jComboBox = (JComboBox<?>) e.getSource();
//            String selectedValue = (String) jComboBox.getSelectedItem();
//            System.out.println(selectedValue);
//            mouseHandler.changeFont(selectedValue);
//
//
//        }

        if(e.getSource() instanceof JComboBox<?>){
            JComboBox<?> jComboBoxSize = (JComboBox<?>) e.getSource();
            Integer size = (Integer) jComboBoxSize.getSelectedItem();
            System.out.println(size);
            mouseHandler.changeFontSize(size);
        }

    }

    public void addMouseHandler(MouseHandler handler) {
        mouseHandler = handler;
    }
}
