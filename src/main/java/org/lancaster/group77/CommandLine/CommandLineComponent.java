package org.lancaster.group77.CommandLine;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.Frame.Buttons.File.NewFileButton;
import org.lancaster.group77.Frame.Buttons.File.OpenFileButton;
import org.lancaster.group77.Frame.Buttons.File.SaveButton;
import org.lancaster.group77.Frame.Buttons.Presentation.FromBeginningButton;
import org.lancaster.group77.Frame.CSPPTFrame;
import org.lancaster.group77.Frame.Slides.SlideManager;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Arrays;

public class CommandLineComponent extends JPanel {
    private String currentDir;
    private JTextPane commandPane;
    private SlideManager slideManager;
    private CSPPTFrame cspptFrame;

    public CommandLineComponent(CSPPTFrame frame) {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(38, 38, 38, 255));
        this.setVisible(false);

        slideManager = frame.getSlideManager();
        cspptFrame = frame;

        //title
        JPanel titleBar = new JPanel(new BorderLayout());
        titleBar.setBackground(new Color(51, 51, 51));

        JLabel titleLabel = new JLabel("   Command Line");
        titleLabel.setForeground(new Color(217, 105, 255));
        titleBar.add(titleLabel, BorderLayout.WEST);

        JButton toggleButton = new JButton(">");
        toggleButton.setContentAreaFilled(false);
        toggleButton.setForeground(new Color(217, 105, 255));
        toggleButton.setBorderPainted(false);
        toggleButton.setFocusPainted(false);
        toggleButton.addActionListener(e -> {
            CommandLineComponent.this.setVisibility();
        });
        titleBar.add(toggleButton, BorderLayout.EAST);
        this.add(titleBar, BorderLayout.NORTH);

        //init command panel
        commandPane = new JTextPane();
        commandPane.setEditable(true);
        commandPane.setBackground(new Color(GlobalVariables.DEFAULT_BACKGROUND_COLOR));
        commandPane.setForeground(Color.WHITE);
        commandPane.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        commandPane.setCaretColor(Color.WHITE);
        commandPane.requestFocus();
        JScrollPane scrollPane = new JScrollPane(commandPane);
        add(scrollPane, BorderLayout.CENTER);

        appendText("Welcome to the CSPPT Command Line!\nUse ", Color.WHITE);
        appendText("<csppt help>", new Color(GlobalVariables.THEME_COLOR));
        appendText(" to get more commands.\n\n", Color.WHITE);

        currentDir = System.getProperty("user.dir");
        appendText(currentDir + "$ ", Color.WHITE);

        commandPane.setCaretPosition(commandPane.getDocument().getLength());

        commandPane.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                String os = System.getProperty("os.name").toLowerCase();
                String text = commandPane.getText();
                if (os.contains("win")) {
                    text=text.replace("\n", "");
                }
                int caretPos = commandPane.getCaretPosition();
                int lastPromptIndex = text.lastIndexOf(currentDir);

                if (lastPromptIndex == -1) {
                    return;
                }
                int lastLineStart = lastPromptIndex + (currentDir).length()+2;

