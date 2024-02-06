package org.lancaster.group77.FileSystem;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
public class FileChooser {
    static String fileLocation;
    public String getFileLocation(){
        fileLocationFunction();
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
        int returnVal = fileChooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            fileLocation = fileChooser.getSelectedFile().getAbsolutePath();
        }
    }

    //For images
    private void fileImageLocationFunction(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif"));
        int returnVal = fileChooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            fileLocation = fileChooser.getSelectedFile().getAbsolutePath();
        }
    }
}
