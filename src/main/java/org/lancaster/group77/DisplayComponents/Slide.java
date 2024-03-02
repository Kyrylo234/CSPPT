package org.lancaster.group77.DisplayComponents;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javafx.scene.control.Tab;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.Tools.GeneralTools;

import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Objects;

public class Slide implements java.io.Serializable {
    private int id;
    private ArrayList<Text> texts = new ArrayList<>();
    private ArrayList<Image> images = new ArrayList<>();
    private ArrayList<Shape> shapes = new ArrayList<>();
    private ArrayList<CodeSection> codeSections = new ArrayList<>();
    private ArrayList<Audio> audios = new ArrayList<>();
    private ArrayList<CSChart> csCharts = new ArrayList<>();
    private ArrayList<MathChart> mathCharts = new ArrayList<>();
    private ArrayList<Table> tables = new ArrayList<>();
    private ArrayList<Video> videos = new ArrayList<>();
    private ArrayList<Animation> animations = new ArrayList<>();
    private ArrayList<SlideData> slideDatas = new ArrayList<>();
    private ArrayList<CSBox> csBoxs = new ArrayList<>();
    private ArrayList<CSLine> csLines = new ArrayList<>();

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
     * @param id    The id of the slide
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
     * @param id     The id of the slide
     * @param texts  The text of the slide
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
     *
     * @param object The object to be added
     */
    public <T> void addObject(T object) {
        //TODO FileSystem add more attributes
        if (object instanceof Text text) {
            texts.add(text);
        }

        if (object instanceof Image image) {
            images.add(image);
        }

        if (object instanceof Shape shape) {
            shapes.add(shape);
        }

        if (object instanceof CodeSection codeSection) {
            codeSections.add(codeSection);
        }

        if (object instanceof Audio audio) {
            audios.add(audio);
        }

        if (object instanceof CSChart csChart) {
            csCharts.add(csChart);
        }

        if (object instanceof MathChart mathChart) {
            mathCharts.add(mathChart);
        }

        if (object instanceof Table table) {
            tables.add(table);
        }

        if (object instanceof Video video) {
            videos.add(video);
        }

        if (object instanceof Animation animation) {
            animations.add(animation);
        }

        if (object instanceof SlideData slideData) {
            slideDatas.add(slideData);
        }

        if (object instanceof CSBox csBox) {
            csBoxs.add(csBox);
        }

        if (object instanceof CSLine csLine) {
            csLines.add(csLine);
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

    public ArrayList<Audio> getAudios() {
        return audios;
    }

    public void setAudios(ArrayList<Audio> audios) {
        this.audios = audios;
    }

    public ArrayList<CSChart> getCsCharts() {
        return csCharts;
    }

    public void setCsCharts(ArrayList<CSChart> csCharts) {
        this.csCharts = csCharts;
    }

    public ArrayList<MathChart> getMathCharts() {
        return mathCharts;
    }

    public void setMathCharts(ArrayList<MathChart> mathCharts) {
        this.mathCharts = mathCharts;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public void setTables(ArrayList<Table> tables) {
        this.tables = tables;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Video> videos) {
        this.videos = videos;
    }

    public ArrayList<Animation> getAnimations() {
        return animations;
    }

    public void setAnimations(ArrayList<Animation> animations) {
        this.animations = animations;
    }

    public ArrayList<SlideData> getSlideDatas() {
        return slideDatas;
    }

    public void setSlideDatas(ArrayList<SlideData> slideDatas) {
        this.slideDatas = slideDatas;
    }

    public ArrayList<CSBox> getCsBoxs() {
        return csBoxs;
    }

    public void setCsBoxs(ArrayList<CSBox> csBoxes) {
        this.csBoxs = csBoxes;
    }

    public ArrayList<CSLine> getCsLines() {
        return csLines;
    }

    public void setCsLines(ArrayList<CSLine> csLines) {
        this.csLines = csLines;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Slide slide = (Slide) object;
        return getId() == slide.getId() && Objects.equals(getTexts(), slide.getTexts()) && Objects.equals(getImages(), slide.getImages()) && Objects.equals(getShapes(), slide.getShapes()) && Objects.equals(getCodeSections(), slide.getCodeSections()) && Objects.equals(getAudios(), slide.getAudios()) && Objects.equals(getCsCharts(), slide.getCsCharts()) && Objects.equals(getMathCharts(), slide.getMathCharts()) && Objects.equals(getTables(), slide.getTables()) && Objects.equals(getVideos(), slide.getVideos()) && Objects.equals(getAnimations(), slide.getAnimations()) && Objects.equals(getSlideDatas(), slide.getSlideDatas()) && Objects.equals(getCsBoxs(), slide.getCsBoxs()) && Objects.equals(getCsLines(), slide.getCsLines());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTexts(), getImages(), getShapes(), getCodeSections(), getAudios(), getCsCharts(), getMathCharts(), getTables(), getVideos(), getAnimations(), getSlideDatas(), getCsBoxs(), getCsLines());
    }
}
