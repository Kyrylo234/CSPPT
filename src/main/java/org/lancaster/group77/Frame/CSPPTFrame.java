package org.lancaster.group77.Frame;

import org.lancaster.group77.DisplayComponents.*;
import org.lancaster.group77.DisplayComponents.Image;
import org.lancaster.group77.DisplayComponents.Shape;
import org.lancaster.group77.FileSystem.FileChooser;
import org.lancaster.group77.Frame.Buttons.Presentation.FromCurrentButton;
import org.lancaster.group77.Frame.ProgressBars.ResizingProgressBar;
import org.lancaster.group77.Frame.RightClickMenus.SlideEditionRightClickMenu;
import org.lancaster.group77.Frame.RightClickMenus.SlideListRightClickMenu;
import org.lancaster.group77.InsertComponents.CodeSection.CodeSectionEditor;
import org.lancaster.group77.InsertComponents.Table.TableComponent;
import org.lancaster.group77.InsertComponents.UML.ClassDiagrams.ClassDiagram;
import org.lancaster.group77.InsertComponents.UML.Lines.BaseLine;
import org.lancaster.group77.Listener.CSPPTFrameAdapter;
import org.lancaster.group77.Listener.KeyListener;
import org.lancaster.group77.CommandLine.CommandLineComponent;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.impl.ActionListenerImplementation;
import org.lancaster.group77.Frame.Bars.*;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.Frame.Slides.SlideManager;
import org.lancaster.group77.InsertComponents.DisplayComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;
import org.lancaster.group77.Listener.ResizeListener;
import org.lancaster.group77.Listener.RightClickMenuListener;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to create the main frame for editor for the application
 */
public class CSPPTFrame extends BaseFrame {
    private CSPPTFile file;
    private String fileLocation;

    private JLayeredPane panel; // it contains all the components
    private JLayeredPane panel1; // panel1 is the main panel in the bottom

    private JPanel bottomPanel;
    private JScrollPane scrollPane;
    private IconButton slideShowButton;
    private CommandLineComponent commandLineComponent;
    private HomeBar mainHomeBar;
    private AnimationBar mainAnimationBar;
    private ResizingProgressBar resizingProgressBar;
    private JLabel slideLabel;

    private MouseHandler handler;
    private SlideManager slideManager;


    /**
     * Constructor for the frame
     * Start the system by creating the frame
     */
    public CSPPTFrame() {
        file = new CSPPTFile();
        GlobalVariables.cspptFrame = this;
        //Mouse listener which is used for moving the components around the screen
        handler = new MouseHandler(this);
        GlobalVariables.handler = this.handler;
        ActionListenerImplementation listener = new ActionListenerImplementation();

        //Frame settings
        setTitle("CSPPT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setIconImage(new ImageIcon("src/main/resources/Icon/csppt_logo.png").getImage());
        setUndecorated(true);

        //Top panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(38, 38, 38));
        topPanel.setPreferredSize(new Dimension(800, 30));
        ImageIcon originalIcon = new ImageIcon("src/main/resources/Icon/csppt_logo.png");
        java.awt.Image scaledImage = originalIcon.getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(scaledImage));
        JLabel titleLabel = new JLabel("CSPPT");
        titleLabel.setForeground(Color.WHITE);

        JButton undoButton = createTopButton("src/main/resources/Icon/TopBar/undo.png", "Undo");
        JButton redoButton = createTopButton("src/main/resources/Icon/TopBar/redo.png", "Redo");

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        titlePanel.setOpaque(false);
        titlePanel.add(iconLabel);
        titlePanel.add(titleLabel);
        titlePanel.add(undoButton);
        titlePanel.add(redoButton);

        undoButton.addActionListener(listener);
        redoButton.addActionListener(listener);

        MouseAdapter ma = new MouseAdapter() {
            private Point initialClick;

            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(topPanel);
                int thisX = frame.getLocation().x;
                int thisY = frame.getLocation().y;
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                frame.setLocation(X, Y);
            }
        };
        topPanel.addMouseListener(ma);
        topPanel.addMouseMotionListener(ma);

