package org.lancaster.group77.Frame.Buttons;

import javax.swing.*;

public class BaseButton extends JButton {
    private String buttonRole;
    public BaseButton(int x, int y, int width, int height, String inputButtonRole){
        buttonRole = inputButtonRole;
        this.setBounds(x, y, width, height);
    }
    public String getButtonRole() {
        return buttonRole;
    }
}
