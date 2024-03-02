package org.lancaster.group77.Frame.Slides;

import org.lancaster.group77.CommandLine.CommandLineComponent;
import org.lancaster.group77.DisplayComponents.*;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.Manager.ClipboardManager;
import org.lancaster.group77.Frame.CSPPTFrame;
import org.lancaster.group77.InsertComponents.*;
import org.lancaster.group77.InsertComponents.Audio.AudioComponent;
import org.lancaster.group77.InsertComponents.CodeSection.CodeSectionEditor;
import org.lancaster.group77.InsertComponents.Image.ImageComponent;
import org.lancaster.group77.InsertComponents.MathChart.ChartComponent;
import org.lancaster.group77.InsertComponents.Shapes.*;
import org.lancaster.group77.InsertComponents.Table.TableComponent;
import org.lancaster.group77.InsertComponents.TextBox.TextBox;
import org.lancaster.group77.InsertComponents.UML.Lines.*;
import org.lancaster.group77.InsertComponents.Video.VideoComponent;
import org.lancaster.group77.InsertComponents.UML.ClassDiagrams.ClassDiagram;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class SlideManager {
    private static ArrayList<Slide> slides = new ArrayList<>();
    private static DefaultListModel<Icon> slideListModel;
    private JList<Icon> slideList; // List of slides on left side

    private static Slide currentSlide;
    private int currentSlideInt = -1;
    private static int totalSlides = 0;
    private MouseHandler handler;
    private JLayeredPane frame;
    //create a new slide content for each slide
    private static ArrayList<SlideContent> slideContents = new ArrayList<>();
    private CSPPTFrame CSPPTFrame;
    private int x, y, width, height;

    public SlideManager(MouseHandler handler, JLayeredPane frame, CSPPTFrame CSPPTFrame) {
//        System.out.println(new Date() + "SlideManager:SlideManager created");
        this.handler = handler;
        this.frame = frame;
        this.CSPPTFrame = CSPPTFrame;
        createSlideList(handler);
        //addSlide();
    }


    private void createSlideList(MouseHandler handler) {
//        System.out.println(new Date() + "SlideManager:createSlideList");
        slideListModel = new DefaultListModel<>();
        slideList = new JList<>(slideListModel);
        slideList.addMouseListener(this.handler);
        slideList.setBackground(new Color(38, 38, 38));

        // Set the cell renderer when selecting
        slideList.setCellRenderer(new DefaultListCellRenderer() {
            private final Border selectedBorder = new LineBorder(Color.MAGENTA, 2, true);
            private final Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBorder(isSelected ? selectedBorder : emptyBorder);
                return c;
            }
        });

        slideList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = slideList.getSelectedIndex();
                    if (selectedIndex >= -1) {
                        // Switching the slides
                        setSlide(selectedIndex);
                    }
                }
            }
        });

    }

    public void getComponents() {
//        System.out.println(new Date() + "SlideManager:getComponents");
        if (slideContents == null || currentSlide == null) {
            return;
        }
        for (SlideContent slideContent : slideContents) {

            if (slideContent.getSlideNumber() == currentSlide.getId()) {
                DisplayComponent[] currentComponents = (DisplayComponent[]) CSPPTFrame.getPane().getComponents();
                slideContent.saveComponents(currentComponents, frame);
                return;

            }
        }
    }


    public void addSlide() {
//        System.out.println(new Date() + "SlideManager:addSlide" + totalSlides);

        clearFrame(CSPPTFrame.getPane());
        openingAddSlide();
        refreshSlideIcon();

        CSPPTFrame.newSlide();

        slideList.setSelectedIndex(totalSlides - 1);
    }

    public void addSlideWithoutRefresh() {
//        System.out.println(new Date() + "SlideManager:addSlide" + totalSlides);

        clearFrame(CSPPTFrame.getPane());
        openingAddSlide();

        CSPPTFrame.newSlide();

        slideList.setSelectedIndex(totalSlides - 1);
    }

    /**
     * Opening a new slide
     * Only used when opening a file
     */
    public void openingAddSlide() {
        Slide slide = new Slide(totalSlides);
        if (currentSlide == null) {
            currentSlide = slide;
        }
        slides.add(slide);
        SlideContent slideContent = new SlideContent(totalSlides);
        slideContents.add(slideContent);
        totalSlides++;

        ImageIcon slideIcon = createGreySlideIcon();
        slideListModel.addElement(slideIcon);

        frame.setVisible(true);
    }

    /**
     * Remove all the slides when creating a new file
     */
    public void createNewFile() {
//        System.out.println(new Date() + "SlideManager:removeAllSlide");
        createNewFileWithoutFileLocation();
        GlobalVariables.cspptFrame.setFileLocation(null);
        ClipboardManager.clearClipboard();
    }

    public void createNewFileWithoutFileLocation() {
//        System.out.println(new Date() + "SlideManager:removeAllSlide");

        // Remove all the slides
        totalSlides = 0;

        // Remove all the slides
        slides = new ArrayList<>();
        currentSlide = null;
        currentSlideInt = -1;

        // Remove all the slide contents
        slideContents = new ArrayList<>();

        // Create a new slide and replace it
        CSPPTFile cspptFile1 = new CSPPTFile();
        GlobalVariables.cspptFrame.setFile(cspptFile1);

        // Remove all the slide list model
        slideListModel.removeAllElements();
    }

    public void nextSlide() {
//        System.out.println(new Date() + "SlideManager:nextSlide");
        int slideno;
        Slide slide = new Slide(totalSlides);
        if (currentSlide == null) {
            currentSlide = slide;
        }

        slideno = getCurrentSlideInt();
        setSlide(slideno);
    }


    public void updateSlideIcon() {
//        refreshSlideIcon();

//        System.out.println(new Date() + "SlideManager:updateSlideIcon");
//        ImageIcon slideIcon = createSlideIcon();
//
//        if (currentSlide != null)
//            slideListModel.setElementAt(slideIcon, currentSlide.getId());
    }

    private ImageIcon createGreySlideIcon() {
//        System.out.println(new Date() + "SlideManager:createGreySlideIcon");
        BufferedImage greyImage = new BufferedImage(160, 90, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = greyImage.createGraphics();
        g2d.setColor(new Color(GlobalVariables.DEFAULT_BACKGROUND_COLOR));
        g2d.fillRect(0, 0, greyImage.getWidth(), greyImage.getHeight());
        g2d.dispose();
        return new ImageIcon(greyImage);
    }

    /**
     * Set the current slide when switching the slides
     *
     * @param index
     */
    public void setSlide(int index) {
//        System.out.println(new Date() + "SlideManager:setSlide" + index + " " + slides.size() + " " + slideContents.size() + " " + currentSlideInt);
        if (index >= 0 && index < slides.size()) {
            clearFrame(CSPPTFrame.getPane());

            currentSlide = slides.get(index);
            currentSlideInt = index;

            for (SlideContent slideContent : slideContents) {
                if (slideContent.getSlideNumber() == currentSlide.getId()) {
                    slideContent.showFrameComponents(CSPPTFrame.getPane());
                }
            }

            slideList.setSelectedIndex(index);
            slideList.ensureIndexIsVisible(index);

            //set background color
            CSPPTFrame.getPane().setBackground(new Color(CSPPTFrame.getFile().getSlides().get(index).getSlideDatas().get(0).getBackground_color()));
            GlobalVariables.cspptFrame.setSlideLabel(index + 1, totalSlides);
        }
    }

    public void initCurrentSlide() {
        currentSlide = null;
        currentSlideInt = 0;
    }

    public void setCurrentSlide() {
        currentSlide = null;
        currentSlideInt = 0;
    }

    public int getCurrentSlideInt() {
        return currentSlideInt;
    }

    public JScrollPane getSlideScrollPane() {
        return new JScrollPane(slideList);
    }


    public Slide getCurrentSlide() {
        return currentSlide;
    }

    public Slide getSlide(int i) {
        return slides.get(i);
    }


    /**
     * Get the slide content from the slideContents arraylist
     *
     * @param index
     * @return
     */
    public ArrayList<DisplayComponent> getSlideContent(int index) {
        return slideContents.get(index).getPresentationComponents();
    }

    public SlideContent getSingleSlideContent(int index) {
        return slideContents.get(index);
    }


    public void addToData(DisplayComponent comp) {
        slideContents.get(getCurrentSlideInt()).addComponentToThisSlide(comp);
    }

    public void addComponentsIndividually(Component component, int slideNumber) {
        if (slideNumber - 1 >= slideContents.size()) {
            slideContents.get(slideNumber - 1).addComponent((DisplayComponent) component);
            return;
        }

        SlideContent slideContent = new SlideContent(slideNumber);
        slideContents.add(slideContent);
        slideContent.addComponent((DisplayComponent) component);
        totalSlides++;

    }

    /**
     * Get the current slide content from the slideContents arraylist
     * Not csppt file data
     *
     * @return
     */
    public SlideContent getCurrentSlideContent() {
        return slideContents.get(getCurrentSlideInt());
    }

    public SlideContent getSelectedSlide(int index) {
        return slideContents.get(index);
    }

    public void clearSlideData() {
        slideContents = new ArrayList<>();
        currentSlide = null;
        totalSlides = 0;
        slides = new ArrayList<>();
    }

    public int getTotalSlides() {
        return totalSlides;
    }

    public void setTotalSlides(int totalSlides) {
        this.totalSlides = totalSlides;
    }

    public SlideContent getSlideClass(int index) {
        return slideContents.get(index);
    }

    public void setAllVisible(boolean mode) {

        for (SlideContent slideContent : slideContents) {
            slideContent.visability(mode);
        }
    }

    /**
     * Clear the frame
     *
     * @param frame
     */
    public void clearFrame(JLayeredPane frame) {
//        System.out.println(new Date() + "SlideManager:clearFrame");
        try {
            for (Component component : frame.getComponents()) {
                if (component instanceof VideoComponent) {
                    ((VideoComponent) component).removeVideoComponent();
                }
                if (component instanceof CodeSectionEditor) {
                    frame.remove(component);
                } else if (!(component instanceof CommandLineComponent)) {
                    frame.remove(component);
                }
            }
            frame.revalidate();
            frame.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a component from the DisplayComponentBase(Text, Image, Shape, CodeSection, etc.)
     *
     * @param displayComponentBase
     * @return
     * @throws IOException
     */
    public static DisplayComponent createComponent(DisplayComponentBase displayComponentBase) throws IOException {
        //TODO FileSystem add more attributes
        switch (displayComponentBase.getJson_name()) {
            case "text":
                return new TextBox((Text) displayComponentBase, GlobalVariables.cspptFrame.getHandler(), GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getCurrentSlide(), true);
            case "shape":
                org.lancaster.group77.DisplayComponents.Shape shape = (org.lancaster.group77.DisplayComponents.Shape) displayComponentBase;
                switch (shape.getShape_name()) {
                    case "circle":
                        return new Circle(shape, GlobalVariables.cspptFrame.getHandler(), GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), true);
                    case "hexagon":
                        return new Hexagon(shape, GlobalVariables.cspptFrame.getHandler(), GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), true);
                    case "octagon":
                        return new Octagon(shape, GlobalVariables.cspptFrame.getHandler(), GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), true);
                    case "pentagon":
                        return new Pentagon(shape, GlobalVariables.cspptFrame.getHandler(), GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), true);
                    case "septagon":
                        return new Septagon(shape, GlobalVariables.cspptFrame.getHandler(), GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), true);
                    case "square":
                        return new Square(shape, GlobalVariables.cspptFrame.getHandler(), GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), true);
                    case "star":
                        return new Star(shape, GlobalVariables.cspptFrame.getHandler(), GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), true);
                    case "triangle":
                        return new Triangle(shape, GlobalVariables.cspptFrame.getHandler(), GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), true);
                    default:
                        return null;
                }
            case "image":
                return new ImageComponent((org.lancaster.group77.DisplayComponents.Image) displayComponentBase, GlobalVariables.cspptFrame.getHandler(), GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getCurrentSlide(), true);
            case "codesection":
                return new CodeSectionEditor((CodeSection) displayComponentBase, GlobalVariables.cspptFrame.getHandler(), GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getCurrentSlide(), true);
            case "audio":
                return new AudioComponent((org.lancaster.group77.DisplayComponents.Audio) displayComponentBase, GlobalVariables.cspptFrame.getHandler(), GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getCurrentSlide(), true);
            case "cs_box":
                CSBox csBox = (org.lancaster.group77.DisplayComponents.CSBox) displayComponentBase;
                Text text = new Text(csBox.getX(), csBox.getY(), csBox.getLayer(), csBox.getWidth(), csBox.getHeight(), csBox.getText(), csBox.getFont(), csBox.getColor(), csBox.getBackgroundColor(), csBox.getFont_size(), csBox.getBold(), csBox.getItalic(), csBox.getUnderline(), csBox.getStrikethrough(), csBox.getRight(), csBox.getLeft(), csBox.getCenter(), csBox.getBulletpoint(), csBox.getLine_spacing());

                return new ClassDiagram(csBox, text, GlobalVariables.cspptFrame.getHandler(), GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getCurrentSlide(), true);
            case "cs_line":
                CSLine line = (org.lancaster.group77.DisplayComponents.CSLine) displayComponentBase;
                switch (line.getLine_type()) {
                    case "Association":
                        return new Line((org.lancaster.group77.DisplayComponents.CSLine) displayComponentBase, GlobalVariables.cspptFrame.getHandler(), GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getCurrentSlide(), true);
                    case "Inheritance":
                        return new InheritanceLine((org.lancaster.group77.DisplayComponents.CSLine) displayComponentBase, GlobalVariables.cspptFrame.getHandler(), GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getCurrentSlide(), true);

                    case "Dependency":
                        return new DependencyLine((org.lancaster.group77.DisplayComponents.CSLine) displayComponentBase, GlobalVariables.cspptFrame.getHandler(), GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getCurrentSlide(), true);
                    case "Realization":
                        return new RealizationLine((org.lancaster.group77.DisplayComponents.CSLine) displayComponentBase, GlobalVariables.cspptFrame.getHandler(), GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getCurrentSlide(), true);
                    default:
                        return null;
                }


            case "mathchart":
                return new ChartComponent((org.lancaster.group77.DisplayComponents.MathChart) displayComponentBase, GlobalVariables.cspptFrame.getHandler(), GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getCurrentSlide(), true);
            case "table":
                return new TableComponent((org.lancaster.group77.DisplayComponents.Table) displayComponentBase, GlobalVariables.cspptFrame.getHandler(), GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getCurrentSlide(), true);
            case "video":
                return new VideoComponent((org.lancaster.group77.DisplayComponents.Video) displayComponentBase, GlobalVariables.cspptFrame.getHandler(), GlobalVariables.cspptFrame.getPane(), GlobalVariables.cspptFrame.getFile(), GlobalVariables.cspptFrame.getCurrentSlide(), true);
            default:
                return null;
        }


    }

    /**
     * Remove a data from the CSPPTFile
     *
     * @param displayComponent
     * @param file
     * @param slideNum
     */
    public static void removeDataFromCSPPTFile(DisplayComponent displayComponent, CSPPTFile file, int slideNum) throws IOException {
        file.removeData(displayComponent.getComponentData(), slideNum);
    }

    /**
     * Clear the screen
     */
    public void wipeFrame() {
        clearFrame(CSPPTFrame.getPane());
        GlobalVariables.cspptFrame.removeAllFromArray();
        GlobalVariables.cspptFrame.getPane().setBackground(new Color(GlobalVariables.DEFAULT_BACKGROUND_COLOR));
    }

    /**
     * Remove a slide from slide contents
     * Also remove the slide from the CSPPTFile
     *
     * @param file
     * @param slideNum
     * @throws IOException
     */
    public static void removeSlide(CSPPTFile file, int slideNum) throws IOException {
        if(slideNum >= slides.size()){
            return;
        }
        if (currentSlide == slides.get(slideNum)) {
            currentSlide = null;
        }
        file.removeSlide(slideNum);
        file.reorderSlide();

        slideListModel.remove(slideNum);
        refreshSlides(file);


        slideContents.remove(slideNum);
        reorderSlideContentNumber();
        totalSlides--;
    }

    /**
     * slides arraylist needs to be refreshed when a slide is removed
     */
    public static void refreshSlides(CSPPTFile file) {
        ArrayList<Slide> fileSlides = file.getSlides();
        slides = new ArrayList<Slide>();
        slides.addAll(fileSlides);
    }

    public ArrayList<SlideContent> getSlideContents() {
        return slideContents;
    }

    public void setSlideContents(ArrayList<SlideContent> slideContents) {
        this.slideContents = slideContents;
    }

    public static ArrayList<Slide> getSlides() {
        return slides;
    }

    public static void setSlides(ArrayList<Slide> slides) {
        SlideManager.slides = slides;
    }

    public void resizeAllComponents(double larger, double larger1) {
        for (int i = 0; i < slideContents.size(); i++) {

            slideContents.get(i).setSize1(larger, larger1);

        }
    }

    /**
     * Refresh the slide icon at left side
     * When a clicking happens, the slide icon needs to be refreshed
     */
    public void refreshSlideIcon() {
        int i = 0;
        for (SlideContent slideContent : slideContents) {
            JLayeredPane pane = new JLayeredPane();
            pane.setLayout(null);
            pane.setBounds(0, 0, frame.getWidth(), frame.getHeight());
            slideContent.showFrameComponents(pane);
            pane.revalidate();
            pane.repaint();
            int finalI = i;
            int finalI1 = i;
            SwingUtilities.invokeLater(() -> {
                ImageIcon icon = convertPanelToIcon(pane, 160, 90, new Color(CSPPTFrame.getFile().getSlides().get(finalI1).getSlideDatas().get(0).getBackground_color()));
                slideListModel.setElementAt(icon, finalI);
                if (currentSlideInt == finalI) {
                    slideContent.showFrameComponents(frame);
                }
            });
            i++;
        }
    }

    public static ImageIcon convertPanelToIcon(JLayeredPane panel, int width, int height, Color color) {
        BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.createGraphics();

        g.setColor(color);
        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
        panel.paint(g);
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, panel.getWidth() - 1, panel.getHeight() - 1);
        g.dispose();

        Image thumbnailImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage thumbnail = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = thumbnail.createGraphics();
        g2d.drawImage(thumbnailImage, 0, 0, null);
        g2d.dispose();
        return new ImageIcon(thumbnail);
    }

    public static BufferedImage convertPanelToImage(JFrame panel, int width, int height) {
        BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = image.createGraphics();

        g.setColor(panel.getBackground());
        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
        panel.paint(g);
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, panel.getWidth() - 1, panel.getHeight() - 1);
        g.dispose();

        Image thumbnailImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage thumbnail = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = thumbnail.createGraphics();
        g2d.drawImage(thumbnailImage, 0, 0, null);
        g2d.dispose();
        return thumbnail;
    }

    /**
     * Reorder the slide number for each slide content in the slideContents arraylist
     */
    public static void reorderSlideContentNumber() {
        for (int i = 0; i < slideContents.size(); i++) {
            slideContents.get(i).setSlideNumber(i);
        }
    }

}

