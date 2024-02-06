package org.lancaster.group77.Frame.Buttons.Insert;

import org.lancaster.group77.DisplayComponents.Slide;
import org.lancaster.group77.Frame.Bars.InsertBar;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.TextBox;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;

public class NewTextBoxButton extends IconButton {
    private  MouseHandler handler;
    private boolean isSlideInitialized = false;
    private InsertBar bar;

    private  Slide slide;

    public NewTextBoxButton(ImageIcon imageIcon, int x, int y, int width, int height, String inputRole, MouseHandler inputHandler, InsertBar bar){
        super(imageIcon,x, y, width, height, inputRole);
        this.bar=bar;
        handler = inputHandler;

        //System.out.println("current slide "+ handler.getcurrentSlide());
    }

    private void initializeSlide() {
        if (!isSlideInitialized) {
           // slide = handler.getcurrentSlide();
            System.out.println("current slide " + slide);
            isSlideInitialized = true;
        }
    }

    public void newTextBoxFunction(){
        initializeSlide();
        //if(handler.getcurrentSlide() == slide) {
            TextBox tb = new TextBox(100, 100, 100, 100, handler, bar.getFrame().getPane(), bar.getFrame().getFile(), bar.getFrame().getSizeOfArray(),bar.getFrame().getCurrentSlide());
            bar.getFrame().getPane().add(tb);
            bar.getFrame().getPane().moveToFront(tb);
            bar.getFrame().getPane().repaint();
            bar.getFrame().addToArray(tb);
            System.out.println("success " );

       // }else{


       // }


    }
}
