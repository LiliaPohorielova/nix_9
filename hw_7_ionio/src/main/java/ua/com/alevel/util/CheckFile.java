package ua.com.alevel.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheckFile {

    public static List<String> checkFilePath(String fileName) {
        List<String> input = new ArrayList<>();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            while (bufferedReader.ready()) {
                input.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            System.out.println("error = " + e.getMessage());
        }
        return input;
    }
}
