package org.lancaster.group77.FileSystem.impl;

import org.lancaster.group77.FileSystem.ClipboardInterface;
import org.lancaster.group77.FileSystem.Manager.ClipboardManager;

import java.util.List;

public class ClipboardImpl implements ClipboardInterface {
    /**
     * Copies the given objects to the clipboard
     * @param objects the objects to copy [may contain slides, text, shapes, etc]
     * @return true if the copy was successful, false otherwise
     */
    @Override
    public boolean copy(List<Object> objects) {
        ClipboardManager.clipboard = objects;
        return true;
    }

    /**
     * Cuts the given objects to the clipboard
     * @param objects the objects to cut [may contain slides, text, shapes, etc]
     * @return true if the cut was successful, false otherwise
     */
    @Override
    public boolean cut(List<Object> objects) {
        return copy(objects);
    }

    /**
     * Pastes the objects from the clipboard
     * @return the objects from the clipboard
     */
    @Override
    public List<Object> paste() {
        return ClipboardManager.clipboard;
    }
}
