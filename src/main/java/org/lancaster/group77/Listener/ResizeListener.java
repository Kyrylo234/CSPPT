package org.lancaster.group77.Listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResizeListener extends MouseAdapter {
    private final JFrame frame;
    private int prevX, prevY;

    public ResizeListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        prevX = e.getXOnScreen();
        prevY = e.getYOnScreen();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int deltaX = e.getXOnScreen() - prevX;
        int deltaY = e.getYOnScreen() - prevY;

        Cursor cursor = frame.getCursor();

        if (cursor.equals(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR))) {
            resizeFrame(deltaX, 0, true, false);
        } else if (cursor.equals(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR))) {
            resizeFrame(deltaX, 0, false, true);
        } else if (cursor.equals(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR))) {
            resizeFrame(0, deltaY, false, false);
        } else if (cursor.equals(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR))) {
            resizeFrame(0, deltaY, true, true);
        }

        prevX = e.getXOnScreen();
        prevY = e.getYOnScreen();
    }

    private void resizeFrame(int deltaX, int deltaY, boolean north, boolean west) {
        Rectangle bounds = frame.getBounds();
        if (west) {
            bounds.x += deltaX;
            bounds.width -= deltaX;
        } else {
            bounds.width += deltaX;
        }
        if (north) {
            bounds.y += deltaY;
            bounds.height -= deltaY;
        } else {
            bounds.height += deltaY;
        }

        // Set minimum frame size
        if (bounds.width >= 626 && bounds.height >= 408) {
            frame.setBounds(bounds);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int width = frame.getWidth();
        int height = frame.getHeight();
        int edge = 10;

        if (x >= width - edge && x <= width) {
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        } else if (x <= edge) {
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
        } else if (y >= height - edge && y <= height) {
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
        } else if (y <= edge) {
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
        } else {
            frame.setCursor(Cursor.getDefaultCursor());
        }
    }
}
