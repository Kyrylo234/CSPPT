package org.lancaster.group77.Listener;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.Manager.ClipboardManager;
import org.lancaster.group77.FileSystem.Tools.GeneralTools;
import org.lancaster.group77.Frame.Buttons.File.SaveButton;
import org.lancaster.group77.Frame.Buttons.Home.CommandLineButton;
import org.lancaster.group77.Frame.Buttons.Home.CopyButton;
import org.lancaster.group77.Frame.Buttons.Home.CutButton;
import org.lancaster.group77.Frame.Buttons.Home.PasteButton;
import org.lancaster.group77.InsertComponents.CodeSection.CodeArea;
import org.lancaster.group77.InsertComponents.CodeSection.ExtendedCodeSection;
import org.lancaster.group77.InsertComponents.DisplayComponent;
import org.lancaster.group77.InsertComponents.TextBox.ExtendedJtextArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class is used to create the key listener for the panel
 * It will listen to the key press and perform the corresponding action
 */
public class KeyListener {

    /**
     * This method is used to initialize the key listener for the panel
     */
    public static void initKeyListenerForPanel() {
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            public void eventDispatched(AWTEvent event) {
                if (event instanceof KeyEvent) {
                    KeyEvent key = (KeyEvent) event;
                    if (key.getID() == KeyEvent.KEY_PRESSED) {
                        if (key.getID() == KeyEvent.KEY_PRESSED && key.isControlDown() && key.getKeyCode() == KeyEvent.VK_C) {
                            //Copy action
                            CopyButton.copy();
                        } else if (key.getKeyCode() == KeyEvent.VK_DELETE) {
                            //delete action
                            deletionAction();
                        } else if (key.isControlDown() && key.getKeyCode() == KeyEvent.VK_X) {
                            //cut action
                            CutButton.cut();
                        } else if (key.isControlDown() && key.getKeyCode() == KeyEvent.VK_V) {
                            //paste action
                            try {
                                PasteButton.paste();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }else if (key.getID() == KeyEvent.KEY_PRESSED && key.isControlDown() && key.getKeyCode() == KeyEvent.VK_M) {
                            //command line action
                            if(GlobalVariables.IS_PRESENTING){
                                CommandLineButton.openCommandLineFunction();
                            }else{
                                CommandLineButton.openCommandLine();
                            }
                        }else if (key.getID() == KeyEvent.KEY_PRESSED && key.isControlDown() && key.getKeyCode() == KeyEvent.VK_S) {
                            //save csppt file
                            try {
                                SaveButton.saveFunction();
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            //normal action
                            normalAction();
                        }
                    }

                }
            }
        }, AWTEvent.KEY_EVENT_MASK);
    }

    /**
     * This method is used to get the init action for the key listener
     * Normal key press will copy the file to the clipboard
     * If the focused component is a code area, it will remove the highlight
     */
    private static void normalAction() {
        Component focusedComponent = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        if (focusedComponent instanceof CodeArea codeArea) {
            codeArea.removeHighlight();
        } else if (!(focusedComponent instanceof ExtendedJtextArea) && !(focusedComponent instanceof ExtendedCodeSection)) {
            ClipboardManager.copyTempFile(GeneralTools.deepCopy(GlobalVariables.cspptFrame.getFile()));
        }
    }

    /**
     * This method is used to get the deletion action for the key listener
     * If the last clicked component is not null, it will remove the component from the right side
     * If the last clicked component is null, it will remove the slide from the left side
     * The file will be copied to the clipboard after the deletion
     */
    private static void deletionAction() {
        DisplayComponent component = GlobalVariables.handler.getLastClicked();
        GlobalVariables.handler.setLastClicked(null);
        if (component != null) {
            //delete the thing from the right side
            GlobalVariables.cspptFrame.removeComponent(component, GlobalVariables.cspptFrame.getPane());
        } else {
            if (GlobalVariables.cspptFrame.getHandler().getSelectedSlide() != -1) {
                //remove  the thing from the left side
                int deleting_slide = GlobalVariables.cspptFrame.getHandler().getSelectedSlide();
                GlobalVariables.cspptFrame.removeSlide(deleting_slide);
            }
        }
        ClipboardManager.copyTempFile(GeneralTools.deepCopy(GlobalVariables.cspptFrame.getFile()));

    }

}
