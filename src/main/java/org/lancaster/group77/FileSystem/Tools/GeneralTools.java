package org.lancaster.group77.FileSystem.Tools;

import org.lancaster.group77.FileSystem.CSPPTFile;

import java.lang.reflect.Method;

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

}