        // control panel buttons
        JButton minimizeButton = createTopButton("src/main/resources/Icon/TopBar/minimise.png", "minimise");
        JButton maximizeButton = createTopButton("src/main/resources/Icon/TopBar/big.png", "maximise");
        JButton closeButton = createTopButton("src/main/resources/Icon/TopBar/close.png", "close");
        minimizeButton.addActionListener(e -> setState(Frame.ICONIFIED));
        maximizeButton.addActionListener(e -> {
            if (getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                setExtendedState(JFrame.NORMAL);
                changeButtonIcon("src/main/resources/Icon/TopBar/big.png", maximizeButton);
            } else {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                changeButtonIcon("src/main/resources/Icon/TopBar/small.png", maximizeButton);
            }
        });
        closeButton.addActionListener(e -> System.exit(0));

        // control panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        controlPanel.setOpaque(false);
        controlPanel.add(minimizeButton);
        controlPanel.add(maximizeButton);
        controlPanel.add(closeButton);

        topPanel.add(titlePanel, BorderLayout.WEST);
        topPanel.add(controlPanel, BorderLayout.EAST);

        //Tab top
        JTabbedPane tabbedPane = new JTabbedPane();
        Dimension dimension = new Dimension(400, 120);
        tabbedPane.setPreferredSize(dimension);
        tabbedPane.setBackground(new Color(38, 38, 38));
        tabbedPane.setForeground(Color.WHITE);

        //Create a new action listener for the File bar
        panel1 = new JLayeredPane();
        panel1.setLayout(null);
        panel1.setBackground(Color.BLACK);
        panel1.setOpaque(true);
        panel = new JLayeredPane();
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setBackground(new Color(GlobalVariables.DEFAULT_BACKGROUND_COLOR));
        panel.setOpaque(true);
        panel.setSize(100, 100);
        panel1.add(panel, Component.CENTER_ALIGNMENT);
        add(panel1, BorderLayout.CENTER);
        slideManager = new SlideManager(handler, panel, this);
        handler.setMainSlideManager(slideManager);

        //Adding the panels
        //adding the buttons to the different panels
        JPanel fileTab = new JPanel();
        FileBar mainFileBar = new FileBar(tabbedPane, fileTab, listener);
        mainHomeBar = new HomeBar(tabbedPane, new JPanel(), listener, handler);
        InsertBar mainInsertBar = new InsertBar(tabbedPane, new JPanel(), listener, handler);
        mainAnimationBar = new AnimationBar(tabbedPane, new JPanel(), handler, listener);
        PresentationBar mainPresentationBar = new PresentationBar(tabbedPane, new JPanel(), listener, handler);
        tabbedPane.setSelectedIndex(1);

        //adding to the frame
        JPanel combinedTopPanel = new JPanel(new BorderLayout());
        combinedTopPanel.add(topPanel, BorderLayout.NORTH);
        combinedTopPanel.add(tabbedPane, BorderLayout.SOUTH);
        add(combinedTopPanel, BorderLayout.NORTH);