                if ((e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) && caretPos <= lastLineStart) {
                    e.consume();
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                    executeCommand();
                } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    e.consume();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                String os = System.getProperty("os.name").toLowerCase();
                String text = commandPane.getText();
                if (os.contains("win")) {
                    text=text.replace("\n", "");
                }
                int caretPos = commandPane.getCaretPosition();
                int lastPromptIndex = text.lastIndexOf(currentDir + "$ ");
                if (lastPromptIndex == -1) {
                    return;
                }
                int lastLineStart = lastPromptIndex + (currentDir + "$ ").length();
                if (caretPos < lastLineStart) {
                    commandPane.setCaretPosition(commandPane.getDocument().getLength());
                } else {
                    super.keyTyped(e);
                }
                commandPane.setCaretPosition(commandPane.getDocument().getLength());
            }
        });
    }

    public void appendText(String text, Color color) {
        StyledDocument doc = commandPane.getStyledDocument();
        Style style = commandPane.addStyle("Color Style", null);
        StyleConstants.setForeground(style, color);
        try {
            doc.insertString(doc.getLength(), text, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        commandPane.setCaretPosition(commandPane.getDocument().getLength());
    }

    /**
     * Set the visibility of the command line.
     */
    public void setVisibility() {
        if (this.isVisible()) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
    }

    private void executeCommand() {
        try {
            String text = commandPane.getText();
            int lastPromptIndex = text.lastIndexOf(currentDir + "$ ");
            String command = text.substring(lastPromptIndex + (currentDir + "$ ").length()).trim();

            if (command.isEmpty()) {
                appendText("\n" + currentDir + "$ ", Color.WHITE);
                return;
            }

            if (command.startsWith("cd")) {
                if (command.length() == 2) {
                    appendText("\n" + currentDir + "$ ", Color.WHITE);
                    return;
                }
                changeDirectory(command.substring(3));
            }
            if (command.startsWith("csppt")) {
                cspptCommand(command);
            } else {
                executeExternalCommand(command);
            }

        } catch (Exception ex) {
            appendText("\nError: " + ex.getMessage() + "\n" + currentDir + "$ ", Color.WHITE);
        }

        commandPane.setCaretPosition(commandPane.getDocument().getLength());

    }

    private void cspptCommand(String command) throws FileNotFoundException {
        String[] parts = command.split(" ", 3);
        if (parts.length < 2) {
            appendText("\n[CSPPT] ", new Color(GlobalVariables.THEME_COLOR));
            appendText("Invalid csppt command\n", Color.RED);
            return;
        }

        switch (parts[1].trim()) {
            case "help":
                appendHelp();
                break;
            case "slide":
                if (parts.length < 3) {
                    appendText("\n[CSPPT] ", new Color(GlobalVariables.THEME_COLOR));
                    appendText("Invalid slide command\n", Color.RED);
                    return;
                }
                handleSlideCommand(parts[2].trim());
                break;
            default:
                if (!handleOtherCommand(parts[1].trim())) {
                    appendText("\n[CSPPT] ", new Color(GlobalVariables.THEME_COLOR));
                    appendText("Invalid csppt command\n", Color.RED);
                }
                break;
        }
        appendText(currentDir + "$ ", Color.WHITE);
    }

    private void executeExternalCommand(String command) throws IOException, InterruptedException {
        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder builder;
        if (os.contains("win")) {
            builder = new ProcessBuilder("cmd.exe", "/c", command);
        } else {
            builder = new ProcessBuilder("sh", "-c", command);
        }

        Process process = builder.start();
        String encoding = System.getProperty("sun.jnu.encoding", "UTF-8");
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), encoding));

        String line;
        appendText("\n", Color.WHITE);
        while ((line = reader.readLine()) != null) {
            appendText(line + "\n", Color.WHITE);
        }

        process.waitFor();
        appendText("\n" + currentDir + "$ ", Color.WHITE);
    }


    private void changeDirectory(String newPath) {
        if (newPath.trim().isEmpty()) {
            appendText("\n" + currentDir + "$ ", Color.WHITE);
            return;
        }

        File newDir = new File(newPath);

        if (!newDir.isAbsolute()) {
            newDir = new File(currentDir, newPath);
        }

        if (newDir.exists() && newDir.isDirectory()) {
            try {
                currentDir = newDir.getCanonicalPath();
                appendText("\n" + currentDir + "$ ", Color.WHITE);
            } catch (IOException e) {
                appendText("\nERROR: CAN NOT GET THE DIRECTORY LISTS\n", Color.RED);
            }
        } else {
            appendText("\nERROR:  \"" + newPath + "\" is not exist or a directory。\n", Color.RED);
        }
    }

    public JTextPane getCommandArea() {
        return commandPane;
    }

    private void appendHelp() {
        appendText("\n[CSPPT Help]\n", new Color(GlobalVariables.THEME_COLOR));
        appendText("Command format: 'csppt <command>'\n", Color.YELLOW);
        appendText(">In Slide Editing:\n", Color.PINK);
        appendText(">>slide <Number>     : Go to that slide.\n", Color.WHITE);
        appendText(">>slide -n           : Go to next slide.\n", Color.WHITE);
        appendText(">>slide -l           : Go to the last slide.\n", Color.WHITE);
        appendText(">>slide -c           : Create a new slide.\n", Color.WHITE);
        appendText(">>slide -rm <Number> : Remove that slide.\n", Color.WHITE);
        appendText(">>new                : Create a new CSPPT file.\n", Color.WHITE);
        appendText(">>save               : Save current file.\n", Color.WHITE);
        appendText(">>open               : Open a CSPPT file.\n", Color.WHITE);
        appendText(">>presentation       : Start the presentation.\n", Color.WHITE);

        appendText("\n>In Presentation:\n", Color.PINK);
        appendText(">>slide -p <Number>  : Switch to that slide.\n", Color.WHITE);
        appendText(">>slide -p -n        : Switch to next slide.\n", Color.WHITE);
        appendText(">>slide -p -l        : Switch to the last slide.\n", Color.WHITE);
        appendText(">>laser              : Turn on/off laser pointer mode.\n", Color.WHITE);
        appendText(">>pen                : Turn on/off mark pen mode.\n", Color.WHITE);
        appendText(">>end                : End the presentation.\n", Color.WHITE);

        appendText("\n", Color.WHITE);
    }

    private void handleSlideCommand(String command) {
        if (command.matches("\\d+")) {
            // Go to that slide
            appendText("\n[CSPPT] ", new Color(GlobalVariables.THEME_COLOR));
            if(GlobalVariables.IS_PRESENTING){
                appendText("Please end the presentation first.\n", Color.RED);
                return;
            }

            int slideNum = Integer.parseInt(command) - 1;
            if (slideNum < slideManager.getTotalSlides() && slideNum >= 0) {
                slideManager.setSlide(slideNum);
                appendText("Current slide: " + command + ".\n", Color.YELLOW);
            } else {
                appendText("Slide " + command + " does not exist.\n", Color.RED);
            }

        } else if (command.equals("-n")) {
            // Go to the next slide
            appendText("\n[CSPPT] ", new Color(GlobalVariables.THEME_COLOR));

            if(GlobalVariables.IS_PRESENTING){
                appendText("Please end the presentation first.\n", Color.RED);
                return;
            }
            int slideNum = slideManager.getCurrentSlideInt();
            if (slideNum + 1 < slideManager.getTotalSlides()) {
                slideManager.setSlide(slideNum + 1);
                appendText("Current slide: " + (slideNum + 2) + ".\n", Color.YELLOW);
            } else {
                appendText("This is the last slide.\n", Color.RED);
            }
        } else if (command.equals("-l")) {
            // Go to the last slide
            appendText("\n[CSPPT] ", new Color(GlobalVariables.THEME_COLOR));
            if(GlobalVariables.IS_PRESENTING){
                appendText("Please end the presentation first.\n", Color.RED);
                return;
            }

            int slideNum = slideManager.getCurrentSlideInt();
            if (slideNum - 1 < slideManager.getTotalSlides() && slideNum - 1 >= 0) {
                slideManager.setSlide(slideNum - 1);
                appendText("Current slide: " + (slideNum) + ".\n", Color.YELLOW);
            } else {
                appendText("This is the first slide.\n", Color.RED);
            }
        } else if (command.startsWith("-c")) {
            // Create a new slide
            slideManager.addSlide();
            appendText("\n[CSPPT] ", new Color(GlobalVariables.THEME_COLOR));
            if(GlobalVariables.IS_PRESENTING){
                appendText("Please end the presentation first.\n", Color.RED);
                return;
            }
            appendText("New slide added.\n", Color.YELLOW);
        } else if (command.startsWith("-rm ")) {
            // Remove a slide
            appendText("\n[CSPPT] ", new Color(GlobalVariables.THEME_COLOR));
            if(GlobalVariables.IS_PRESENTING){
                appendText("Please end the presentation first.\n", Color.RED);
                return;
            }

            String[] parts = command.split(" ", 2);
            if (parts.length == 2 && parts[1].matches("\\d+")) {
                int slideNum = Integer.parseInt(parts[1]) - 1;
                if (slideNum < slideManager.getTotalSlides() && slideNum >= 0) {
                    cspptFrame.removeSlide(slideNum);
                    appendText("Slide " + parts[1] + " removed.\n", Color.YELLOW);
                } else {
                    appendText("Slide " + parts[1] + " does not exist.\n", Color.RED);
                }
            } else {
                appendText("\nInvalid slide remove command.\n", Color.RED);
            }
        } else if (command.startsWith("-p ")) {
            // Presentation
            appendText("\n[CSPPT] ", new Color(GlobalVariables.THEME_COLOR));
            if(!GlobalVariables.IS_PRESENTING){
                appendText("Please start the presentation first.\n", Color.RED);
                return;
            }

            String[] parts = command.split(" ", 2);
            if (parts.length == 2 && parts[1].matches("\\d+")) {
                //presentation slide switch to that slide
                int slideNum = Integer.parseInt(parts[1]) - 1;
                if (slideNum < GlobalVariables.CURRENT_PRESENTATION_FRAME.getAmountofSlide() && slideNum >= 0) {
                    GlobalVariables.CURRENT_PRESENTATION_FRAME.goThatSlide(Integer.parseInt(parts[1]) - 1);
                    appendText("Current presentation slide: " + parts[1] + ".\n", Color.YELLOW);
                } else {
                    appendText("Slide " + parts[1] + " does not exist.\n", Color.RED);
                }
            } else if (parts.length == 2 && parts[1].equals("-n")) {
                //presentation slide switch to next slide
                if (GlobalVariables.CURRENT_PRESENTATION_FRAME.getCurrentSlide() == GlobalVariables.CURRENT_PRESENTATION_FRAME.getAmountofSlide() - 1) {
                    appendText("This is the last slide.\n", Color.RED);
                } else {
                    GlobalVariables.CURRENT_PRESENTATION_FRAME.goNextSlide();
                    appendText("Current presentation slide: " + (GlobalVariables.CURRENT_PRESENTATION_FRAME.getCurrentSlide() + 1) + ".\n", Color.YELLOW);
                }
            } else if (parts.length == 2 && parts[1].equals("-l")) {
                //presentation slide switch to last slide
                if (GlobalVariables.CURRENT_PRESENTATION_FRAME.getCurrentSlide() == 0) {
                    appendText("This is the first slide.\n", Color.RED);
                } else {
                    GlobalVariables.CURRENT_PRESENTATION_FRAME.goLastSlide();
                    appendText("Current presentation slide: " + (GlobalVariables.CURRENT_PRESENTATION_FRAME.getCurrentSlide() + 1) + ".\n", Color.YELLOW);

                }
            }
        } else {
            appendText("\n[CSPPT] ", new Color(GlobalVariables.THEME_COLOR));
            appendText("Invalid slide command\n", Color.RED);
        }
    }

    private boolean handleOtherCommand(String command) throws FileNotFoundException {
        if (command.equals("new")) {
            // new a CSPPT file
            appendText("\n[CSPPT] ", new Color(GlobalVariables.THEME_COLOR));
            if(GlobalVariables.IS_PRESENTING){
                appendText("Please end the presentation first.\n", Color.RED);
                return true;
            }
            NewFileButton.newFileFunction();
            appendText("New file created.\n", Color.YELLOW);
            return true;
        } else if (command.equals("save")) {
            // save current file
            appendText("\n[CSPPT] ", new Color(GlobalVariables.THEME_COLOR));
            if(GlobalVariables.IS_PRESENTING){
                appendText("Please end the presentation first.\n", Color.RED);
                return true;
            }
            SaveButton.saveFunction();
            appendText("New saved.\n", Color.YELLOW);
            return true;

        } else if (command.equals("open")) {
            // open a CSPPT file
            appendText("\n[CSPPT] ", new Color(GlobalVariables.THEME_COLOR));
            if(GlobalVariables.IS_PRESENTING){
                appendText("Please end the presentation first.\n", Color.RED);
                return true;
            }
            appendText("Opening a file...\n", Color.YELLOW);
            OpenFileButton.openFileFunction();
            return true;

        } else if (command.equals("presentation")) {
            // start the presentation
            appendText("\n[CSPPT] ", new Color(GlobalVariables.THEME_COLOR));
            if(GlobalVariables.IS_PRESENTING){
                appendText("Please end the presentation first.\n", Color.RED);
                return true;
            }
            appendText("Presentation starts.\n", Color.YELLOW);
            FromBeginningButton.fromBeginningFunction();
            this.setVisible(false);
            return true;

        } else if (command.equals("laser")) {
            // laser mode presentation
            appendText("\n[CSPPT] ", new Color(GlobalVariables.THEME_COLOR));
            if(!GlobalVariables.IS_PRESENTING){
                appendText("Please start the presentation first.\n", Color.RED);
                return true;
            }
            boolean isTurnOn = GlobalVariables.CURRENT_PRESENTATION_FRAME.turnLaser();
            if (isTurnOn) {
                appendText("Laser pointer mode on.\n", Color.YELLOW);
            } else {
                appendText("Laser pointer mode off.\n", Color.YELLOW);
            }
            return true;
        }else if (command.equals("pen")) {
            // pen mode presentation
            appendText("\n[CSPPT] ", new Color(GlobalVariables.THEME_COLOR));
            if(!GlobalVariables.IS_PRESENTING){
                appendText("Please start the presentation first.\n", Color.RED);
                return true;
            }
            boolean isTurnOn = GlobalVariables.CURRENT_PRESENTATION_FRAME.turnPen();
            if (isTurnOn) {
                appendText("Mark pen mode on.\n", Color.YELLOW);
            } else {
                appendText("Mark pen mode off.\n", Color.YELLOW);
            }
            return true;
        } else if (command.equals("end")) {
            // end the presentation
            appendText("\n[CSPPT] ", new Color(GlobalVariables.THEME_COLOR));
            if(!GlobalVariables.IS_PRESENTING){
                appendText("Please start the presentation first.\n", Color.RED);
                return true;
            }
            GlobalVariables.CURRENT_PRESENTATION_FRAME.endPresentation();
            appendText("Presentation ends.\n", Color.YELLOW);
            this.setVisible(false);
            return true;
        }
        return false;
    }
}