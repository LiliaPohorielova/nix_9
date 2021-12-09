package ua.com.alevel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ParseCSVUtil {

    private static final String CSV_SEPARATOR = ",";
    public static void saveObjectToCSV(Object object) throws IOException {
        String pathToFile = object.getClass().getSimpleName().toLowerCase() + ".csv";
        Field[] objectFields = object.getClass().getDeclaredFields();
        int countOfFields = objectFields.length;
        String[] titlesOfObjectFields = new String[countOfFields];
        StringBuilder resultObjectString = new StringBuilder();
        Class<?> objectClass = object.getClass();
        Field tempField;
        String tempFieldTitle;
        Object tempObject;

        for (int i = 0; i < countOfFields; i++) titlesOfObjectFields[i] = objectFields[i].getName();

        for (int i = 0; i < countOfFields; i++) {
            tempFieldTitle = titlesOfObjectFields[i];
            try {
                tempField = objectClass.getField(tempFieldTitle);
                tempObject = tempField.get(object);
                if (tempObject != null) resultObjectString.append(tempObject);
                if ((i == countOfFields - 1)) {
                    resultObjectString.append("\n");
                } else {
                    resultObjectString.append(CSV_SEPARATOR);
                }
            } catch ( NoSuchFieldException | IllegalAccessException  e) {
                System.out.println("error = " + e.getMessage());
            }
        }
        try(FileWriter fileWriter = new FileWriter(pathToFile, true)) {
            fileWriter.write(resultObjectString.toString());
        } catch (IOException e) {
            System.out.println("error = " + e.getMessage());
        }
    }

    //TODO: FILE PATH AS A PARAMETER
    public static <ENTITY> List<ENTITY> getObjectsFromCSV(Class<ENTITY> dataClass) throws ReflectiveOperationException {
        String pathToFile = dataClass.getSimpleName().toLowerCase() + ".csv";
        List<String> fileStrings = getFileAsListOfStrings(pathToFile);
        Field[] objectFields = dataClass.getDeclaredFields();
        int countOfFields = objectFields.length;
        String[] titlesOfObjectFields = new String[countOfFields];
        List<ENTITY> resultArrayOfObjects = new ArrayList<>();
        ENTITY tempEntity;
        String[] inputData;
        String typeOfObject;

        for (int i = 0; i < countOfFields; i++) titlesOfObjectFields[i] = objectFields[i].getName();

        for (int i = 0; i < fileStrings.size(); i++) {
            inputData = fileStrings.get(i).split(CSV_SEPARATOR);
            tempEntity = dataClass.newInstance();
            for (int j = 0; j < inputData.length; i++) {
                typeOfObject = tempEntity.getClass().getField(titlesOfObjectFields[i]).getType().getTypeName();
                if (typeOfObject.equals("java.lang.String")) {
                    tempEntity.getClass().getField(titlesOfObjectFields[i]).set(tempEntity, inputData[i]);
                }
                if (typeOfObject.equals("int")) {
                    tempEntity.getClass().getField(titlesOfObjectFields[i]).setInt(tempEntity, Integer.parseInt(inputData[i]));
                }
                if (typeOfObject.equals("boolean")) {
                    tempEntity.getClass().getField(titlesOfObjectFields[i]).setBoolean(tempEntity, Boolean.parseBoolean(inputData[i]));
                }
            }
            resultArrayOfObjects.add(tempEntity);
        }
        return resultArrayOfObjects;
    }

    //!!!!!!!! findByID (Declaration) !!!!!!!!
    public static <ENTITY> List<ENTITY> findRelation(Class<ENTITY> clazz, String id) throws ReflectiveOperationException {
        List<String> inputRelation = getFileAsListOfStrings("bookauthor.csv");
        List<String> idList = new ArrayList<>();
        for (String s : inputRelation) {
            if (s.contains(id)) {
                String[] relationArray = s.split(",");
                for (String value : relationArray) {
                    if (!value.equals(id)) {
                        idList.add(value);
                    }
                }
            }
        }
        List<ENTITY> result = new ArrayList<>();
        for (String s : idList) {
            if (findObjectByID(clazz, s) != null) {
                result.add(findObjectByID(clazz, s));
            }

        }
        return result;
    }

    //!!!!!!!! findByID (Declaration) !!!!!!!!
    public static <ENTITY> ENTITY findObjectByID(Class<ENTITY> dataClass, String id) throws ReflectiveOperationException {
        String pathToFile = dataClass.getSimpleName().toLowerCase() + ".csv";
        List<String> fileStrings = getFileAsListOfStrings(pathToFile);
        if (fileStrings.isEmpty()) throw new ReflectiveOperationException("file is empty");

        Field[] fields = dataClass.getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }

        String[] values;
        String[] forOutput = null;
        boolean matches = false;
        for (String s : fileStrings) {
            values = s.split(CSV_SEPARATOR);
            for (String value : values) {
                if (value.equals(id)) {
                    matches = true;
                    forOutput = values;
                    break;
                }
            }
        }
        if (!matches) {
            values = null;
        } else {
            values = forOutput;
        }
        if (values == null) return null;

        ENTITY object = dataClass.newInstance();
        for (int i = 0; i < values.length; i++) {
            String type = object.getClass().getField(fieldNames[i]).getType().getTypeName();
            if (type.equals("java.lang.String")) {
                object.getClass().getField(fieldNames[i]).set(object, values[i]);
            }
            if (type.equals("int")) {
                object.getClass().getField(fieldNames[i]).setInt(object, Integer.parseInt(values[i]));
            }
            if (type.equals("boolean")) {
                object.getClass().getField(fieldNames[i]).setBoolean(object, Boolean.parseBoolean(values[i]));
            }
        }
        return object;
    }

    //!!!!!!!!!!!!!!!!UTIL!!!!!!!!!!!!!!!!!
    public static List<String> getFileAsListOfStrings(String filePath) {
        List<String> input = new ArrayList<>();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            while (bufferedReader.ready()) {
                input.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            System.out.println("error = " + e.getMessage());
        }
        return input;
    }
}
