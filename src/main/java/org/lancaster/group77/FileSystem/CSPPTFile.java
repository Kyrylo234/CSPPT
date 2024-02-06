package org.lancaster.group77.FileSystem;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.lancaster.group77.DisplayComponents.Slide;
import org.lancaster.group77.DisplayComponents.Text;
import org.lancaster.group77.FileSystem.Strucutre.Head;

import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class CSPPTFile {
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
                        if (value.length() > 10) {
                            String keyValue = keyInner + ":" + value;
                            keyValueOccurrences.put(keyValue, keyValueOccurrences.getOrDefault(keyValue, 0) + 1);
                        }
                    }
                }
            }
        }

        // replace duplicate key value pairs #C%S&P$P!T[uniqueId]
        JSONArray duplicateDataArr = new JSONArray();
        for (Map.Entry<String, Integer> entry : keyValueOccurrences.entrySet()) {
            if (entry.getValue() > 1) {
                String[] keyValuePair = entry.getKey().split(":");
                duplicateDataArr.add(keyValuePair[1]);

                for (int i = 0; i < jsonArray.size(); i++) {
                    for (String keyInner : jsonArray.getJSONObject(i).keySet()) {
                        jsonArray.getJSONObject(i).getJSONObject(keyInner).put(keyValuePair[0], "#C%S&P$P!T[" + uniqueId + "]");
                    }
                }
                uniqueId++;
            }
        }

        slidesJsonArray = jsonArray;
        this.duplicateDataArr = duplicateDataArr;
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
        slides.add(new Slide(slides.size() + 2));
    }

    public int numOfSlides(){
        return slides.size();
    }
}
