package org.lancaster.group77.InsertComponents;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import javafx.scene.Scene;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.Frame.RightClickMenus.SlideEditionRightClickMenu;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;
import org.lancaster.group77.Listener.RightClickMenuListener;


public class DraggableComponent extends DisplayComponent {

    public DraggableComponent(int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame1, CSPPTFile file, String componentType) {
        super(x, y, width, height, listener, frame1, file, componentType);
        initRightClickMenu(this);
    }

    public static void initRightClickMenu(JComponent component) {
        component.addMouseListener(new RightClickMenuListener(new SlideEditionRightClickMenu()));
    }


}

