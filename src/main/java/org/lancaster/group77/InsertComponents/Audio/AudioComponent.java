package org.lancaster.group77.InsertComponents.Audio;

import javafx.embed.swing.JFXPanel;
import org.bytedeco.javacpp.Loader;
import org.lancaster.group77.DisplayComponents.Audio;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.Tools.GeneralTools;
import org.lancaster.group77.InsertComponents.DraggableComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.sound.sampled.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.event.MouseMotionListener;
import java.util.Base64;
import java.util.Optional;

public class AudioComponent extends DraggableComponent {
    private Clip clip;
    private Audio audioData;
    private String audioFile;

    public AudioComponent(int x, int y, int width, int height, MouseHandler listener, String audioFile, JLayeredPane frame, CSPPTFile file, int slideNum) throws IOException {
        super(x, y, width, height, listener, frame, file, "AudioComponent");

        audioData = new Audio();

        this.audioFile = audioFile;
        AudioInputStream audioInputStream = initAudioComponent(x, y, width, height, listener, audioFile, frame, file, slideNum, true);

        //init the audio data
        this.setComponentData(audioData);
        audioData.setX(x/GlobalVariables.FRAME_SIZE_INDEX_X);
        audioData.setY(y/GlobalVariables.FRAME_SIZE_INDEX_Y);
        audioData.setWidth(width/GlobalVariables.FRAME_SIZE_INDEX_X);
        audioData.setHeight(height/GlobalVariables.FRAME_SIZE_INDEX_Y);
        audioData.setWidth(width);
        audioData.setHeight(height);

        file.getSlides().get(slideNum).addObject(audioData);

    }

    public AudioComponent(Audio audioData1, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int slideNum) throws IOException {
        super((int) audioData1.getX(), (int) audioData1.getY(), (int) audioData1.getWidth(), (int) audioData1.getHeight(), listener, frame, file, "AudioComponent");

        //init the audio data
        audioData = audioData1;
        this.setComponentData(audioData);

        AudioInputStream audioInputStream = initAudioComponent((int) audioData1.getX(), (int) audioData1.getY(), (int) audioData1.getWidth(), (int) audioData1.getHeight(), listener, null, frame, file, slideNum, false);

        file.getSlides().get(slideNum).addObject(audioData);
    }

    public AudioComponent(Audio audioData1, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int slideNum, boolean isOpeningFile) throws IOException {
        super((int) (audioData1.getX()* GlobalVariables.FRAME_SIZE_INDEX_X), (int) (audioData1.getY()* GlobalVariables.FRAME_SIZE_INDEX_Y), (int) (audioData1.getWidth()* GlobalVariables.FRAME_SIZE_INDEX_X), (int) (audioData1.getHeight()* GlobalVariables.FRAME_SIZE_INDEX_Y), listener, frame, file, "AudioComponent");

        //init the audio data
        audioData = audioData1;
        this.setComponentData(audioData);

        AudioInputStream audioInputStream = initAudioComponent((int) (audioData1.getX()* GlobalVariables.FRAME_SIZE_INDEX_X), (int) (audioData1.getY()* GlobalVariables.FRAME_SIZE_INDEX_Y), (int) (audioData1.getWidth()* GlobalVariables.FRAME_SIZE_INDEX_X), (int) (audioData1.getHeight()* GlobalVariables.FRAME_SIZE_INDEX_Y), listener, null, frame, file, slideNum, false);

        if (!isOpeningFile)
            file.getSlides().get(slideNum).addObject(audioData);
    }

    private AudioInputStream initAudioComponent(int x, int y, int width, int height, MouseHandler listener, String audioFile, JLayeredPane frame, CSPPTFile file, int slideNum, boolean isAudioFileAPath) {
        setBounds(x, y, width, height);

        setLayout(null);
        setBackground(Color.blue);
        addMouseListener(listener);
        addMouseMotionListener(listener);
        setVisible(true);

        try {
            AudioInputStream audioInputStream;

            if (isAudioFileAPath) {
                File file1 = new File(audioFile);
                if (GeneralTools.getFileExtension(file1.getName()).equals("mp3")) {
                    audioInputStream = convertMp3ToWavInputStream(audioFile);
                } else {
                    audioInputStream = AudioSystem.getAudioInputStream(new File(audioFile));
                }
                audioData.setAudio_bytes(audioInputStreamToString(audioInputStream));
                audioData.setAudio_format(audioInputStream.getFormat().toString());
            } else {
                audioInputStream = stringToAudioInputStream(audioData.getAudio_bytes(), stringToAudioFormat(audioData.getAudio_format()));
            }

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

//            JLabel label = new JLabel("Click to play audio", JLabel.CENTER);
//            add(label);

            ImageIcon icon = createResizedImageIcon("src/main/resources/Icon/Animation Bar/start.png", 25, 25); // Adjust the size as needed
            JButton play = new JButton(icon);

//            JButton play = new JButton(">");
            play.setBounds(10, 3, 30, 30);
            play.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!clip.isRunning()) {
                        clip.start();
                    }
                }
            });

            ImageIcon iconp = createResizedImageIcon("src/main/resources/Icon/Audio/pauseAudio.png", 25, 25); // Adjust the size as needed
            JButton pause = new JButton(iconp);

