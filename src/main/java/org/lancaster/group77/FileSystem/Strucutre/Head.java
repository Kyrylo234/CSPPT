package org.lancaster.group77.FileSystem.Strucutre;

import com.alibaba.fastjson.JSONObject;

public class Head {
    private String author;
    private String file_name;
    private String created_time;
    private String updated_time;
    private int head_json_size = 512;

    /**
     * Get the head of the file as a JSONObject
     *
     * @return The head of the file as a JSONObject
     */
    public JSONObject getHeadJson(String index_json_str) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("author", author);
        jsonObject.put("file_name", file_name);
        jsonObject.put("created_time", created_time);
        jsonObject.put("updated_time", updated_time);
        jsonObject.put("index_start_position", 513);
        jsonObject.put("index_size", index_json_str.getBytes().length);
        head_json_size = jsonObject.toJSONString().getBytes().length;


        return jsonObject;
    }

    /**
     * Get the head of the file as a json string
     *
     * @return The head of the file as a json string
     */
    public String getHeadJsonString(String index_json_str) {
        JSONObject jsonObject = getHeadJson(index_json_str);
        String jsonStr = jsonObject.toString();
        while (jsonStr.length() < 512) {
            jsonStr += " ";
        }
        return jsonStr;
    }

    public Head(String author, String file_name, String created_time, String updated_time) {
        this.author = author;
        this.file_name = file_name;
        this.created_time = created_time;
        this.updated_time = updated_time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }

    public int getHeadSize() {
        return head_json_size;
    }
}
