package ua.com.alevel.util;

import ua.com.alevel.date.CustomDate;
import ua.com.alevel.enums.NameOfMonth;

import static ua.com.alevel.date.CustomDateList.dateList;

public class DateToString {

    public static void dateToString(int dateFormat) {
        try {
            switch (dateFormat) {
                case 1 -> printDdMmYyyy();
                case 2 -> printMmDdYyyy();
                case 3 -> printMmmDdYyyy();
                case 4 -> printDdMmmYyyy();
                default -> System.out.println("Incorrect choice!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void printDdMmYyyy() {
        if (!dateList.isEmpty()) {
            System.out.println("\nYour list of Dates:");
            for (CustomDate d : dateList) {
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
            for (CustomDate d : dateList) {
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
            for (CustomDate d : dateList) {
                System.out.println("Date: " +
                        String.format("%3s", NameOfMonth.values()[d.getMonth() - 1].getMonthName()) + "-" + String.format("%02d", d.getDay()) + "-" + String.format("%04d", d.getYear()) + " " +
                        String.format("%02d", d.getHour()) + ":" + String.format("%02d", d.getMinute()) + ":" + String.format("%02d", d.getSecond()) + ":" + String.format("%03d", d.getMillisecond()));
            }
        } else {
            System.out.println("Your list of Dates is empty!");
        }
    }

    private static void printDdMmmYyyy() {
        if (!dateList.isEmpty()) {
            System.out.println("\nYour list of Dates:");
            for (CustomDate d : dateList) {
                System.out.println("Date: " +
                        String.format("%02d", d.getDay()) + "-" + String.format("%3s", NameOfMonth.values()[d.getMonth() - 1].getMonthName()) + "-" + String.format("%04d", d.getYear()) + " " +
                        String.format("%02d", d.getHour()) + ":" + String.format("%02d", d.getMinute()) + ":" + String.format("%02d", d.getSecond()) + ":" + String.format("%03d", d.getMillisecond()));
            }
        } else {
            System.out.println("Your list of Dates is empty!");
        }
    }

    public static void printDatesWithIndex() {
        if (!dateList.isEmpty()) {
            System.out.println("Your list of dates:");
            for (int i = 0; i < dateList.size(); i++) {
                System.out.println(i + " - " + dateList.get(i));
            }
        } else {
            System.out.println("Your list of Dates is empty!");
            throw new RuntimeException("Empty list of dates!");
        }
    }
}
