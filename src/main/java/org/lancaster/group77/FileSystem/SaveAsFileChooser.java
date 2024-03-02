package org.lancaster.group77.FileSystem;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveAsFileChooser {
    static String fileLocation;

    /**
     * Get the file location for csppt file saving
     * @return
     */
    public String getSaveAsFileLocation() {
        saveAsFileLocationFunction();
        return fileLocation;
    }

    /**
     * Get the file location for PDF file saving
     * @return
     */
    public String getSaveAsFileLocationPDF() {
        saveAsFileLocationFunctionPDF();
        return fileLocation;
    }

    /**
     * Get the file location for MP4 file saving
     * @return
     */
    public String getSaveAsFileLocationMP4() {
        saveAsFileLocationFunctionMP4();
        return fileLocation;
    }

    /**
     * Save as file location for csppt file
     */
    private void saveAsFileLocationFunction() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save As");
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSPPT Files", "csppt"));
        int returnVal = fileChooser.showSaveDialog(FileChooser.getTempFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileLocation = fileChooser.getSelectedFile().getAbsolutePath();
            if (!fileLocation.toLowerCase().endsWith(".csppt")) {
                fileLocation = fileLocation + ".csppt";
            }
        }
    }

    /**
     * Save as file location for PDF
     */
    private void saveAsFileLocationFunctionPDF() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save As");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF", "pdf"));
        int returnVal = fileChooser.showSaveDialog(FileChooser.getTempFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileLocation = fileChooser.getSelectedFile().getAbsolutePath();
            if (!fileLocation.toLowerCase().endsWith(".pdf")) {
                fileLocation = fileLocation + ".pdf";
            }
        }
    }

    /**
     * Save as file location for MP4
     */
    private void saveAsFileLocationFunctionMP4() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save As");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Video MP4", "mp4"));
        int returnVal = fileChooser.showSaveDialog(FileChooser.getTempFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileLocation = fileChooser.getSelectedFile().getAbsolutePath();
            if (!fileLocation.toLowerCase().endsWith(".mp4")) {
                fileLocation = fileLocation + ".mp4";
            }
        }
    }
}


