package org.lancaster.group77.FileSystem;

import java.util.List;

public interface ClipboardInterface {
    /**
     * Copies the given objects to the clipboard
     * @param objects the objects to copy [may contain slides, text, shapes, etc]
     * @return true if the copy was successful, false otherwise
     */
    boolean copy(List<Object> objects);

    /**
     * Cuts the given objects to the clipboard
     * @param objects the objects to cut [may contain slides, text, shapes, etc]
     * @return true if the cut was successful, false otherwise
     */
    boolean cut(List<Object> objects);

    /**
     * Pastes the objects from the clipboard
     * @return the objects from the clipboard
     */
    List<Object> paste();
}
