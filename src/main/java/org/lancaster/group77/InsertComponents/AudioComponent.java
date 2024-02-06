package org.lancaster.group77.InsertComponents;

import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class AudioComponent extends JFrame {
    private Clip clip;
    AudioComponent audio;

    public AudioComponent(int x, int y, int width, int height, MouseHandler listener,String audioFile){
      //  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(x,y,width,height);
        setVisible(true);
        setLayout(null);
        setBackground(Color.blue);
        addMouseListener(listener);
        addMouseMotionListener(listener);


       // setContentPane(this);

        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(audioFile));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            JOptionPane.showMessageDialog(null, "press ok to pause");
            long clipTimePosition = clip.getMicrosecondPosition();
            clip.stop();
            JOptionPane.showMessageDialog(null, "Press ok to stop resume");
            clip.setMicrosecondPosition(clipTimePosition);
            clip.start();

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();

        }
       // frame.setLocationRelativeTo(null);

        //file.getSlides().get(0).addObject(audio);

    }



//        SwingUtilities.invokeLater(() -> {
//            AudioComponent player = new AudioComponent();
//            player.setVisible(true);
//        });
    }


