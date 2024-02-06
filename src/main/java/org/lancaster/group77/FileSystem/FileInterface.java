package org.lancaster.group77.FileSystem;

import org.lancaster.group77.Exceptions.FileDoesNotExistException;
import org.lancaster.group77.Frame.CSPPTFrame;

import java.io.FileNotFoundException;

public interface FileInterface {
    /**
     * Open a .csppt file and save it to the memory as CSPPTFile object
     * @param path The path of the file to be opened
     * @return The CSPPTFile object
     * @throws FileNotFoundException If the file does not exist
     */
    CSPPTFile openFile(String path) throws FileDoesNotExistException, FileNotFoundException;

    /**
     * Save the CSPPTFile object to the file
     *
     * @param path      The path of the file to be saved
     * @param cspptFile The CSPPTFile object to be saved
     * @return True if the file is saved successfully
     */
    boolean saveFile(String path, CSPPTFile cspptFile);
}
