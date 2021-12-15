package ua.com.alevel;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParseCSVUtil {

    private static final String CSV_SEPARATOR = ",";

    public static void saveObjectToCSV(Object object) throws IOException {
        String pathToFile = object.getClass().getSimpleName().toLowerCase() + ".csv";
        ArrayList<Field> objectFields = (ArrayList<Field>) getFields(object);
        int countOfFields = objectFields.size();
        String[] titlesOfObjectFields = new String[countOfFields];
        StringBuilder resultObjectString = new StringBuilder();
        Class<?> objectClass = object.getClass();
        Field tempField;
        String tempFieldTitle;
        Object tempObject;

        for (int i = 0; i < countOfFields; i++) titlesOfObjectFields[i] = objectFields.get(i).getName();

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
            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.out.println("error = " + e.getMessage());
            }
        }
        try (FileWriter fileWriter = new FileWriter(pathToFile, true)) {
            fileWriter.write(resultObjectString.toString());
        } catch (IOException e) {
            System.out.println("error = " + e.getMessage());
        }
    }

    public static <ENTITY> ArrayList<ENTITY> getObjectsFromCSV(Class<ENTITY> dataClass) throws ReflectiveOperationException {
        String pathToFile = dataClass.getSimpleName().toLowerCase() + ".csv";
        List<String> fileStrings = getFileAsListOfStrings(pathToFile);
        ArrayList<Field> objectFields = new ArrayList<>();
        objectFields.addAll(Arrays.asList(dataClass.getSuperclass().getDeclaredFields()));
        objectFields.addAll(Arrays.asList(dataClass.getDeclaredFields()));
        int countOfFields = objectFields.size();
        String[] titlesOfObjectFields = new String[countOfFields];
        ArrayList<ENTITY> resultArrayOfObjects = new ArrayList<>();
        ENTITY tempEntity;
        String[] inputObject;
        String typeOfObject;

        for (int i = 0; i < countOfFields; i++) titlesOfObjectFields[i] = objectFields.get(i).getName();

        for (String fileString : fileStrings) {
            inputObject = fileString.split(CSV_SEPARATOR);
            tempEntity = dataClass.newInstance();
            for (int j = 0; j < inputObject.length; j++) {
                typeOfObject = tempEntity.getClass().getField(titlesOfObjectFields[j]).getType().getTypeName();
                if (typeOfObject.equals("java.lang.String")) {
                    tempEntity.getClass().getField(titlesOfObjectFields[j]).set(tempEntity, inputObject[j]);
                }
                if (typeOfObject.equals("byte")) {
                    tempEntity.getClass().getField(titlesOfObjectFields[j]).setByte(tempEntity, Byte.parseByte(inputObject[j]));
                }
                if (typeOfObject.equals("short")) {
                    tempEntity.getClass().getField(titlesOfObjectFields[j]).setShort(tempEntity, Short.parseShort(inputObject[j]));
                }
                if (typeOfObject.equals("int")) {
                    tempEntity.getClass().getField(titlesOfObjectFields[j]).setInt(tempEntity, Integer.parseInt(inputObject[j]));
                }
                if (typeOfObject.equals("long")) {
                    tempEntity.getClass().getField(titlesOfObjectFields[j]).setLong(tempEntity, Long.parseLong(inputObject[j]));
                }
                if (typeOfObject.equals("float")) {
                    tempEntity.getClass().getField(titlesOfObjectFields[j]).setFloat(tempEntity, Float.parseFloat(inputObject[j]));
                }
                if (typeOfObject.equals("double")) {
                    tempEntity.getClass().getField(titlesOfObjectFields[j]).setDouble(tempEntity, Double.parseDouble(inputObject[j]));
                }
                if (typeOfObject.equals("boolean")) {
                    tempEntity.getClass().getField(titlesOfObjectFields[j]).setBoolean(tempEntity, Boolean.parseBoolean(inputObject[j]));
                }
            }
            if (!isEmpty(tempEntity)) resultArrayOfObjects.add(tempEntity);
        }
        return resultArrayOfObjects;
    }

    public static List<String> getFileAsListOfStrings(String filePath) {
        List<String> input = new ArrayList<>();
        BufferedReader bufferedReader;
        try {
            File yourFile = new File(filePath);
            yourFile.createNewFile();
            bufferedReader = new BufferedReader(new FileReader(filePath));
            while (bufferedReader.ready()) {
                input.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return input;
    }

    private static <T> List<Field> getFields(T t) {
        List<Field> fields = new ArrayList<>();
        Class<?> dataClass = t.getClass().getSuperclass();
        fields.addAll(Arrays.asList(dataClass.getDeclaredFields()));
        fields.addAll(Arrays.asList(t.getClass().getDeclaredFields()));
        return fields;
    }

    private static boolean isEmpty(Object o) {
        for (Field field : o.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (field.get(o) == null) {
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Exception occurred in processing!");
            }
        }
        return false;
    }
}
