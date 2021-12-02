package ua.com.alevel.dateSort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.ChooseTask;

import java.io.BufferedReader;

import static ua.com.alevel.date.CustomDateList.*;

public class DateSort {

    private static final Logger INFO = LoggerFactory.getLogger("info");
    private static final Logger ERROR = LoggerFactory.getLogger("error");

    public static void run(BufferedReader reader) {
        INFO.info("Date Sort");
        System.out.println("================== SORT DATES ==================");
        try {
            System.out.println("Your list of Dates: ");
            printList();
            System.out.println("\nSorting Dates in Ascending order:");
            sortDateList(true);
            System.out.println("\nSorting Dates in Descending order:");
            sortDateList(false);
        } catch (RuntimeException e) {
            ERROR.error(e.getMessage());
            System.out.println("Problem = " + e.getMessage());
        }
        new ChooseTask().run();
    }
}
