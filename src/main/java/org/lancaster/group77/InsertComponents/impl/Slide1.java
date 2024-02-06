package org.lancaster.group77.InsertComponents.impl;


import org.lancaster.group77.Frame.Slides.SlideManager;
import org.lancaster.group77.InsertComponents.DraggableComponent;
import org.lancaster.group77.InsertComponents.ImageComponent;
import org.lancaster.group77.InsertComponents.TextBox;

import java.util.ArrayList;

public class Slide1 {
    private ArrayList<DraggableComponent> arrayList;
    private SlideManager manager;
    public Slide1(SlideManager manager){
        arrayList = new ArrayList<>();
        this.manager = manager;
    }

    public void toFront(int componentToFrontIndex){
        boolean changed = false;
        if(arrayList.get(componentToFrontIndex) instanceof TextBox){
            TextBox textBox = (TextBox)arrayList.get(componentToFrontIndex);
            arrayList.remove(componentToFrontIndex);
            textBox.setLayer(arrayList.size());
            arrayList.add(textBox);
            for(int i = componentToFrontIndex; i<arrayList.size()-1; i++){
                if(arrayList.get(i) instanceof TextBox){
                    ((TextBox)arrayList.get(i)).setLayer();
                }
            }
        }
    }


    public void addTexts(TextBox tb){
        boolean added = false;
        //then we need to order them
        if(arrayList.isEmpty()){
            arrayList.add(tb);
        }else{
            for(int o = 0; o<arrayList.size();o++){
                if(arrayList.get(o) instanceof TextBox){
                    TextBox textBox = (TextBox)arrayList.get(o);
                    if(textBox.getLayer() < tb.getLayer()){
                        arrayList.add(o, tb);
                        added= true;
                        break;
                    }
                }else if(arrayList.get(o) instanceof ImageComponent){
                    ImageComponent textBox = (ImageComponent)arrayList.get(o);
                    if(textBox.getLayer() < tb.getLayer()){
                        arrayList.add(o, tb);
                        added= true;
                        break;
                    }
                }
            }
            if(added==false){
                arrayList.add(0,tb);
            }
        }
    }

    public void addImage(ImageComponent tb){
        boolean added = false;
        //then we need to order them
        if(arrayList.isEmpty()){
            arrayList.add(tb);
        }else{
            for(int o = 0; o<arrayList.size();o++){
                if(arrayList.get(o) instanceof TextBox){
                    TextBox textBox = (TextBox)arrayList.get(o);
                    if(textBox.getLayer() < tb.getLayer()){
                        arrayList.add(o, tb);
                        added= true;
                        break;
                    }
                }else if(arrayList.get(o) instanceof ImageComponent){
                    ImageComponent textBox = (ImageComponent)arrayList.get(o);
                    if(textBox.getLayer() < tb.getLayer()){
                        arrayList.add(o, tb);
                        added= true;
                        break;
                    }
                }
            }
            if(added==false){
                arrayList.add(0,tb);
            }
        }
    }

    public int getSlideSize(){
        return arrayList.size();
    }

    public DraggableComponent get(int i){
        return arrayList.get(i);
    }

    public int getPostition(DraggableComponent component){
        return arrayList.indexOf(component);
    }

    public boolean isEmpty(){
        return arrayList.isEmpty();
    }

    public void add(int index,DraggableComponent comp){
        arrayList.add(index,comp);
    }

    public int indexOf(DraggableComponent comp){
        return arrayList.indexOf(comp);
    }

}
