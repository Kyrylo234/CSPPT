package org.lancaster.group77.Frame.RightClickMenus;

import javax.swing.*;

public class BaseRightClickMenu extends JPopupMenu {
    private int x;
    private int y;

    public BaseRightClickMenu() {
    }

    public void setX1(int x) {
        this.x = x;
    }


    public void setY1(int y) {
        this.y = y;
    }

    public int getX1() {
        return x;
    }

    public int getY1() {
        return y;
    }
}
