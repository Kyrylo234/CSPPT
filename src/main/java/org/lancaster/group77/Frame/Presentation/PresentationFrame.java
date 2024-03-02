package org.lancaster.group77.Frame.Presentation;

import org.javadev.effects.CubeAnimation;
import org.javadev.effects.FadeAnimation;
import org.javadev.effects.IrisAnimation;
import org.javadev.effects.RadialAnimation;
import org.javadev.layout.AnimatingCardLayout;
import org.lancaster.group77.CommandLine.CommandLineComponent;
import org.lancaster.group77.DisplayComponents.Animation;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.Frame.Buttons.Home.CommandLineButton;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.Frame.CSPPTFrame;
import org.lancaster.group77.Frame.RightClickMenus.PresentationRightClickMenu;
import org.lancaster.group77.InsertComponents.CodeSection.CodeSectionEditor;
import org.lancaster.group77.InsertComponents.DisplayComponent;
import org.lancaster.group77.InsertComponents.ScreenRecordComponent;
import org.lancaster.group77.InsertComponents.Table.TableComponent;
import org.lancaster.group77.InsertComponents.TextBox.TextBox;
import org.lancaster.group77.InsertComponents.UML.ClassDiagrams.ClassDiagram;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;

public class PresentationFrame extends JFrame {
    private int currentSlide = -1;
    private int amountofSlide = 1;

    private MouseHandler handler;
    private CSPPTFrame cspptFrame;
    private ArrayList<DisplayComponent> slideComponents;
    private AnimatingCardLayout ac;

    private ArrayList<JLayeredPane> slideShowPanel;
    private JLayeredPane mainPanel;
    private CommandLineComponent commandLineComponent;
    private JLayeredPane basePanel;
    private JPanel penPanel;
    private ArrayList<Point> points = new ArrayList<>();
    private ArrayList<ArrayList<Point>> lines = new ArrayList<>();
    private int buttonListY = 0;
    private ScreenRecordComponent screenRecordComponent;
    private long lastScrollTime = -1;
    private JLayeredPane basicPanel;


