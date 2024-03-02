package org.lancaster.group77.FileSystem.Tools;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.lancaster.group77.FileSystem.CSPPTFile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class GeneralTools {
    /**
     * Get the data from the duplicate data array
     * Change #C%S&P$P!T[index] to the data from the duplicate data array
     *
     * @param cspptFile The CSPPTFile object
     * @param data      The data
     * @return The data from the duplicate data array
     */
    public static String getDataFromDuplicate(CSPPTFile cspptFile, String data) {
        if (data.contains("#C%S&P$P!T")) {
            int index = Integer.parseInt(data.split("#C%S&P\\$P!T\\[")[1].split("]")[0]);
            return cspptFile.getDuplicateDataArr().getString(index);
        }

        return data;
    }


    /**
     * Invoke a Get method from an object by the method name
     *
     * @param obj
     * @param fieldName
     */
    public static Object invokeGetMethods(Object obj, String fieldName) {
        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (isGetter(method, fieldName)) {
                try {
                    return (method.invoke(obj));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }


    /**
     * Invoke a Set method from an object by the method name
     *
     * @param obj
     * @param fieldName
     * @param fieldValue
     */
    public static void invokeSetMethods(Object obj, String fieldName, Object fieldValue) {
        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (isSetter(method, fieldName)) {
                try {
//                    Class<?> expectedType = method.getParameterTypes()[0];
//                    if (!expectedType.isAssignableFrom(fieldValue.getClass())) {
//                        //detect type mismatch
//                        System.out.println("Method: " + method.getName() + ", Expected Type: " + expectedType.getName() + ", Value Type: " + fieldValue.getClass().getName());
//                        System.out.println("Type mismatch detected. Handling conversion if necessary.");
//                    }
                    method.invoke(obj, fieldValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Invoke a Save method from an object by the method name
     *
     * @param obj
     */
    public static void invokeSaveMethods(Object obj) {
        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            if (isSaver(method, "")) {
                try {
                    method.invoke(obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static boolean isSetter(Method method, String fieldName) {
        return method.getName().equalsIgnoreCase("set" + fieldName)
                && method.getParameterTypes().length == 1
                && method.getReturnType() == void.class;
    }

    private static boolean isSaver(Method method, String fieldName) {
        return method.getName().equalsIgnoreCase("save" + fieldName)
                && method.getParameterTypes().length == 0
                && method.getReturnType() == void.class;
    }

    private static boolean isGetter(Method method, String fieldName) {
        return method.getName().equalsIgnoreCase("get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1))
                && method.getParameterTypes().length == 0
                && method.getReturnType() != void.class;
    }

    public static Object convertJsonToFieldType(Object jsonValue, Class<?> fieldType) {
        if (jsonValue == null) return null;

        if (fieldType.isInstance(jsonValue)) {
            return jsonValue;
        }

        try {
            if (fieldType == int.class || fieldType == Integer.class) {
                return jsonValue instanceof Number ? ((Number) jsonValue).intValue() : Integer.parseInt(jsonValue.toString());
            } else if (fieldType == long.class || fieldType == Long.class) {
                return jsonValue instanceof Number ? ((Number) jsonValue).longValue() : Long.parseLong(jsonValue.toString());
            } else if (fieldType == double.class || fieldType == Double.class) {
                return jsonValue instanceof Number ? ((Number) jsonValue).doubleValue() : Double.parseDouble(jsonValue.toString());
            } else if (fieldType == float.class || fieldType == Float.class) {
                return jsonValue instanceof Number ? ((Number) jsonValue).floatValue() : Float.parseFloat(jsonValue.toString());
            } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                return Boolean.parseBoolean(jsonValue.toString());
            } else if (fieldType == String.class) {
                return jsonValue.toString();
            } else if (fieldType == char.class || fieldType == Character.class) {
                return jsonValue.toString().charAt(0);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Cannot convert " + jsonValue + " to " + fieldType, e);
        }

        return jsonValue;
    }

    /**
     * Convert the first letter of the string to lower case
     *
     * @param str The string
     * @return The string with the first letter in lower case
     */
    public static String toLowerCaseFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * Deep copy an object and return the copied object
     * @param object
     * @return The copied object
     * @param <T>
     */
    public static <T extends Serializable> T deepCopy(T object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            @SuppressWarnings("unchecked")
            T copied = (T) ois.readObject();
            ois.close();

            return copied;
        } catch (IOException | ClassNotFoundException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Get the file extension from the file name
     * @param fileName
     * @return
     */
    public static String getFileExtension(String fileName) {
        if(fileName == null || !fileName.contains(".") || fileName.lastIndexOf(".") == fileName.length() - 1) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * Converts a string in the format like "[1, 1]" to an array of integers.
     * @param input
     * @return The array of integers
     */
    public static int[] stringToIntArray(String input) {
        String[] numberStrings = input.substring(1, input.length() - 1).split(", ");
        int[] intArray = new int[numberStrings.length];

        for (int i = 0; i < numberStrings.length; i++) {
            intArray[i] = Integer.parseInt(numberStrings[i]);
        }

        return intArray;
    }

    /**
     * Converts a string in the format like "[a, b]" to an array of string.
     * @param input The input string
     * @return The array of string
     */
    public static String[] stringToStringArray(String input) {
        String[] stringArray = input.substring(1, input.length() - 1).split(", ");
        return stringArray;
    }

    /**
     * Save the layeredPanes as a PDF file
     * @param layeredPaneList
     * @param filePath
     */
    public static void saveAsPDF(ArrayList<JLayeredPane> layeredPaneList, String filePath) {
        try (PDDocument document = new PDDocument()) {
            for (JLayeredPane layeredPane : layeredPaneList) {
                PDPage page = new PDPage(new PDRectangle(layeredPane.getWidth(), layeredPane.getHeight()));
                document.addPage(page);

                BufferedImage image = new BufferedImage(
                        layeredPane.getWidth() * 2,
                        layeredPane.getHeight() * 2,
                        BufferedImage.TYPE_INT_RGB
                );

                Graphics2D graphics = image.createGraphics();
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                graphics.scale(2.0, 2.0);
                layeredPane.paint(graphics);
                graphics.dispose();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "PNG", baos);
                PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, baos.toByteArray(), "layered_pane_image");

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    contentStream.drawImage(pdImage, 0, 0, layeredPane.getWidth(), layeredPane.getHeight());
                }
            }

            document.save(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
