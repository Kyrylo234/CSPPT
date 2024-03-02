package org.lancaster.group77.Frame.ProgressBars;

import org.lancaster.group77.FileSystem.GlobalVariables;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class ResizingProgressBar extends JPanel {
    private JSlider slider;
    private JButton minusButton;
    private JButton plusButton;
    private JLabel percentageLabel;
    private int initX = 1600;
    private int initY = 900;

    public ResizingProgressBar(JLayeredPane panel) {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        minusButton = new JButton("-");
        customiseButton(minusButton);

        plusButton = new JButton("+");
        customiseButton(plusButton);

        slider = new JSlider(JSlider.HORIZONTAL, 1, 200, 100);
        slider.setPreferredSize(new Dimension(100, 15));
        slider.setPaintTicks(false);
        slider.setPaintLabels(false);
        slider.setOpaque(false);
        slider.setUI(new BasicSliderUI(slider) {
            @Override
            public void paintTrack(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                Rectangle trackBounds = trackRect;

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(Color.BLACK);
                g2d.fill(new RoundRectangle2D.Double(trackBounds.x, trackBounds.y + (trackBounds.height - 4) / 2.0,
                        trackBounds.width, 2, 4, 4));
            }

            @Override
            public void paintThumb(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(GlobalVariables.THEME_COLOR));
                g2d.fillOval(thumbRect.x, thumbRect.y, thumbRect.width - 5, thumbRect.height - 5);
            }
        });

        percentageLabel = new JLabel("100%");
        percentageLabel.setForeground(Color.BLACK);

        this.setOpaque(false);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = ((JSlider) e.getSource()).getValue();
                double scaleFactor = value / 100.0;

                int newWidth = (int) (initX * scaleFactor);
                int newHeight = (int) (initY * scaleFactor);

                int centerX = panel.getLocation().x + panel.getWidth() / 2;
                int centerY = panel.getLocation().y + panel.getHeight() / 2;
                int newX = centerX - newWidth / 2;
                int newY = centerY - newHeight / 2;
                panel.setBounds(newX, newY, newWidth, newHeight);

                percentageLabel.setText(value + "%");
            }
        });

        add(minusButton);
        add(slider);
        add(plusButton);
        add(percentageLabel);
    }

    private void customiseButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == minusButton) {
                    slider.setValue(slider.getValue() - 1);
                } else if (e.getSource() == plusButton) {
                    slider.setValue(slider.getValue() + 1);
                }
            }
        });
    }

    public int getInitX() {
        return initX;
    }

    public void setInitX(int initX) {
        this.initX = initX;
    }

    public int getInitY() {
        return initY;
    }

    public void setInitY(int initY) {
        this.initY = initY;
    }
}
