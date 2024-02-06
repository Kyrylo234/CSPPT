package org.lancaster.group77.Frame.Bars;

import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.impl.ActionListenerImplmenetation;
import org.lancaster.group77.Frame.Buttons.File.*;
import org.lancaster.group77.Frame.CSPPTFrame;

import javax.swing.*;


public class FileBar extends BaseBar {
    private NewFileButton newFileButton;
    private SaveAsButton saveAsButton;

    private SaveButton saveButton;
    private CSPPTFrame frame;
    public FileBar(JTabbedPane tabbedPane, JPanel fileTab, ActionListenerImplmenetation listener, CSPPTFrame frame) {
        super(tabbedPane,fileTab);
        this.frame=frame;
        fileTab.setLayout(null);
        //Creates the different buttons
        newFileButton = new NewFileButton(new ImageIcon("src/main/resources/Icon/File Bar/new_file.png"), 10, 15, 30, 35, "NewFile", this);
        OpenFileButton openFileButton = new OpenFileButton(new ImageIcon("src/main/resources/Icon/File Bar/open.png"), 60, 15, 30, 35, "OpenFile", this);
        saveButton = new SaveButton(new ImageIcon("src/main/resources/Icon/File Bar/save.png"), 110, 15, 30, 35, "Save", this);
        saveAsButton = new SaveAsButton(new ImageIcon("src/main/resources/Icon/File Bar/save_as.png"), 160, 15, 30, 35, "SaveAs", this);
        ExportAsPDFButton exportAsPDFButton = new ExportAsPDFButton(new ImageIcon("src/main/resources/Icon/File Bar/export_as_pdf.png"), 210, 15, 30, 35, "ExportAsPDF");

        //Adds them to the file tab
        fileTab.add(newFileButton);
        fileTab.add(createLabel("New File",8,10,50,50,30));
        fileTab.add(openFileButton);
        fileTab.add(createLabel("Open",8,65,50,50,30));
        fileTab.add(saveButton);
        fileTab.add(createLabel("Save",8,115,50,50,30));
        fileTab.add(saveAsButton);
        fileTab.add(createLabel("Save as",8,160,50,50,30));
        fileTab.add(exportAsPDFButton);
        fileTab.add(createLabel("Export as",8,210,50,60,30));
        fileTab.add(createLabel("PDF",8,220,60,60,30));


        //Adds the action listener to the buttons
        newFileButton.addActionListener(listener);
        openFileButton.addActionListener(listener);
        saveAsButton.addActionListener(listener);
        saveButton.addActionListener(listener);
        exportAsPDFButton.addActionListener(listener);

        tabbedPane.addTab("File",fileTab);
    }

    public CSPPTFrame getFrame(){
        return frame;
    }

    public SaveAsButton getSaveAsButton(){
        return saveAsButton;
    }
}