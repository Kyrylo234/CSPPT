package org.lancaster.group77.Frame;

import Listener.CSPPTFrameAdapter;
import org.lancaster.group77.CommandLine.CommandLineComponent;
import org.lancaster.group77.DisplayComponents.CodeSection;
import org.lancaster.group77.DisplayComponents.Image;
import org.lancaster.group77.DisplayComponents.Shape;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.impl.ActionListenerImplmenetation;
import org.lancaster.group77.Frame.Bars.*;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.Frame.Slides.SlideManager;
import org.lancaster.group77.InsertComponents.DraggableComponent;
import org.lancaster.group77.InsertComponents.Shapes.Circle;
import org.lancaster.group77.InsertComponents.TextBox;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;
import org.lancaster.group77.DisplayComponents.Text;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CSPPTFrame extends BaseFrame {
    private ArrayList<IconButton> initButtons;
    private CSPPTFile file;
    private String fileLocation;
    private JLayeredPane panel;


    private ArrayList<Text> textBoxArray;
    private ArrayList<Image> imageArray;
    private ArrayList<Shape> shapesArray;
    private ArrayList<CodeSection> codeSectionArray;
    private MouseHandler handler;
    private SlideManager slideManager;
    private CommandLineComponent commandLineComponent;


    public CSPPTFrame() {
        file = new CSPPTFile();
        file.addEmptySlide();



        setTitle("CSPPT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setIconImage(new ImageIcon("src/main/resources/Icon/csppt_logo.png").getImage());
        setUndecorated(true);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(38, 38, 38));
        topPanel.setPreferredSize(new Dimension(800, 30));
        ImageIcon originalIcon = new ImageIcon("src/main/resources/Icon/csppt_logo.png");
        java.awt.Image scaledImage = originalIcon.getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(scaledImage));
        JLabel titleLabel = new JLabel("CSPPT");
        titleLabel.setForeground(Color.WHITE);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        titlePanel.setOpaque(false);
        titlePanel.add(iconLabel);
        titlePanel.add(titleLabel);

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
        JButton minimizeButton = createTopButton("src/main/resources/Icon/TopBar/minimise.png");
        JButton maximizeButton = createTopButton("src/main/resources/Icon/TopBar/small.png");
        JButton closeButton = createTopButton("src/main/resources/Icon/TopBar/close.png");

        minimizeButton.addActionListener(e -> setState(Frame.ICONIFIED));
        maximizeButton.addActionListener(e -> {
            if (getExtendedState() == JFrame.MAXIMIZED_BOTH) {
                setExtendedState(JFrame.NORMAL);
            } else {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
        closeButton.addActionListener(e -> System.exit(0));

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        controlPanel.setOpaque(false);
        controlPanel.add(minimizeButton);
        controlPanel.add(maximizeButton);
        controlPanel.add(closeButton);

        topPanel.add(titlePanel, BorderLayout.WEST);
        topPanel.add(controlPanel, BorderLayout.EAST);

        //Mouse listener which is used for moving the components around the screen
        handler = new MouseHandler(this);
        System.out.println("CSPPTFrame reference: " + this);

        //Tab top
        JTabbedPane tabbedPane = new JTabbedPane();
        Dimension dimension = new Dimension(400, 120);
        tabbedPane.setPreferredSize(dimension);
        tabbedPane.setBackground(new Color(38, 38, 38));
        tabbedPane.setForeground(Color.WHITE);

        //Create a new action listener for the File bar
        ActionListenerImplmenetation listener = new ActionListenerImplmenetation();
        panel = new JLayeredPane();
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setBackground(new Color(38, 38, 38));
        panel.setOpaque(true);
        add(panel, BorderLayout.CENTER);

        slideManager = new SlideManager(handler, panel);
        handler.setMainSlideManager(slideManager);

        //Adding the panels
        //adding the buttons to the different panels
        FileBar mainFileBar = new FileBar(tabbedPane, new JPanel(), listener, this);
        HomeBar mainHomeBar = new HomeBar(tabbedPane, new JPanel(), listener, handler, this);
        InsertBar mainInsertBar = new InsertBar(tabbedPane, new JPanel(), listener, handler, this);
        AnimationBar mainAnimationBar = new AnimationBar(tabbedPane, new JPanel());
        PresentationBar mainPresentationBar = new PresentationBar(tabbedPane, new JPanel(), listener, this);

        //adding to the frame
        JPanel combinedTopPanel = new JPanel(new BorderLayout());
        combinedTopPanel.add(topPanel, BorderLayout.NORTH);
        combinedTopPanel.add(tabbedPane, BorderLayout.SOUTH);
        add(combinedTopPanel, BorderLayout.NORTH);

        //Page select left
        JScrollPane scrollPane = slideManager.getSlideScrollPane();
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane, BorderLayout.WEST);

        //Edit frame right
        panel.addMouseListener(handler);

        //Window size -- fix this
        setSize(800, 600);

        //init command line
        commandLineComponent = new CommandLineComponent(this);
        panel.add(commandLineComponent, JLayeredPane.PALETTE_LAYER);

        listener.addMouseHandler(handler);

        this.addComponentListener(new CSPPTFrameAdapter(this));
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

    public void addToArray(DraggableComponent component){
        slideManager.addToData(component);
    }

    public int getPostition(DraggableComponent component){
        return slideManager.getCurrentSlideData().indexOf(component);
    }

    public void populateFrame() {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        int i = file.numOfSlides();
        for(int index = 0; index < i; index++){
            slideManager.addDataSlide();
            slideManager.addSlide();
        }
        for(int index = 0; index < i; index++){
            if(file.getSlides().get(index).getTexts() != null){
                textBoxArray = new ArrayList<Text>(file.getSlides().get(index).getTexts());
                createTexts(index);
            }
            if(file.getSlides().get(index).getImages() != null){
                imageArray = new ArrayList<Image>(file.getSlides().get(index).getImages());
                createImages(index);
            }

            if(file.getSlides().get(index).getShapes() != null){
                shapesArray = new ArrayList<Shape>(file.getSlides().get(index).getShapes());
                createShapes(index);
            }

            if(file.getSlides().get(index).getCodeSections() != null){
                codeSectionArray = new ArrayList<CodeSection>(file.getSlides().get(index).getCodeSections());
            }
        }
    }

    public void createTexts(int index){
        if(!textBoxArray.isEmpty()){
            for(int i=0;i<textBoxArray.size();i++){
                TextBox tb1= new TextBox(textBoxArray.get(i), handler, panel, file,getCurrentSlide());
                slideManager.getSlideContent(index).addComponenet(tb1);
            }
        }
    }

    public void createImages(int index){
        if(!imageArray.isEmpty()){
            for(int i=0;i<imageArray.size();i++){
                //ImageComponent tb1= new ImageComponent(imageArray.get(i), handler, panel, file,getCurrentSlide());
                //slideManager.getSlideData(index).addImage(tb1);
            }
        }
    }


    public void createShapes(int index){
        if(!shapesArray.isEmpty()){
            for(int i=0;i<shapesArray.size();i++){
                System.out.println(shapesArray.get(i).getShapeName());
                if(shapesArray.get(i).getShapeName() == "circle"){
                    Circle circle = new Circle(shapesArray.get(i),handler,panel,file,getCurrentSlide());
                    slideManager.getSlideContent(index).addComponenet(circle);
                }
            }
        }
    }
    public void createCodeSections(int index){
        if(!codeSectionArray.isEmpty()){
            for(int i=0;i<codeSectionArray.size();i++){

            }
        }
    }

    public int getCurrentSlide() {
        return slideManager.getCurrentSlideInt();
    }


    public void removeAllFromArray(){
        slideManager.clearSlideData();
        slideManager.clearSlides();
    }

    public void newSlide() {
        file.addEmptySlide();
    }

    public CommandLineComponent getCommandLineComponent() {
        return commandLineComponent;
    }

    public void toFront(int componentToFrontIndex){
        slideManager.getCurrentSlideData().toFront(componentToFrontIndex);
    }

    public int getSizeOfArray(){
        return slideManager.getCurrentSlideData().getSlideSize();
    }

    public void setCommandLineComponent(CommandLineComponent commandLineComponent) {
        this.commandLineComponent = commandLineComponent;
    }

    public void changeBackgroundColour(){
        JColorChooser colorChooser = new JColorChooser();
        Color  color = JColorChooser.showDialog(null, "select", Color.RED);
        String colour1 = String.valueOf(color.getRGB());
        panel.setBackground(color);
        panel.setOpaque(true);
        panel.repaint();
    }

    public void updateCommandLinePosition() {
        int width = panel.getWidth();
        int height = panel.getHeight();
        int componentWidth = width / 2;
        commandLineComponent.setBounds(width - componentWidth, 0, componentWidth, height);
        commandLineComponent.revalidate();
        commandLineComponent.repaint();
    }

    private static JButton createTopButton(String iconPath) {
        ImageIcon originalIcon = new ImageIcon(iconPath);
        java.awt.Image scaledImage = originalIcon.getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
        JButton button = new JButton(new ImageIcon(scaledImage));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        return button;
    }
}
