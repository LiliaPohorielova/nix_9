package ua.com.alevel.dateFormat;

import java.util.Scanner;

import static ua.com.alevel.util.StringToDate.stringToDate;

public class InputDateFormat {

    public static void selectDataFormat() {
        System.out.println("Dates Without Time:\n" +
                "1 - dd/mm/yyyy\n" +
                "2 - mm/dd/yyyy\n" +
                "3 - mmm-dd-yyyy\n" +
                "4 - dd-mmm-yyyy\n\n" +
                "Dates With Time:\n" +
                "5 - dd/mm/yyyy 00:00\n" +
                "6 - mm/dd/yyyy 00:00\n" +
                "7 - mmm-dd-yyyy 00:00\n" +
                "8 - dd-mmm-yyyy 00:00\n" +
                "9 - dd/mm/yyyy 00:00:00\n" +
                "10 - mm/dd/yyyy 00:00:00\n" +
                "11 - mmm-dd-yyyy 00:00:00\n" +
                "12 - dd-mmm-yyyy 00:00:00\n" +
                "13 - dd/mm/yyyy 00:00:00:000\n" +
                "14 - mm/dd/yyyy 00:00:00:000\n" +
                "15 - mmm-dd-yyyy 00:00:00:000\n" +
                "16 - dd-mmm-yyyy 00:00:00:000\n");
        System.out.print("Please enter your Input Date format: ");
        Scanner scanner = new Scanner(System.in);
        int choiceFormat = Integer.parseInt(scanner.nextLine());
        stringToDate(choiceFormat);
    }
}
