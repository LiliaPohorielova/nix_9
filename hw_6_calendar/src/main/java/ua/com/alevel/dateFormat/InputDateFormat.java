package ua.com.alevel.dateFormat;

import java.util.Scanner;

import static ua.com.alevel.util.StringToDate.stringToDate;

public class InputDateFormat {

    public static void selectDataFormat() {
        System.out.print("Please enter number of Input Date format: ");
        Scanner scanner = new Scanner(System.in);
        int choiceFormat = Integer.parseInt(scanner.nextLine());
        stringToDate(choiceFormat);
    }
}
