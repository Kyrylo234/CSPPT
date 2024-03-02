package org.lancaster.group77.Frame.Bars;

import org.lancaster.group77.FileSystem.impl.ActionListenerImplementation;
import org.lancaster.group77.Frame.Buttons.File.*;

import javax.swing.*;


public class FileBar extends BaseBar {
    private  SaveAsButton saveAsButton;
    public FileBar(JTabbedPane tabbedPane, JPanel fileTab, ActionListenerImplementation listener) {
        super(tabbedPane,fileTab);
        fileTab.setLayout(null);
        //Creates the different buttons
        NewFileButton newFileButton = new NewFileButton(new ImageIcon("src/main/resources/Icon/File Bar/new_file.png"), 10, 15, 30, 35, "NewFile");
        OpenFileButton openFileButton = new OpenFileButton(new ImageIcon("src/main/resources/Icon/File Bar/open.png"), 60, 15, 30, 35, "OpenFile");
        SaveButton saveButton = new SaveButton(new ImageIcon("src/main/resources/Icon/File Bar/save.png"), 110, 15, 30, 35, "Save", this);
        saveAsButton = new SaveAsButton(new ImageIcon("src/main/resources/Icon/File Bar/save_as.png"), 160, 15, 30, 35, "SaveAs");
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

    public SaveAsButton getSaveAsButton(){
        return saveAsButton;
    }
}