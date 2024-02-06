package org.lancaster.group77.FileSystem.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.jna.platform.win32.Ntifs;
import org.lancaster.group77.DisplayComponents.Image;
import org.lancaster.group77.DisplayComponents.Shape;
import org.lancaster.group77.DisplayComponents.Slide;
import org.lancaster.group77.DisplayComponents.Text;
import org.lancaster.group77.Exceptions.FileDoesNotExistException;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.FileInterface;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.Strucutre.Head;
import org.lancaster.group77.FileSystem.Tools.GeneralTools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileInterfaceImpl implements FileInterface {

    /**
     * Open a .csppt file and save it to the memory as CSPPTFile object
     *
     * @param path The path of the file to be opened
     * @return The CSPPTFile object
     * @throws FileNotFoundException If the file does not exist
     */
    @Override
    public CSPPTFile openFile(String path) throws FileNotFoundException {
        try {
            // Read the head of the file
            RandomAccessFile fileReader = new RandomAccessFile(path, "r");
            StringBuilder headBuilder = new StringBuilder();
            int character;
            while ((character = fileReader.read()) != -1) {
                if (character == '|') {
                    break;
                }
                headBuilder.append((char) character);
            }
            JSONObject headJson = JSONObject.parseObject(headBuilder.toString());
            Head head = new Head(headJson.getString("author"), headJson.getString("file_name"), headJson.getString("created_time"), headJson.getString("updated_time"));

            // Read the index of the file
            JSONArray indexArray = JSONArray.parseArray(readFile(fileReader, headJson.getIntValue("index_start_position"), headJson.getIntValue("index_size")));

            // Read the content of the file
            ArrayList<Slide> slides = new ArrayList<>();
            for (int i = 0; i < indexArray.size(); i++) {
                JSONObject index_json = indexArray.getJSONObject(i);
                JSONObject content_json = JSONObject.parseObject(readFile(fileReader, index_json.getIntValue("position"), index_json.getIntValue("size")));
                slides.add(newSlide(content_json, i + 1));
            }

            // Read the duplicate data of the file
            fileReader.seek(indexArray.getJSONObject(indexArray.size() - 1).getIntValue("position") + indexArray.getJSONObject(indexArray.size() - 1).getIntValue("size") + 1);
            int byteRead;
            StringBuilder stringBuilder = new StringBuilder();
            while ((byteRead = fileReader.read()) != -1) {
                stringBuilder.append((char) byteRead);
            }
            JSONArray duplicateDataArr = JSONArray.parseArray(stringBuilder.toString());
            fileReader.close();
            return new CSPPTFile(slides, head, duplicateDataArr);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File does not exist");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Save the CSPPTFile object to the file
     *
     * @param path      The path of the file to be saved
     * @param cspptFile The CSPPTFile object to be saved
     * @return True if the file is saved successfully
     */
    @Override
    public boolean saveFile(String path, CSPPTFile cspptFile) {
        cspptFile.findAndReplaceDuplicates();
        String index = cspptFile.getIndexJsonStringSave();
        String head = cspptFile.getHead().getHeadJsonString(index);
        String content = cspptFile.getSlidesJsonString(); //includes duplicate data
        String total = head + "|" + index + "|" + content;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(total);
            writer.close();
            System.err.println("SAVE SUCCESS, path: " + path);
        } catch (IOException e) {
            System.err.println("SAVE ERROR: " + e.getMessage());
        }
        return false;
    }

    /**
     * Read a segment of the file
     *
     * @param fileReader     The file reader RandomAccessFile
     * @param start_position The start position of the segment
     * @param size           The size of the segment
     * @return The string of the segment
     * @throws IOException
     */
    private String readFile(RandomAccessFile fileReader, int start_position, int size) throws IOException {
        fileReader.seek(start_position);
        String str = "";
        byte[] buffer = new byte[size];
        int bytesRead = fileReader.read(buffer, 0, size);
        if (bytesRead > 0) {
            str = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
        }

        return str;
    }

    /**
     * Create a new slide object from the json object
     *
     * @param content_json The json object of the slide
     * @return The slide object
     */
    private Slide newSlide(JSONObject content_json, int id) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ArrayList<ArrayList<Object>> arrays_big = new ArrayList<>();
        for (Class<?> clazz : GlobalVariables.ALL_COMPONENTS) {
            JSONArray arrays_json = content_json.getJSONArray(clazz.getSimpleName().toLowerCase());
            ArrayList<Object> arrays = new ArrayList<>();

            for (int i = 0; i < arrays_json.size(); i++) {
                JSONObject json = arrays_json.getJSONObject(i);
                Object instance = clazz.getDeclaredConstructor().newInstance();

                Class<?> tempClass = clazz;
                while (tempClass != null && tempClass != Object.class) {
                    Field[] fields = tempClass.getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        Object value = GeneralTools.convertJsonToFieldType(json.get(GeneralTools.toLowerCaseFirstLetter(field.getName())), field.getType());
                        GeneralTools.invokeSetMethods(instance, field.getName(), value);
                    }
                    tempClass = tempClass.getSuperclass();
                }
                arrays.add(instance);
            }

            arrays_big.add(arrays);
        }

        Slide slide = new Slide(id);
        for (int i = 0; i < arrays_big.size(); i++) {
            GeneralTools.invokeSetMethods(slide, GlobalVariables.ALL_COMPONENTS[i].getSimpleName().toLowerCase() + "s", arrays_big.get(i));
        }
        return slide;
    }
}


