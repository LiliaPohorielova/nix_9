package ua.com.alevel.dateSort;

import ua.com.alevel.ChooseTask;

import java.io.BufferedReader;

import static ua.com.alevel.date.CustomDateList.*;

public class DateSort {

    public static void run(BufferedReader input) {
        System.out.println("================== SORT DATES ==================");
        try {
            System.out.println("Your list of Dates: ");
            printList();
            System.out.println("\nSorting Dates in Ascending order:");
            sortDateList(true);
            System.out.println("\nSorting Dates in Descending order:");
            sortDateList(false);
        } catch (RuntimeException e) {
            System.out.println("Problem = " + e.getMessage());
        }
        new ChooseTask().run();
    }
}