        //Page select left
        scrollPane = slideManager.getSlideScrollPane();
        Dimension preferredSize = scrollPane.getPreferredSize();
        preferredSize.width = 163;
        scrollPane.setPreferredSize(preferredSize);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane, BorderLayout.WEST);

        //Edit frame right
        panel.addMouseListener(handler);

        //Init bottom panel
        initBottomPanel();

        //Window size
        setSize(800, 600);

        //init command line
        commandLineComponent = new CommandLineComponent(this);
        panel1.add(commandLineComponent, JLayeredPane.PALETTE_LAYER);

        listener.addMouseHandler(handler);

        this.addComponentListener(new CSPPTFrameAdapter(this));

        //init key listener
        KeyListener.initKeyListenerForPanel();
        tabbedPane.addMouseListener(handler);
        topPanel.addMouseListener(handler);

        //init resize screen listener
        ResizeListener resizeListener = new ResizeListener(this);
        addMouseListener(resizeListener);
        addMouseMotionListener(resizeListener);
        topPanel.addMouseListener(resizeListener);
        topPanel.addMouseMotionListener(resizeListener);
        scrollPane.getViewport().getView().addMouseListener(resizeListener);
        scrollPane.getViewport().getView().addMouseMotionListener(resizeListener);
        panel.addMouseListener(resizeListener);
        panel.addMouseMotionListener(resizeListener);
        tabbedPane.addMouseListener(resizeListener);
        tabbedPane.addMouseMotionListener(resizeListener);

        //init right click menu
        initRightClickMenu();
    }

    /**
     * This method is used to create the bottom panel
     */
    private void initBottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setLayout(null);
        bottomPanel.setPreferredSize(new Dimension(getWidth(), 15));
        bottomPanel.setBackground(new Color(170, 170, 170));

        slideLabel = new JLabel("Slide 1/1");
        slideLabel.setForeground(Color.BLACK);
        slideLabel.setFont(new Font("Arial", Font.BOLD, 10));
        slideLabel.setBounds(10, 0, 100, 15);
        bottomPanel.add(slideLabel);

        slideShowButton = getSlideShowButton();
        bottomPanel.add(slideShowButton);

        resizingProgressBar = new ResizingProgressBar(panel);
        resizingProgressBar.setBounds(570, 0, 200, 15);
        bottomPanel.add(resizingProgressBar);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * This method is used to create the slide show button for the bottom panel
     * @return IconButton
     */
    private static IconButton getSlideShowButton() {
        IconButton slideShowButton = new IconButton(new ImageIcon("src/main/resources/Icon/slide_show.png"), 0, 0, 15, 15, "SlideShow");
        slideShowButton.setBounds(550, 0, 15, 15);
        slideShowButton.setBorderPainted(false);
        slideShowButton.setContentAreaFilled(false);
        slideShowButton.setFocusPainted(false);
        slideShowButton.setOpaque(false);
        slideShowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FromCurrentButton.fromCurrentFunction();
            }
        });
        return slideShowButton;
    }

    /**
     * This method is used to initialise the right click menu
     */
    private void initRightClickMenu() {
        getPane().addMouseListener(new RightClickMenuListener(new SlideEditionRightClickMenu()));
        scrollPane.getViewport().getView().addMouseListener(new RightClickMenuListener(new SlideListRightClickMenu()));
    }

    /**
     * This method is used to create a button for the top panel
     * @param iconPath
     * @param buttonRole
     * @return
     */
    private static JButton createTopButton(String iconPath, String buttonRole) {
        ImageIcon originalIcon = new ImageIcon(iconPath);
        java.awt.Image scaledImage = originalIcon.getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
        IconButton button = new IconButton(new ImageIcon(scaledImage), 0, 0, 15, 15, buttonRole);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        return button;
    }

    /**
     * This method is used to change the icon of a button
     * @param iconPath
     * @param button
     */
    private void changeButtonIcon(String iconPath, JButton button) {
        ImageIcon originalIcon = new ImageIcon(iconPath);
        java.awt.Image scaledImage = originalIcon.getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));
    }







    /**
     * This method is used to populate the frame with the components from the file
     * Only used when opening a file
     */
    public void populateFrame() throws IOException {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        slideManager.clearSlideData();
        int slideSize = file.numOfSlides();

        slideManager.initCurrentSlide();

        //Loop for set the components to the frame for each slide
        for (int index = 0; index < slideSize; index++) {
            slideManager.openingAddSlide();
            createComponents(index);
        }

        //Display the first slide
        slideManager.setSlide(0);
    }

    /**
     * This method is used to create the components for the frame
     * It takes the content in the csppt file
     * @param index
     * @throws IOException
     */
    public void createComponents(int index) throws IOException {
        //TODO FileSystem add more attributes
        if (file.getSlides().get(index).getTexts() != null && !file.getSlides().get(index).getTexts().isEmpty()) {
            ArrayList<Text> textBoxArray = new ArrayList<Text>(file.getSlides().get(index).getTexts());
            for (int i = 0; i < textBoxArray.size(); i++) {
                slideManager.getSelectedSlide(index).addComponentToThisSlide(SlideManager.createComponent(textBoxArray.get(i)));
            }
        }

        if (file.getSlides().get(index).getImages() != null && !file.getSlides().get(index).getImages().isEmpty()) {
            ArrayList<Image> imageCompArray = new ArrayList<Image>(file.getSlides().get(index).getImages());
            for (int i = 0; i < imageCompArray.size(); i++) {
                slideManager.getSelectedSlide(index).addComponentToThisSlide(SlideManager.createComponent(imageCompArray.get(i)));
            }
        }

        if (file.getSlides().get(index).getShapes() != null && !file.getSlides().get(index).getShapes().isEmpty()) {
            ArrayList<Shape> shapeCompArray = new ArrayList<Shape>(file.getSlides().get(index).getShapes());
            for (int i = 0; i < shapeCompArray.size(); i++) {
                slideManager.getSelectedSlide(index).addComponentToThisSlide(SlideManager.createComponent(shapeCompArray.get(i)));
            }
        }

        if (file.getSlides().get(index).getCodeSections() != null && !file.getSlides().get(index).getCodeSections().isEmpty()) {
            ArrayList<CodeSection> codeSectionArray = new ArrayList<CodeSection>(file.getSlides().get(index).getCodeSections());
            for (int i = 0; i < codeSectionArray.size(); i++) {
                slideManager.getSelectedSlide(index).addComponentToThisSlide(SlideManager.createComponent(codeSectionArray.get(i)));
            }
        }

        if (file.getSlides().get(index).getAudios() != null && !file.getSlides().get(index).getAudios().isEmpty()) {
            ArrayList<Audio> audioArray = new ArrayList<Audio>(file.getSlides().get(index).getAudios());
            for (int i = 0; i < audioArray.size(); i++) {
                slideManager.getSelectedSlide(index).addComponentToThisSlide(SlideManager.createComponent(audioArray.get(i)));
            }
        }

        if (file.getSlides().get(index).getCsCharts() != null && !file.getSlides().get(index).getCsCharts().isEmpty()) {
            ArrayList<CSChart> chartArray = new ArrayList<CSChart>(file.getSlides().get(index).getCsCharts());
            for (int i = 0; i < chartArray.size(); i++) {
                slideManager.getSelectedSlide(index).addComponentToThisSlide(SlideManager.createComponent(chartArray.get(i)));
            }
        }

        if (file.getSlides().get(index).getMathCharts() != null && !file.getSlides().get(index).getMathCharts().isEmpty()) {
            ArrayList<MathChart> mathChartArray = new ArrayList<MathChart>(file.getSlides().get(index).getMathCharts());
            for (int i = 0; i < mathChartArray.size(); i++) {
                slideManager.getSelectedSlide(index).addComponentToThisSlide(SlideManager.createComponent(mathChartArray.get(i)));
            }
        }

        if (file.getSlides().get(index).getTables() != null && !file.getSlides().get(index).getTables().isEmpty()) {
            ArrayList<Table> tableArray = new ArrayList<Table>(file.getSlides().get(index).getTables());
            for (int i = 0; i < tableArray.size(); i++) {
                slideManager.getSelectedSlide(index).addComponentToThisSlide(SlideManager.createComponent(tableArray.get(i)));
            }
        }

        if (file.getSlides().get(index).getVideos() != null && !file.getSlides().get(index).getVideos().isEmpty()) {
            ArrayList<Video> videoArray = new ArrayList<Video>(file.getSlides().get(index).getVideos());
            for (int i = 0; i < videoArray.size(); i++) {
                slideManager.getSelectedSlide(index).addComponentToThisSlide(SlideManager.createComponent(videoArray.get(i)));
            }
        }

        if (file.getSlides().get(index).getCsBoxs() != null && !file.getSlides().get(index).getCsBoxs().isEmpty()) {
            ArrayList<CSBox> csBoxArray = new ArrayList<CSBox>(file.getSlides().get(index).getCsBoxs());
            for (int i = 0; i < csBoxArray.size(); i++) {
                slideManager.getSelectedSlide(index).addComponentToThisSlide(SlideManager.createComponent(csBoxArray.get(i)));
            }
        }

        if (file.getSlides().get(index).getCsLines() != null && !file.getSlides().get(index).getCsLines().isEmpty()) {
            ArrayList<CSLine> csLineArray = new ArrayList<CSLine>(file.getSlides().get(index).getCsLines());
            for (int i = 0; i < csLineArray.size(); i++) {
                slideManager.getSelectedSlide(index).addComponentToThisSlide(SlideManager.createComponent(csLineArray.get(i)));
            }
        }
    }

    /**
     * This method is used to add a component to the frame
     * Also adds the component to the array
     *
     * @param displayComponent
     */
    public void addToFrame(DisplayComponent displayComponent) {
        this.getPane().add(displayComponent);

            this.getPane().moveToFront(displayComponent);

        this.getPane().repaint();
        this.addToArray(displayComponent);
        displayComponent.setVisible();
    }

    /**
     * This method is used to remove a component from the frame
     *
     * @param component
     * @param pane
     */
    public void removeComponent(DisplayComponent component, JLayeredPane pane) {
        if (component instanceof CodeSectionEditor) {
            ((CodeSectionEditor) component).removeButtonAndComponent();
        } else if (component instanceof TableComponent) {
            ((TableComponent) component).removeFinishButton();
        }
        if(component instanceof ClassDiagram){
            ((ClassDiagram) component).deleteLines();
        }
        pane.remove(component);
        pane.repaint();
        slideManager.getCurrentSlideContent().removeComponent(component);
        try {
            SlideManager.removeDataFromCSPPTFile(component, file, getCurrentSlideInt());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    /**
     * This method is used to remove a slide from the frame
     * Also removes the slide data from file
     *
     * @param deleting_slide
     */
    public void removeSlide(int deleting_slide) {
        try {
            SlideManager.removeSlide(GlobalVariables.cspptFrame.getFile(), deleting_slide);

            if (deleting_slide - 1 < 0 && GlobalVariables.cspptFrame.getSlideManager().getTotalSlides() > 0) {
                GlobalVariables.cspptFrame.getSlideManager().setSlide(deleting_slide + 1);
            } else if (deleting_slide - 1 < 0) {
                GlobalVariables.cspptFrame.getSlideManager().createNewFile();
                GlobalVariables.cspptFrame.getSlideManager().wipeFrame();
            } else {
                GlobalVariables.cspptFrame.getSlideManager().setSlide(GlobalVariables.cspptFrame.getHandler().getSelectedSlide() - 1);
            }
            GlobalVariables.cspptFrame.getSlideManager().refreshSlideIcon();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }





    /**
     * This method is used to change the background colour of the slide
     */
    public void changeBackgroundColour() {
        Color color = JColorChooser.showDialog(FileChooser.getTempFrame(), "Select Colour", Color.RED);
        if (color == null) {
            return;
        }
        file.getSlides().get(slideManager.getCurrentSlideInt()).setSlideDatas(new ArrayList<>(List.of(new SlideData(color.getRGB()))));
        panel.setBackground(color);
        panel.setOpaque(true);
        panel.repaint();
    }






    /**
     * Resizes the screen when the window is resized
     */
    public void resizeScreen() {
        SwingUtilities.invokeLater(() -> {
            double width = panel1.getWidth();
            double height = panel1.getHeight();
            double aspectRatio = (double) 16 / 9;
            if (width / height <= aspectRatio) {
                width = width * 0.9;
                height = (int) ((width / 16) * 9);
            } else {
                height = height * 0.9;
                width = (int) (height / 9) * 16;
                GlobalVariables.FRAME_SIZE_INDEX = height / 9;
            }
            panel.setBounds((int) (panel1.getWidth() - width) / 2, (int) (panel1.getHeight() - height) / 2, (int) width, (int) height);
            resizingProgressBar.setInitX(panel.getWidth());
            resizingProgressBar.setInitY(panel.getHeight());
            resizingProgressBar.setBounds(panel1.getWidth() - 50, 0, 200, 15);
            slideShowButton.setBounds(panel1.getWidth() - 80, 0, 15, 15);
            slideManager.resizeAllComponents(panel.getWidth(), panel.getHeight());
            GlobalVariables.FRAME_SIZE_INDEX_X = (panel.getWidth());
            GlobalVariables.FRAME_SIZE_INDEX_Y = (panel.getHeight());
            this.revalidate();
            this.repaint();
        });

    }

    /**
     * This method is used to update the command line position
     */
    public void updateCommandLinePosition() {
        int width = panel1.getWidth();
        int height = panel1.getHeight();
        int componentWidth = width / 2;
        commandLineComponent.setBounds(width - componentWidth, 0, componentWidth, height);
        commandLineComponent.revalidate();
        commandLineComponent.repaint();
        panel1.moveToFront(commandLineComponent);
    }






    /**
     * Get the slide manager
     * @return
     */
    public SlideManager getSlideManager() {
        return slideManager;
    }

    /**
     * Get the mouse handler
     * @return
     */
    public MouseHandler getHandler() {
        return handler;
    }

    /**
     * Set the mouse handler
     * @param handler
     */
    public void setHandler(MouseHandler handler) {
        this.handler = handler;
    }

    /**
     * Add new slide to CSPPTFile
     */
    public void newSlide() {
        file.addEmptySlide();
    }

    /**
     * Use for next slide
     */
    public void nextSlide() {
        file.nextSlide();
    }

    /**
     * Get the base panel for the panel
     * @return
     */
    public JLayeredPane getPanel1() {
        return panel1;
    }

    public AnimationBar getMainAnimationBar() {
        return mainAnimationBar;
    }

    /**
     * This method is used to set the slide label for bottom panel
     * @param currentSlide
     * @param totalSlides
     */
    public void setSlideLabel(int currentSlide, int totalSlides) {
        slideLabel.setText("Slide " + currentSlide + "/" + totalSlides);
    }

    public void setFile(CSPPTFile file1) {
        file = file1;
    }

    public CSPPTFile getFile() {
        return file;
    }

    public void setFileLocation(String location1) {
        fileLocation = location1;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public JLayeredPane getPane() {
        return panel;
    }

    /**
     * This method is used to add a component to the slide contents arraylist
     * @param component
     */
    public void addToArray(DisplayComponent component) {
        slideManager.addToData(component);
    }

    public int getCurrentSlideInt() {
        return slideManager.getCurrentSlideInt();
    }

    public int getCurrentSlide() {
        return slideManager.getCurrentSlideInt();
    }

    public void removeAllFromArray() {
        slideManager.clearSlideData();
    }

    public CommandLineComponent getCommandLineComponent() {
        return commandLineComponent;
    }

    /**
     * This method is used to get the new layer for the component
     * It increments the top layer by 1
     * @return
     */
    public int getNewLayer() {
        GlobalVariables.TOP_LAYER += 1;
        return GlobalVariables.TOP_LAYER;
    }

    public HomeBar getMainHomeBar() {
        return mainHomeBar;
    }
}

