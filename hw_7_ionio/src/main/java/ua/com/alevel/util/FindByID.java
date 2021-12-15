package ua.com.alevel.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ua.com.alevel.ParseCSVUtil.getFileAsListOfStrings;

public class FindByID {

    public static <ENTITY> ENTITY findObjectByID(Class<ENTITY> dataClass, String id) throws ReflectiveOperationException {
        String pathToFile = dataClass.getSimpleName().toLowerCase() + ".csv";
        List<String> fileStrings = getFileAsListOfStrings(pathToFile);
        if (fileStrings.isEmpty()) throw new ReflectiveOperationException("file is empty");

        ArrayList<Field> objectFields = new ArrayList<>();
        objectFields.addAll(Arrays.asList(dataClass.getSuperclass().getDeclaredFields()));
        objectFields.addAll(Arrays.asList(dataClass.getDeclaredFields()));
        String[] fieldNames = new String[objectFields.size()];
        for (int i = 0; i < objectFields.size(); i++) {
            fieldNames[i] = objectFields.get(i).getName();
        }

        String[] objectWithThisId = isIdInDB(fileStrings, id);
        if (objectWithThisId != null) {
            ENTITY object = dataClass.newInstance();
            for (int i = 0; i < objectWithThisId.length; i++) {
                String type = object.getClass().getField(fieldNames[i]).getType().getTypeName();
                if (type.equals("java.lang.String")) {
                    object.getClass().getField(fieldNames[i]).set(object, objectWithThisId[i]);
                }
                if (type.equals("int")) {
                    object.getClass().getField(fieldNames[i]).setInt(object, Integer.parseInt(objectWithThisId[i]));
                }
                if (type.equals("boolean")) {
                    object.getClass().getField(fieldNames[i]).setBoolean(object, Boolean.parseBoolean(objectWithThisId[i]));
                }
            }
            return object;
        } else throw new RuntimeException("Id is not found");
    }

    public static String[] isIdInDB(List<String> fileStrings, String id) {
        String[] tempObject;
        String[] objectWithThisId = null;
        boolean isIdInDb = false;
        for (String s : fileStrings) {
            tempObject = s.split(",");
            for (String value : tempObject) {
                if (value.equals(id)) {
                    isIdInDb = true;
                    objectWithThisId = tempObject;
                    break;
                }
            }
        }
        if (!isIdInDb) return null;
        return objectWithThisId;
    }
}
