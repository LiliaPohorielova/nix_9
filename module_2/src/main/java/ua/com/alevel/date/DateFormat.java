package ua.com.alevel.date;

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
                System.out.println("File is empty");
            }
        }

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("module_2/src/main/resources/results/" + outputFile), StandardCharsets.UTF_8))) {
            writeResults(formatters, myFormat, inputs, writer);
        } catch (FileNotFoundException e) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/results/" + outputFile), StandardCharsets.UTF_8))) {
                writeResults(formatters, myFormat, inputs, writer);
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void writeResults(List<DateTimeFormatter> formatters, DateTimeFormatter myFormat, List<String> inputs, Writer writer) throws IOException {
        for (String input : inputs) {
            LocalDate localDate = null;
            for (DateTimeFormatter formatter : formatters) {
                try {
                    localDate = LocalDate.parse(input, formatter);
                } catch (DateTimeParseException dateTimeParseException) {
                   // writer.write("\nERROR: " + dateTimeParseException.getMessage());
                }
            }
            if (Objects.isNull(localDate)) {
                writer.write("\nERROR: Unexpected input: " + input);
            } else {
                writer.write("\nInput: " + input + " -> " + localDate.format(myFormat));
            }
        }
        System.out.println("Data writing successfully completed.");
        writer.flush();
    }
}
