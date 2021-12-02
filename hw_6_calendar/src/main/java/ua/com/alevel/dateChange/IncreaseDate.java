package ua.com.alevel.dateChange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.ChooseTask;
import ua.com.alevel.date.CustomDate;

import java.io.BufferedReader;
import java.io.IOException;

import static ua.com.alevel.date.CustomDateList.findDateById;
import static ua.com.alevel.enums.MilliDate.*;
import static ua.com.alevel.enums.MilliDate.YEAR;
import static ua.com.alevel.util.DateToString.printDatesWithIndex;
import static ua.com.alevel.util.Navigation.printIncreaseDecreaseMenu;
import static ua.com.alevel.util.ParseDate.dateToMilliseconds;
import static ua.com.alevel.util.ParseDate.millisecondsToDate;

public class IncreaseDate {

    private static final Logger INFO = LoggerFactory.getLogger("info");
    private static final Logger ERROR = LoggerFactory.getLogger("error");

    public static void run(BufferedReader reader) {
        INFO.info("Increase date");
        System.out.println("\n===================== INCREASE DATE =======================");
        String choice;
        int num;
        try {
            CustomDate date;
            printDatesWithIndex();
            System.out.print("\nEnter id of Your Date: ");
            date = findDateById(reader);
            System.out.println("\nYou want add: ");
            printIncreaseDecreaseMenu();
            choice = reader.readLine();
            switch (choice) {
                case "1":
                    System.out.print("\nYour Date Before Increase:\n" + date);
                    System.out.print("\n\nPlease enter integer number of years to Increase: ");
                    num = Integer.parseInt(reader.readLine());
                    date = increaseYear(num, date);
                    System.out.print("\nYour Date after Increase:\n" + date);
                    break;
                case "2":
                    System.out.print("\nYour Date Before Increase:\n" + date);
                    System.out.print("\n\nPlease enter integer number of days to Increase: ");
                    num = Integer.parseInt(reader.readLine());
                    date = increaseDay(num, date);
                    System.out.print("\nYour Date after Increase:\n" + date);
                    break;
                case "3":
                    System.out.print("\nYour Date Before Increase:\n" + date);
                    System.out.print("\n\nPlease enter integer number of hours to Increase: ");
                    num = Integer.parseInt(reader.readLine());
                    date = increaseHour(num, date);
                    System.out.print("\nYour Date after Increase:\n" + date);
                    break;
                case "4":
                    System.out.print("\nYour Date Before Increase:\n" + date);
                    System.out.print("\n\nPlease enter integer number of minutes to Increase: ");
                    num = Integer.parseInt(reader.readLine());
                    date = increaseMin(num, date);
                    System.out.print("\nYour Date after Increase:\n" + date);
                    break;
                case "5":
                    System.out.print("\nYour Date Before Increase:\n" + date);
                    System.out.print("\n\nPlease enter integer number of seconds to Increase: ");
                    num = Integer.parseInt(reader.readLine());
                    date = increaseSec(num, date);
                    System.out.print("\nYour Date after Increase:\n" + date);
                    break;
                case "6":
                    System.out.print("\nYour Date Before Increase:\n" + date);
                    System.out.print("\n\nPlease enter integer number of milliseconds to Increase: ");
                    num = Integer.parseInt(reader.readLine());
                    date = increaseMill(num, date);
                    System.out.print("\nYour Date after Increase:\n" + date);
                    break;
                case "0":
                    new ChooseTask().run();
                    break;
            }
        } catch (RuntimeException | IOException e) {
            ERROR.error(e.getMessage());
            System.out.println("Problem = " + e.getMessage());
        }
        new ChooseTask().run();
    }

    public static CustomDate increaseMill(int num, CustomDate date) {
        if (dateToMilliseconds(date) + num * MILLISECOND.getValueInMillisecondOrder() > 0)
            return new CustomDate(millisecondsToDate(dateToMilliseconds(date) + num * MILLISECOND.getValueInMillisecondOrder()));
        else throw new NumberFormatException("Can't increase this date! Time before our era! ");
    }

    public static CustomDate increaseSec(int num, CustomDate date) {
        if (dateToMilliseconds(date) + num * SECOND.getValueInMillisecondOrder() > 0)
            return new CustomDate(millisecondsToDate(dateToMilliseconds(date) + num * SECOND.getValueInMillisecondOrder()));
        else throw new NumberFormatException("Can't increase this date! Time before our era! ");
    }

    public static CustomDate increaseMin(int num, CustomDate date) {
        if (dateToMilliseconds(date) + num * MINUTE.getValueInMillisecondOrder() > 0)
            return new CustomDate(millisecondsToDate(dateToMilliseconds(date) + num * MINUTE.getValueInMillisecondOrder()));
        else throw new NumberFormatException("Can't increase this date! Time before our era! ");
    }

    public static CustomDate increaseHour(int num, CustomDate date) {
        if (dateToMilliseconds(date) + num * HOUR.getValueInMillisecondOrder() > 0)
            return new CustomDate(millisecondsToDate(dateToMilliseconds(date) + num * HOUR.getValueInMillisecondOrder()));
        else throw new NumberFormatException("Can't increase this date! Time before our era! ");
    }

    public static CustomDate increaseDay(int num, CustomDate date) {
        if (dateToMilliseconds(date) + num * DAY.getValueInMillisecondOrder() > 0)
            return new CustomDate(millisecondsToDate(dateToMilliseconds(date) + num * DAY.getValueInMillisecondOrder()));
        else throw new NumberFormatException("Can't increase this date! Time before our era! ");
    }

    public static CustomDate increaseYear(int num, CustomDate date) {
        if (dateToMilliseconds(date) + num * YEAR.getValueInMillisecondOrder() > 0)
            return new CustomDate(millisecondsToDate(dateToMilliseconds(date) + num * YEAR.getValueInMillisecondOrder()));
        else throw new NumberFormatException("Can't increase this date! Time before our era! ");
    }
}
