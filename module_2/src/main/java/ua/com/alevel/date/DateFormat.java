package ua.com.alevel.date;

import ua.com.alevel.ChooseTask;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DateFormat {

    public static void run(Scanner scanner) throws IOException {
        String inputFile = "input_date.txt";
        String outputFile = "output_date.txt";
        List<DateTimeFormatter> formatters =
                List.of(
                        DateTimeFormatter.ofPattern("yyyy/MM/dd"), // 2020/04/05
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"), // 05/04/2020
                        DateTimeFormatter.ofPattern("MM-dd-yyyy")  // 04-05-2020
                );
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
        BufferedReader bufferedReader = null;
        List<String> inputs = new ArrayList<>();
        System.out.println("\n---------------------- Date Format -------------------------");
        System.out.println("Reading input file: resources/input_date.txt");
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(DateFormat.class.getClassLoader().getResourceAsStream("starts/" + inputFile)));
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
        System.out.println("Analyzing dates...");
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("module_2/src/main/resources/results/" + outputFile, true), StandardCharsets.UTF_8))) {
            findAndSaveDates(formatters, myFormat, inputs, writer);
        } catch (FileNotFoundException e) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/results/" + outputFile, true), StandardCharsets.UTF_8))) {
                findAndSaveDates(formatters, myFormat, inputs, writer);
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        new ChooseTask().run();
    }

    private static void findAndSaveDates(List<DateTimeFormatter> formatters, DateTimeFormatter myFormat, List<String> inputs, Writer writer) throws IOException {
        System.out.print("Correct inputs: ");
        int ignoringInputCount = 0;
        for (String input : inputs) {
            LocalDate localDate = null;
            for (DateTimeFormatter formatter : formatters) {
                try {
                    localDate = LocalDate.parse(input, formatter);
                } catch (DateTimeParseException ignored) {
                }
            }
            if (Objects.isNull(localDate)) {
                ignoringInputCount++;
            } else {
                writer.write("\nInput: " + input + " -> " + localDate.format(myFormat));
                System.out.print("\nInput: " + input + " -> " + localDate.format(myFormat));
            }
        }
        System.out.println("\nIgnoring input count: " + ignoringInputCount);
        System.out.println("Data writing successfully completed!");
        writer.write("\n");
        writer.close();
    }
}
