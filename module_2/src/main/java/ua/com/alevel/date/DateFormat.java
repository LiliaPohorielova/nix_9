package ua.com.alevel.date;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DateFormat {

    public static void run(Scanner scanner) throws IOException {
        System.out.println("DateFormat.run");
        List<DateTimeFormatter> formatters =
                List.of(

                        DateTimeFormatter.ofPattern("yyyy/MM/dd"), // 2020/04/05
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"), // 05/04/2020
                        DateTimeFormatter.ofPattern("MM-dd-yyyy")  // 04-05-2020
                );
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyyMMdd");

        List<String> inputs =
                List.of(
                        "01-23-2019",
                        "2019-01-23",
                        "2019/01/23",
                        "banana"
                );

        for (String input : inputs) {
            LocalDate localDate = null;
            for (DateTimeFormatter formatter : formatters) {
                try {
                    localDate = LocalDate.parse(input, formatter);
                } catch (DateTimeParseException e) {
                    // Ignoring exception, as it is expected.
                }
            }
            if (Objects.isNull(localDate)) { // Deal with unexpected input
                System.out.println("ERROR: Unexpected input: " + input);
            } else {
                System.out.println("Input: " + input + " ➙ " + localDate.format(myFormat));
            }
        }
    }
}
