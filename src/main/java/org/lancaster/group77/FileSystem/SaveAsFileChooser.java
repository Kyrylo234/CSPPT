package org.lancaster.group77.FileSystem;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveAsFileChooser {
    static String fileLocation;
    public String getSaveAsFileLocation(){
        saveAsFileLocationFunction();
        return fileLocation;
    }

    private void saveAsFileLocationFunction(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save As");
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSPPT Files", "csppt"));
        int returnVal = fileChooser.showSaveDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            fileLocation = fileChooser.getSelectedFile().getAbsolutePath();
            if (!fileLocation.toLowerCase().endsWith(".csppt")) {
                fileLocation = fileLocation + ".csppt";
            }
        }
    }
}


