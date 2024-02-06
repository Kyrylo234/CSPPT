package org.lancaster.group77.InsertComponents;

//import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

//import uk.co.caprica.vlcj.binding.LibVlc;
//import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
//import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import javax.swing.*;


public class VideoComponent extends JFrame{
    public VideoComponent(int x, int y, int width, int height, MouseListener listener, String path){
        setBounds(x,y,width,height);
        setVisible(true);
        setLayout(null);
        addMouseListener(listener);
        addMouseMotionListener((MouseMotionListener) listener);
        setBackground(Color.black);
        EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        setContentPane(mediaPlayerComponent);
        mediaPlayerComponent.setBounds(7,7,width-14,height-14);
        mediaPlayerComponent.mediaPlayer().media().startPaused(path);
        mediaPlayerComponent.mediaPlayer().controls().play();
    }
}
