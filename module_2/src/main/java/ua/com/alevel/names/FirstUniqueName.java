package ua.com.alevel.names;

import ua.com.alevel.ChooseTask;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FirstUniqueName {

    public static void run() throws IOException {
        String inputFile = "input_name.txt";
        String outputFile = "output_name.txt";

        BufferedReader bufferedReader = null;
        List<String> inputs = new ArrayList<>();
        System.out.println("\n---------------------- First Unique Name -------------------------");
        System.out.println("Reading input file: resources/input_date.txt");
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(FirstUniqueName.class.getClassLoader().getResourceAsStream("starts/" + inputFile)));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                inputs.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputs.isEmpty()) {
                System.out.println("File is empty!");
                new ChooseTask().run();
            }
        }
        System.out.println("Analyzing names...");
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("module_2/src/main/resources/results/" + outputFile, true), StandardCharsets.UTF_8))) {
            findAndSaveUniqueName(inputs, writer);
        } catch (FileNotFoundException e) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/results/" + outputFile, true), StandardCharsets.UTF_8))) {
                findAndSaveUniqueName(inputs, writer);
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        new ChooseTask().run();
    }

    private static void findAndSaveUniqueName(List<String> list, Writer writer) throws IOException {
        final LinkedHashMap<String, Integer> map = new LinkedHashMap<>(list.size());
        for (String s : list) {
            Integer count = map.getOrDefault(s, 0);
            map.put(s, count + 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet())
            if (entry.getValue() == 1) {
                System.out.println("First unique name is: " + entry.getKey());
                writer.write(entry.getKey());
            }
        if (map.isEmpty()) System.out.println("Not found unique names!");
        System.out.println("Data writing successfully completed!");
        writer.write("\n");
        writer.close();
    }
}
