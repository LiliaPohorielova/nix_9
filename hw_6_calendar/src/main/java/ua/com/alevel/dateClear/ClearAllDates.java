package ua.com.alevel.dateClear;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.ChooseTask;

import static ua.com.alevel.date.CustomDateList.dateList;
import static ua.com.alevel.date.CustomDateList.printList;

public class ClearAllDates {

    private static final Logger WARN = LoggerFactory.getLogger("info");

    public static void run() {
        WARN.info("Clear All Dates");
        System.out.println("\n===================== CLEAR ALL DATES =======================");
        dateList.clear();
        printList();
        new ChooseTask().run();
    }
}
