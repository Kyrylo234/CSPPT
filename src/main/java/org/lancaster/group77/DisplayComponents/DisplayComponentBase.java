package org.lancaster.group77.DisplayComponents;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class DisplayComponentBase {
    private double x = 0;
    private double y = 0;
    private double width = 100;
    private double height = 100;
    private int layer = 0; //higher value on top
    private String json_name = "";

    public DisplayComponentBase(String json_name) {
        this.json_name = json_name;
    }

    public DisplayComponentBase(int x, int y, int layer, String json_name, int width1, int height1) {
        this.x = x;
        this.y = y;
        this.layer = layer;
        this.json_name = json_name;
        width = width1;
        height = height1;
    }

    public DisplayComponentBase(double x, double y, int layer, String json_name, int width1, int height1) {
        this.x = x;
        this.y = y;
        this.layer = layer;
        this.json_name = json_name;
        width = width1;
        height = height1;
    }

    /**
     * Get the json object of this component
     *
     * @return The json object
     */
    public JSONObject getJson() {
        JSONObject json = new JSONObject();
        Class<?> currentClass = this.getClass();

        while (currentClass != Object.class) {
            Field[] fields = currentClass.getDeclaredFields();
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }

                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = null;
                try {
                    value = field.get(this);
                    if (value == null)
                        json.put(fieldName, "");
                    else
                        json.put(fieldName, value);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            currentClass = currentClass.getSuperclass();
        }

        return json;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public String getJson_name() {
        return json_name;
    }

    public void setJson_name(String json_name) {
        this.json_name = json_name;
    }

    public void setWidth(double i) {
        width = i;
    }

    public void setHeight(double i) {
        height = i;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