//            JButton pause = new JButton("||");

            pause.setBounds(50, 3, 30, 30);
            pause.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (clip.isRunning()) {
                        clip.stop();
                    }
                }
            });

            JPanel buttonpanel = new JPanel();
            buttonpanel.add(play);
            buttonpanel.add(pause);

            buttonpanel.setLayout(null);
            buttonpanel.setBackground(Color.GRAY);
            Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
            buttonpanel.setBorder(border);

            buttonpanel.setBounds(7,7, 100-14, 50-14);
            add(buttonpanel);


            return audioInputStream;
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    private static ImageIcon createResizedImageIcon(String path, int width, int height) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Convert AudioInputStream to String
     *
     * @param audioInputStream
     * @return
     * @throws IOException
     */
    public String audioInputStreamToString(AudioInputStream audioInputStream) throws IOException {
        audioInputStream.mark(Integer.MAX_VALUE);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        while ((read = audioInputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, read);
        }
        byte[] audioBytes = byteArrayOutputStream.toByteArray();

        String encodedString = Base64.getEncoder().encodeToString(audioBytes);

        audioInputStream.reset();
        return encodedString;
    }

    /**
     * Convert String to AudioInputStream
     *
     * @param encodedString
     * @param format
     * @return
     */
    public AudioInputStream stringToAudioInputStream(String encodedString, AudioFormat format) {
        byte[] audioBytes = Base64.getDecoder().decode(encodedString);

        ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
        AudioInputStream audioInputStream = new AudioInputStream(bais, format, audioBytes.length / format.getFrameSize());

        return audioInputStream;
    }

    /**
     * Convert String to AudioFormat
     *
     * @param formatString
     * @return
     */
    public AudioFormat stringToAudioFormat(String formatString) {
        String[] parts = formatString.split(", ");

        String encodingType = parts[0].split(" ")[0];
        float sampleRate = Float.parseFloat(parts[0].split(" ")[1]);
        int sampleSizeInBits = Integer.parseInt(parts[1].split(" ")[0]);
        String channelConfig = parts[2].split(" ")[0];
        int channels = "stereo".equals(channelConfig) ? 2 : 1;
        int frameSize = Integer.parseInt(parts[3].split(" ")[0]);
        boolean bigEndian = !"little-endian".equals(parts[4].split(" ")[0]);

        AudioFormat.Encoding encoding = "PCM_SIGNED".equals(encodingType) ? AudioFormat.Encoding.PCM_SIGNED : AudioFormat.Encoding.PCM_UNSIGNED;
        AudioFormat format = new AudioFormat(encoding, sampleRate, sampleSizeInBits, channels, frameSize, sampleRate, bigEndian);

        return format;
    }

    public static AudioInputStream convertMp3ToWavInputStream(String mp3FilePath) throws UnsupportedAudioFileException, IOException {
        String ffmpeg = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ProcessBuilder processBuilder = new ProcessBuilder(
                ffmpeg, "-i", mp3FilePath, "-f", "wav", "-ar", "16000", "-ac", "1", "-y", "pipe:1");

        try {
            Process process = processBuilder.start();

            InputStream processInputStream = process.getInputStream();
            byte[] buffer = new byte[1024];
            int read;
            while ((read = processInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }

            process.waitFor();

            byte[] wavBytes = outputStream.toByteArray();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(wavBytes);

            AudioFormat format = new AudioFormat(16000, 16, 1, true, false);
            return new AudioInputStream(inputStream, format, wavBytes.length / format.getFrameSize());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("ffmpeg process was interrupted", e);
        }
    }
}


