package org.lancaster.group77.FileSystem;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.lancaster.group77.DisplayComponents.*;
import org.lancaster.group77.FileSystem.Strucutre.Head;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CSPPTFile implements java.io.Serializable {
    private ArrayList<Slide> slides;
    private Head head;
    private JSONArray slidesJsonArray;
    private JSONArray duplicateDataArr;

    /**
     * Constructor
     * Create a new CSPPTFile object
     */
    public CSPPTFile() {
        slides = new ArrayList<>();
        java.util.Date date = new java.util.Date();
        String created_time = date.toString();
        head = new Head(UserData.author, "New CSPPT1.csppt", created_time, created_time);
    }

    /**
     * Constructor
     * Create a new CSPPTFile object with given slides and head
     */
    public CSPPTFile(ArrayList<Slide> slides, Head head) {
        this.slides = slides;
        this.head = head;
    }

    /**
     * Constructor
     * Create a new CSPPTFile object with given slides and head and duplicateDataArr
     * This is for saving stuff so you don’t need to use it
     */
    public CSPPTFile(ArrayList<Slide> slides, Head head, JSONArray duplicateDataArr) {
        this.slides = slides;
        this.head = head;
        this.duplicateDataArr = duplicateDataArr;
    }

    /**
     * Get the json string of the slides
     *
     * @return The json string of the slides
     */
    public String getSlidesJsonString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < slidesJsonArray.size(); i++) {
            stringBuilder.append(slidesJsonArray.getJSONObject(i).toJSONString());
        }
        return stringBuilder.toString() + "|" + duplicateDataArr;
    }

    public void findAndReplaceDuplicates() {
        Map<String, Integer> keyValueOccurrences = new HashMap<>();
        int uniqueId = 0;

        JSONArray jsonArray = new JSONArray();

        //get duplicate key value pairs
        for (int i = 0; i < slides.size(); i++) {
            jsonArray.add(slides.get(i).getSlideJson());
            for (String key : jsonArray.getJSONObject(i).keySet()) {
                JSONArray jsonInner = jsonArray.getJSONObject(i).getJSONArray(key);
                for (int j = 0; j < jsonInner.size(); j++) {
                    JSONObject jsonInnerInner = jsonInner.getJSONObject(j);

                    for (String keyInner : jsonInnerInner.keySet()) {
                        String value = jsonInnerInner.getString(keyInner);
                        if (!Objects.equals(keyInner, "json_name") && !Objects.equals(keyInner, "x") && !Objects.equals(keyInner, "y") && !Objects.equals(keyInner, "width") && !Objects.equals(keyInner, "height")&&!Objects.equals(keyInner, "line_type")) {
                            if (value.length() > 10 && !value.contains("#C%S&P$P!T") && !value.contains("@TC@") && !value.contains("@TR@")) {
                                String keyValue = keyInner + ":" + value;
                                keyValueOccurrences.put(keyValue, keyValueOccurrences.getOrDefault(keyValue, 0) + 1);
                            }
                        }
                    }
                }
            }
        }

        // replace duplicate key value pairs #C%S&P$P!T[uniqueId]
        if (duplicateDataArr == null) {
            duplicateDataArr = new JSONArray();
        }
        for (Map.Entry<String, Integer> entry : keyValueOccurrences.entrySet()) {
            if (entry.getValue() > 1) {
                String[] keyValuePair = entry.getKey().split(":");
                duplicateDataArr.add(keyValuePair[1]);

                for (int i = 0; i < jsonArray.size(); i++) {
                    for (String keyInner : jsonArray.getJSONObject(i).keySet()) {
                        JSONArray jsonArray_inner = jsonArray.getJSONObject(i).getJSONArray(keyInner);
                        for (int j = 0; j < jsonArray_inner.size(); j++) {
                            if (jsonArray_inner.getJSONObject(j).getString(keyValuePair[0]) != null) {
                                if (jsonArray_inner.getJSONObject(j).getString(keyValuePair[0]).equals(duplicateDataArr.get(uniqueId))) {
                                    jsonArray_inner.getJSONObject(j).put(keyValuePair[0], "#C%S&P$P!T[" + uniqueId + "]");
                                }
                            }
                        }
                    }
                }
                uniqueId++;
            }
        }

        slidesJsonArray = jsonArray;
    }


    /**
     * Get the json string of the index
     *
     * @return The json string of the index
     */
    public String getIndexJsonString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        int firstPosition = getFirstContentPosition();
        int contentSize = 0;
        for (int i = 0; i < slides.size(); i++) {
            JSONObject index_json = new JSONObject();
            index_json.put("id", slides.get(i).getId());
            index_json.put("size", slides.get(i).getSlideSize());
            if (i == 0) {
                index_json.put("position", firstPosition);
                contentSize = slides.get(i).getSlideSize();
            } else {
                index_json.put("position", contentSize + firstPosition);
                contentSize += slides.get(i).getSlideSize();
            }

            String jsonStr = index_json.toString();
            while (jsonStr.length() < 63) {
                jsonStr += " ";
            }

            stringBuilder.append(jsonStr);
            if (i != slides.size() - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /**
     * Get the json string of the index for save version
     *
     * @return The json string of the index
     */
    public String getIndexJsonStringSave() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        int firstPosition = getFirstContentPosition();
        int contentSize = 0;
        for (int i = 0; i < slides.size(); i++) {
            JSONObject index_json = new JSONObject();
            index_json.put("id", slides.get(i).getId());
            byte[] utf8Bytes = slidesJsonArray.getJSONObject(i).toJSONString().getBytes(StandardCharsets.UTF_8);
            index_json.put("size", utf8Bytes.length);
            if (i == 0) {
                index_json.put("position", firstPosition);
                contentSize = utf8Bytes.length;
            } else {
                index_json.put("position", contentSize + firstPosition);
                contentSize += utf8Bytes.length;
            }

            String jsonStr = index_json.toString();
            while (jsonStr.length() < 63) {
                jsonStr += " ";
            }

            stringBuilder.append(jsonStr);
            if (i != slides.size() - 1) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /**
     * Get the position of the first content in the file
     *
     * @return The position of the first content in the file
     */
    private int getFirstContentPosition() {
        int head_length = head.getHeadSize() + 1; // 1 is the length of the |
        int index_length = 1 + 64 * slides.size() + 1; // 1 is the length of the []
        return head_length + index_length;
    }

    /**
     * Get the slides arraylist
     *
     * @return The slides arraylist
     */
    public ArrayList<Slide> getSlides() {
        return slides;
    }

    /**
     * Set the slides arraylist
     *
     * @param slides
     */
    public void setSlides(ArrayList<Slide> slides) {
        this.slides = slides;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public JSONArray getSlidesJsonArray() {
        return slidesJsonArray;
    }

    public void setSlidesJsonArray(JSONArray slidesJsonArray) {
        this.slidesJsonArray = slidesJsonArray;
    }

    public JSONArray getDuplicateDataArr() {
        return duplicateDataArr;
    }

    public void setDuplicateDataArr(JSONArray duplicateDataArr) {
        this.duplicateDataArr = duplicateDataArr;
    }

    /**
     * Add a new empty slide to the slides
     */
    public void addEmptySlide() {
        slides.add(new Slide(slides.size()));

        // add default background color
        slides.get(slides.size() - 1).setSlideDatas(new ArrayList<>(List.of(new SlideData(GlobalVariables.DEFAULT_BACKGROUND_COLOR))));
    }

    public int nextSlide() {
        return slides.size() + 1;
    }

    public int numOfSlides() {
        return slides.size();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CSPPTFile cspptFile = (CSPPTFile) object;
        return Objects.equals(getSlides(), cspptFile.getSlides()) && Objects.equals(getHead(), cspptFile.getHead()) && Objects.equals(getSlidesJsonArray(), cspptFile.getSlidesJsonArray()) && Objects.equals(getDuplicateDataArr(), cspptFile.getDuplicateDataArr());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSlides(), getHead(), getSlidesJsonArray(), getDuplicateDataArr());
    }

    /**
     * Remove a data from the CSPPTFile
     *
     * @param slideNum
     * @return
     * @throws IOException
     */
    public void removeData(DisplayComponentBase componentBase, int slideNum) throws IOException {
        //TODO FileSystem add more attributes
        switch (componentBase.getJson_name()) {
            case "text":
                Text text = (Text) componentBase;
                ArrayList<Text> texts = this.getSlides().get(slideNum).getTexts();
                for (int i = 0; i < texts.size(); i++) {
                    if (texts.get(i).equals(text)) {
                        texts.remove(text);
                        break;
                    }
                }
                break;
            case "shape":
                Shape shape = (Shape) componentBase;
                ArrayList<Shape> shapes = this.getSlides().get(slideNum).getShapes();
                for (int i = 0; i < shapes.size(); i++) {
                    if (shapes.get(i).equals(shape)) {
                        shapes.remove(shape);
                        break;
                    }
                }
                break;
            case "image":
                Image image = (Image) componentBase;
                ArrayList<Image> images = this.getSlides().get(slideNum).getImages();
                for (int i = 0; i < images.size(); i++) {
                    if (images.get(i).equals(image)) {
                        images.remove(image);
                        break;
                    }
                }
                break;
            case "codesection":
                CodeSection codeSection = (CodeSection) componentBase;
                ArrayList<CodeSection> codeSections = this.getSlides().get(slideNum).getCodeSections();
                for (int i = 0; i < codeSections.size(); i++) {
                    if (codeSections.get(i).equals(codeSection)) {
                        codeSections.remove(codeSection);
                        break;
                    }
                }
                break;
            case "audio":
                Audio audio = (Audio) componentBase;
                ArrayList<Audio> audios = this.getSlides().get(slideNum).getAudios();
                for (int i = 0; i < audios.size(); i++) {
                    if (audios.get(i).equals(audio)) {
                        audios.remove(audio);
                        break;
                    }
                }
                break;
            case "cschart":
                CSChart csChart = (CSChart) componentBase;
                ArrayList<CSChart> csCharts = this.getSlides().get(slideNum).getCsCharts();
                for (int i = 0; i < csCharts.size(); i++) {
                    if (csCharts.get(i).equals(csChart)) {
                        csCharts.remove(csChart);
                        break;
                    }
                }
                break;
            case "mathchart":
                MathChart mathChart = (MathChart) componentBase;
                ArrayList<MathChart> mathCharts = this.getSlides().get(slideNum).getMathCharts();
                for (int i = 0; i < mathCharts.size(); i++) {
                    if (mathCharts.get(i).equals(mathChart)) {
                        mathCharts.remove(mathChart);
                        break;
                    }
                }
                break;
            case "table":
                Table table = (Table) componentBase;
                ArrayList<Table> tables = this.getSlides().get(slideNum).getTables();
                for (int i = 0; i < tables.size(); i++) {
                    if (tables.get(i).equals(table)) {
                        tables.remove(table);
                        break;
                    }
                }
                break;
            case "video":
                Video video = (Video) componentBase;
                ArrayList<Video> videos = this.getSlides().get(slideNum).getVideos();
                for (int i = 0; i < videos.size(); i++) {
                    if (videos.get(i).equals(video)) {
                        videos.remove(video);
                        break;
                    }
                }
                break;
            case "animation":
                Animation animation = (Animation) componentBase;
                ArrayList<Animation> animations = this.getSlides().get(slideNum).getAnimations();
                for (int i = 0; i < animations.size(); i++) {
                    if (animations.get(i).equals(animation)) {
                        animations.remove(animation);
                        break;
                    }
                }
                break;
            case "slidedata":
                SlideData slideData = (SlideData) componentBase;
                ArrayList<SlideData> slideDatas = this.getSlides().get(slideNum).getSlideDatas();
                for (int i = 0; i < slideDatas.size(); i++) {
                    if (slideDatas.get(i).equals(slideData)) {
                        slideDatas.remove(slideData);
                        break;
                    }
                }
                break;
            case "cs_box":
                CSBox csBox = (CSBox) componentBase;
                ArrayList<CSBox> csBoxes = this.getSlides().get(slideNum).getCsBoxs();
                for (int i = 0; i < csBoxes.size(); i++) {
                    if (csBoxes.get(i).equals(csBox)) {
                        csBoxes.remove(csBox);
                        break;
                    }
                }
                break;
            case "cs_line":
                CSLine csLine = (CSLine) componentBase;
                ArrayList<CSLine> csLines = this.getSlides().get(slideNum).getCsLines();
                for (int i = 0; i < csLines.size(); i++) {
                    if (csLines.get(i).equals(csLine)) {
                        csLines.remove(csLine);
                        break;
                    }
                }
                break;
            default:
                System.out.println("Error: removeData: json_name not found");
        }
    }

    public void removeSlide(int index) throws IOException {
        getSlides().remove(index);
    }

    /**
     * Call this method when deleting a slide
     */
    public void reorderSlide() {
        for (int i = 0; i < getSlides().size(); i++) {
            getSlides().get(i).setId(i);
        }
    }
}
