package org.lancaster.group77.CommandLine;

import org.lancaster.group77.Frame.CSPPTFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLineComponent extends JPanel {
    private String currentDir;
    private JTextArea commandArea;

    public CommandLineComponent(CSPPTFrame frame) {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(38, 38, 38, 255));
        this.setVisible(false);

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

        //init command area
        commandArea = new JTextArea();
        commandArea.setEditable(true);
        commandArea.setBackground(new Color(38, 38, 38, 255));
        commandArea.setForeground(Color.WHITE);
        commandArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        commandArea.setLineWrap(true);
        commandArea.setCaretColor(Color.WHITE);
        commandArea.requestFocus();

        JScrollPane scrollPane = new JScrollPane(commandArea);
        add(scrollPane, BorderLayout.CENTER);

        currentDir = System.getProperty("user.dir");
        commandArea.append(currentDir + "$ ");

        commandArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                    executeCommand();
                } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    e.consume(); // 防止使用上下箭头键
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                // 确保只能在最后一行编辑
                int lastLineStart = commandArea.getText().lastIndexOf(currentDir + "$ ") + (currentDir + "$ ").length();
                if (commandArea.getCaretPosition() < lastLineStart) {
                    e.consume();
                }
            }
        });
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
            String text = commandArea.getText();
            int lastPromptIndex = text.lastIndexOf(currentDir + "$ ");
            String command = text.substring(lastPromptIndex + (currentDir + "$ ").length()).trim();

            if (command.isEmpty()) {
                commandArea.append("\n" + currentDir + "$ ");
                return;
            }

            if (command.startsWith("cd")) {
                if(command.length() == 2) {
                    commandArea.append("\n" + currentDir + "$ ");
                    return;
                }
                changeDirectory(command.substring(3));
            } else {
                ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
                builder.redirectErrorStream(true);

                Process process = builder.start();
                String encoding = System.getProperty("sun.jnu.encoding", "UTF-8");
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), encoding));

                String line;
                commandArea.append("\n");
                while ((line = reader.readLine()) != null) {
                    commandArea.append(line + "\n");
                }

                process.waitFor();
                commandArea.append("\n" + currentDir + "$ ");
            }
        } catch (Exception ex) {
            commandArea.append("\nError: " + ex.getMessage() + "\n" + currentDir + "$ ");
        }
    }


    private void changeDirectory(String newPath) {
        if (newPath.trim().isEmpty()) {
            commandArea.append("\n" + currentDir + "$ ");
            return;
        }

        File newDir = new File(newPath);

        if (!newDir.isAbsolute()) {
            newDir = new File(currentDir, newPath);
        }

        if (newDir.exists() && newDir.isDirectory()) {
            try {
                currentDir = newDir.getCanonicalPath();
                commandArea.append("\n" + currentDir + "$ ");
            } catch (IOException e) {
                commandArea.append("\nERROR: CAN NOT GET THE DIRECTORY LISTS\n");
            }
        } else {
            commandArea.append("\nERROR:  \"" + newPath + "\" is not exist or a directory。\n");
        }
    }

    public JTextArea getCommandArea() {
        return commandArea;
    }

    public void setCommandArea(JTextArea commandArea) {
        this.commandArea = commandArea;
    }
}