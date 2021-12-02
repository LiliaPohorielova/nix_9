package ua.com.alevel.datePrint;

import ua.com.alevel.ChooseTask;

import static ua.com.alevel.dateFormat.OutputDateFormat.selectDataOutputFormat;
import static ua.com.alevel.util.Navigation.printOutputDateFormatMenu;

public class PrintListOfDates {

    public static void run() {
        System.out.println("\n================== PRINT LIST OF DATES ==================");
        try {
            printOutputDateFormatMenu();
            selectDataOutputFormat();
        } catch (RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        new ChooseTask().run();
    }
}
