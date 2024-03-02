package org.lancaster.group77.FileSystem;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class FileChooser {
    private String fileLocation;
    private boolean isFileSelected = false;
    public String getFileLocation(){
        fileLocationFunction();
        return fileLocation;
    }

    public String getFileLocationForAnyType(String description, String... extensions){
        fileLocationFunctionForAnyType(description, extensions);
        return fileLocation;
    }

    public String getImageFileLocation(){
        fileImageLocationFunction();
        return fileLocation;
    }


    //For CSPPT files
    private void fileLocationFunction(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSPPT Files", "csppt"));
        int returnVal = fileChooser.showOpenDialog(getTempFrame());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            fileLocation = fileChooser.getSelectedFile().getAbsolutePath();
            isFileSelected = true;
        }else{
            isFileSelected = false;
        }
    }

    /**
     * fileLocationFunction extension for any type of file
     */
    private void fileLocationFunctionForAnyType(String description, String... extensions){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter(description, extensions));
        int returnVal = fileChooser.showOpenDialog(getTempFrame());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            fileLocation = fileChooser.getSelectedFile().getAbsolutePath();
        }
    }

    //For images
    private void fileImageLocationFunction(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif"));
        int returnVal = fileChooser.showOpenDialog(getTempFrame());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            fileLocation = fileChooser.getSelectedFile().getAbsolutePath();
        }
    }

    /**
     * fileLocationFunction extension for PDF files
     */
    private void filePDFLocationFunction(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF", "pdf"));
        int returnVal = fileChooser.showOpenDialog(getTempFrame());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            fileLocation = fileChooser.getSelectedFile().getAbsolutePath();
        }
    }

    public boolean isFileSelected() {
        return isFileSelected;
    }

    /**
     * Get a temporary frame with logo icon
     * @return
     */
    public static JFrame getTempFrame(){
        JFrame tempFrame = new JFrame();
        tempFrame.setIconImage(new ImageIcon("src/main/resources/Icon/csppt_logo.png").getImage());
        tempFrame.setUndecorated(true);
        tempFrame.setLocationRelativeTo(null);
        tempFrame.setOpacity(0f);
        tempFrame.setVisible(true);
        return tempFrame;
    }
}
