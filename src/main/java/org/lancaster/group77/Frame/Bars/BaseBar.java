
package org.lancaster.group77.Frame.Bars;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class BaseBar {

    public BaseBar(JTabbedPane tabbedPane, JPanel tab){
       tab.setBackground(Color.LIGHT_GRAY);
       tab.setSize(1000,1000);
    }
    public JButton barButton(int height , int length ,int x , int y, String imagePath, String buttonName ){
        JButton button = new JButton(buttonName);
        button.setBounds(x,y,length,height);
        button.setIcon(new ImageIcon(imagePath));

        return button;
    }

    public JLabel createLabel (String labelName,int fontSize, int x,int y, int width, int height ){
        JLabel label = new JLabel(labelName);
        label.setBounds(x,y,width,height);
        Font customFont = new Font("Arial", Font.BOLD, fontSize);
        label.setFont(customFont);
        return label;
    }

    public <T> JComboBox<T> createDropDown(T[] options,int x,int y, int width, int height){
        JComboBox<T> newDropdown = new JComboBox<T>(options);
        newDropdown.setBounds(x,y,width,height);
        newDropdown.setBackground(Color.LIGHT_GRAY);

        newDropdown.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                return new JButton() {
                    @Override
                    public int getWidth() {
                        return 0;
                    }
                };
            }
        });
        newDropdown.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return newDropdown;
    }

    public JSeparator createSeparator(int x){
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setBounds(x,0,1,100);  // Adjust the bounds as needed
        separator.setForeground(Color.DARK_GRAY);
        return  separator;
    }


}