package org.lancaster.group77.InsertComponents.Shapes;

import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.InsertComponents.DraggableComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.awt.*;

public class InsertShape extends DraggableComponent {
    Image img;
    public InsertShape(int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame, CSPPTFile file){
        super(x,y,width,height, listener, frame,file,"Shape");

        this.setPreferredSize(new Dimension(200,200));
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
        img = new ImageIcon("src/main/resources/Icon/Home Bar/image.png").getImage();

    }

    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;

       // g2D.drawLine(0,0,200,200);
        g2D.fillRect(50,50,20,20);
        //g2D.drawImage(img,0,0,null);
    }
}
