package ua.com.alevel.util;

import ua.com.alevel.date.CustomDate;
import ua.com.alevel.date.CustomDateList;
import ua.com.alevel.enums.NameOfMonth;

import java.io.IOException;
import java.util.Scanner;

public class StringToDate {

    public static void stringToDate(int dateFormat) {
        System.out.println("\n\n------------------ INPUTTING DATE ------------------");
        CustomDate date = new CustomDate();
        Scanner scanner = new Scanner(System.in);
        String inputString;
        String[] arrayOfStrings;
        try {
            switch (dateFormat) {
                case 1:
                    System.out.println("Please enter your Date in format: 10/10/2021");
                    inputString = scanner.nextLine();
                    arrayOfStrings = inputString.split("/");
                    date.setDay(Integer.parseInt(arrayOfStrings[0]));
                    date.setMonth(Integer.parseInt(arrayOfStrings[1]));
                    date.setYear(Integer.parseInt(arrayOfStrings[2]));
                    CustomDateList.dateList.add(date);
                    break;
                case 2:
                    System.out.println("Please enter your Date in format: 10/24/2021");
                    inputString = scanner.nextLine();
                    arrayOfStrings = inputString.split("/");
                    date.setDay(Integer.parseInt(arrayOfStrings[1]));
                    date.setMonth(Integer.parseInt(arrayOfStrings[0]));
                    date.setYear(Integer.parseInt(arrayOfStrings[2]));
                    CustomDateList.dateList.add(date);
                    break;
                case 3:
                    System.out.println("Please enter your Date in format: January-24-2021");
                    inputString = scanner.nextLine();
                    arrayOfStrings = inputString.split("-");
                    date.setMonth(NameOfMonth.fromString(arrayOfStrings[0]).getMonthOrder());
                    date.setDay(Integer.parseInt(arrayOfStrings[1]));
                    date.setYear(Integer.parseInt(arrayOfStrings[2]));
                    CustomDateList.dateList.add(date);
                    break;
                case 4:
                    System.out.println("Please enter your Date in format: 25-January-2021");
                    inputString = scanner.nextLine();
                    arrayOfStrings = inputString.split("-");
                    date.setDay(Integer.parseInt(arrayOfStrings[0]));
                    date.setMonth(NameOfMonth.fromString(arrayOfStrings[1]).getMonthOrder());
                    date.setYear(Integer.parseInt(arrayOfStrings[2]));
                    CustomDateList.dateList.add(date);
                    break;
                case 5:
                    System.out.println("Please enter your Date in format: 25/05/2021 11:59");
                    inputString = scanner.nextLine();
                    arrayOfStrings = inputString.split(" ");
                    String dayMonthYear[] = arrayOfStrings[0].split("/");
                    date.setDay(Integer.parseInt(dayMonthYear[0]));
                    date.setMonth(Integer.parseInt(dayMonthYear[1]));
                    date.setYear(Integer.parseInt(dayMonthYear[2]));

                    String hourMinute[] = arrayOfStrings[1].split(":");
                    date.setHour(Integer.parseInt(hourMinute[0]));
                    date.setMinute(Integer.parseInt(hourMinute[1]));
                    CustomDateList.dateList.add(date);
                default:
                    System.out.println("Incorrect Input");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    //TODO: VALID DAY IN DATE CLASS!!
}
