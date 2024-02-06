package org.lancaster.group77.Frame.Slides;

import org.lancaster.group77.DisplayComponents.Slide;
import org.lancaster.group77.InsertComponents.DraggableComponent;
import org.lancaster.group77.InsertComponents.TextBox;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;
import org.lancaster.group77.InsertComponents.impl.Slide1;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SlideManager {
    private ArrayList<Slide> slides = new ArrayList<>();
    private DefaultListModel<Icon> slideListModel;
    private JList<Icon> slideList;

    private Slide currentSlide;
    private int currentSlideInt =0;
    private static int totalSlides = 1;
    private MouseHandler handler;
    private JLayeredPane frame;
    //create a new slide contetn for each slide
    private ArrayList<SlideContent> slideContents = new ArrayList<>();
    private ArrayList<Slide1> slidesDataArrayList = new ArrayList<>();
    public SlideManager(MouseHandler handler, JLayeredPane frame) {
        this.handler=handler;
        this.frame = frame;
        createSlideList(handler);
        addSlide();
    }


    private void createSlideList(MouseHandler handler) {
        slideListModel = new DefaultListModel<>();
        slideList = new JList<>(slideListModel);
        slideList.addMouseListener(this.handler);
        slideList.setBackground(new Color(38, 38, 38));

    }
    public void getComponents(){
        if (slideContents == null || currentSlide == null) {
            return;
        }
        for(SlideContent slideContent : slideContents){

            if(slideContent.getSlideNumber() == currentSlide.getId()   && slideContent != null){
                Component[] currentComponents = frame.getComponents();
                slideContent.saveComponents(currentComponents,frame);
                return;

            }
        }
    }



    public void addSlide() {

        Slide slide = new Slide(totalSlides);
        slidesDataArrayList.add(new Slide1(this));
        /*
        Text text_test = new Text();
        text_test.setText("hello slide 1");
        slide.addObject(text_test);
            */
        if(currentSlide == null){
            currentSlide =slide;
        }
        slides.add(slide);
        SlideContent slideContent = new SlideContent(totalSlides);
        slideContents.add(slideContent);
        totalSlides++;
        ImageIcon slideIcon = createGreySlideIcon();
        slideListModel.addElement(slideIcon);
        //updateSlideIcon();

    }

    public void updateSlideIcon(){
        ImageIcon slideIcon = createSlideIcon();
        slideListModel.setElementAt(slideIcon, currentSlide.getId()-1);

    }

    private ImageIcon createGreySlideIcon() {
        BufferedImage greyImage = new BufferedImage(150, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = greyImage.createGraphics();
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, greyImage.getWidth(), greyImage.getHeight());
        g2d.dispose();
        return new ImageIcon(greyImage);
    }


    private ImageIcon createSlideIcon() {

        if (frame != null && currentSlide.getId()-1 >= 0 && currentSlide.getId()-1 < slideListModel.size()) {
            BufferedImage screenshot = SlideScreenshot.ScreenShot(frame);
            return new ImageIcon(screenshot.getScaledInstance(150, 100, Image.SCALE_SMOOTH));
        }
        return null;
    }


    public void setSlide(int index) {

        if (index >= 0 && index < slides.size()) {
            if (currentSlide != null) {
                for (SlideContent slideContent : slideContents) {
                    if (slideContent.getSlideNumber() == currentSlide.getId()) {
                        updateSlideIcon();
                        getComponents();
                        break;
                    }
                }
            }

            currentSlide = slides.get(index);
            currentSlideInt = index;
            System.out.println(currentSlideInt);

            for (SlideContent slideContent : slideContents) {
                if (slideContent.getSlideNumber() == currentSlide.getId()) {
                    slideContent.showFrameComponents(frame);
                }

            }
        } else {
            System.out.println("No slide exists");
        }
    }

    public int getCurrentSlideInt(){return currentSlideInt;}

    public JScrollPane getSlideScrollPane() {
        return new JScrollPane(slideList);
    }


    public Slide getCurrentSlide() {
        return currentSlide;
    }

    public Slide getSlide(int i){
        return slides.get(i);
    }

    public Slide1 getSlideData(int index){
        return slidesDataArrayList.get(index);
    }

    public SlideContent getSlideContent(int index){
        return slideContents.get(index);
    }

    public void addToData(DraggableComponent comp){
        if(comp instanceof TextBox){
            slidesDataArrayList.get(getCurrentSlideInt()).addTexts((TextBox)comp);
        }
    }

    public Slide1 getCurrentSlideData(){
        return slidesDataArrayList.get(currentSlideInt);
    }

    public void clearSlideData(){
        slidesDataArrayList.clear();
        slideContents.clear();
    }

    public void addDataSlide(){
        slidesDataArrayList.add(new Slide1(this));
    }

    public void addJustSlide(){
        slideContents.add(new SlideContent(totalSlides));
        totalSlides++;
    }

    public void addToSlide(DraggableComponent comp, int num){
        slideContents.get(num).addComponenet(comp);
    }

    public void clearSlides(){
        slideContents.clear();
        totalSlides = 1;
    }
}
