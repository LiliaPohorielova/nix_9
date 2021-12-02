package ua.com.alevel.dateCompare;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.ChooseTask;
import ua.com.alevel.date.CustomDate;

import java.io.BufferedReader;

import static ua.com.alevel.date.CustomDateList.findDateById;
import static ua.com.alevel.util.DateToString.printDatesWithIndex;
import static ua.com.alevel.util.ParseDate.dateToMilliseconds;
import static ua.com.alevel.util.ParseDate.millisecondsToDate;

public class DifferenceBetweenDates {

    private static final Logger INFO = LoggerFactory.getLogger("info");
    private static final Logger ERROR = LoggerFactory.getLogger("error");

    public static void run(BufferedReader reader) {
        INFO.info("Difference Between Dates");
        System.out.println("================== DIFFERENCE BETWEEN DATES ==================");
        CustomDate date1;
        CustomDate date2;
        CustomDate result;
        try {
            printDatesWithIndex();
            System.out.print("\nEnter id of First Date:");
            date1 = findDateById(reader);
            System.out.print("Enter id of Second Date:");
            date2 = findDateById(reader);
            result = millisecondsToDate(Math.abs(dateToMilliseconds(date1) - dateToMilliseconds(date2)));
            if (dateToMilliseconds(result) == 0) {
                System.out.println("\nDates are equals!");
            } else {
                System.out.println("\nDifference between two dates: ");
                System.out.println(result.getYear() + " years, " +
                        result.getMonth() + " months, "
                        + result.getDay() + " days, "
                        + result.getHour() + " hours, "
                        + result.getMinute() + " minutes, "
                        + result.getSecond() + " seconds, "
                        + result.getMillisecond() + " milliseconds.");
            }
        } catch (RuntimeException e) {
            ERROR.error(e.getMessage());
            System.out.println("Problem = " + e.getMessage());
        }
        new ChooseTask().run();
    }
}
