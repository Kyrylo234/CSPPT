package org.lancaster.group77.DisplayComponents;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.Tools.GeneralTools;

import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Slide {
    private int id;
    private ArrayList<Text> texts = new ArrayList<>();
    private ArrayList<Image> images = new ArrayList<>();
    private ArrayList<Shape> shapes = new ArrayList<>();
    private ArrayList<CodeSection> codeSections = new ArrayList<>();
    //TODO FileSystem add more attributes


    /**
     * Constructor
     * Create a new Slide object
     */
    public Slide(int id) {
        this.id = id;
    }

    /**
     * Constructor
     * Create a new Slide object with all the attributes
     *
     * @param id   The id of the slide
     * @param texts The text of the slide
     */
    public Slide(int id, ArrayList<Text> texts) {
        this.id = id;
        this.texts = texts;
    }

    /**
     * Constructor
     * Create a new Slide object with all the attributes
     *
     * @param id   The id of the slide
     * @param texts The text of the slide
     * @param images The images of the slide
     * @param shapes The shapes of the slide
     */
    public Slide(int id, ArrayList<Text> texts, ArrayList<Image> images, ArrayList<Shape> shapes) {
        this.id = id;
        this.texts = texts;
        this.images = images;
        this.shapes = shapes;
    }

    /**
     * Get the json object of the slide
     *
     * @return The json object of the slide
     */
    public JSONObject getSlideJson() {
        JSONObject slide_json = new JSONObject();

        for (Class<?> clazz : GlobalVariables.ALL_COMPONENTS) {
            JSONArray jsonArray = new JSONArray();
            for (Object object : (ArrayList<?>) GeneralTools.invokeGetMethods(this, clazz.getSimpleName().toLowerCase() + "s")) {
                jsonArray.add(GeneralTools.invokeGetMethods(object, "json"));
            }
            slide_json.put(clazz.getSimpleName().toLowerCase(), jsonArray);
        }

        return slide_json;
    }

    /**
     * Get the size of the slide in bytes
     *
     * @return The size of the slide in bytes
     */
    public int getSlideSize() {
        byte[] utf8Bytes = getSlideJson().toJSONString().getBytes(StandardCharsets.UTF_8);
        int sizeInBytes = utf8Bytes.length;
        return sizeInBytes;
    }

    public ArrayList<Text> getTexts() {
        return texts;
    }

    public void setTexts(ArrayList<Text> texts) {
        this.texts = texts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Add an object to the slide
     * For example, add a text object to the slide
     * @param object The object to be added
     */
    public <T> void addObject(T object) {
        //TODO FileSystem add more attributes
        if(object instanceof Text text){
            texts.add(text);
        }

        if(object instanceof Image image){
            images.add(image);
        }

        if(object instanceof Shape shape){
            shapes.add(shape);
        }

        if(object instanceof CodeSection codeSection){
            codeSections.add(codeSection);
        }
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    public ArrayList<CodeSection> getCodeSections() {
        return codeSections;
    }

    public void setCodeSections(ArrayList<CodeSection> codeSections) {
        this.codeSections = codeSections;
    }
}
