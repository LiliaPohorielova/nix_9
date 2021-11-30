package ua.com.alevel.datePrint;

import ua.com.alevel.ChooseTask;

import static ua.com.alevel.date.CustomDateList.printList;

public class PrintListOfDates {

    public static void run() {
        System.out.println("\n------------------ PRINT LIST OF DATES ------------------");
        try {
            printList();
        } catch (RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        new ChooseTask().run();
    }
}
