package org.lancaster.group77.Listener;

import org.lancaster.group77.Frame.RightClickMenus.BaseRightClickMenu;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RightClickMenuListener extends MouseAdapter {
    private BaseRightClickMenu menu;

    public RightClickMenuListener(BaseRightClickMenu menu) {
        this.menu = menu;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            int x = e.getX();
            int y = e.getY();
            menu.setX1(x);
            menu.setY1(y);
            menu.show(e.getComponent(), x, y);

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            int x = e.getX();
            int y = e.getY();
            menu.setX1(x);
            menu.setY1(y);
            menu.show(e.getComponent(), x, y);

        }
    }
}