    /**
     * Create a PresentationFrame
     * Call showPresentation() to show the presentation after creating the frame
     *
     * @param cspptFrame the CSPPTFrame
     * @param handler    the mouse handler
     */
    public PresentationFrame(CSPPTFrame cspptFrame, MouseHandler handler) {

        this.handler = handler;
        this.cspptFrame = cspptFrame;
        setUndecorated(true);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        setTitle("CSPPT Presentation");
        setIconImage(new ImageIcon("src/main/resources/Icon/csppt_logo.png").getImage());

        slideComponents = new ArrayList<>();

        initKeyboardListener();

        slideShowPanel = new ArrayList<>();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        setSize((int) width, (int) height);
        setLocation(0, 0);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        basePanel = new JLayeredPane();
        basePanel.setLayout(null);
        ac = new AnimatingCardLayout(null);
        mainPanel = new JLayeredPane();
        mainPanel.setLayout(ac);
        mainPanel.setOpaque(true);
        setBackground(Color.BLACK);
        double width1 = (int) this.getWidth();
        double height1 = (int) this.getHeight();

        double aspectRatio = (double) 16 / 9;
        if (width / height <= aspectRatio) {
            height = (int) ((width1 / 16) * 9);
        } else {
            width = (int) (height1 / 9) * 16;
        }
        basePanel.setBounds((int) (this.getWidth() - width) / 2, (int) (this.getHeight() - height) / 2, (int) width, (int) height);

        initButtons();
        mainPanel.setBounds(0, 0, basePanel.getWidth(), basePanel.getHeight());
        basePanel.add(mainPanel);

        //init first slide
        initSlideShowPanels();
        goNextSlide();

        basicPanel = new JLayeredPane();
        basicPanel.setLayout(null);
        basicPanel.setBounds(0,0, (int) getWidth(), (int) getHeight());
        basicPanel.add(basePanel);
        basicPanel.setBackground(Color.BLACK);
        basicPanel.setOpaque(true);
        add(basicPanel);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //init command line
        initCommandLine();

        //init right click menu
        PresentationRightClickMenu menu = new PresentationRightClickMenu(this);
        basePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int x = e.getX();
                    int y = e.getY();
                    menu.setX1(x);
                    menu.setY1(y);
                    menu.show(e.getComponent(), x, y);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int x = e.getX();
                    int y = e.getY();
                    menu.setX1(x);
                    menu.setY1(y);
                    menu.show(e.getComponent(), x, y);
                } else {
                    if (penPanel == null) {
                        goNextSlide();
                    }
                }
            }
        });

        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                long currentTime = System.currentTimeMillis();
                if (lastScrollTime == -1 || (currentTime - lastScrollTime > 100)) {
                    int units = e.getWheelRotation();
                    if (units < 0) {
                        goLastSlide();

                    } else {
                        goNextSlide();

                    }
                    lastScrollTime = currentTime;
                }
            }
        });
    }

    public void initCommandLine() {
        commandLineComponent = cspptFrame.getCommandLineComponent();
        basePanel.add(commandLineComponent);

        int width = basePanel.getWidth();
        int height = basePanel.getHeight();
        int componentWidth = width / 2;
        commandLineComponent.setBounds(width - componentWidth, 0, componentWidth, height);
//        commandLineComponent.revalidate();
//        commandLineComponent.repaint();
        basePanel.moveToFront(commandLineComponent);
        basePanel.revalidate();
        basePanel.repaint();
    }

    /**
     * Initialize the slide show panels
     */
    private void initSlideShowPanels() {
        amountofSlide = cspptFrame.getSlideManager().getTotalSlides();
        for (int i = 0; i < amountofSlide; i++) {
            JLayeredPane panel = new JLayeredPane();
            panel.setLayout(null);
            addComponents(panel, i);
            slideShowPanel.add(panel);
            mainPanel.add(panel, "panel" + i);
        }
        GlobalVariables.FRAME_SIZE_INDEX_X = (mainPanel.getWidth());
        GlobalVariables.FRAME_SIZE_INDEX_Y = (mainPanel.getHeight());
    }

    /**
     * Initialize the buttons at left bottom side
     */
    private void initButtons() {
        int height = this.getHeight() - 100;
        JButton prevButton = new IconButton(new ImageIcon("src/main/resources/Icon/Slide Show/left.png"), 20, height, 50, 50, "lastSlide");
        JButton nextButton = new IconButton(new ImageIcon("src/main/resources/Icon/Slide Show/right.png"), 75, height, 50, 50, "nextSlide");
        JButton laserButton = new IconButton(new ImageIcon("src/main/resources/Icon/Slide Show/laser.png"), 130, height, 50, 50, "laser");
        JButton penButton = new IconButton(new ImageIcon("src/main/resources/Icon/Slide Show/pen.png"), 185, height, 50, 50, "pen");
        JButton commandlineButton = new IconButton(new ImageIcon("src/main/resources/Icon/Slide Show/command_line.png"), 240, height, 50, 50, "commandline_presentation");
        JButton endButton = new IconButton(new ImageIcon("src/main/resources/Icon/Slide Show/end.png"), 295, height, 50, 50, "end_presentation");

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goLastSlide();
            }
        });


        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goNextSlide();
            }
        });

        laserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turnLaser();
            }
        });

        penButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turnPen();
            }
        });

        commandlineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommandLineButton.openCommandLineFunction();
            }
        });

        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endPresentation();
            }
        });

        basePanel.add(prevButton);
        basePanel.add(nextButton);
        basePanel.add(commandlineButton);
        basePanel.add(laserButton);
        basePanel.add(penButton);
        basePanel.add(endButton);
        prevButton.setOpaque(false);
        nextButton.setOpaque(false);
        commandlineButton.setOpaque(false);
        laserButton.setOpaque(false);
        penButton.setOpaque(false);
        endButton.setOpaque(false);

        buttonListY = prevButton.getY();
        basePanel.revalidate();
        basePanel.repaint();
    }


    /**
     * Show the presentation
     * Disable the mouse handler and keyboard input
     */
    public void showPresentation() {
        setVisible(true);
        handler.disableHandler();
        disableComponentsInput();
        GlobalVariables.IS_PRESENTING = true;
        this.revalidate();
        this.repaint();
    }

    /**
     * End the presentation
     * Enable the mouse handler and keyboard input
     */
    public void endPresentation() {
        dispose();
        handler.getSlideManager().setSlide(currentSlide);
        handler.enableHandler();
        enableComponentsInput();
        commandLineComponent.setVisible(false);
        GlobalVariables.IS_PRESENTING = false;
        GlobalVariables.cspptFrame.resizeScreen();
        if (screenRecordComponent != null) {
            screenRecordComponent.setThreadRunning(false);
        }
    }


    /**
     * Go to the slide with the given slide number
     *
     * @param slideNum the slide number to go to
     * @return true if the slide number is valid, false otherwise
     */
    public boolean goThatSlide(int slideNum) {
        if (slideNum >= amountofSlide) {
            return false;
        }

        int slide_before = currentSlide;
        currentSlide = slideNum;

        try {
            setAnimation(currentSlide);
            showThatSlide("panel" + String.valueOf(currentSlide));
            displayCodeSectionButtons(currentSlide);
            mainPanel.setBackground(new Color(GlobalVariables.cspptFrame.getFile().getSlides().get(currentSlide).getSlideDatas().get(0).getBackground_color()));
        } catch (Exception e) {
            currentSlide = slide_before;
        }
        return true;
    }

    /**
     * Go to the slide with the given slide number
     * No animation
     *
     * @param slideNum the slide number to go to
     * @return true if the slide number is valid, false otherwise
     */
    public boolean goThatSlideWithNoAnimation(int slideNum) {
        if (slideNum >= amountofSlide) {
            return false;
        }

        int slide_before = currentSlide;
        currentSlide = slideNum;

        try {
            ac.setAnimation(null);
            showThatSlide("panel" + String.valueOf(currentSlide));
            mainPanel.setBackground(new Color(GlobalVariables.cspptFrame.getFile().getSlides().get(currentSlide).getSlideDatas().get(0).getBackground_color()));
            displayCodeSectionButtons(currentSlide);
        } catch (Exception e) {
            currentSlide = slide_before;
        }
        return true;
    }

    /**
     * Go to the next slide
     *
     * @return true if the next slide exists, false otherwise
     */
    public boolean goNextSlide() {
        if (penPanel != null) {
            clearPenPanel();
        }

        if (currentSlide + 1 >= amountofSlide) {
            return false;
        }

        currentSlide++;

        try {
            setAnimation(currentSlide);
            showThatSlide("panel" + String.valueOf(currentSlide));
            mainPanel.setBackground(new Color(GlobalVariables.cspptFrame.getFile().getSlides().get(currentSlide).getSlideDatas().get(0).getBackground_color()));
            displayCodeSectionButtons(currentSlide);
        } catch (Exception e) {
            currentSlide--;
        }
        return true;
    }

    /**
     * Go to the last slide
     *
     * @return true if the last slide exists, false otherwise
     */
    public boolean goLastSlide() {
        if (penPanel != null) {
            clearPenPanel();
        }

        if (currentSlide - 1 < 0) {
            return false;
        }

        currentSlide--;
        try {
            setAnimation(currentSlide);
            showThatSlide("panel" + String.valueOf(currentSlide));
            mainPanel.setBackground(new Color(GlobalVariables.cspptFrame.getFile().getSlides().get(currentSlide).getSlideDatas().get(0).getBackground_color()));
            displayCodeSectionButtons(currentSlide);
        } catch (Exception e) {
            currentSlide++;
        }
        return true;
    }

    /**
     * Show the slide with the given slide number
     *
     * @param panelName
     */
    private void showThatSlide(String panelName) {
        ac.show(mainPanel, panelName);
    }

    /**
     * Display the code section buttons
     *
     * @param slideNum
     */
    private void displayCodeSectionButtons(int slideNum) {
        ArrayList<DisplayComponent> slideComponents = cspptFrame.getSlideManager().getSlideContent(slideNum);
        SwingUtilities.invokeLater(() -> {
            for (Component component : slideComponents) {
                if (component instanceof CodeSectionEditor) {
                    ((CodeSectionEditor) component).displayCodeRunButton(slideShowPanel.get(slideNum), true);
                    ((CodeSectionEditor) component).updateButtonPosition(component.getX(), component.getY(), component.getWidth(), component.getHeight());
                    slideShowPanel.get(slideNum).revalidate();
                    slideShowPanel.get(slideNum).repaint();
                }
            }
        });
    }

    /**
     * Add components to the slide show panel
     *
     * @param panel
     * @param slideNum
     */
    public void addComponents(JLayeredPane panel, int slideNum) {
        slideComponents = cspptFrame.getSlideManager().getSlideContent(slideNum);
        for (Component component : slideComponents) {
            System.out.println(slideComponents.size());
            ((DisplayComponent) component).resizeComponent((int) (((DisplayComponent) component).getComponentData().getX() * (mainPanel.getWidth())), (int) (((DisplayComponent) component).getComponentData().getY() * (mainPanel.getHeight())), (int) (((DisplayComponent) component).getComponentData().getWidth() * (mainPanel.getWidth())), (int) (((DisplayComponent) component).getComponentData().getHeight() * (mainPanel.getHeight())));
            if(component instanceof ClassDiagram) {
                ((ClassDiagram) component).updateLines();
            }

            panel.add(component);
        }
    }


    /**
     * Initialize the keyboard listener
     * Press ESC to exit the presentation
     */
    private void initKeyboardListener() {
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke("ESCAPE");
        Action escapeAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                endPresentation();
            }
        };
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);

        KeyStroke rightArrowKeyStroke = KeyStroke.getKeyStroke("RIGHT");
        // Create the action for the right arrow key press event
        Action rightArrowAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                goNextSlide();
            }
        };

        // Register the right arrow key press event with the action
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(rightArrowKeyStroke, "RIGHT_ARROW");
        getRootPane().getActionMap().put("RIGHT_ARROW", rightArrowAction);


        KeyStroke leftArrowKeyStroke = KeyStroke.getKeyStroke("LEFT");
        // Create the action for the right arrow key press event
        Action leftArrowAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                goLastSlide();
            }
        };

        // Register the right arrow key press event with the action
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(leftArrowKeyStroke, "LEFT_ARROW");
        getRootPane().getActionMap().put("LEFT_ARROW", leftArrowAction);
    }


    /**
     * Set the animation for the slide
     *
     * @param slideNum the slide number
     */
    public void setAnimation(int slideNum) {

        try {
            Animation animation = GlobalVariables.cspptFrame.getFile().getSlides().get(slideNum).getAnimations().get(0);
            if (animation.getAnimation_name() != null) {
                if (animation.getAnimation_name().equals("cube")) {
                    cubeAnimation();
                }
                if (animation.getAnimation_name().equals("iris")) {
                    irisAnimation();
                }
                if (animation.getAnimation_name().equals("radial")) {
                    radialAnimation();
                }
                if (animation.getAnimation_name().equals("fade")) {
                    FadeAnimation();
                }
                ac.setAnimationDuration(animation.getDuration());
            } else {
                ac.setAnimation(null);
            }
        } catch (Exception e) {
            ac.setAnimation(null);
        }
    }


    public void cubeAnimation() {
        ac.setAnimation(new CubeAnimation());
    }

    public void irisAnimation() {
        ac.setAnimation(new IrisAnimation());
    }

    public void radialAnimation() {
        ac.setAnimation(new RadialAnimation());
    }

    public void FadeAnimation() {
        ac.setAnimation(new FadeAnimation());
    }

    /**
     * Disable the input from all components
     * This is used when the presentation is shown
     */
    public void disableComponentsInput() {
        for (int i = 0; i < amountofSlide; i++) {
            ArrayList<DisplayComponent> slideComponents = cspptFrame.getSlideManager().getSlideContent(i);
            for (DisplayComponent component : slideComponents) {
                component.setInvisible();
                if (component instanceof TextBox) {
                    ((TextBox) component).disableInput();
                } else if (component instanceof CodeSectionEditor) {
                    ((CodeSectionEditor) component).disableInput();
                } else if (component instanceof TableComponent) {
                    ((TableComponent) component).disableInput();
                }
            }
        }
    }

    /**
     * Enable the input from all components
     * This is used when the presentation is ended
     */
    public void enableComponentsInput() {
        for (int i = 0; i < amountofSlide; i++) {
            ArrayList<DisplayComponent> slideComponents = cspptFrame.getSlideManager().getSlideContent(i);
            for (Component component : slideComponents) {
                if (component instanceof TextBox) {
                    ((TextBox) component).enableInput();
                } else if (component instanceof CodeSectionEditor) {
                    ((CodeSectionEditor) component).enableInput();
                } else if (component instanceof TableComponent) {
                    ((TableComponent) component).enableInput();
                } else if (component instanceof JTextField) {
                    ((JTextField) component).setEditable(true);
                } else if (component instanceof JTextArea) {
                    ((JTextArea) component).setEditable(true);
                } else if (component instanceof JTextPane) {
                    ((JTextPane) component).setEditable(true);
                }
            }
        }
    }

    /**
     * Turn on or off the laser pointer
     */
    public boolean turnLaser() {
        if (penPanel != null) {
            clearPenPanel();
        }

        if (this.getCursor().equals(Cursor.getDefaultCursor())) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();

            Image image = toolkit.getImage("src/main/resources/Icon/Slide Show/laser-mouse-pointer.png");
            Cursor c = toolkit.createCustomCursor(image, new Point(this.getX(), this.getY()), "img");
            this.setCursor(c);
            return true;
        } else {
            this.setCursor(Cursor.getDefaultCursor());
            return false;
        }
    }

    /**
     * Turn on/off the pen
     * Create a new panel for drawing
     */
    public boolean turnPen() {
        if (penPanel != null) {
            clearPenPanel();
            return false;
        }

        //pen cursor - actually it is a 32x32 pixel image
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension cursorSize = toolkit.getBestCursorSize(32, 32);
        BufferedImage cursorImg = new BufferedImage(cursorSize.width, cursorSize.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = cursorImg.createGraphics();
        g2.setColor(new Color(GlobalVariables.THEME_COLOR));
        int centerX = cursorSize.width / 2;
        int centerY = cursorSize.height / 2;
        g2.fillRect(centerX - 1, centerY - 1, 3, 3);
        g2.dispose();
        Cursor penCursor = toolkit.createCustomCursor(cursorImg, new Point(centerX, centerY), "");
        this.setCursor(penCursor);

        penPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(GlobalVariables.THEME_COLOR));
                g2d.setStroke(new BasicStroke(2));

                for (ArrayList<Point> line : lines) {
                    if (line.size() > 1) {
                        Point start = line.get(0);
                        for (int i = 1; i < line.size(); i++) {
                            Point end = line.get(i);
                            g2d.drawLine(start.x, start.y, end.x, end.y);
                            start = end;
                        }
                    }
                }
            }
        };

        penPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ArrayList<Point> newLine = new ArrayList<>();
                newLine.add(new Point(e.getX(), e.getY()));
                lines.add(newLine);
            }
        });

        penPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //avoid join to last point
                ArrayList<Point> currentLine = lines.get(lines.size() - 1);
                currentLine.add(new Point(e.getX(), e.getY()));
                penPanel.repaint();
            }
        });

        penPanel.setOpaque(false);
        penPanel.setBounds(0, 0, getWidth(), buttonListY);
        basePanel.add(penPanel, JLayeredPane.DRAG_LAYER);
        basePanel.moveToFront(penPanel);
        return true;
    }

    /**
     * Clear the pen panel
     * Remove all the drawings
     */
    public void clearPenPanel() {
        lines.clear();
        penPanel.revalidate();
        penPanel.repaint();
        basePanel.remove(penPanel);
        penPanel = null;
        basePanel.revalidate();
        basePanel.repaint();
        this.setCursor(Cursor.getDefaultCursor());
    }

    /**
     * Insert text to the current slide
     *
     * @param x
     * @param y
     */
    public void insertText(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 100, 30);
        textField.setBackground(Color.WHITE);
        textField.setOpaque(true);
        textField.requestFocus();
        slideShowPanel.get(currentSlide).add(textField);
        slideShowPanel.get(currentSlide).moveToFront(textField);
        slideShowPanel.get(currentSlide).revalidate();
        slideShowPanel.get(currentSlide).repaint();
    }

    public int getCurrentSlide() {
        return currentSlide;
    }

    public int getAmountofSlide() {
        return amountofSlide;
    }

    public ScreenRecordComponent getScreenRecordComponent() {
        return screenRecordComponent;
    }

    public void setScreenRecordComponent(ScreenRecordComponent screenRecordComponent) {
        this.screenRecordComponent = screenRecordComponent;
    }
}