package ua.com.alevel.dateFormat;

import java.util.Scanner;

import static ua.com.alevel.util.DateToString.dateToString;

public class OutputDateFormat {

    public static void selectDataOutputFormat() {
        System.out.print("\nPlease enter number of Output Date format: ");
        Scanner scanner = new Scanner(System.in);
        int choiceFormat = Integer.parseInt(scanner.nextLine());
        dateToString(choiceFormat);
    }
}
