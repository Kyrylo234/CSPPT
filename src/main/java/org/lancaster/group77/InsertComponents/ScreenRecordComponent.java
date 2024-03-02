package org.lancaster.group77.InsertComponents;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.SaveAsFileChooser;
import org.lancaster.group77.Frame.Buttons.Presentation.RecordButton;
import org.lancaster.group77.Frame.Dialogs.PopupDialog;
import org.lancaster.group77.Frame.Presentation.PresentationFrame;
import org.lancaster.group77.Frame.Slides.SlideManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.TimeUnit;

public class ScreenRecordComponent implements Runnable {
    private Rectangle rect;
    private boolean isThreadRunning;
    private PresentationFrame presentationFrame;

    /**
     * creating a thread that continues running while recording
     */

    public void buttonAction() {
        rect = presentationFrame.getBounds();
        if(isThreadRunning){
            isThreadRunning = false;
        }else{
            rect.width = (int) (Math.ceil((double) rect.width / 2) * 2);
            rect.height = (int) (Math.ceil((double) rect.height / 2) * 2);
            isThreadRunning = true;
            Thread th = new Thread(this);
            th.start();
        }
    }

    @Override
    public void run() {
        String tempPath = "tmp/csppt_recording.mp4";

        IMediaWriter writer = ToolFactory.makeWriter(tempPath);
        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_H264, rect.width, rect.height);
        long Startime = System.nanoTime();
        while (true) {
            try {
                BufferedImage image1 = SlideManager.convertPanelToImage(presentationFrame,rect.width, rect.height);
                writer.encodeVideo(0, image1, System.nanoTime() - Startime, TimeUnit.NANOSECONDS);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (!isThreadRunning) {
                break;
            }

        }
        writer.close();

        /**
         * lets user save file in their desired directory
         */

        SaveAsFileChooser saveAsFileChooser = new SaveAsFileChooser();
        String location = saveAsFileChooser.getSaveAsFileLocationMP4();
        try {
            if (location != null && !location.isEmpty()) {
                Files.move(Paths.get(tempPath), Paths.get(location), StandardCopyOption.REPLACE_EXISTING);
                new PopupDialog("Video successfully saved", "Success");
            } else {
                new PopupDialog("Video saving failed", "Fail");
            }
        } catch (IOException e) {
            new PopupDialog("Video saving failed", "Fail");
        }

    }

    public PresentationFrame getPresentationFrame() {
        return presentationFrame;
    }

    public void setPresentationFrame(PresentationFrame presentationFrame) {
        this.presentationFrame = presentationFrame;
    }

    public boolean isThreadRunning() {
        return isThreadRunning;
    }

    public void setThreadRunning(boolean threadRunning) {
        isThreadRunning = threadRunning;
    }
}
