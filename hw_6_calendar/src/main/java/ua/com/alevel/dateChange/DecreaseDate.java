package ua.com.alevel.dateChange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.ChooseTask;
import ua.com.alevel.date.CustomDate;

import java.io.BufferedReader;
import java.io.IOException;

import static ua.com.alevel.date.CustomDateList.findDateById;
import static ua.com.alevel.enums.MilliDate.*;
import static ua.com.alevel.util.DateToString.printDatesWithIndex;
import static ua.com.alevel.util.Navigation.printIncreaseDecreaseMenu;
import static ua.com.alevel.util.ParseDate.dateToMilliseconds;
import static ua.com.alevel.util.ParseDate.millisecondsToDate;

public class DecreaseDate {

    private static final Logger INFO = LoggerFactory.getLogger("info");
    private static final Logger ERROR = LoggerFactory.getLogger("error");

    public static void run(BufferedReader reader) {
        INFO.info("Decrease date");
        System.out.println("\n===================== DECREASE DATE =======================");
        String choice;
        int num;
        try {
            CustomDate date;
            printDatesWithIndex();
            System.out.print("\nEnter id of Your Date: ");
            date = findDateById(reader);
            System.out.println("\nYou want subtract: ");
            printIncreaseDecreaseMenu();
            choice = reader.readLine();
            switch (choice) {
                case "1":
                    System.out.print("\nYour Date Before Decrease:\n" + date);
                    System.out.print("\n\nPlease enter integer number of years to Decrease: ");
                    num = Integer.parseInt(reader.readLine());
                    date = decreaseYear(num, date);
                    System.out.print("\nYour Date after Decrease:\n" + date);
                    break;
                case "2":
                    System.out.print("\nYour Date Before Decrease:\n" + date);
                    System.out.print("\n\nPlease enter integer number of days to Decrease: ");
                    num = Integer.parseInt(reader.readLine());
                    date = decreaseDay(num, date);
                    System.out.print("\nYour Date after Decrease:\n" + date);
                    break;
                case "3":
                    System.out.print("\nYour Date Before Decrease:\n" + date);
                    System.out.print("\n\nPlease enter integer number of hours to Decrease: ");
                    num = Integer.parseInt(reader.readLine());
                    date = decreaseHour(num, date);
                    System.out.print("\nYour Date after Decrease:\n" + date);
                    break;
                case "4":
                    System.out.print("\nYour Date Before Decrease:\n" + date);
                    System.out.print("\n\nPlease enter integer number of minutes to Decrease: ");
                    num = Integer.parseInt(reader.readLine());
                    date = decreaseMin(num, date);
                    System.out.print("\nYour Date after Decrease:\n" + date);
                    break;
                case "5":
                    System.out.print("\nYour Date Before Decrease:\n" + date);
                    System.out.print("\n\nPlease enter integer number of seconds to Decrease: ");
                    num = Integer.parseInt(reader.readLine());
                    date = decreaseSec(num, date);
                    System.out.print("\nYour Date after Decrease:\n" + date);
                    break;
                case "6":
                    System.out.print("\nYour Date Before Decrease:\n" + date);
                    System.out.print("\n\nPlease enter integer number of milliseconds to Decrease: ");
                    num = Integer.parseInt(reader.readLine());
                    date = decreaseMill(num, date);
                    System.out.print("\nYour Date after Decrease:\n" + date);
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

    public static CustomDate decreaseMill(int num, CustomDate date) {
        if (dateToMilliseconds(date) - num * MILLISECOND.getValueInMillisecondOrder() > 0)
            return new CustomDate(millisecondsToDate(dateToMilliseconds(date) - num * MILLISECOND.getValueInMillisecondOrder()));
        else throw new NumberFormatException("Can't decrease this date! Time before our era! ");
    }

    public static CustomDate decreaseSec(int num, CustomDate date) {
        if (dateToMilliseconds(date) - num * SECOND.getValueInMillisecondOrder() > 0)
            return new CustomDate(millisecondsToDate(dateToMilliseconds(date) - num * SECOND.getValueInMillisecondOrder()));
        else throw new NumberFormatException("Can't decrease this date! Time before our era! ");
    }

    public static CustomDate decreaseMin(int num, CustomDate date) {
        if (dateToMilliseconds(date) - num * MINUTE.getValueInMillisecondOrder() > 0)
            return new CustomDate(millisecondsToDate(dateToMilliseconds(date) - num * MINUTE.getValueInMillisecondOrder()));
        else throw new NumberFormatException("Can't decrease this date! Time before our era! ");
    }

    public static CustomDate decreaseHour(int num, CustomDate date) {
        if (dateToMilliseconds(date) - num * HOUR.getValueInMillisecondOrder() > 0)
            return new CustomDate(millisecondsToDate(dateToMilliseconds(date) - num * HOUR.getValueInMillisecondOrder()));
        else throw new NumberFormatException("Can't decrease this date! Time before our era! ");
    }

    public static CustomDate decreaseDay(int num, CustomDate date) {
        if (dateToMilliseconds(date) - num * DAY.getValueInMillisecondOrder() > 0)
            return new CustomDate(millisecondsToDate(dateToMilliseconds(date) - num * DAY.getValueInMillisecondOrder()));
        else throw new NumberFormatException("Can't decrease this date! Time before our era! ");
    }

    public static CustomDate decreaseYear(int num, CustomDate date) {
        if (dateToMilliseconds(date) - num * YEAR.getValueInMillisecondOrder() > 0) {
            return new CustomDate(millisecondsToDate(dateToMilliseconds(date) - num * YEAR.getValueInMillisecondOrder()));
        } else throw new NumberFormatException("Can't decrease this date! Time before our era! ");
    }
}
