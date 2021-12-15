package ua.com.alevel.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static ua.com.alevel.util.CheckFile.checkFilePath;

public class DeleteUtil {

    public static <T> void deleteInDB(Class<T> clazz, String id) throws InstantiationException, IllegalAccessException {
        String fileName = clazz.getSimpleName().toLowerCase() + ".csv";
        List<String> input = checkFilePath(fileName);
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).contains(id) ) {
                input.remove(i);
                i--;
            } else {
                output.append(input.get(i));
                output.append("\n");
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(output.toString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
