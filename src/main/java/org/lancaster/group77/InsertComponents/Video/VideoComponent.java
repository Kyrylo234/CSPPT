package org.lancaster.group77.InsertComponents.Video;

//import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.util.Base64;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.util.Duration;
import org.lancaster.group77.DisplayComponents.Video;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.InsertComponents.DraggableComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;


import javax.swing.*;

public class VideoComponent extends DraggableComponent {
    private String path;
    private MediaPlayer player;
    private boolean isPlaying = false;
    private Media media;
    private org.lancaster.group77.DisplayComponents.Video videoData;
    private Scene scene;
    private MediaView mediaView;
    private JButton playButton;
    private int width, height;

    public VideoComponent(int x, int y, int width, int height, MouseHandler listener, String path, JLayeredPane frame, CSPPTFile file, int layer, int slideNum) throws IOException {
        super(x, y, width, height + 125, listener, frame, file, "VideoComponent");
        addMouseListener(listener);
        addMouseMotionListener(listener);

        this.path = path;
        this.width = width;
        this.height = height;

        File source = new File(path);
        media = new Media(source.toURI().toString());

        initVideo(width, height);

        addPlayPauseButton();

        //init video data
        initVideoData(x, y, width, height, convertVideoToBase64(source), layer);
        file.getSlides().get(slideNum).addObject(videoData);
    }

    public VideoComponent(Video videoData, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int slideNum, boolean isOpeningFile) throws IOException {
        super((int) (videoData.getX() * GlobalVariables.FRAME_SIZE_INDEX_X), (int) (videoData.getY() * GlobalVariables.FRAME_SIZE_INDEX_Y), (int) (videoData.getWidth() * GlobalVariables.FRAME_SIZE_INDEX_X), (int) ((videoData.getHeight() * GlobalVariables.FRAME_SIZE_INDEX_Y) + 125), listener, frame, file, "VideoComponent");
        addMouseListener(listener);
        addMouseMotionListener(listener);

        this.width = (int) (videoData.getWidth() * GlobalVariables.FRAME_SIZE_INDEX_X);
        this.height = (int) (videoData.getHeight() * GlobalVariables.FRAME_SIZE_INDEX_Y);

        media = null;
        media = convertBase64ToMedia(videoData.getVideo_bytes());
        initVideo((int) (videoData.getWidth() * GlobalVariables.FRAME_SIZE_INDEX_X), (int) (videoData.getHeight() * GlobalVariables.FRAME_SIZE_INDEX_Y));

        addPlayPauseButton();
        //init video data
        this.videoData = videoData;
        this.setComponentData(videoData);
        if (!isOpeningFile) {
            file.getSlides().get(slideNum).addObject(videoData);
        }
    }

    private void initVideo(int width, int height) {
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {
            player = new MediaPlayer(media);
            mediaView = new MediaView(player);

            mediaView.setFitWidth(width);
            mediaView.setFitHeight(height);

            StackPane root = new StackPane(mediaView);
            scene = new Scene(root, width, height);
            scene.setFill(Color.GRAY);


            if (player != null) {
                player.setOnEndOfMedia(() -> {
                    player.seek(player.getStartTime());
                    player.pause();
                    isPlaying = false;
                });
            }

            try {
                SwingUtilities.invokeLater(() -> {
                    if (scene != null)
                        this.setScene(scene);
                });
            } catch (Exception e) {
            }
        });

        //error checking thread
        new Thread(() -> {
            while (!isPlaying && !(this.player == null)) {
                if (player.getError() != null) {
                    Platform.runLater(this::reAddVideoComponent);
                    break;
                }
                try {
                    Thread.sleep(100); // Check interval
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }).start();
    }

    private void addPlayPauseButton() {

        playButton = new JButton("Play/Pause");
        playButton.setVisible(true);
        int buttonX = (this.getWidth() - 200) / 2;
        playButton.setBounds(buttonX, this.getHeight() - 30, 200, 30);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VideoComponent thisComp = (VideoComponent) playButton.getParent();
                MediaException error = thisComp.getPlayer().getError();
                if (error != null) {
                    thisComp.reAddVideoComponent();
                    isPlaying = false;
                }
                if (!isPlaying) {
                    player.play();
                    isPlaying = true;
                } else if (isPlaying) {
                    player.pause();
                    isPlaying = false;
                }
            }
        });

        this.add(playButton);
    }


    private void initVideoData(int x, int y, int width, int height, String video_bytes, int layer) {
        videoData = new Video((x / GlobalVariables.FRAME_SIZE_INDEX_X), (y / GlobalVariables.FRAME_SIZE_INDEX_Y), layer, (width / GlobalVariables.FRAME_SIZE_INDEX_X), (height / GlobalVariables.FRAME_SIZE_INDEX_Y), video_bytes);
        this.setComponentData(videoData);
    }

    public void removeVideoComponent() {
        if (player != null) {
            player.dispose();
            player = null;
        }
    }

    public MediaPlayer getPlayer() {
        return this.player;
    }

    public void reAddVideoComponent() {
        initVideo(this.getWidth(), this.getHeight());
    }

    @Override
    public void resizeComponent(int inputX, int inputY, int inputWidth, int inputHeight, int layer) {
        super.resizeComponent(inputX, inputY, inputWidth, inputHeight, layer);
        setBounds(inputX, inputY, inputWidth, inputHeight);
        mediaView.setFitWidth(inputWidth);
        mediaView.setFitHeight(inputHeight);
        int buttonX = (inputWidth - 200) / 2;
        playButton.setBounds(buttonX, inputHeight - 30, 200, 30);
    }

    @Override
    public void resizeComponent(int inputX, int inputY, int inputWidth, int inputHeight) {
        super.resizeComponent(inputX, inputY, inputWidth, inputHeight);
        setBounds(inputX, inputY, inputWidth, inputHeight);
        mediaView.setFitWidth(inputWidth);
        mediaView.setFitHeight(inputHeight);
        int buttonX = (inputWidth - 200) / 2;
        playButton.setBounds(buttonX, inputHeight - 30, 200, 30);
    }


    /**
     * convert video file to base64 string
     *
     * @param videoFile
     * @return
     * @throws IOException
     */
    private String convertVideoToBase64(File videoFile) throws IOException {
        byte[] videoBytes = Files.readAllBytes(videoFile.toPath());

        String encodedString = Base64.getEncoder().encodeToString(videoBytes);
        return encodedString;
    }

    /**
     * convert base64 string to media (video mp4)
     *
     * @param base64String
     * @return
     */
    private Media convertBase64ToMedia(String base64String) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64String);

            File tempVideoFile = File.createTempFile("temp", ".mp4");
            tempVideoFile.deleteOnExit();

            try (FileOutputStream fos = new FileOutputStream(tempVideoFile)) {
                fos.write(decodedBytes);
            }

            Media media = new Media(tempVideoFile.toURI().toString());
            return media;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
