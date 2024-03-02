package org.lancaster.group77.FileSystem.Manager;

import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.Tools.GeneralTools;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class ClipboardManager {
    public static List<Object> clipboard;
    public static int copySlideNumber = -1;
    public static ArrayList<CSPPTFile> tempFileList = new ArrayList<>();
    public static ArrayList<CSPPTFile> redoList = new ArrayList<>();
    public static String copyType = "";


    /**
     * Copy the last edited file to the clipboard
     *
     * @param tempFile
     */
    public static void copyTempFile(CSPPTFile tempFile) {
        try {
            boolean canRefresh = copyTempFileWithoutRefreshIcon(tempFile);

            //refresh the slide icon when the file changes
            if (canRefresh)
                GlobalVariables.cspptFrame.getSlideManager().refreshSlideIcon();
        } catch (Exception e) {
        }
    }

    /**
     * Copy the last edited file to the clipboard without refreshing the slide icon
     * @param tempFile
     * @return
     */
    public static boolean copyTempFileWithoutRefreshIcon(CSPPTFile tempFile) {
        try {
            if (tempFileList.isEmpty()) {
                tempFileList.add(tempFile);
                return true;
            }

            if (!tempFile.equals(tempFileList.get(tempFileList.size() - 1))) {
                tempFileList.add(tempFile);
                if (tempFileList.size() > 10) {
                    tempFileList.remove(0);
                }
                return true;
            }
            return false;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * Undo the last action
     * @param tempFile
     * @throws IOException
     */
    public static void undo(CSPPTFile tempFile) throws IOException {
        //save the file to the clipboard for redo
        if (tempFileList.size() > 1) {
            int slideNumber = GlobalVariables.cspptFrame.getCurrentSlideInt();
            GlobalVariables.cspptFrame.getSlideManager().createNewFileWithoutFileLocation();
            GlobalVariables.cspptFrame.getSlideManager().wipeFrame();

            CSPPTFile currentFile = tempFileList.remove(tempFileList.size() - 1);
            redoList.add(currentFile);

            if (redoList.size() > 10) {
                redoList.remove(0);
            }

            CSPPTFile previousFile = tempFileList.get(tempFileList.size() - 1);
            GlobalVariables.cspptFrame.setFile(GeneralTools.deepCopy(previousFile));
            GlobalVariables.cspptFrame.removeAllFromArray();
            GlobalVariables.cspptFrame.populateFrame();
            GlobalVariables.cspptFrame.getSlideManager().setSlide(slideNumber);
            GlobalVariables.cspptFrame.getSlideManager().refreshSlideIcon();
        } else if (tempFileList.size() == 1) {
            redoList.add(GeneralTools.deepCopy(tempFile));
        }
    }

    /**
     * Redo the last action
     * @throws IOException
     */
    public static void redo() throws IOException {
        //save the file to the clipboard for redo
        if (!redoList.isEmpty()) {
            int slideNumber = GlobalVariables.cspptFrame.getCurrentSlideInt();
            GlobalVariables.cspptFrame.getSlideManager().createNewFileWithoutFileLocation();
            GlobalVariables.cspptFrame.getSlideManager().wipeFrame();

            GlobalVariables.cspptFrame.setFile(redoList.get(redoList.size() - 1));
            GlobalVariables.cspptFrame.removeAllFromArray();
            GlobalVariables.cspptFrame.populateFrame();
            GlobalVariables.cspptFrame.getSlideManager().setSlide(slideNumber);

            copyTempFileWithoutRefreshIcon(redoList.get(redoList.size() - 1));
            redoList.remove(redoList.size() - 1);

            GlobalVariables.cspptFrame.getSlideManager().refreshSlideIcon();
        }
    }

    /**
     * Clear the clipboard
     */
    public static void clearClipboard() {
        if(clipboard != null)
            clipboard.clear();
    }
}
