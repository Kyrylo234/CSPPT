package org.lancaster.group77.Frame.Dialogs;

import javax.swing.*;

public class PopupDialog extends JOptionPane {
    public PopupDialog(String message, String title) {
        super(message);
        JDialog dialog = this.createDialog(title);
        dialog.setIconImage(new ImageIcon("src/main/resources/Icon/csppt_logo.png").getImage());
        dialog.setVisible(true);
    }
}
