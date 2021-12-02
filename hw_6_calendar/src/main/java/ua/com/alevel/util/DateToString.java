package ua.com.alevel.util;

import ua.com.alevel.date.CustomDate;
import ua.com.alevel.enums.NameOfMonth;

import static ua.com.alevel.date.CustomDateList.dateList;

public class DateToString {

    public static void dateToString(int dateFormat) {
        try {
            switch (dateFormat) {
                case 1:
                    printDdMmYyyy();
                    break;
                case 2:
                    printMmDdYyyy();
                    break;
                case 3:
                    printMmmDdYyyy();
                    break;
                case 4:
                    printDdMmmYyyy();
                    break;
                default:
                    System.out.println("Incorrect choice!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void printDdMmYyyy() {
        if (!dateList.isEmpty()) {
            System.out.println("\nYour list of Dates:");
            for (CustomDate d: dateList) {
                System.out.println("Date: " +
                        String.format("%02d", d.getDay()) + "/" + String.format("%02d", d.getMonth()) + "/" + String.format("%04d", d.getYear()) + " " +
                        String.format("%02d", d.getHour()) + ":" + String.format("%02d", d.getMinute()) + ":" + String.format("%02d", d.getSecond()) + ":" + String.format("%03d", d.getMillisecond()));
            }
        } else {
            System.out.println("Your list of Dates is empty!");
        }
    }

    private static void printMmDdYyyy() {
        if (!dateList.isEmpty()) {
            System.out.println("\nYour list of Dates:");
            for (CustomDate d: dateList) {
                System.out.println("Date: " +
                        String.format("%02d", d.getMonth()) + "/" + String.format("%02d", d.getDay()) + "/" + String.format("%04d", d.getYear()) + " " +
                        String.format("%02d", d.getHour()) + ":" + String.format("%02d", d.getMinute()) + ":" + String.format("%02d", d.getSecond()) + ":" + String.format("%03d", d.getMillisecond()));
            }
        } else {
            System.out.println("Your list of Dates is empty!");
        }
    }

    private static void printMmmDdYyyy() {
        if (!dateList.isEmpty()) {
            System.out.println("\nYour list of Dates:");
            for (CustomDate d: dateList) {
                System.out.println("Date: " +
                        String.format("%3s", NameOfMonth.values()[d.getMonth()-1].getMonthName()) + "-" + String.format("%02d", d.getDay()) + "-" + String.format("%04d", d.getYear()) + " " +
                        String.format("%02d", d.getHour()) + ":" + String.format("%02d", d.getMinute()) + ":" + String.format("%02d", d.getSecond()) + ":" + String.format("%03d", d.getMillisecond()));
            }
        } else {
            System.out.println("Your list of Dates is empty!");
        }
    }

    private static void printDdMmmYyyy() {
        if (!dateList.isEmpty()) {
            System.out.println("\nYour list of Dates:");
            for (CustomDate d: dateList) {
                System.out.println("Date: " +
                        String.format("%02d", d.getDay()) + "-" + String.format("%3s", NameOfMonth.values()[d.getMonth()-1].getMonthName()) + "-" + String.format("%04d", d.getYear()) + " " +
                        String.format("%02d", d.getHour()) + ":" + String.format("%02d", d.getMinute()) + ":" + String.format("%02d", d.getSecond()) + ":" + String.format("%03d", d.getMillisecond()));
            }
        } else {
            System.out.println("Your list of Dates is empty!");
        }
    }
}
