package ua.com.alevel.util;

import ua.com.alevel.date.CustomDate;
import ua.com.alevel.date.CustomDateList;

import java.util.Scanner;

public class StringToDate {

    public static void stringToDate(int dataFormat) {
        System.out.println("\n\n------------------ INPUTTING DATE ------------------");
        CustomDate date = new CustomDate();
        Scanner scanner = new Scanner(System.in);
        String inputString;
        switch (dataFormat) {
            case 1:
                System.out.println("Please enter your Date in format: 10/10/2020");
                inputString = scanner.nextLine();
                String[] dayMonthYear = inputString.split("/");
                if (dayMonthYear[0].equals("")) {
                    date.setDay(1);
                } else date.setDay(Integer.parseInt(dayMonthYear[0]));
                if (dayMonthYear[1].equals("")) {
                    date.setMonth(1);
                } else date.setMonth(Integer.parseInt(dayMonthYear[1]));
                if (dayMonthYear[2].equals("")) {
                    date.setYear(0);
                } else date.setYear(Integer.parseInt(dayMonthYear[2]));
                CustomDateList.dateList.add(date);
                break;
            default:
                System.out.println("Incorrect Input");
        }
    }
}
